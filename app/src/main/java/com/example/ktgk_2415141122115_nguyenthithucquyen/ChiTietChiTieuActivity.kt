package com.example.ktgk_2415141122115_nguyenthithucquyen

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var txtThongTinChiTiet_115: TextView
    private lateinit var btnQuayLai_115: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        // Thiết lập tiêu đề Toolbar
        supportActionBar?.title = "Nguyễn Thị Thục Quyên - 2415141122115 - Chi tiết chi tiêu"

        txtThongTinChiTiet_115 = findViewById(R.id.txtThongTinChiTiet_115)
        btnQuayLai_115 = findViewById(R.id.btnQuayLai_115)

        // Nhận dữ liệu truyền qua Intent từ MainActivity
        val tenKhoanChi_115 = intent.getStringExtra("EXTRA_TEN")
        val soTien_115 = intent.getDoubleExtra("EXTRA_SOTIEN", -1.0)
        val ghiChu_115 = intent.getStringExtra("EXTRA_GHICHU")

        // Kiểm tra điều kiện dữ liệu theo yêu cầu Câu 2
        if (tenKhoanChi_115.isNullOrEmpty() || soTien_115 <= 0.0) {
            txtThongTinChiTiet_115.text = "Lỗi: Tên khoản chi bị trống hoặc số tiền không hợp lệ!"
        } else {
            // Hiển thị thông tin chi tiết
            val ketQua_115 = "Tên khoản chi: $tenKhoanChi_115\n" +
                    "Số tiền: $soTien_115 VNĐ\n" +
                    "Ghi chú: ${if (ghiChu_115.isNullOrEmpty()) "Không có" else ghiChu_115}"
            txtThongTinChiTiet_115.text = ketQua_115
        }

        // Nút quay lại màn hình chính
        btnQuayLai_115.setOnClickListener {
            finish()
        }
    }
}