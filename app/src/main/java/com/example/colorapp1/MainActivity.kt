//
//package com.example.colorapp1
//
//import android.graphics.Color
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.widget.Toast
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.colorapp1.databinding.ActivityMainBinding
//import com.example.colorapp1.databinding.DialogAddColorBinding
//import com.google.firebase.database.FirebaseDatabase
//import kotlin.random.Random
//
//
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//    private val colorList = mutableListOf<ColorCard>()
//    private lateinit var colorAdapter: ColorAdapter
//    private lateinit var mDatabase: FirebaseDatabase
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Initialize Firebase
//        mDatabase = FirebaseDatabase.getInstance()
//
//        // Initialize RecyclerView
//        colorAdapter = ColorAdapter(colorList)
//        binding.rvColors.apply {
//            layoutManager = GridLayoutManager(this@MainActivity, 2)
//            adapter = colorAdapter
//        }
//
//        // Add Color Button Click Listener
//        binding.addColorButton.setOnClickListener {
//            showAddColorDialog()
//        }
//
//        // Sync Button Click Listener
//        binding.syncButton.setOnClickListener {
//            syncDataToFirebase()
//        }
//    }
//
//    private fun syncDataToFirebase() {
//        // Getting reference to the Firebase database node where the color data will be stored
//        val colorsRef = mDatabase.getReference("colors")
//
//        // Push the list of colors to Firebase
//        colorsRef.setValue(colorList)
//            .addOnSuccessListener {
//                Toast.makeText(this, "Data synchronized successfully!", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener { exception ->
//                Toast.makeText(this, "Error syncing data: ${exception.message}", Toast.LENGTH_SHORT).show()
//            }
//    }
//
//    private fun showAddColorDialog() {
//        val dialogBinding = DialogAddColorBinding.inflate(layoutInflater)
//        val dialog = AlertDialog.Builder(this)
//            .setTitle("Add New Color")
//            .setView(dialogBinding.root)
//            .setPositiveButton("Add") { _, _ ->
//                val colorCode = generateRandomColor()
//                addNewColor(colorCode)
//            }
//            .setNegativeButton("Cancel", null)
//            .create()
//
//        dialog.show()
//    }
//
//    private fun addNewColor(colorCode: String) {
//        val currentDate = android.text.format.DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()).toString()
//        val newColor = ColorCard(colorCode, "Created at $currentDate")
//        colorList.add(newColor)
//        colorAdapter.notifyItemInserted(colorList.size - 1)
//    }
//
//    private fun generateRandomColor(): String {
//        val random = Random
//        val color = String.format("#%06X", (0xFFFFFF and random.nextInt()))
//        return color
//    }
//}
package com.example.colorapp1
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

import com.example.colorapp1.ColorAdapter
import com.example.colorapp1.ColorCard
import com.example.colorapp1.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val colorList = mutableListOf<ColorCard>()
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var mDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance()

        // Initialize RecyclerView
        colorAdapter = ColorAdapter(colorList)
        binding.rvColors.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = colorAdapter
        }

        // Add Color Button Click Listener
        binding.addColorButton.setOnClickListener {
            addNewColor(generateRandomColor())
        }

        // Sync Button Click Listener
        binding.syncButton.setOnClickListener {
            syncDataToFirebase()
        }
    }

    private fun syncDataToFirebase() {
        // Getting reference to the Firebase database node where the color data will be stored
        val colorsRef = mDatabase.getReference("colors")

        // Push the list of colors to Firebase
        colorsRef.setValue(colorList)
            .addOnSuccessListener {
                Toast.makeText(this, "Data synchronized successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error syncing data: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addNewColor(colorCode: String) {
        val currentDate = android.text.format.DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()).toString()
        val newColor = ColorCard(colorCode, "Created at $currentDate")
        colorList.add(newColor)
        colorAdapter.notifyItemInserted(colorList.size - 1)
    }

    private fun generateRandomColor(): String {
        val random = Random
        return String.format("#%06X", (0xFFFFFF and random.nextInt()))
    }
}
