package com.example.formvalidation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formvalidation.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hoTen = intent.getStringExtra("hoTen") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val soDienThoai = intent.getStringExtra("soDienThoai") ?: ""

        binding.tvThongTinNguoiDung.text = """
            Họ và tên: $hoTen

            Email: $email

            Số điện thoại: $soDienThoai
        """.trimIndent()

        binding.btnQuayLai.setOnClickListener {
            finish()
        }
    }
}