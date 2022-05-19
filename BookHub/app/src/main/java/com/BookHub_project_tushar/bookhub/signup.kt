package com.BookHub_project_tushar.bookhub


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class signup: AppCompatActivity(){

    lateinit var auth: FirebaseAuth
    lateinit var database: FirebaseDatabase

    lateinit var sign:Button
    lateinit var login:TextView

    lateinit var regemail:EditText
    lateinit var regpass:EditText
    lateinit var regrepass:EditText


    override fun onCreate(savedInstanceState: Bundle??) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        sign=findViewById(R.id.button)
        login=findViewById(R.id.textView)
        regemail=findViewById(R.id.emailEt)
        regpass=findViewById(R.id.passET)
        regrepass=findViewById(R.id.confirmPassEt)


        login.setOnClickListener{
            startActivity(Intent(this@signup, login::class.java))
        }


        sign.setOnClickListener{
           val email=regemail.getEditableText().toString()
            val pass=regpass.getEditableText().toString()
            val repass=regrepass.getEditableText().toString()


            if(pass.length <6){
                regpass.setError("Inavalid password !")
                Toast.makeText(this@signup,"Enter more then 6 charaters",Toast.LENGTH_SHORT).show()
            }
            else if (pass !=repass) {
                Toast.makeText(this@signup, "Password Doesn't match", Toast.LENGTH_SHORT).show()
            }
            else {
                auth!!.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val reference = database!!.reference
                                .child("users").child(auth!!.currentUser!!.uid)
                            val users = user(auth!!.currentUser!!.uid, email, pass)
                            reference.setValue(users).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this@signup,"Directing you to login page",Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@signup, login::class.java))
                                }
                                else {
                                    Toast.makeText(
                                        this@signup,
                                        "Some error! occured",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            Toast.makeText(
                                this@signup,
                                "Oops! \n Something went wrong!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }


        }

    }


}