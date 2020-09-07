package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageTwoBinding
import com.example.pendataanrtlh.model.IdentitasPenghuniRmh
import com.example.pendataanrtlh.utils.Data.IDENTITAS_PENGHUNI_RMH
import com.example.pendataanrtlh.utils.Data.TEMP_FORM
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PageTwoFragment : Fragment() {
    private lateinit var binding: FragmentPageTwoBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    private var jenisKelamin: String? = ""
    private var pendidikan: String? = ""
    private var pekerjaan: String? = ""
    private var penghasilan: String? = ""
    private var statusTanah: String? = ""
    private var statusRumah: String? = ""
    private var assetRumah: String? = ""
    private var assetTanah: String? = ""
    private var bantuanRumah: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("$TEMP_FORM/$nikPeserta/$IDENTITAS_PENGHUNI_RMH")

        binding.btnPrev.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.btnNext.setOnClickListener {
            val inNomorRumah = binding.textNoRumah.text.toString().trim { it <= ' ' }
            val inNamaLengkap = binding.textNmLengkap.text.toString().trim { it <= ' ' }
            val inUsia = binding.textUsia.text.toString().trim { it <= ' ' }
            val inAlmLengkp = binding.textAlmtLengkap.text.toString().trim { it <= ' ' }
            val inNoKtp = binding.textNoKTP.text.toString().trim { it <= ' ' }
            val inJumlhKK = binding.textJumKK.text.toString().trim { it <= ' ' }
            var inputKosong = false

            when {
                inNomorRumah.isEmpty() -> {
                    inputKosong = true
                    binding.textNoRumah.error = getString(R.string.msg_error_empty)
                }
                inNamaLengkap.isEmpty() -> {
                    inputKosong = true
                    binding.textNmLengkap.error = getString(R.string.msg_error_empty)
                }
                inUsia.isEmpty() -> {
                    inputKosong = true
                    binding.textUsia.error = getString(R.string.msg_error_empty)
                }
                inAlmLengkp.isEmpty() -> {
                    inputKosong = true
                    binding.textAlmtLengkap.error = getString(R.string.msg_error_empty)
                }
                inNoKtp.isEmpty() -> {
                    inputKosong = true
                    binding.textNoKTP.error = getString(R.string.msg_error_empty)
                }
                inJumlhKK.isEmpty() -> {
                    inputKosong = true
                    binding.textJumKK.error = getString(R.string.msg_error_empty)
                }
            }

            pendidikan = binding.listPendidikan.selectedItem.toString()
            pekerjaan = binding.listPekerjaan.selectedItem.toString()
            penghasilan = binding.listPenghasilan.selectedItem.toString()
            statusTanah = binding.listStatusTanah.selectedItem.toString()
            statusRumah = binding.listStatusRumah.selectedItem.toString()
            bantuanRumah = binding.listBantuanPerumahan.selectedItem.toString()

            jenisKelamin = if (binding.chipPria.isChecked) {
                "Pria"
            } else {
                "Wanita"
            }

            assetRumah = if (binding.rbAdaRumah.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }

            assetTanah = if (binding.rbAdaTanah.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }

            if (!inputKosong) {
                if (!jenisKelamin.isNullOrEmpty() && pendidikan != "pilih") {
                    myRef.setValue(
                        IdentitasPenghuniRmh(
                            inNomorRumah,
                            inNamaLengkap,
                            "",
                            pendidikan,
                            jenisKelamin,
                            inAlmLengkp,
                            inNoKtp,
                            inJumlhKK,
                            pekerjaan,
                            penghasilan,
                            statusTanah,
                            statusRumah,
                            assetRumah,
                            assetTanah,
                            bantuanRumah,
                            ""
                        )
                    )
                        .addOnCompleteListener {
                            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
                        }
                }
            } else {
                Toast.makeText(context, "Harap Diisi dahulu", Toast.LENGTH_SHORT).show()
            }


        }
    }
}