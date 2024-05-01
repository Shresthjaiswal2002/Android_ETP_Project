
package com.example.program_transfer_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class echapter2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_echapter2)
        var pdfview=findViewById<PDFView>(R.id.pdfview)
        pdfview.fromAsset("e-chapter2.pdf").load()
    }
}