package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.net.PasswordAuthentication

class Login : AppCompatActivity() {

    private lateinit var editEmail : EditText
    private lateinit var editPasswordAuthentication: EditText
    private lateinit var btnsignup : Button
    private lateinit var btnlogin : Button

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.edit_email)
        editPasswordAuthentication = findViewById(R.id.editTextTextPassword)
        btnsignup = findViewById(R.id.btnsignup)
        btnlogin = findViewById(R.id.btnlogin)

        btnsignup.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPasswordAuthentication.text.toString()

            login(email,password)
        }
    }

    private fun login(Email : String, password : String){
        mAuth.signInWithEmailAndPassword(Email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login,"Some error occrured with Sigin", Toast.LENGTH_LONG).show()
                }
            }
    }
}