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

        // Nhận dữ liệu cũ từ Intent của màn hình chi tiết
        val tenCu_115 = intent.getStringExtra("EXTRA_TEN")
        val soTienCu_115 = intent.getDoubleExtra("EXTRA_SOTIEN", 0.0)
        val ghiChuCu_115 = intent.getStringExtra("EXTRA_GHICHU")

        // Hiển thị dữ liệu cũ lên các ô nhập
        edtEditTen_115.setText(tenCu_115)
        edtEditSoTien_115.setText(soTienCu_115.toString())
        edtEditGhiChu_115.setText(ghiChuCu_115)

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
                Toast.makeText(this, "Số tiền phải là số hợp lệ và lớn hơn 0!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Đóng gói dữ liệu mới trả về bằng Intent Result
            val resultIntent_115 = Intent().apply {
                putExtra("EXTRA_TEN_MOI", tenMoi_115)
                putExtra("EXTRA_SOTIEN_MOI", soTienMoi_115)
                putExtra("EXTRA_GHICHU_MOI", ghiChuMoi_115)
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