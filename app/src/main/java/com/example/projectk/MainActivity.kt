package com.example.projectk

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projectk.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

          binding.Save.setOnClickListener {
              val name = binding.PersonName.text.toString()
            val age = binding.PersonAge.text.toString()
            val id = binding.PersonID.text.toString()

        // Create a new user with a first and last name
        val person = hashMapOf(
            "name" to name,
            "id" to id,
            "age" to age
        )

        // Add a new document with a generated ID
        db.collection("Person")
            .add(person)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(applicationContext,"${documentReference.id}", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(applicationContext,"$e", Toast.LENGTH_LONG).show()
            }
          }
    }
}