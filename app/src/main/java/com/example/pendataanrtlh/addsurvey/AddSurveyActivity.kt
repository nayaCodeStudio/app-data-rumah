package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.model.FormData
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class AddSurveyActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    private var inDesKel = "Cilendek Timur"
    private var inKec = "Bogor Barat"
    private var inkabKot = "Kota Bogor"
    private var inProv = "Jawa Barat"
    private var inCoordinate = "-"
    private var inImage = "-"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey)
        setSupportActionBar(findViewById(R.id.toolbar))

//        realTimeDB(FormData(inDesKel, inKec, inkabKot, inProv, inCoordinate, inImage))

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