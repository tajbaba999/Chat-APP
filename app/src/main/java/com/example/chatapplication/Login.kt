package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.net.PasswordAuthentication

class Login : AppCompatActivity() {

    private lateinit var editEmail : EditText
    private lateinit var editPasswordAuthentication: EditText
    private lateinit var btnsignup : Button
    private lateinit var btnlogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.edit_email)
        editPasswordAuthentication = findViewById(R.id.editTextTextPassword)
        btnsignup = findViewById(R.id.btnsignup)
        btnlogin = findViewById(R.id.btnlogin)

        btnsignup.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }
    }
}