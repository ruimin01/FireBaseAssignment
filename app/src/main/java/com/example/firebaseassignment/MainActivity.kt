package com.example.firebaseassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var usernameTextView: TextView
    private lateinit var passwordTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        usernameTextView = findViewById(R.id.usernameTextView)
        passwordTextView = findViewById(R.id.passwordTextView)

        val currentUser = auth.currentUser

        currentUser?.let { user ->
            val username = user.email
            val password = "******" // 这里使用星号代替密码，您也可以使用其他文本来表示密码

            usernameTextView.text = "Username: $username"
            passwordTextView.text = "Password: $password"
        }
    }
}