package com.example.pendataanrtlh

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pendataanrtlh.model.FormData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    private var inDesKel = "Cilendek Timur"
    private var inKec = "Bogor Barat"
    private var inkabKot = "Kota Bogor"
    private var inProv = "Jawa Barat"
    private var inCoordinate = "-"
    private var inImage = "-"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            realTimeDB(FormData(inDesKel, inKec, inkabKot, inProv, inCoordinate, inImage))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun realTimeDB(formData: FormData) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("wilayah")

        myRef.child("testData").setValue(formData).addOnCompleteListener {
            Toast.makeText(this, "berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fireStoreDB() {
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815


        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(
                    this,
                    "DocumentSnapshot added with ID: ${documentReference.id}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding document $e", Toast.LENGTH_SHORT).show()
            }
    }

}