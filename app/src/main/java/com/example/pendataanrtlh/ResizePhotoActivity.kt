package com.example.pendataanrtlh

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pendataanrtlh.utils.Data.imgPath
import com.example.pendataanrtlh.utils.FileUtil
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import id.zelory.compressor.Compressor
import id.zelory.compressor.loadBitmap
import kotlinx.android.synthetic.main.activity_resize_photo.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.DecimalFormat
import java.util.*
import kotlin.math.log10
import kotlin.math.pow

class ResizePhotoActivity : AppCompatActivity() {

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
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
        compressImageButton.setOnClickListener { compressImage() }
//        customCompressImageButton.setOnClickListener { customCompressImage() }
    }

    private fun chooseImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun compressImage() {
        actualImage?.let { imageFile ->
            lifecycleScope.launch {
                // Default compression
                compressedImage = Compressor.compress(this@ResizePhotoActivity, imageFile)
                val filee = Uri.fromFile(compressedImage)
//                uploadImageDua(filee)
//                setCompressedImage()
                imgPath = filee
                finish()
            }
        } ?: showError("Please choose an image!")
    }

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

    private fun setCompressedImage() {
        compressedImage?.let {
            compressedImageView.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
            compressedSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
            Toast.makeText(this, "Compressed image save in " + it.path, Toast.LENGTH_LONG).show()
            Log.d("Compressor", "Compressed image save in " + it.path)
        }
    }

    private fun clearImage() {
        actualImageView.setBackgroundColor(getRandomColor())
        compressedImageView.setImageDrawable(null)
        compressedImageView.setBackgroundColor(getRandomColor())
        compressedSizeTextView.text = "Size : -"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) {
                showError("Failed to open picture!")
                return
            }
            try {
                actualImage = FileUtil.from(this, data.data)?.also {
                    actualImageView.setImageBitmap(loadBitmap(it))
                    actualSizeTextView.text = String.format("Size : %s", getReadableFileSize(it.length()))
                    clearImage()
                }
            } catch (e: IOException) {
                showError("Failed to read picture data!")
                e.printStackTrace()
            }
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun getRandomColor() = Random().run {
        Color.argb(100, nextInt(256), nextInt(256), nextInt(256))
    }

    private fun getReadableFileSize(size: Long): String {
        if (size <= 0) {
            return "0"
        }
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (log10(size.toDouble()) / log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }

    private fun uploadImageDua(filee: Uri) {
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

        storageRef.putFile(filee)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    databaseKonf.child("buktiPembayaran").setValue(it.toString())
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

