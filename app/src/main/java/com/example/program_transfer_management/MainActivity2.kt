package com.example.program_transfer_management

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {
    private lateinit var transfer: CardView
    private lateinit var quiz: CardView
    private lateinit var help: CardView
    private lateinit var progress: CardView
    private lateinit var bottom: BottomNavigationView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initViews()
        setupNavigation()
    }

    private fun initViews() {
        transfer = findViewById(R.id.clothingCard)
        quiz = findViewById(R.id.instructions)
        help = findViewById(R.id.help)
        progress = findViewById(R.id.progress)
        bottom = findViewById(R.id.btm)
        transfer.setOnClickListener {
            startActivity(Intent(this, programtransfer::class.java))
            overridePendingTransition(R.anim.fadein, R.anim.slideslide)
        }
        quiz.setOnClickListener {
            startActivity(Intent(this, quizmain::class.java))
            overridePendingTransition(R.anim.slideslide, R.anim.fadein)
        }
        help.setOnClickListener {
            val intent = Intent(this, contactus::class.java)
            startActivity(intent)
        }
        progress.setOnClickListener {
            val intent = Intent(this, com.example.program_transfer_management.Class::class.java)
            startActivity(intent)
        }
    }

    private fun setupNavigation() {
        bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.firstcgpa -> {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.secondcgpa -> {
                    val intent = Intent(this, MainActivity3_f::class.java)
                    startActivity(intent)
                    true
                }
                R.id.fourthcgpa -> {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("LogOut Alert")
                        .setMessage("Are you sure, you want to Log Out")
                        .setCancelable(true)
                        .setPositiveButton("Yes") { _, _ ->
                            auth = FirebaseAuth.getInstance()
                            auth.signOut()
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        }
                        .setNegativeButton("No") { dialogInterface, _ ->
                            Toast.makeText(this, "You Have Clicked No", Toast.LENGTH_SHORT).show()
                        }
                        .show()
                    true
                }
                else -> true
            }
        }
    }
}
