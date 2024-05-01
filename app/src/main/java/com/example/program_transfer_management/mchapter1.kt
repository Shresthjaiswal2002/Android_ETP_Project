package com.example.program_transfer_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class mchapter1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mchapter1)
        var pdfview=findViewById<PDFView>(R.id.pdfview)
        pdfview.fromAsset("mchapter1.pdf").load()
    }
}