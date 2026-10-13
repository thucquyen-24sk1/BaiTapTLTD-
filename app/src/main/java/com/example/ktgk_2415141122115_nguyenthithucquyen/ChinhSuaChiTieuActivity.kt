
package com.example.ktgk_2415141122115_nguyenthithucquyen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    private lateinit var edtEditTen_115: EditText
    private lateinit var edtEditSoTien_115: EditText
    private lateinit var edtEditGhiChu_115: EditText
    private lateinit var chkThanhToan_115: CheckBox
    private lateinit var btnLuu_115: Button
    private lateinit var btnHuy_115: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)

        supportActionBar?.title =
            "Nguyễn Thị Thục Quyên - 2415141122115 - Chỉnh sửa"

        edtEditTen_115 = findViewById(R.id.edtEditTen_115)
        edtEditSoTien_115 = findViewById(R.id.edtEditSoTien_115)
        edtEditGhiChu_115 = findViewById(R.id.edtEditGhiChu_115)
        chkThanhToan_115 = findViewById(R.id.chkThanhToan_115)
        btnLuu_115 = findViewById(R.id.btnLuu_115)
        btnHuy_115 = findViewById(R.id.btnHuy_115)

        // Nhận dữ liệu cũ
        val tenCu_115 = intent.getStringExtra("EXTRA_TEN") ?: ""
        val soTienCu_115 = intent.getDoubleExtra("EXTRA_SOTIEN", 0.0)
        val ghiChuCu_115 = intent.getStringExtra("EXTRA_GHICHU") ?: ""
        val daThanhToanCu_115 =
            intent.getBooleanExtra("EXTRA_DA_THANH_TOAN", false)

        // Hiển thị dữ liệu cũ
        edtEditTen_115.setText(tenCu_115)
        edtEditSoTien_115.setText(soTienCu_115.toString())
        edtEditGhiChu_115.setText(ghiChuCu_115)
        chkThanhToan_115.isChecked = daThanhToanCu_115

        // Lưu thay đổi
        btnLuu_115.setOnClickListener {
            val tenMoi_115 = edtEditTen_115.text.toString().trim()
            val soTienStr_115 = edtEditSoTien_115.text.toString().trim()
            val ghiChuMoi_115 = edtEditGhiChu_115.text.toString().trim()

            if (tenMoi_115.isEmpty()) {
                edtEditTen_115.error = "Tên khoản chi không được để trống"
                edtEditTen_115.requestFocus()
                return@setOnClickListener
            }

            val soTienMoi_115 = soTienStr_115.toDoubleOrNull()

            if (soTienMoi_115 == null || !soTienMoi_115.isFinite()
                || soTienMoi_115 <= 0.0
            ) {
                edtEditSoTien_115.error =
                    "Số tiền phải là số hợp lệ và lớn hơn 0"
                edtEditSoTien_115.requestFocus()
                return@setOnClickListener
            }

            val resultIntent_115 = Intent().apply {
                putExtra("EXTRA_TEN_MOI", tenMoi_115)
                putExtra("EXTRA_SOTIEN_MOI", soTienMoi_115)
                putExtra("EXTRA_GHICHU_MOI", ghiChuMoi_115)
                putExtra(
                    "EXTRA_DA_THANH_TOAN_MOI",
                    chkThanhToan_115.isChecked
                )
            }

            setResult(Activity.RESULT_OK, resultIntent_115)
            finish()
        }

        // Hủy: không trả dữ liệu chỉnh sửa
        btnHuy_115.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
