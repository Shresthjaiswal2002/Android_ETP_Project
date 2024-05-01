package com.example.program_transfer_management

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class Science : AppCompatActivity() {
    lateinit var englishchapter1: CardView
    lateinit var instructions1: CardView
    lateinit var auth: FirebaseAuth
    lateinit var help: CardView
    lateinit var progress: CardView
    lateinit var bottom: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science)
        englishchapter1=findViewById(R.id.clothingCard)
        instructions1=findViewById(R.id.instructions)
        help=findViewById(R.id.help)
        bottom=findViewById(R.id.btm)
        progress=findViewById(R.id.progress)
        englishchapter1.setOnClickListener{
            val intent= Intent(this,schapter1::class.java)
            startActivity(intent)
        }
        instructions1.setOnClickListener {
            val intent= Intent(this,schapter2::class.java)
            startActivity(intent)
        }
        help.setOnClickListener {
            val intent= Intent(this,schapter3::class.java)
            startActivity(intent)
        }
        progress.setOnClickListener {
            val intent= Intent(this, schapter4::class.java)
            startActivity(intent)
        }
        bottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.firstcgpa->{
                    val intent= Intent(this,MainActivity2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.secondcgpa->{
                    val intent= Intent(this,MainActivity3_f::class.java)
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
                            val intent= Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                        }
                        .setNegativeButton("No"){dialogInterface,which->
                            Toast.makeText(this,"You Have Clicked No", Toast.LENGTH_SHORT).show()
                        }
                        .show()
                    true
                }
                else->true
            }
        }
    }
}
