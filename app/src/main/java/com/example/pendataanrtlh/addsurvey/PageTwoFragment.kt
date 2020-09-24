package com.example.pendataanrtlh.addsurvey

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pendataanrtlh.R
import com.example.pendataanrtlh.databinding.FragmentPageTwoBinding
import com.example.pendataanrtlh.utils.Data.almLengkp
import com.example.pendataanrtlh.utils.Data.assetRumah
import com.example.pendataanrtlh.utils.Data.assetTanah
import com.example.pendataanrtlh.utils.Data.bantuanRumah
import com.example.pendataanrtlh.utils.Data.jenisKelamin
import com.example.pendataanrtlh.utils.Data.jumlhKK
import com.example.pendataanrtlh.utils.Data.kawasanLokasi
import com.example.pendataanrtlh.utils.Data.namaLengkap
import com.example.pendataanrtlh.utils.Data.noKTPUser
import com.example.pendataanrtlh.utils.Data.nomorRumah
import com.example.pendataanrtlh.utils.Data.pekerjaan
import com.example.pendataanrtlh.utils.Data.pendidikan
import com.example.pendataanrtlh.utils.Data.penghasilan
import com.example.pendataanrtlh.utils.Data.statusRumah
import com.example.pendataanrtlh.utils.Data.statusTanah
import com.example.pendataanrtlh.utils.Data.usia
import com.google.android.gms.location.LocationServices
import java.util.*
import kotlin.math.*


class PageTwoFragment : Fragment() {
    private lateinit var binding: FragmentPageTwoBinding

    companion object {
        const val ID_LOCATION_PERMISSION = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkPermissionLocation()

        binding.loadKoordinat.hide()

        binding.btnKoordinat.setOnClickListener {
            binding.loadKoordinat.show()
            Handler(Looper.getMainLooper()).postDelayed({
                getLocationCoordinat()
            }, 4000)
        }

        binding.btnNext.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
            with(binding) {
                val inNomorRumah = textNoRumah.text.toString().trim { it <= ' ' }
                val inNamaLengkap = textNmLengkap.text.toString().trim { it <= ' ' }
                val inUsia = textUsia.text.toString().trim { it <= ' ' }
                val inAlmLengkp = textAlmtLengkap.text.toString().trim { it <= ' ' }
                val inNoKtp = textNoKTP.text.toString().trim { it <= ' ' }
                val inJumlhKK = textJumKK.text.toString().trim { it <= ' ' }
                var inputKosong = false

                when {
                    inNomorRumah.isEmpty() -> {
                        inputKosong = true
                        textNoRumah.error = getString(R.string.msg_error_empty)
                    }
                    inNamaLengkap.isEmpty() -> {
                        inputKosong = true
                        textNmLengkap.error = getString(R.string.msg_error_empty)
                    }
                    inUsia.isEmpty() -> {
                        inputKosong = true
                        textUsia.error = getString(R.string.msg_error_empty)
                    }
                    inAlmLengkp.isEmpty() -> {
                        inputKosong = true
                        textAlmtLengkap.error = getString(R.string.msg_error_empty)
                    }
                    inNoKtp.isEmpty() -> {
                        inputKosong = true
                        textNoKTP.error = getString(R.string.msg_error_empty)
                    }
                    inJumlhKK.isEmpty() -> {
                        inputKosong = true
                        textJumKK.error = getString(R.string.msg_error_empty)
                    }
                }

                val inPendidikan = listPendidikan.selectedItem.toString()
                val inPekerjaan = listPekerjaan.selectedItem.toString()
                val inPenghasilan = listPenghasilan.selectedItem.toString()
                val inStatusTanah = listStatusTanah.selectedItem.toString()
                val inStatusRumah = listStatusRumah.selectedItem.toString()
                val inBantuanRumah = listBantuanPerumahan.selectedItem.toString()

                val inJenisKelamin = if (chipPria.isChecked) {
                    "Pria"
                } else {
                    "Wanita"
                }

                val inAssetRumah = if (rbAdaRumah.isChecked) {
                    "Ada"
                } else {
                    "Tidak Ada"
                }

                val inAssetTanah = if (rbAdaTanah.isChecked) {
                    "Ada"
                } else {
                    "Tidak Ada"
                }

                val inKawasanLokasi = StringBuilder()
                if (cbRawanAir.isChecked) {
                    inKawasanLokasi.append("Rawan Air,")
                }
                if (cbKEK.isChecked) {
                    inKawasanLokasi.append("KEK, ")
                }
                if (cbPerbatasan.isChecked) {
                    inKawasanLokasi.append("Perbatasan, ")
                }
                if (cbPulauKecil.isChecked) {
                    inKawasanLokasi.append("PulauKecil, ")
                }
                if (cbDaerahTertinggal.isChecked) {
                    inKawasanLokasi.append("DaerahTertinggal, ")
                }
                if (cbKumuh.isChecked) {
                    inKawasanLokasi.append("Kumuh, ")
                }
                if (cbKSPN.isChecked) {
                    inKawasanLokasi.append("KSPN, ")
                }
                if (cbPesisir.isChecked) {
                    inKawasanLokasi.append("Pesisir, ")
                }
                if (cbTransmigrasi.isChecked) {
                    inKawasanLokasi.append("Transmigrasi")
                }

                if (!inputKosong) {
                    if (inPendidikan != "pilih" &&
                        inPekerjaan != "pilih" &&
                        inPenghasilan != "pilih" &&
                        inStatusRumah != "pilih" &&
                        inStatusTanah != "pilih" &&
                        inBantuanRumah != "pilih" &&
                        inKawasanLokasi.isNotEmpty()
                    ) {
                        //One Push Methode
                        nomorRumah = inNomorRumah
                        namaLengkap = inNamaLengkap
                        usia = inUsia
                        pendidikan = inPendidikan
                        jenisKelamin = inJenisKelamin
                        almLengkp = inAlmLengkp
                        noKTPUser = inNoKtp
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


    private fun getLocationCoordinat() {
        if (checkPermission()) {
            if (isLoactionEnable()) {
                LocationServices.getFusedLocationProviderClient(requireContext()).lastLocation.addOnSuccessListener { location ->
                    val currentLat = location.latitude
                    val currentLong = location.longitude

                    val resultCoordinat = "$currentLat, $currentLong"
                    binding.titikKoordinat.text = resultCoordinat

                    Log.d("posisi hp", "lokasi $resultCoordinat")

                    getAddressesLatLot(currentLat, currentLong)

                    binding.loadKoordinat.hide()
                }
            } else {
                showTurnOnLocation()
            }
        } else {
            reqPermission()
        }
    }

    private fun getAddressesLatLot(lat: Double, lon: Double) {
        try {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lon, 1)
            val address = addresses[0].getAddressLine(0)
            Log.d("alamat", "lokasi $address")
//            binding.btnKoordinat.text = address

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Suppress("SameParameterValue")
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6372.8 // in kilometers

        val radiansLat1 = Math.toRadians(lat1)
        val radiansLat2 = Math.toRadians(lat2)
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        return 2 * r * asin(
            sqrt(
                sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(radiansLat1) * cos(
                    radiansLat2
                )
            )
        )
    }

    override fun onRequestPermissionsResult(
        reqCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(reqCode, permissions, grantResults)
        if (reqCode == ID_LOCATION_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED ||
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(requireContext(), "Akses diizinkan", Toast.LENGTH_SHORT).show()
                if (!isLoactionEnable()) {
                    showTurnOnLocation()
                }
            } else {
                Toast.makeText(requireContext(), "Akses Tidak diizinkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermissionLocation() {
        if (checkPermission()) {
            if (!isLoactionEnable()) {
                showTurnOnLocation()
            }
        } else {
            reqPermission()
        }
    }

    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun isLoactionEnable(): Boolean {
        val locManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        ) {
            return true
        }
        return false
    }

    private fun reqPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ), ID_LOCATION_PERMISSION
        )
    }

    private fun showTurnOnLocation() {
        Toast.makeText(requireContext(), "Silahakan Aktifkan Lokasi Kamu", Toast.LENGTH_SHORT)
            .show()
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }
}