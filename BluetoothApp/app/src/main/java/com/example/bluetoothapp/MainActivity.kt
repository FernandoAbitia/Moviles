package com.example.bluetoothapp

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.*
import java.net.URLConnection
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothDevice: BluetoothDevice
    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private lateinit var File : File
    private lateinit var uri: Uri
    private lateinit var buttonLoad: Button
    private lateinit var buttonSend: Button
    private lateinit var textViewPath : TextView
    private lateinit var textViewSize : TextView
    private lateinit var textViewName : TextView
    private lateinit var imageViewPreview: ImageView

    private val REQUEST_ENABLE_BT = 10
    private val MY_READ_EXTERNAL_REQUEST = 1

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewPreview = findViewById(R.id.imageViewPreview)
        textViewPath = findViewById(R.id.textViewPath)
        textViewName = findViewById(R.id.textViewName)
        textViewSize = findViewById(R.id.textViewSize)
        buttonLoad = findViewById(R.id.buttonLoad)
        buttonLoad.setOnClickListener(btnClick)

        buttonSend = findViewById(R.id.buttonSend)
        buttonSend.setOnClickListener(btnSend)

        bluetoothAdapter =  BluetoothAdapter.getDefaultAdapter()

        if (bluetoothAdapter == null) {
            Log.d("TAG", "BLUETOOTH NOT SUPPORTED")
        } else {
            Log.d("TAG", "BLUETOOTH SUPPORTED")
        }
        if (checkSelfPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_READ_EXTERNAL_REQUEST
            )
        }
        if (!bluetoothAdapter.isEnabled){
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
        val pairedDevices: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices
        pairedDevices.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address
            Log.d("TAG", "PairedDevices: " + deviceName + " " + deviceHardwareAddress)
        }
        /* Parte de la conexión de bluetooth
        bluetoothDevice = bluetoothAdapter.getRemoteDevice("C0:D3:C0:4C:5F:2A") Teléfono que utilicé como server
        val bluetoothTread = ConnectThread()
        bluetoothTread.start()
         */

    }
    private inner class ConnectThread() : Thread() {

        private val bluetoothSocket: BluetoothSocket by lazy(LazyThreadSafetyMode.NONE) {
            bluetoothDevice.createRfcommSocketToServiceRecord(uuid)
        }

        public override fun run() {
            bluetoothAdapter.cancelDiscovery()
            bluetoothSocket.use { socket ->
                socket.connect()
            }
        }

        fun cancel() {
            try {
                bluetoothSocket.close()
            } catch (e: IOException) {
                Log.e("TAG", "Could not close the client socket", e)
            }
        }
    }
    private val btnClick = View.OnClickListener {
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select a file"), 100)
    }
    private val btnSend = View.OnClickListener {
        if (!this::File.isInitialized){
            Toast.makeText(this,"THERE IS NOT ANY FILE TO SEND, LOAD ONE FIRST",Toast.LENGTH_LONG).show()
        }
        else{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = URLConnection.guessContentTypeFromName(File.name)
            shareIntent.putExtra(Intent.EXTRA_STREAM,this.uri)
            startActivity(Intent.createChooser(shareIntent, "Share file using"))
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            val selectedFile = data!!.data //The uri with the location of the file
            uri = data!!.data!!
            File = fileFromContentUri(this, selectedFile!!)
            textViewName.text = "Name: "+ File.name
            textViewSize.text = "Size: "+ File.length()/ (1000.0 * 1000.0) + "MB"

            when (File.extension){
                "jpg", "png", "jpeg" -> {
                    val myBitmap: Bitmap = BitmapFactory.decodeFile(File.absolutePath)
                    imageViewPreview.setImageBitmap(myBitmap)
                }
                "apk" -> {
                    imageViewPreview.setImageResource(R.drawable.ic_apk_file)
                }
                "mp3" -> {
                    imageViewPreview.setImageResource(R.drawable.ic_mp3)
                }
                "doc", "docx" -> {
                    imageViewPreview.setImageResource(R.drawable.ic_palabra)
                }
                "pdf" -> {
                    imageViewPreview.setImageResource(R.drawable.ic_pdf)
                }
                "mp4" -> {
                    imageViewPreview.setImageResource(R.drawable.ic_video)
                }
                "xls" -> {
                    imageViewPreview.setImageResource(R.drawable.ic_xls)
                }
                "ppt", "pptx" -> {
                    imageViewPreview.setImageResource(R.drawable.ic_ppt)
                }
                else ->{
                    imageViewPreview.setImageResource(R.drawable.ic_archivo)
                }
            }
            Toast.makeText(this,"FILE LOADED SUCCESSFULLY",Toast.LENGTH_LONG).show()
        }
    }
    fun getUriFromFile(file: File, context: Context): Uri? =
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Uri.fromFile(file)
        } else {
            try {
                FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file) //Me marcaba error, buscaba en una ruta diferente (caché)
            } catch (e: Exception) {
                throw if (e.message?.contains("ProviderInfo.loadXmlMetaData") == true) {
                    Error("FileProvider is not set or doesn't have needed permissions")
                } else {
                    e
                }
            }
        }

    fun fileFromContentUri(context: Context, contentUri: Uri): File {
        val fileName = File(contentUri.path).name
        val tempFile = File(context.cacheDir, fileName)
        tempFile.createNewFile()

        try {
            val oStream = FileOutputStream(tempFile)
            val inputStream = context.contentResolver.openInputStream(contentUri)

            inputStream?.let {
                copy(inputStream, oStream)
            }

            oStream.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tempFile
    }
    @Throws(IOException::class)
    private fun copy(source: InputStream, target: OutputStream) {
        val buf = ByteArray(8192)
        var length: Int
        while (source.read(buf).also { length = it } > 0) {
            target.write(buf, 0, length)
        }
    }
}
