package com.example.loginpage

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var repassword: EditText
    private lateinit var signup: Button
    private lateinit var signin: Button
    private lateinit var dB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        signup = findViewById(R.id.btnsignup)
        signin = findViewById(R.id.btnsignin)
        dB = DBHelper(this)

        signup.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            val repass = repassword.text.toString()

            if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                Toast.makeText(this@MainActivity, "Please enter all the fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (pass == repass) {
                    val checkuser = dB.checkusername(user)
                    if (checkuser == false) {
                        val insert = dB.insertData(user, pass)
                        if (insert == true) {
                            Toast.makeText(
                                this@MainActivity,
                                "Registered successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Registration failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "User already exists! please sign in",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Passwords not matching", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        signin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
