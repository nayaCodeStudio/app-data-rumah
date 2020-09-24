package com.example.pendataanrtlh

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_upload_photo.*
import java.util.*

class UploadPhotoActivity : AppCompatActivity() {
//    private lateinit var storageRef: StorageReference
//    private lateinit var storage: FirebaseStorage
    private var imgPath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_photo)

//        val storage = Firebase.storage
//        var storageRef = storage.reference
//        storageRef = FirebaseStorage.getInstance().reference


        btn_choose_file.setOnClickListener {
            val iImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(iImg, 0)
        }

        btn_text_configuration.setOnClickListener {
            uploadImageDua()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            imageContainer.visibility = View.VISIBLE
            imgPath = data?.data
            imageContainer.setImageURI(imgPath)
        }
    }

    private fun uploadImageDua() {
//        val emailExtra = intent.getStringExtra("joe@gmail.com")
//        val emailUser = emailExtra?.replace("""[$,.@ ]""".toRegex(), "")
        val setNamaFile = "kondisi_balok"
        val namaFile = "$setNamaFile.jpg"
        val pathImage = "gambar/$namaFile"

        val storageRef = FirebaseStorage
            .getInstance()
            .reference.child(pathImage)

        val databaseKonf = FirebaseDatabase
            .getInstance()
            .getReference("DATA_PEMESAN_TEST")
            .child("jojojo")

        storageRef.putFile(imgPath!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    databaseKonf.child("buktiPembayaran").setValue(it.toString())
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Add Image Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainMenuActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finishAffinity()
                }
            }
            .addOnFailureListener {
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Info File : ${it.message}", Toast.LENGTH_SHORT).show()
            }.addOnProgressListener { taskSnapshot ->
                progressBar.visibility = View.VISIBLE
                val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                progressBar.progress = progress.toInt()
            }
    }
}