package com.BookHub_project_tushar.bookhub


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class reset :AppCompatActivity(){
    lateinit var done: Button
    override fun onCreate(savedInstanceState: Bundle??) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_pass)
        done=findViewById(R.id.btn_done)
        done.setOnClickListener{
            var intent= Intent(this,login::class.java)
            startActivity(intent)
        }
    }
}