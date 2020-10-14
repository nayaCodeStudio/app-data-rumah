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

    private var actualImage: File? = null
    private var compressedImage: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resize_photo)
        actualImageView.setBackgroundColor(getRandomColor())
        clearImage()
        setupClickListener()
    }

    private fun setupClickListener() {
        chooseImageButton.setOnClickListener { chooseImage() }
        compressImageButton.setOnClickListener {
            for (i in 0 until 3) {
                Toast.makeText(this, i, Toast.LENGTH_SHORT).show()
            }
        }
        fotoKondisi1.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST) }
        fotoKondisi2.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_1) }
        fotoKondisi3.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_2) }
//        customCompressImageButton.setOnClickListener { customCompressImage() }
    }

    private fun chooseImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun chooseImageMulti(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, requestCode)
    }

/*    private fun compressImage() {
        actualImage?.let { imageFile ->
            lifecycleScope.launch {
                // Default compression
                compressedImage = Compressor.compress(this@ResizePhotoActivity, imageFile)
                val filee = Uri.fromFile(compressedImage)
//                uploadImageDua(filee)
//                setCompressedImage()
                imgPath = filee
//                finish()
            }
        } ?: showError("Please choose an image!")
    }*/

//    private fun customCompressImage() {
//        actualImage?.let { imageFile ->
//            lifecycleScope.launch {
//                // Default compression with custom destination file
//                compressedImage = Compressor.compress(this@ResizePhotoActivity, imageFile) {
//                    default()
//                    getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.also {
//                        val file = File("${it.absolutePath}${File.separator}my_image.${imageFile.extension}")
//                        destination(file)
//                        val filee = Uri.fromFile(file)
//                        uploadImageDua(filee)
//                    }
//                }
//
//                // Full custom
///*                compressedImage = Compressor.compress(this@ResizePhotoActivity, imageFile) {
//                    resolution(1280, 720)
//                    quality(80)
//                    format(Bitmap.CompressFormat.WEBP)
//                    size(2_097_152) // 2 MB
//                }*/
//                setCompressedImage()
//            }
//        } ?: showError("Please choose an image!")
//    }

/*    private fun setCompressedImage() {
        compressedImage?.let {
            compressedImageView.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
            compressedSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
            Toast.makeText(this, "Compressed image save in " + it.path, Toast.LENGTH_LONG).show()
            Log.d("Compressor", "Compressed image save in " + it.path)
        }
    }*/

    private fun clearImage() {
        actualImageView.setBackgroundColor(getRandomColor())
        compressedImageView.setImageDrawable(null)
        compressedImageView.setBackgroundColor(getRandomColor())
        compressedSizeTextView.text = "Size : -"
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
                                clearImage()
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
                                clearImage()
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
                                clearImage()
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
        }
/*        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) {
                showError("Failed to open picture!")
                return
            }
            try {
                actualImage = FileUtil.from(this, data.data)?.also { imageFile ->

                    lifecycleScope.launch {
                        // Default compression
                        val compressedImage = Compressor.compress(this@ResizePhotoActivity, imageFile)
//                            val filee = Uri.fromFile(compressedImage)
//                            uploadImageDua(filee)
//                            imgPath = filee
                        actualImageView.setImageBitmap(loadBitmap(compressedImage))
                        actualSizeTextView.text = String.format("Size : %s", getReadableFileSize(compressedImage.length()))
                        clearImage()
                    }

//                    actualImageView.setImageBitmap(loadBitmap(imageFile))
//                    actualSizeTextView.text = String.format("Size : %s", getReadableFileSize(imageFile.length()))
//                    clearImage()
                }
            } catch (e: IOException) {
                showError("Failed to read picture data!")
                e.printStackTrace()
            }
        }*/
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun getRandomColor() = Random().run {
        Color.argb(100, nextInt(256), nextInt(256), nextInt(256))
    }

/*
    private fun getReadableFileSize(size: Long): String {
        if (size <= 0) {
            return "0"
        }
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (log10(size.toDouble()) / log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }
*/

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

