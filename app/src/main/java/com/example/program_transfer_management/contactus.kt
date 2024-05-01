package com.example.program_transfer_management

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class contactus : AppCompatActivity() {
    private lateinit var bottom: BottomNavigationView
    private lateinit var submit: Button
    private lateinit var database: DatabaseReference
    private lateinit var mapLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactus)

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().getReference("Users")

        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        val et3 = findViewById<EditText>(R.id.et3)
        val et4 = findViewById<EditText>(R.id.et4)
        submit = findViewById(R.id.submit)
        bottom = findViewById(R.id.btm)
        mapLogo = findViewById(R.id.map)

        submit.setOnClickListener {
            val t1 = et1.text.toString()
            val t2 = et2.text.toString()
            val t3 = et3.text.toString()
            val t4 = et4.text.toString()

            if (t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t4.isEmpty()) {
                Toast.makeText(this, "Please Fill out All the Details", Toast.LENGTH_SHORT).show()
            } else {
                // Save data to Firebase database
                saveDataToFirebase(t1, t2, t3, t4)
            }
        }

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
                        .setMessage("Are you sure, you want to Log Out?")
                        .setCancelable(true)
                        .setPositiveButton("Yes") { _, _ ->
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

        mapLogo.setOnClickListener {
            openMap()
        }
    }

    private fun saveDataToFirebase(name: String, email: String, phone: String, message: String) {
        val userMap = HashMap<String, Any>()
        userMap["Name"] = name
        userMap["Email"] = email
        userMap["Phone"] = phone
        userMap["Message"] = message

        val userId = database.push().key
        userId?.let {
            database.child(it).setValue(userMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "Data Successfully Submitted", Toast.LENGTH_SHORT).show()
                    showNotification("Submission Successful", "Thank you for contacting us, we will get back to you soon.")
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to Submit Data", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun showNotification(title: String, message: String) {
        val notificationId = 1
        val channelId = "app_notifications"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "App Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "App notification channel"
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.baseline_notifications_active_24)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(this).notify(notificationId, notificationBuilder.build())
    }

    private fun openMap() {
        val geoUri = "geo:28.6139,77.2090"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}
