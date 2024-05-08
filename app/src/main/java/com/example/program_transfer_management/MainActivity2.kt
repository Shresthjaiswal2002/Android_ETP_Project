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
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class MainActivity2 : AppCompatActivity(), SensorEventListener {
    private lateinit var transfer: CardView
    private lateinit var quiz: CardView
    private lateinit var help: CardView
    private lateinit var progress: CardView
    private lateinit var bottom: BottomNavigationView
    private lateinit var auth: FirebaseAuth
    private lateinit var sensorManager: SensorManager
    private var lastUpdate: Long = 0
    private val SHAKE_THRESHOLD = 600

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initViews()
        setupNavigation()

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastUpdate > 100) {
                val diffTime = currentTime - lastUpdate
                lastUpdate = currentTime
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000
                if (speed > SHAKE_THRESHOLD) {
                    // Shake gesture detected, navigate to a different activity
                    val intent = Intent(this, programtransfer::class.java)
                    startActivity(intent)
                }
                last_x = x
                last_y = y
                last_z = z
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Do nothing
    }

    private fun initViews() {
        transfer = findViewById(R.id.clothingCard)
        quiz = findViewById(R.id.instructions)
        help = findViewById(R.id.help)
        progress = findViewById(R.id.progress)
        bottom = findViewById(R.id.btm)
        transfer.setOnClickListener {
            startActivity(Intent(this, TodoListMainActivity::class.java))
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
        bottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.firstcgpa -> {
                    // Handle navigation
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.secondcgpa -> {
                    // Handle navigation
                    val intent = Intent(this, MainActivity3_f::class.java)
                    startActivity(intent)
                    true
                }
                R.id.fourthcgpa -> {
                    // Handle navigation
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
                else -> false
            }
        }
    }

    companion object {
        private var last_x: Float = 0f
        private var last_y: Float = 0f
        private var last_z: Float = 0f
    }
}
