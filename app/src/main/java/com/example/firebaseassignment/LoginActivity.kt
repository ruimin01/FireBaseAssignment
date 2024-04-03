package com.example.firebaseassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.firebaseassignment.databinding.ActivityLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityLoginBinding
    private lateinit var  firebaseDatabase: FirebaseDatabase
    private lateinit var  databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")
        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener{
            val loginUsername = binding.loginUsername.text.toString()
            val loginPassword = binding.loginPassword.text.toString()

            if (loginUsername.isNotEmpty() && loginPassword.isNotEmpty()) {
                loginUser(loginUsername, loginPassword)
            } else {
                Toast.makeText(this@LoginActivity,"All fields are mandatory", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signupRedirect.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            finish()
        }
    }

    private fun loginUser(username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 登录成功
                    Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    // 登录失败
                    Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

//    private fun loginUser (username : String, password: String){
//        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()){
//                    for(userSnapshot in dataSnapshot.children){
//                        val userData = userSnapshot.getValue(UserData::class.java)
//
//                        if(userData != null && userData.password == password){
//                            Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_LONG).show()
//                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                            finish()
//                            return
//                        }
//                    }
//                }
//                Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_LONG).show()
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Toast.makeText(this@LoginActivity,"Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
//
//            }
//        })
//    }
}