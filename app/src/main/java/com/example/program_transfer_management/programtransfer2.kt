package com.example.program_transfer_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class programtransfer2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programtransfer2)
        var res = findViewById<TextView>(R.id.res)

        var t1 = intent.getStringExtra("name")
        var t2 = intent.getStringExtra("pass")
        var t3 = intent.getStringExtra("email")
        var t4 = intent.getStringExtra("date")
        var t5 = intent.getStringExtra("phone")
        var option = intent.getStringExtra("course")

        res.text="Your application has been sent sucessfully with Name $t1 and Email Id $t3"
    }
}
