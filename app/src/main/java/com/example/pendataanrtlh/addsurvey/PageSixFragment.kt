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
import com.example.pendataanrtlh.databinding.FragmentPageSixBinding
import com.example.pendataanrtlh.model.FormDataSurvey
import com.example.pendataanrtlh.model.FormSurveyor
import com.example.pendataanrtlh.utils.Data
import com.example.pendataanrtlh.utils.Data.konAtap
import com.example.pendataanrtlh.utils.Data.konDinding
import com.example.pendataanrtlh.utils.Data.konLantai
import com.example.pendataanrtlh.utils.Data.matAtap
import com.example.pendataanrtlh.utils.Data.matDinding
import com.example.pendataanrtlh.utils.Data.matLantai
import com.example.pendataanrtlh.utils.Data.noKTPUser
import com.example.pendataanrtlh.utils.FileUtil
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import id.zelory.compressor.Compressor
import id.zelory.compressor.loadBitmap
import kotlinx.coroutines.launch
import java.io.IOException


class PageSixFragment : Fragment() {
    private lateinit var binding: FragmentPageSixBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var myRef1: DatabaseReference

    companion object {
        private const val PICK_IMAGE_REQUEST_1 = 1
        private const val PICK_IMAGE_REQUEST_2 = 2
        private const val PICK_IMAGE_REQUEST_3 = 3
        private const val PICK_IMAGE_REQUEST_4 = 4
        private const val PICK_IMAGE_REQUEST_5 = 5
        private const val PICK_IMAGE_REQUEST_6 = 6
        private const val PICK_IMAGE_REQUEST_7 = 7
        private const val PICK_IMAGE_REQUEST_8 = 8
        private const val PICK_IMAGE_REQUEST_9 = 9
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageSixBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("${Data.DATA_SURVEY}/${Data.noKTPUser}")
        myRef1 = database.getReference("${Data.DATA_SURVEYOR}/${Data.noKTPUser}")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_sixFragment_to_sevenFragment)
        }

        binding.btnNext.setOnClickListener {
//            findNavController().navigate(R.id.action_sixFragment_to_firstFragment)

            val inMatAtap = binding.listMaterialAtap.selectedItem.toString()
            val inKonAtap = binding.listKondisiAtap.selectedItem.toString()
            val inMatDinding = binding.listMaterialDinding.selectedItem.toString()
            val inKonDinding = binding.listKondisiDinding.selectedItem.toString()
            val inMatLantai = binding.listMaterialLantai.selectedItem.toString()
            val inKonLantai = binding.listKondisiLantai.selectedItem.toString()

            if (inMatAtap != "pilih" &&
                inKonAtap != "pilih" &&
                inMatDinding != "pilih" &&
                inKonDinding != "pilih" &&
                inMatLantai != "pilih" &&
                inKonLantai != "pilih"
            ) {
                //One Push Method
                matAtap = inMatAtap
                konAtap = inKonAtap
                matDinding = inMatDinding
                konDinding = inKonDinding
                matLantai = inMatLantai
                konLantai = inKonLantai

                myRef.setValue(
                    FormDataSurvey(
                        Data.nomorRumah,
                        Data.namaLengkap,
                        Data.usia,
                        Data.pendidikan,
                        Data.jenisKelamin,
                        Data.titikKoordinat,
                        Data.almLengkp,
                        Data.noKTPUser,
                        Data.jumlhKK,
                        Data.pekerjaan,
                        Data.penghasilan,
                        Data.statusTanah,
                        Data.statusRumah,
                        Data.assetRumah,
                        Data.assetTanah,
                        Data.bantuanRumah,
                        Data.kawasanLokasi,
                        Data.pondasi,
                        Data.balok,
                        Data.atap,
                        Data.jendela,
                        Data.ventilasi,
                        Data.kmrMandi,
                        Data.jrkKmrMandi,
                        Data.sumAirMinum,
                        Data.sumListrik,
                        Data.luasRumah,
                        Data.jumPenghuni,
                        matAtap,
                        konAtap,
                        matDinding,
                        konDinding,
                        matLantai,
                        konLantai,
                        Data.nameDesaKel,
                        Data.nameKec,
                        Data.nameKotaKab,
                        Data.nameProv,
                    )
                ).addOnCompleteListener {
                    myRef1.setValue(
                        FormSurveyor(
                            Data.namaLengkap,
                            Data.noKTPUser,
                            Data.fullName,
                            Data.noKTPSurveyor,
                            Data.tglInput,
                        )
                    )
                    findNavController().navigate(R.id.action_sixFragment_to_sevenFragment)
                }
            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }

        setupClickListener()
    }

    private fun setupClickListener() {
        binding.fotoKondisiAtap1.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_1) }
        binding.fotoKondisiAtap2.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_2) }
        binding.fotoKondisiAtap3.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_3) }
        binding.fotoKondisiDinding1.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_4) }
        binding.fotoKondisiDinding2.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_5) }
        binding.fotoKondisiDinding3.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_6) }
        binding.fotoKondisiLantai1.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_7) }
        binding.fotoKondisiLantai2.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_8) }
        binding.fotoKondisiLantai3.setOnClickListener { chooseImageMulti(PICK_IMAGE_REQUEST_9) }
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiAtap1.setImageBitmap(loadBitmap(compressedImage))
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiAtap2.setImageBitmap(loadBitmap(compressedImage))
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiAtap3.setImageBitmap(loadBitmap(compressedImage))
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiDinding1.setImageBitmap(loadBitmap(compressedImage))
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiDinding2.setImageBitmap(loadBitmap(compressedImage))
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiDinding3.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_7 -> {
                if (requestCode == PICK_IMAGE_REQUEST_7 && resultCode == RESULT_OK) {
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiLantai1.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_8 -> {
                if (requestCode == PICK_IMAGE_REQUEST_8 && resultCode == RESULT_OK) {
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiLantai2.setImageBitmap(loadBitmap(compressedImage))
                            }
                        }
                    } catch (e: IOException) {
                        showError("Failed to read picture data!")
                        e.printStackTrace()
                    }
                }
            }
            PICK_IMAGE_REQUEST_9 -> {
                if (requestCode == PICK_IMAGE_REQUEST_9 && resultCode == RESULT_OK) {
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
                                uploadImageDua(file, "form6_${requestCode}_$noKTPUser")
                                binding.fotoKondisiLantai3.setImageBitmap(loadBitmap(compressedImage))
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
                    Toast.makeText(requireContext(), "Add Image Successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .addOnFailureListener {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Info File : ${it.message}", Toast.LENGTH_SHORT)
                    .show()
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