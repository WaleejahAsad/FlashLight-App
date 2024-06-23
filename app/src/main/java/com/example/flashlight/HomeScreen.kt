package com.example.flashlight

import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.getSystemService

class HomeScreen : AppCompatActivity() {

    private lateinit var flashlight : ImageView
    private var isActive  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        flashlight = findViewById(R.id.flashlight)
        lightState(isActive)
        flashlight.setOnClickListener{
            if (isActive == false){
                flashlight.setImageResource(R.drawable.flashlight_on)
                isActive=true
                lightState(isActive)
            }
            else{
                flashlight.setImageResource(R.drawable.flashlight_off)
                isActive=false
                lightState(isActive)
            }
        }
    }

    private fun lightState(state: Boolean) {

        var cameraManager : CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        var cameraId :String?=null

        try{
            cameraId= cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId,state)
        }
        catch (e:Exception){
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }
    }
}