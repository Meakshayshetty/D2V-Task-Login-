package com.example.d2vtask

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private var backButton: ImageView?=null
    private lateinit var btnLogin: AppCompatButton
    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var progressBar: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        backButton = findViewById(R.id.loginBackBtn)
        etEmail = findViewById(R.id.etLoginEmail)
        etPassword = findViewById(R.id.etLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.LoginProgressBar)
        backButton?.setOnClickListener{
            onBackPressed()
        }
        btnLogin.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            validateData(etEmail.text.toString(),etPassword.text.toString())
        }
        // ...
// Initialize Firebase Auth
        auth = Firebase.auth

    }
    private fun validateData(email:String,password:String){
        Log.e("LOGINDATA", "$email , $password")
        if(email.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Fill the form", Toast.LENGTH_SHORT).show()
        }else if(password.length <= 10){
            Toast.makeText(this, "password must be greater than 10 charector", Toast.LENGTH_SHORT).show()
        }else{
            Log.e("LOGINDATA", "Login in called")
            login(email, password)
        }
    }
    private fun login(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressBar.visibility =View.GONE
                    Toast.makeText(
                        baseContext,
                        "Login Successful.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LOGIN", "signInWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    progressBar.visibility =View.GONE
                    // If sign in fails, display a message to the user.
                    Log.w("LOGIN", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    //updateUI(null)
                }
            }
    }
}