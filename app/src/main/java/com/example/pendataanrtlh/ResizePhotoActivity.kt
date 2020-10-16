package com.example.pendataanrtlh

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pendataanrtlh.utils.FileUtil
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import id.zelory.compressor.Compressor
import id.zelory.compressor.loadBitmap
import kotlinx.android.synthetic.main.activity_resize_photo.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.util.*

class ResizePhotoActivity : AppCompatActivity() {

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
        private const val PICK_IMAGE_REQUEST_1 = 2
        private const val PICK_IMAGE_REQUEST_2 = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resize_photo)
        setupClickListener()
    }

    private fun setupClickListener() {
        fotoKondisi1.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST) }
        fotoKondisi2.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_1) }
        fotoKondisi3.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_2) }
    }

    private fun chooseImageMulti(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            PICK_IMAGE_REQUEST -> {
                if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(this, data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    this@ResizePhotoActivity,
                                    imageFile
                                )
                                val filee = Uri.fromFile(compressedImage)
//                                img1 = filee
                                uploadImageDua(filee, "foto1")
                                fotoKondisi1.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_1 -> {
                if (requestCode == PICK_IMAGE_REQUEST_1 && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(this, data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    this@ResizePhotoActivity,
                                    imageFile
                                )
                                val filee = Uri.fromFile(compressedImage)
//                                img2 = filee
                                uploadImageDua(filee, "foto2")
                                fotoKondisi2.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_2 -> {
                if (requestCode == PICK_IMAGE_REQUEST_2 && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(this, data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    this@ResizePhotoActivity,
                                    imageFile
                                )
                                val filee = Uri.fromFile(compressedImage)
//                                img3 = filee
                                uploadImageDua(filee, "foto3")
                                fotoKondisi3.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun uploadImageDua(file: Uri, nameImage: String) {
        val pathImage = "gambar/$nameImage.jpg"

        val storageRef = FirebaseStorage
            .getInstance()
            .reference.child(pathImage)

        val databaseKonf = FirebaseDatabase
            .getInstance()
            .getReference("uploadFoto")
            .child("jojojo")

        storageRef.putFile(file)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    databaseKonf.child(nameImage).setValue(it.toString())
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Add Image Successfully", Toast.LENGTH_SHORT).show()
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

