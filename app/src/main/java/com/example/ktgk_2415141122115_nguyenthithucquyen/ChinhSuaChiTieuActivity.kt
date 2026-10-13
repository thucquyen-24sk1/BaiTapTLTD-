package com.example.ktgk_2415141122115_nguyenthithucquyen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    private lateinit var edtEditTen_115: EditText
    private lateinit var edtEditSoTien_115: EditText
    private lateinit var edtEditGhiChu_115: EditText
    private lateinit var btnLuu_115: Button
    private lateinit var btnHuy_115: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)

        supportActionBar?.title = "Nguyễn Thị Thục Quyên - 2415141122115 - Chỉnh sửa"

        edtEditTen_115 = findViewById(R.id.edtEditTen_115)
        edtEditSoTien_115 = findViewById(R.id.edtEditSoTien_115)
        edtEditGhiChu_115 = findViewById(R.id.edtEditGhiChu_115)
        btnLuu_115 = findViewById(R.id.btnLuu_115)
        btnHuy_115 = findViewById(R.id.btnHuy_115)

        // Nhận đối tượng KhoanChi_115 cũ từ màn hình chi tiết
        val khoanChiCu_115 = intent.getSerializableExtra("EXTRA_KHOAN_CHI") as? KhoanChi_115

        if (khoanChiCu_115 != null) {
            edtEditTen_115.setText(khoanChiCu_115.tenKhoanChi_115)
            edtEditSoTien_115.setText(khoanChiCu_115.soTien_115.toString())
            edtEditGhiChu_115.setText(khoanChiCu_115.ghiChu_115)
        }

        // Xử lý nút Lưu thay đổi
        btnLuu_115.setOnClickListener {
            val tenMoi_115 = edtEditTen_115.text.toString().trim()
            val soTienStr_115 = edtEditSoTien_115.text.toString().trim()
            val ghiChuMoi_115 = edtEditGhiChu_115.text.toString().trim()

            if (tenMoi_115.isEmpty()) {
                Toast.makeText(this, "Tên khoản chi không được để trống!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val soTienMoi_115 = soTienStr_115.toDoubleOrNull()
            if (soTienMoi_115 == null || soTienMoi_115 <= 0.0) {
                Toast.makeText(this, "Số tiền phải lớn hơn 0 và hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tạo đối tượng KhoanChi_115 mới truyền đủ 5 tham số
            val khoanChiMoi_115 = KhoanChi_115(
                maKhoanChi_115 = khoanChiCu_115?.maKhoanChi_115 ?: "MC01",
                tenKhoanChi_115 = tenMoi_115,
                soTien_115 = soTienMoi_115,
                ghiChu_115 = ghiChuMoi_115,
                daThanhToan_115 = khoanChiCu_115?.daThanhToan_115 ?: false
            )

            // Trả đối tượng mới về thông qua Intent kết quả
            val resultIntent_115 = Intent().apply {
                putExtra("EXTRA_KHOAN_CHI", khoanChiMoi_115)
            }
            setResult(Activity.RESULT_OK, resultIntent_115)
            finish()
        }

        // Xử lý nút Hủy
        btnHuy_115.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}