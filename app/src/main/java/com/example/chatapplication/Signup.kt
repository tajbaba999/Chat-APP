package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {


    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPasswordAuthentication: EditText
    private lateinit var btnsignup: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.edit_email)
        editPasswordAuthentication = findViewById(R.id.editTextTextPassword)
        btnsignup = findViewById(R.id.btnsignup)
        btnsignup = findViewById(R.id.btnsignup)
        editName = findViewById(R.id.edit_Name)

        btnsignup.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPasswordAuthentication.text.toString()
            val name = editName.text.toString()

            signUp(name, email, password)
        }
    }

    private fun signUp(name: String, email: String, password: String) {
        //login for loggind user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //jumping to home activity
                    addusertoDatabse(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@Signup, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@Signup,
                        "Some error occrured with Signup",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun addusertoDatabse(name: String, email: String, uid: String?) {
        mDbRef = FirebaseDatabase.getInstance().getReference()

        if (uid != null) {
            mDbRef.child("user").child(uid).setValue(User(name,email,uid))
        }
    }


}