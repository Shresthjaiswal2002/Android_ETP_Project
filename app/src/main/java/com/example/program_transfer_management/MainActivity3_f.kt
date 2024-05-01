package com.example.program_transfer_management

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity3_f : AppCompatActivity() {
    lateinit var bottom:BottomNavigationView
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity3_f)
        val simpleRatingBar = findViewById<RatingBar>(R.id.simpleRatingBar)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        val submitButton = findViewById<Button>(R.id.submitButton)
        bottom=findViewById(R.id.btm)

        submitButton.setOnClickListener {
            val totalStars = " Total Stars: " + simpleRatingBar.numStars
            val rating = " Rating: " + simpleRatingBar.rating
            Toast.makeText(
                this, """ $totalStars$rating""".trimIndent(),
                Toast.LENGTH_LONG
            ).show()
            Toast.makeText(this,"Thank You For Your Valuable Feedback",Toast.LENGTH_SHORT).show()
        }
        bottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.firstcgpa->{
                    val intent=Intent(this,MainActivity2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.secondcgpa->{
                    val intent=Intent(this,MainActivity3_f::class.java)
                    startActivity(intent)
                    true
                }
                R.id.fourthcgpa->{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("LogOut Alert")
                        .setMessage("Are you sure, you want to Log Out ")
                        .setCancelable(true)
                        .setPositiveButton("Yes"){dialogInterface,which->
                            auth = FirebaseAuth.getInstance()
                            auth.signOut()
                            val intent=Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                        }
                        .setNegativeButton("No"){dialogInterface,which->
                            Toast.makeText(this,"You Have Clicked No",Toast.LENGTH_SHORT).show()
                        }
                        .show()
                    true
                }
                else->true
            }
        }
        toolbar.setNavigationOnClickListener {
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
    }
}