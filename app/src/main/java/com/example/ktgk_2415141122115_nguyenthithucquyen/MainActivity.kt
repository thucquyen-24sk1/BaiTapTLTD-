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

            // Kiểm tra tên khoản chi
            if (ten_115.isEmpty()) {
                edtTenKhoanChi_115.error = "Vui lòng nhập tên khoản chi"
                edtTenKhoanChi_115.requestFocus()
                return@setOnClickListener
            }

            // Kiểm tra số tiền có phải số hợp lệ không
            val soTien_115 = soTienStr_115.toDoubleOrNull()

            if (soTien_115 == null) {
                edtSoTien_115.error = "Vui lòng nhập số tiền hợp lệ"
                edtSoTien_115.requestFocus()
                return@setOnClickListener
            }

            // Kiểm tra số tiền phải lớn hơn 0
            if (soTien_115 <= 0.0) {
                edtSoTien_115.error = "Số tiền phải lớn hơn 0"
                edtSoTien_115.requestFocus()
                return@setOnClickListener
            }

            // Khởi tạo đối tượng khoản chi
            val khoanChi_115 = KhoanChi_115(
                maKhoanChi_115 = "KC001",
                tenKhoanChi_115 = ten_115,
                soTien_115 = soTien_115,
                ghiChu_115 = ghiChu_115,
                daThanhToan_115 = false
            )

            // Kiểm tra bằng phương thức của lớp KhoanChi_115
            if (!khoanChi_115.laSoTienHopLe_115()) {
                Toast.makeText(
                    this,
                    "Số tiền phải lớn hơn 0!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Toast.makeText(
                this,
                "Thêm khoản chi thành công!",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnXemChiTiet_115.setOnClickListener {
            val ten_115 = edtTenKhoanChi_115.text.toString().trim()
            val soTienStr_115 = edtSoTien_115.text.toString().trim()
            val ghiChu_115 = edtGhiChu_115.text.toString().trim()

            if (ten_115.isEmpty()) {
                edtTenKhoanChi_115.error = "Vui lòng nhập tên khoản chi"
                edtTenKhoanChi_115.requestFocus()
                return@setOnClickListener
            }

            val soTien_115 = soTienStr_115.toDoubleOrNull()

            if (soTien_115 == null || soTien_115 <= 0.0) {
                edtSoTien_115.error = "Số tiền phải là số hợp lệ và lớn hơn 0"
                edtSoTien_115.requestFocus()
                return@setOnClickListener
            }

            val khoanChi_115 = KhoanChi_115(
                maKhoanChi_115 = "KC001",
                tenKhoanChi_115 = ten_115,
                soTien_115 = soTien_115,
                ghiChu_115 = ghiChu_115,
                daThanhToan_115 = false
            )

            val intent_115 = Intent(
                this,
                ChiTietChiTieuActivity::class.java
            ).apply {
                putExtra("EXTRA_KHOAN_CHI", khoanChi_115)
                // Giữ dữ liệu riêng để tương thích với màn hình chi tiết cũ
                putExtra("EXTRA_TEN", ten_115)
                putExtra("EXTRA_SOTIEN", soTien_115)
                putExtra("EXTRA_GHICHU", ghiChu_115)
                putExtra("EXTRA_DA_THANH_TOAN", false)
            }

            startActivity(intent_115)
        }
    }

    }