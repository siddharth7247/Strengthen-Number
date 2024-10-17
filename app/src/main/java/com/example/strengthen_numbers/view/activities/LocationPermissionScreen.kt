package com.example.strengthen_numbers.view.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.strengthen_numbers.R

class LocationPermissionScreen : AppCompatActivity() {
    lateinit var txtBulletPointList : TextView
    lateinit var btnAllow : Button
    lateinit var btnDontAllow : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location_permission_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtBulletPointList = findViewById(R.id.txtDese)
        btnAllow = findViewById(R.id.btnAllow)
        btnDontAllow = findViewById(R.id.btnDontAllow)

        txtBulletPointList.setText(R.string.bullted_list)
    }
}