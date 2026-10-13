package com.example.ktgk_2415141122115_nguyenthithucquyen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var edtTenKhoanChi_115: EditText
    private lateinit var edtSoTien_115: EditText
    private lateinit var edtGhiChu_115: EditText
    private lateinit var btnThemKhoanChi_115: Button
    private lateinit var btnXemChiTiet_115: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thiết lập tiêu đề Toolbar
        supportActionBar?.title = "Nguyễn Thị Thục Quyên - 2415141122115 - Quản lý chi tiêu cá nhân"

        khoiTaoGiaoDien_115()
        xuLySuKien_115()
    }

    private fun khoiTaoGiaoDien_115() {
        edtTenKhoanChi_115 = findViewById(R.id.edtTenKhoanChi_115)
        edtSoTien_115 = findViewById(R.id.edtSoTien_115)
        edtGhiChu_115 = findViewById(R.id.edtGhiChu_115)
        btnThemKhoanChi_115 = findViewById(R.id.btnThemKhoanChi_115)
        btnXemChiTiet_115 = findViewById(R.id.btnXemChiTiet_115)
    }

    private fun xuLySuKien_115() {
        btnThemKhoanChi_115.setOnClickListener {
            val ten_115 = edtTenKhoanChi_115.text.toString().trim()
            val soTienStr_115 = edtSoTien_115.text.toString().trim()
            val ghiChu_115 = edtGhiChu_115.text.toString().trim()

            if (ten_115.isEmpty() || soTienStr_115.isEmpty()) {
                Toast.makeText(
                    this,
                    "Vui lòng nhập đầy đủ tên và số tiền!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val soTien_115 = soTienStr_115.toDoubleOrNull()

            if (soTien_115 == null) {
                Toast.makeText(this, "Số tiền phải là số hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val khoanChi_115 = KhoanChi_115(
                maKhoanChi_115 = "KC001",
                tenKhoanChi_115 = ten_115,
                soTien_115 = soTien_115,
                ghiChu_115 = ghiChu_115,
                daThanhToan_115 = false
            )

            if (!khoanChi_115.laSoTienHopLe_115()) {
                Toast.makeText(this, "Số tiền phải lớn hơn 0!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(
                this,
                khoanChi_115.layThongTin_115(),
                Toast.LENGTH_LONG
            ).show()
        }

        btnXemChiTiet_115.setOnClickListener {
            val ten_115 = edtTenKhoanChi_115.text.toString().trim()
            val soTienStr_115 = edtSoTien_115.text.toString().trim()
            val ghiChu_115 = edtGhiChu_115.text.toString().trim()

            if (ten_115.isEmpty() || soTienStr_115.isEmpty()) {
                Toast.makeText(this, "Chưa có dữ liệu để xem chi tiết!", Toast.LENGTH_SHORT).show()
            } else {
                val intent_115 = Intent(this, ChiTietChiTieuActivity::class.java).apply {
                    putExtra("EXTRA_TEN", ten_115)
                    putExtra("EXTRA_SOTIEN", soTienStr_115.toDouble())
                    putExtra("EXTRA_GHICHU", ghiChu_115)
                }
                startActivity(intent_115)
            }
        }
    }
    }