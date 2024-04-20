package com.example.d2vtask

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var loginBtn :Button? = null
    private var signUpBtn :Button? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loginBtn = findViewById(R.id.btnMainLogin)
        signUpBtn = findViewById(R.id.btnSignUp)

        loginBtn?.setOnClickListener{
            val intent = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intent)
        }

        signUpBtn?.setOnClickListener{
            val intent = Intent(this@MainActivity,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}