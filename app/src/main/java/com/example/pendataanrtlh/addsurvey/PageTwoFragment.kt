package com.example.pendataanrtlh.addsurvey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageTwoBinding
import com.example.pendataanrtlh.model.FormData
import com.example.pendataanrtlh.model.IdentitasPenghuniRmh
import com.example.pendataanrtlh.utils.Data.IDENTITAS_PENGHUNI_RMH
import com.example.pendataanrtlh.utils.Data.USER_DATA
import com.example.pendataanrtlh.utils.Data.almLengkp
import com.example.pendataanrtlh.utils.Data.assetRumah
import com.example.pendataanrtlh.utils.Data.assetTanah
import com.example.pendataanrtlh.utils.Data.bantuanRumah
import com.example.pendataanrtlh.utils.Data.jenisKelamin
import com.example.pendataanrtlh.utils.Data.jumlhKK
import com.example.pendataanrtlh.utils.Data.kawasanLokasi
import com.example.pendataanrtlh.utils.Data.namaLengkap
import com.example.pendataanrtlh.utils.Data.nikPeserta
import com.example.pendataanrtlh.utils.Data.noKTP
import com.example.pendataanrtlh.utils.Data.nomorRumah
import com.example.pendataanrtlh.utils.Data.pekerjaan
import com.example.pendataanrtlh.utils.Data.pendidikan
import com.example.pendataanrtlh.utils.Data.penghasilan
import com.example.pendataanrtlh.utils.Data.statusRumah
import com.example.pendataanrtlh.utils.Data.statusTanah
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PageTwoFragment : Fragment() {
    private lateinit var binding: FragmentPageTwoBinding
//    private lateinit var database: FirebaseDatabase
//    private lateinit var myRef: DatabaseReference
//    private lateinit var myRef1: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        database = FirebaseDatabase.getInstance()
//        myRef = database.getReference("$USER_DATA/$nikPeserta/$IDENTITAS_PENGHUNI_RMH")
//        myRef1 = database.getReference("IdentitasPenghuniRmh/$nikPeserta")
        binding.textNoKTP.setText(nikPeserta)

        binding.btnPrev.setOnClickListener {
            Toast.makeText(requireContext(), "${FormData()}", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
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

            val inPendidikan = binding.listPendidikan.selectedItem.toString()
            val inPekerjaan = binding.listPekerjaan.selectedItem.toString()
            val inPenghasilan = binding.listPenghasilan.selectedItem.toString()
            val inStatusTanah = binding.listStatusTanah.selectedItem.toString()
            val inStatusRumah = binding.listStatusRumah.selectedItem.toString()
            val inBantuanRumah = binding.listBantuanPerumahan.selectedItem.toString()

            val inJenisKelamin = if (binding.chipPria.isChecked) {
                "Pria"
            } else {
                "Wanita"
            }

            val inAssetRumah = if (binding.rbAdaRumah.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }

            val inAssetTanah = if (binding.rbAdaTanah.isChecked) {
                "Ada"
            } else {
                "Tidak Ada"
            }

            val inKawasanLokasi = StringBuilder()
            if (binding.cbRawanAir.isChecked) {
                inKawasanLokasi.append("Rawan Air,")
            }
            if (binding.cbKEK.isChecked) {
                inKawasanLokasi.append("KEK, ")
            }
            if (binding.cbPerbatasan.isChecked) {
                inKawasanLokasi.append("Perbatasan, ")
            }
            if (binding.cbPulauKecil.isChecked) {
                inKawasanLokasi.append("PulauKecil, ")
            }
            if (binding.cbDaerahTertinggal.isChecked) {
                inKawasanLokasi.append("DaerahTertinggal, ")
            }
            if (binding.cbKumuh.isChecked) {
                inKawasanLokasi.append("Kumuh, ")
            }
            if (binding.cbKSPN.isChecked) {
                inKawasanLokasi.append("KSPN, ")
            }
            if (binding.cbPesisir.isChecked) {
                inKawasanLokasi.append("Pesisir, ")
            }
            if (binding.cbTransmigrasi.isChecked) {
                inKawasanLokasi.append("Transmigrasi")
            }

            if (!inputKosong) {
                if (inPendidikan != "pilih" &&
                    inPekerjaan != "pilih" &&
                    inPenghasilan != "pilih" &&
                    inStatusRumah != "pilih" &&
                    inStatusTanah != "pilih" &&
                    inBantuanRumah != "pilih" &&
                    inKawasanLokasi.isNotEmpty()) {
                    //One Push Methode
                    nomorRumah = inNomorRumah
                    namaLengkap = inNamaLengkap
                    pendidikan = inPendidikan
                    jenisKelamin = inJenisKelamin
                    almLengkp = inAlmLengkp
                    noKTP = inNoKtp
                    jumlhKK = inJumlhKK
                    pekerjaan = inPekerjaan
                    penghasilan = inPenghasilan
                    statusTanah = inStatusTanah
                    statusRumah = inStatusRumah
                    assetRumah = inAssetRumah
                    assetTanah = inAssetTanah
                    bantuanRumah = inBantuanRumah
                    kawasanLokasi = inKawasanLokasi.toString()

                    findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
                }
            } else {
                Toast.makeText(context, "Harap diisi dahulu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}