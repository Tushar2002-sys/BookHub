package com.BookHub_project_tushar.bookhub

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class forgot:AppCompatActivity() {
    lateinit var reset:Button

     lateinit var mauth: FirebaseAuth
     lateinit var email: EditText
    override fun onCreate(o1: Bundle?) {
        super.onCreate(o1)
        setContentView(R.layout.forgot_pass)
        reset = findViewById<View>(R.id.btn_reset) as Button
        email = findViewById<View>(R.id.edtemail) as EditText
        mauth = FirebaseAuth.getInstance()
        reset!!.setOnClickListener {
            val userEmail = email!!.text.toString()
            if (TextUtils.isEmpty(userEmail)) {
                Toast.makeText(this@forgot, "Enter valid email !", Toast.LENGTH_LONG).show()
            } else {
                mauth!!.sendPasswordResetEmail(userEmail).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@forgot,
                            "Please check your Email Account",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this@forgot, login::class.java))
                    } else {
                        Toast.makeText(
                            this@forgot, "oops An Error Occurred !" + task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}