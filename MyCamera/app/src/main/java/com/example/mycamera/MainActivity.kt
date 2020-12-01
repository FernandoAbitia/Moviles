package com.example.mycamera

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.hardware.camera2.params.OutputConfiguration
import android.hardware.camera2.params.SessionConfiguration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private var camera = 0
    private lateinit var buttonCamera: ImageView
    private val REQUEST_CAMERA = 10
    private val executorCamera = Executors.newSingleThreadExecutor()

    private lateinit var captureSession : CameraCaptureSession
    private lateinit var cameraDevice: CameraDevice
    private lateinit var builderCapture: CaptureRequest.Builder
    private lateinit var surfaceView : SurfaceView


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA){
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                configCamera(camera)
            }else{
                Toast.makeText(this, "PERMISO NO ACEPTADO", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        surfaceView = findViewById(R.id.surface_preview)
        buttonCamera = findViewById(R.id.imgToggleCamera)
        buttonCamera.setOnClickListener(buttonCameraClick)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            configCamera(camera)
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA
            )
        }

    }
    @SuppressLint("MissingPermission")
    private val buttonCameraClick = View.OnClickListener { Button ->

        if (this::captureSession.isInitialized){
            captureSession.close()
        }

        if (this::cameraDevice.isInitialized)
            cameraDevice.close()

        when (camera) {
            1 -> camera=0
            0 -> camera=1
        }
        val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        cameraManager.openCamera(camera.toString(), cameraStateCallback, null)
    }

    @SuppressLint("MissingPermission")
    fun configCamera(cameraId: Int){
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

                val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
                cameraManager.openCamera(cameraId.toString(), cameraStateCallback, null)

            }

            override fun surfaceChanged(holder: SurfaceHolder, p1: Int, p2: Int, p3: Int) { }

            override fun surfaceDestroyed(p0: SurfaceHolder) { }
        })
    }
    private val cameraStateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {

            cameraDevice=camera

            builderCapture = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            builderCapture.addTarget(surfaceView.holder.surface)

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                cameraDevice.createCaptureSession(
                    listOf(surfaceView.holder.surface),
                    object : CameraCaptureSession.StateCallback() {
                        override fun onConfigured(session: CameraCaptureSession) {
                            captureSession = session
                            captureSession.setRepeatingRequest(builderCapture.build(), null, null)
                        }

                        override fun onConfigureFailed(session: CameraCaptureSession) {}

                    }, null
                )
            } else {
                val sessionConfiguration = SessionConfiguration(
                    SessionConfiguration.SESSION_REGULAR,
                    listOf(OutputConfiguration(surfaceView.holder.surface)), executorCamera,
                    object : CameraCaptureSession.StateCallback() {
                        override fun onConfigured(session: CameraCaptureSession) {
                            captureSession = session
                            captureSession.setRepeatingRequest(builderCapture.build(), null, null)
                        }

                        override fun onConfigureFailed(session: CameraCaptureSession) {}
                    }
                )
                cameraDevice.createCaptureSession(sessionConfiguration)
            }
        }
        override fun onDisconnected(p0: CameraDevice) { }
        override fun onError(p0: CameraDevice, p1: Int) { }
    }
}