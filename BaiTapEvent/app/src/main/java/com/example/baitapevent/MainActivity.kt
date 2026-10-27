package com.example.baitapevent

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        xuLySuKienEditText_122()
        xuLySuKienRadioButton_122()
        xuLySuKienCheckBox_122()

        binding.btnDangKy.setOnClickListener {
            dangKy_122()
        }
    }

    private fun xuLySuKienEditText_122() {

        binding.edtHoTen.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.toString().isNotBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Đang nhập họ tên",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edtMaSV.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(
                    this,
                    "Bạn đang nhập mã sinh viên",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.edtLop.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(
                    this,
                    "Bạn đang nhập lớp học",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(
                    this,
                    "Bạn đang nhập email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun xuLySuKienRadioButton_122() {

        binding.rgGioiTinh.setOnCheckedChangeListener { _, checkedId ->

            val gioiTinh = when (checkedId) {
                binding.rbNam.id -> "Nam"
                binding.rbNu.id -> "Nữ"
                else -> return@setOnCheckedChangeListener
            }

            Toast.makeText(
                this,
                "Bạn đã chọn giới tính: $gioiTinh",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun xuLySuKienCheckBox_122() {

        binding.cbFacebook.setOnCheckedChangeListener { _, isChecked ->
            hienThongBaoCheckBox_122("Facebook", isChecked)
        }

        binding.cbInstagram.setOnCheckedChangeListener { _, isChecked ->
            hienThongBaoCheckBox_122("Instagram", isChecked)
        }

        binding.cbTikTok.setOnCheckedChangeListener { _, isChecked ->
            hienThongBaoCheckBox_122("TikTok", isChecked)
        }
    }

    private fun hienThongBaoCheckBox_122(
        tenMangXaHoi: String,
        daChon: Boolean
    ) {

        val thongBao = if (daChon) {
            "Đã chọn $tenMangXaHoi"
        } else {
            "Đã bỏ chọn $tenMangXaHoi"
        }

        Toast.makeText(
            this,
            thongBao,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun dangKy_122() {

        val hoTen = binding.edtHoTen.text.toString().trim()
        val maSV = binding.edtMaSV.text.toString().trim()
        val lop = binding.edtLop.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()

        val gioiTinh = when (binding.rgGioiTinh.checkedRadioButtonId) {
            binding.rbNam.id -> "Nam"
            binding.rbNu.id -> "Nữ"
            else -> ""
        }

        val mangXaHoi = mutableListOf<String>()

        if (binding.cbFacebook.isChecked) {
            mangXaHoi.add("Facebook")
        }

        if (binding.cbInstagram.isChecked) {
            mangXaHoi.add("Instagram")
        }

        if (binding.cbTikTok.isChecked) {
            mangXaHoi.add("TikTok")
        }

        if (
            hoTen.isEmpty() ||
            maSV.isEmpty() ||
            lop.isEmpty() ||
            email.isEmpty() ||
            gioiTinh.isEmpty()
        ) {
            Toast.makeText(
                this,
                "Vui lòng nhập đầy đủ thông tin và chọn giới tính!",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        val intent_122 = Intent(
            this,
            ChiTietActivity::class.java
        )

        intent_122.putExtra("hoTen", hoTen)
        intent_122.putExtra("maSV", maSV)
        intent_122.putExtra("lop", lop)
        intent_122.putExtra("email", email)
        intent_122.putExtra("gioiTinh", gioiTinh)

        intent_122.putStringArrayListExtra(
            "mangXaHoi",
            ArrayList(mangXaHoi)
        )

        startActivity(intent_122)
    }
}