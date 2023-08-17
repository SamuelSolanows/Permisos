package com.example.permisos

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permisos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFoto.setOnClickListener {
            VerificarPermiso()
        }

    }

    private fun VerificarPermiso() {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            PreguntarSevicioCamara()
        } else {
            AbrirCamara()
        }
    }

    private fun AbrirCamara() {
        Toast.makeText(this, "Abriendo camara", Toast.LENGTH_SHORT).show()
    }

    private fun PreguntarSevicioCamara() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.CAMERA
            )
        ) {

            Toast.makeText(this, "Permiso Recazado", Toast.LENGTH_SHORT).show()

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 777)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 777) {
            if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AbrirCamara()
            } else {
                Toast.makeText(this, "Permiso Recazado por primera vez", Toast.LENGTH_SHORT).show()
            }
        }
    }
}