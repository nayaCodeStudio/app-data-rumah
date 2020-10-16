package com.example.pendataanrtlh.addsurvey

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageThreeBinding
import com.example.pendataanrtlh.utils.Data.atap
import com.example.pendataanrtlh.utils.Data.balok
import com.example.pendataanrtlh.utils.Data.noKTPUser
import com.example.pendataanrtlh.utils.Data.pondasi
import com.example.pendataanrtlh.utils.FileUtil
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import id.zelory.compressor.Compressor
import id.zelory.compressor.loadBitmap
import kotlinx.coroutines.launch
import java.io.IOException


class PageThreeFragment : Fragment() {
    private lateinit var binding: FragmentPageThreeBinding

    companion object {
        private const val PICK_IMAGE_REQUEST_1 = 1
        private const val PICK_IMAGE_REQUEST_2 = 2
        private const val PICK_IMAGE_REQUEST_3 = 3
        private const val PICK_IMAGE_REQUEST_4 = 4
        private const val PICK_IMAGE_REQUEST_5 = 5
        private const val PICK_IMAGE_REQUEST_6 = 6
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageThreeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }

        binding.btnNext.setOnClickListener {
//            findNavController().navigate(R.id.action_ThirdFragment_to_FourFragment)

            val inPondasi = if (binding.chipAda.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }
            val inBalok = binding.listKondisiKolom.selectedItem.toString()
            val inAtap = binding.listKondisiKonstruksi.selectedItem.toString()

            if (!inPondasi.isEmpty() &&
                inBalok != "pilih" &&
                inAtap != "pilih"
            ) {
                //One Push Methode
                pondasi = inPondasi
                balok = inBalok
                atap = inAtap

                findNavController().navigate(R.id.action_ThirdFragment_to_FourFragment)
            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }

        setupClickListener()
    }

    private fun setupClickListener() {
        binding.fotoKondisiKolom1.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_1) }
        binding.fotoKondisiKolom2.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_2) }
        binding.fotoKondisiKolom3.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_3) }
        binding.fotoKondisiKonstruksiAtap1.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_4) }
        binding.fotoKondisiKonstruksiAtap2.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_5) }
        binding.fotoKondisiKonstruksiAtap3.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_6) }
    }

    private fun chooseImageMulti(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_IMAGE_REQUEST_1 -> {
                if (requestCode == PICK_IMAGE_REQUEST_1 && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(requireContext(), data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    requireContext(),
                                    imageFile
                                )
                                val file = Uri.fromFile(compressedImage)
                                uploadImageDua(file, "form3_${requestCode}_$noKTPUser")
                                binding.fotoKondisiKolom1.setImageBitmap(loadBitmap(compressedImage))
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
                        FileUtil.from(requireContext(), data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    requireContext(),
                                    imageFile
                                )
                                val file = Uri.fromFile(compressedImage)
                                uploadImageDua(file, "form3_${requestCode}_$noKTPUser")
                                binding.fotoKondisiKolom2.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_3 -> {
                if (requestCode == PICK_IMAGE_REQUEST_3 && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(requireContext(), data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    requireContext(),
                                    imageFile
                                )
                                val file = Uri.fromFile(compressedImage)
                                uploadImageDua(file, "form3_${requestCode}_$noKTPUser")
                                binding.fotoKondisiKolom3.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_4 -> {
                if (requestCode == PICK_IMAGE_REQUEST_4 && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(requireContext(), data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    requireContext(),
                                    imageFile
                                )
                                val file = Uri.fromFile(compressedImage)
                                uploadImageDua(file, "form3_${requestCode}_$noKTPUser")
                                binding.fotoKondisiKonstruksiAtap1.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_5 -> {
                if (requestCode == PICK_IMAGE_REQUEST_5 && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(requireContext(), data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    requireContext(),
                                    imageFile
                                )
                                val file = Uri.fromFile(compressedImage)
                                uploadImageDua(file, "form3_${requestCode}_$noKTPUser")
                                binding.fotoKondisiKonstruksiAtap2.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_6 -> {
                if (requestCode == PICK_IMAGE_REQUEST_6 && resultCode == RESULT_OK) {
                    if (data == null) {
                        showError("Failed to open picture!")
                        return
                    }
                    try {
                        FileUtil.from(requireContext(), data.data)?.also { imageFile ->
                            lifecycleScope.launch {
                                // Default compression
                                val compressedImage = Compressor.compress(
                                    requireContext(),
                                    imageFile
                                )
                                val file = Uri.fromFile(compressedImage)
                                uploadImageDua(file, "form3_${requestCode}_$noKTPUser")
                                binding.fotoKondisiKonstruksiAtap3.setImageBitmap(loadBitmap(compressedImage))
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

    private fun uploadImageDua(file: Uri, nameImage: String) {
        val pathImage = "gambar/$nameImage.jpg"
        val storageRef = FirebaseStorage
            .getInstance()
            .reference.child(pathImage)

        val databaseKonf = FirebaseDatabase
            .getInstance()
            .getReference("uploadFoto")
            .child(noKTPUser.toString())

        storageRef.putFile(file)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    databaseKonf.child(nameImage).setValue(it.toString())
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Add Image Successfully", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Info File : ${it.message}", Toast.LENGTH_SHORT).show()
            }.addOnProgressListener { taskSnapshot ->
                binding.progressBar.visibility = View.VISIBLE
                val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                binding.progressBar.progress = progress.toInt()
            }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }
}