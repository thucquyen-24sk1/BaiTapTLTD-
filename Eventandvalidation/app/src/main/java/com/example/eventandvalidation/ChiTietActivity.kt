package com.example.eventandvalidation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventandvalidation.databinding.ActivityChiTietBinding

class ChiTietActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChiTietBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvHoTen.text =
            "Ho ten: ${intent.getStringExtra("hoTen") ?: "Chua co"}"

        binding.tvEmail.text =
            "Email: ${intent.getStringExtra("email") ?: "Chua co"}"

        binding.tvSDT.text =
            "SDT: ${intent.getStringExtra("sdt") ?: "Chua co"}"

        binding.tvMSV.text =
            "MSV: ${intent.getStringExtra("msv") ?: "Chua co"}"

        binding.tvLop.text =
            "Lop: ${intent.getStringExtra("lop") ?: "Chua co"}"

        binding.btnQuayLai.setOnClickListener {
            finish()
        }
    }
}