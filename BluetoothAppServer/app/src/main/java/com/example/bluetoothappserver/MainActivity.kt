package com.example.bluetoothappserver

import androidx.appcompat.app.AppCompatActivity

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothDevice: BluetoothDevice
    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private val REQUEST_ENABLE_BT = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothAdapter =  BluetoothAdapter.getDefaultAdapter()

        if (bluetoothAdapter == null) {
            Log.d("TAG", "BLUETOOTH NO SOPORTADO")
        } else {
            Log.d("TAG", "BLUETOOTH SOPORTADO")
        }

        if (!bluetoothAdapter.isEnabled){
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }
        val bluetoothTread = AcceptThread()
        bluetoothTread.start()

    }
    private inner class AcceptThread : Thread() {

        private val bluetoothServerSocket: BluetoothServerSocket by lazy(LazyThreadSafetyMode.NONE) {
            bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("BluetoothApp", uuid)
        }

        override fun run() {
            // Keep listening until exception occurs or a socket is returned.
            var shouldLoop = true
            while (shouldLoop) {
                val socket: BluetoothSocket? = try {
                    bluetoothServerSocket.accept()
                } catch (e: IOException) {
                    Log.e("TAG", "Socket's accept() method failed", e)
                    shouldLoop = false
                    null
                }
                socket.also {
                    bluetoothServerSocket.close()
                    shouldLoop = false
                }
            }
        }

        // Closes the connect socket and causes the thread to finish.
        fun cancel() {
            try {
                bluetoothServerSocket.close()
            } catch (e: IOException) {
                Log.e("TAG", "Could not close the connect socket", e)
            }
        }
    }

}