package com.example.program_transfer_management

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Hindi : AppCompatActivity() {
    lateinit var class1: CardView
    lateinit var instructions1: CardView
    lateinit var help: CardView
    lateinit var progress: CardView
    lateinit var bottom: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hindi)
        class1=findViewById(R.id.clothingCard)
        instructions1=findViewById(R.id.instructions)
        help=findViewById(R.id.help)
        bottom=findViewById(R.id.btm)
        progress=findViewById(R.id.progress)
        class1.setOnClickListener{
            val intent= Intent(this,chapter1::class.java)
            startActivity(intent)
        }
        instructions1.setOnClickListener {
            val intent= Intent(this,hchapter3::class.java)
            startActivity(intent)
        }
        help.setOnClickListener {
            val intent= Intent(this,hchapter2::class.java)
            startActivity(intent)
        }
        progress.setOnClickListener {
            val intent= Intent(this, hchapter4
            ::class.java)
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
                else->true
            }
        }
    }
}