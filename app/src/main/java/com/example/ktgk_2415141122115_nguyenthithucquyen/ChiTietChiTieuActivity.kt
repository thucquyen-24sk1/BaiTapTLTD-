
package com.example.ktgk_2415141122115_nguyenthithucquyen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var txtThongTinChiTiet_115: TextView
    private lateinit var btnQuayLai_115: Button
    private lateinit var btnSua_115: Button

    private var khoanChiHienTai_115: KhoanChi_115? = null

    // Nhận kết quả trả về từ màn hình chỉnh sửa
    private val suaKhoanChiLauncher_115 =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { ketQua_115 ->

            if (ketQua_115.resultCode == Activity.RESULT_OK) {

                val intentKetQua_115 = ketQua_115.data

                val khoanChiMoi_115 =
                    intentKetQua_115?.getSerializableExtra(
                        "EXTRA_KHOAN_CHI"
                    ) as? KhoanChi_115

                if (khoanChiMoi_115 != null) {
                    khoanChiHienTai_115 = khoanChiMoi_115
                    hienThiThongTin_115()

                    Toast.makeText(
                        this,
                        "Cập nhật khoản chi thành công!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            // Nếu hủy, không thay đổi dữ liệu hiện tại
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        supportActionBar?.title =
            "Nguyễn Thị Thục Quyên - 2415141122115 - Chi tiết chi tiêu"

        txtThongTinChiTiet_115 =
            findViewById(R.id.txtThongTinChiTiet_115)

        btnQuayLai_115 =
            findViewById(R.id.btnQuayLai_115)

        btnSua_115 =
            findViewById(R.id.btnSua_115)

        // Nhận dữ liệu từ MainActivity
        val ten_115 = intent.getStringExtra("EXTRA_TEN")
        val soTien_115 = intent.getDoubleExtra("EXTRA_SOTIEN", -1.0)
        val ghiChu_115 = intent.getStringExtra("EXTRA_GHICHU") ?: ""

        // Kiểm tra dữ liệu
        if (ten_115.isNullOrBlank() ||
            !soTien_115.isFinite() ||
            soTien_115 <= 0.0
        ) {
            txtThongTinChiTiet_115.text =
                "Lỗi: Tên khoản chi hoặc số tiền không hợp lệ!"

            btnSua_115.isEnabled = false
        } else {
            // Tạo đối tượng khoản chi từ dữ liệu nhận được
            khoanChiHienTai_115 = KhoanChi_115(
                maKhoanChi_115 = "MC01",
                tenKhoanChi_115 = ten_115,
                soTien_115 = soTien_115,
                ghiChu_115 = ghiChu_115,
                daThanhToan_115 = false
            )

            hienThiThongTin_115()
        }

        // SỰ KIỆN NÚT CHỈNH SỬA
        btnSua_115.setOnClickListener {
            val khoanChi_115 = khoanChiHienTai_115

            if (khoanChi_115 != null) {
                val intentSua_115 = Intent(
                    this,
                    ChinhSuaChiTieuActivity::class.java
                )

                intentSua_115.putExtra(
                    "EXTRA_KHOAN_CHI",
                    khoanChi_115
                )

                suaKhoanChiLauncher_115.launch(intentSua_115)
            }
        }

        // Nút quay lại màn hình chính
        btnQuayLai_115.setOnClickListener {
            finish()
        }
    }

    // Hiển thị thông tin khoản chi
    private fun hienThiThongTin_115() {
        val khoanChi_115 = khoanChiHienTai_115 ?: return

        txtThongTinChiTiet_115.text =
            khoanChi_115.layThongTin_115()
    }
}
