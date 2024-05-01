package com.example.program_transfer_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.program_transfer_management.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth


class Signup : AppCompatActivity() {
    lateinit var button:Button
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
         lateinit var binding: ActivitySignupBinding
         lateinit var firebaseAuth: FirebaseAuth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        button=findViewById(R.id.signupButton)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupText1.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.signupButton.setOnClickListener {
            val email = binding.username.text.toString()
            val pass = binding.signuppassword.text.toString()
            val confirmPass = binding.signupconfirmpassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            auth = FirebaseAuth.getInstance()
                            auth.signOut()
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}