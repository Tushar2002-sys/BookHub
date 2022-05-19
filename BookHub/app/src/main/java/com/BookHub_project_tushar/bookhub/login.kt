package com.BookHub_project_tushar.bookhub

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.BookHub_project_tushar.bookhub.activity.MainActivity


class login :AppCompatActivity(){

    lateinit var auth: FirebaseAuth
    lateinit var regemail:EditText
    lateinit var regpass:EditText


    lateinit var login_btn:Button
    lateinit var signup: TextView
    lateinit var forgot:TextView
    override fun onCreate(savedInstanceState: Bundle??) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        auth = FirebaseAuth.getInstance()
        regemail=findViewById(R.id.emailEt)
        regpass=findViewById(R.id.passET)


        login_btn = findViewById(R.id.button)
        signup = findViewById(R.id.textView)
        forgot=findViewById(R.id.forgottextview)
        login_btn.setOnClickListener {
            val email = regemail.getEditableText().toString()
            val pass = regpass.getEditableText().toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this@login, "Enter valid Data", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth!!.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this@login, MainActivity::class.java))
                        Toast.makeText(
                            this@login,
                            "Successfully logged in",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@login,
                            "Enter correct email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        signup.setOnClickListener{
            var intent=Intent(this@login,com.BookHub_project_tushar.bookhub.signup::class.java)
            startActivity(intent)
        }

        forgot.setOnClickListener{
            var intent=Intent(this@login,com.BookHub_project_tushar.bookhub.forgot::class.java)
            startActivity(intent)


        }
    }}

