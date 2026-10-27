package com.example.formvalidation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formvalidation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDangKy.setOnClickListener {
            dangKy()
        }

        binding.btnHuy.setOnClickListener {
            huyNhapLieu()
        }
    }

    private fun dangKy() {

        val hoTen = binding.edtHoTen.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val matKhau = binding.edtMatKhau.text.toString()
        val soDienThoai = binding.edtSoDienThoai.text.toString().trim()

        // Xoa cac thong bao loi cu
        binding.tilHoTen.error = null
        binding.tilEmail.error = null
        binding.tilMatKhau.error = null
        binding.tilSoDienThoai.error = null

        // Kiem tra ho ten
        if (!FormValidator.kiemTraHoTen(hoTen)) {
            binding.tilHoTen.error = "Họ tên phải có ít nhất 2 ký tự"
            binding.edtHoTen.requestFocus()

            hienSnackbar("Vui lòng kiểm tra họ tên!")
            return
        }

        // Kiem tra email
        if (!FormValidator.kiemTraEmail(email)) {
            binding.tilEmail.error = "Email không đúng định dạng"
            binding.edtEmail.requestFocus()

            hienSnackbar("Vui lòng nhập email hợp lệ!")
            return
        }

        // Kiem tra mat khau
        if (!FormValidator.kiemTraMatKhau(matKhau)) {
            binding.tilMatKhau.error = "Mật khẩu phải có ít nhất 6 ký tự"
            binding.edtMatKhau.requestFocus()

            hienSnackbar("Mật khẩu chưa hợp lệ!")
            return
        }

        // Kiem tra so dien thoai
        if (!FormValidator.kiemTraSoDienThoai(soDienThoai)) {
            binding.tilSoDienThoai.error =
                "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng 0"

            binding.edtSoDienThoai.requestFocus()

            hienSnackbar("Vui lòng nhập số điện thoại hợp lệ!")
            return
        }

        // Tao doi tuong nguoi dung
        val nguoiDung = NguoiDung(
            hoTen = hoTen,
            email = email,
            matKhau = matKhau,
            soDienThoai = soDienThoai
        )

        // Chuyen sang man hinh trang chu
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("hoTen", nguoiDung.hoTen)
            putExtra("email", nguoiDung.email)
            putExtra("soDienThoai", nguoiDung.soDienThoai)
        }

        startActivity(intent)
    }

    private fun huyNhapLieu() {

        binding.edtHoTen.text?.clear()
        binding.edtEmail.text?.clear()
        binding.edtMatKhau.text?.clear()
        binding.edtSoDienThoai.text?.clear()

        binding.tilHoTen.error = null
        binding.tilEmail.error = null
        binding.tilMatKhau.error = null
        binding.tilSoDienThoai.error = null

        binding.edtHoTen.requestFocus()

        hienSnackbar("Đã xóa toàn bộ dữ liệu!")
    }

    private fun hienSnackbar(thongBao: String) {
        Snackbar.make(
            binding.root,
            thongBao,
            Snackbar.LENGTH_LONG
        ).show()
    }
}