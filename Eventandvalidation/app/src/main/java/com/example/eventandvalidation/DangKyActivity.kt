
package com.example.eventandvalidation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.eventandvalidation.databinding.ActivityDangKyBinding

class DangKyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDangKyBinding

    // Luu thong tin da dang ky
    private var hoTenDaLuu = ""
    private var emailDaLuu = ""
    private var sdtDaLuu = ""
    private var msvDaLuu = ""
    private var lopDaLuu = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDangKyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khoi phuc du lieu khi quay lai man hinh
        if (savedInstanceState != null) {
            hoTenDaLuu = savedInstanceState.getString("hoTen", "") ?: ""
            emailDaLuu = savedInstanceState.getString("email", "") ?: ""
            sdtDaLuu = savedInstanceState.getString("sdt", "") ?: ""
            msvDaLuu = savedInstanceState.getString("msv", "") ?: ""
            lopDaLuu = savedInstanceState.getString("lop", "") ?: ""
        }

        // Hien lai du lieu da nhap
        binding.edtHoTen.setText(hoTenDaLuu)
        binding.edtEmail.setText(emailDaLuu)
        binding.edtSDT.setText(sdtDaLuu)
        binding.edtMSV.setText(msvDaLuu)
        binding.edtLop.setText(lopDaLuu)

        binding.btnDangKy.setOnClickListener {
            dangKy()
        }

        binding.btnSangChiTiet.setOnClickListener {
            moManHinhChiTiet()
        }
    }

    private fun dangKy() {
        val hoTen = binding.edtHoTen.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val sdt = binding.edtSDT.text.toString().trim()
        val msv = binding.edtMSV.text.toString().trim()
        val lop = binding.edtLop.text.toString().trim()

        if (hoTen.isBlank()) {
            binding.edtHoTen.error = "Vui long nhap ho ten"
            binding.edtHoTen.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edtEmail.error = "Email khong dung dinh dang"
            binding.edtEmail.requestFocus()
            return
        }

        if (!sdt.matches(Regex("^0[0-9]{9}$"))) {
            binding.edtSDT.error = "SDT phai co 10 chu so va bat dau bang 0"
            binding.edtSDT.requestFocus()
            return
        }

        if (msv.isBlank() || !msv.all { it.isDigit() }) {
            binding.edtMSV.error = "MSV phai la so va khong duoc trong"
            binding.edtMSV.requestFocus()
            return
        }

        if (lop.isBlank()) {
            binding.edtLop.error = "Vui long nhap lop"
            binding.edtLop.requestFocus()
            return
        }

        // Luu lai du lieu da dang ky
        hoTenDaLuu = hoTen
        emailDaLuu = email
        sdtDaLuu = sdt
        msvDaLuu = msv
        lopDaLuu = lop

        moManHinhChiTiet()
    }

    private fun moManHinhChiTiet() {
        val intent = Intent(this, ChiTietActivity::class.java)
        intent.putExtra("hoTen", hoTenDaLuu)
        intent.putExtra("email", emailDaLuu)
        intent.putExtra("sdt", sdtDaLuu)
        intent.putExtra("msv", msvDaLuu)
        intent.putExtra("lop", lopDaLuu)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("hoTen", hoTenDaLuu)
        outState.putString("email", emailDaLuu)
        outState.putString("sdt", sdtDaLuu)
        outState.putString("msv", msvDaLuu)
        outState.putString("lop", lopDaLuu)
        super.onSaveInstanceState(outState)
    }
}
