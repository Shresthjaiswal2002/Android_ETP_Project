package com.example.program_transfer_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class echapter4viewpdf : AppCompatActivity() {
    private lateinit var bottom: BottomNavigationView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hchapter2viewpdf)
        val viewVideosCardView: CardView = findViewById(R.id.progress)
        val viewpdf: CardView = findViewById(R.id.help)
        // Set OnClickListener for the CardView
        viewpdf.setOnClickListener {
            val intent = Intent(this, echapter4::class.java)
            startActivity(intent)
        }
        viewVideosCardView.setOnClickListener {
            // Open YouTube using WebView
            val webView = WebView(this)
            setContentView(webView)
            webView.loadUrl("https://www.youtube.com")
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