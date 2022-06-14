package com.example.permissionkotlin

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //tutorial: https://www.youtube.com/watch?v=rdfjT0bQBgs&list=PL8ie04dqq7_OcBYDpvHrcSFVoggLi3cm_&index=16&t=1s&ab_channel=Programaci%C3%B3nAndroidbyAristiDevs

        val btnCamera = findViewById<Button>(R.id.btnCamera)

        btnCamera.setOnClickListener{ checkPermissions() }
    }

    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //permiso no aceptado
            requestCameraPermission()
        }else{
            //abrir camara
            openCamera()
        }
    }

    private fun requestCameraPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            //el usuario ya ha rechazado los permisos
            Toast.makeText(this, "Permisos rechazados", Toast.LENGTH_SHORT).show()
        }else{
            //pedir permisos
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 777)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 777){
            //nuestros permisos
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                //el permiso fue aceptado
                openCamera()
            }else{
                //el permiso no fue aceptado
                Toast.makeText(this, "Permisos rechazados por primera vez", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openCamera() {
        Toast.makeText(this, "Abriendo Camera", Toast.LENGTH_SHORT).show()
    }
}