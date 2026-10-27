package com.example.baitapevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapevent.databinding.ActivityChiTietBinding

class ChiTietActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChiTietBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hoTen = intent.getStringExtra("hoTen") ?: ""
        val maSV = intent.getStringExtra("maSV") ?: ""
        val lop = intent.getStringExtra("lop") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val gioiTinh = intent.getStringExtra("gioiTinh") ?: ""

        val mangXaHoi = intent.getStringArrayListExtra("mangXaHoi")
            ?: arrayListOf()

        binding.tvChiTiet.text = """
            Họ và tên: $hoTen

            Mã sinh viên: $maSV

            Lớp: $lop

            Email: $email

            Giới tính: $gioiTinh
        """.trimIndent()

        binding.tvMangXaHoi.text = if (mangXaHoi.isEmpty()) {
            "Bạn chưa chọn mạng xã hội nào."
        } else {
            mangXaHoi.joinToString("\n") { "• $it" }
        }

        binding.btnQuayLai.setOnClickListener {
            finish()
        }
    }
}