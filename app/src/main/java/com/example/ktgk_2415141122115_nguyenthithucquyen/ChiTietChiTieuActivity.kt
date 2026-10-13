
package com.example.ktgk_2415141122115_nguyenthithucquyen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var txtThongTinChiTiet_115: TextView
    private lateinit var btnQuayLai_115: Button
    private lateinit var btnSua_115: Button

    private var tenKhoanChi_115 = ""
    private var soTien_115 = 0.0
    private var ghiChu_115 = ""
    private var daThanhToan_115 = false

    private val suaKhoanChiLauncher_115 =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {
                val data_115 = result.data

                if (data_115 != null) {
                    tenKhoanChi_115 =
                        data_115.getStringExtra("EXTRA_TEN_MOI")
                            ?: tenKhoanChi_115

                    soTien_115 =
                        data_115.getDoubleExtra(
                            "EXTRA_SOTIEN_MOI",
                            soTien_115
                        )

                    ghiChu_115 =
                        data_115.getStringExtra("EXTRA_GHICHU_MOI")
                            ?: ghiChu_115

                    daThanhToan_115 = data_115.getBooleanExtra(
                        "EXTRA_DA_THANH_TOAN_MOI",
                        daThanhToan_115
                    )

                    // Cập nhật thông tin mới sau khi chỉnh sửa
                    hienThiThongTin_115()
                }
            }
            // Nếu hủy chỉnh sửa, giữ nguyên dữ liệu cũ
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
        tenKhoanChi_115 =
            intent.getStringExtra("EXTRA_TEN") ?: ""

        soTien_115 =
            intent.getDoubleExtra("EXTRA_SOTIEN", -1.0)

        ghiChu_115 =
            intent.getStringExtra("EXTRA_GHICHU") ?: ""

        daThanhToan_115 =
            intent.getBooleanExtra("EXTRA_DA_THANH_TOAN", false)

        hienThiThongTin_115()

        // Mở màn hình chỉnh sửa

        btnSua_115.setOnClickListener {
            val intentSua_115 = Intent(
                this,
                ChinhSuaChiTieuActivity::class.java
            ).apply {
                putExtra("EXTRA_TEN", tenKhoanChi_115)
                putExtra("EXTRA_SOTIEN", soTien_115)
                putExtra("EXTRA_GHICHU", ghiChu_115)
                putExtra("EXTRA_DA_THANH_TOAN", daThanhToan_115)
            }

            suaKhoanChiLauncher_115.launch(intentSua_115)
        }


        // Quay lại màn hình chính
        btnQuayLai_115.setOnClickListener {
            finish()
        }
    }



    private fun hienThiThongTin_115() {
        val trangThai_115 = if (daThanhToan_115) {
            "Đã thanh toán"
        } else {
            "Chưa thanh toán"
        }

        txtThongTinChiTiet_115.text =
            if (tenKhoanChi_115.isBlank() || soTien_115 <= 0.0) {
                "Lỗi: Tên khoản chi bị trống hoặc số tiền không hợp lệ!"
            } else {
                """
            Tên khoản chi: $tenKhoanChi_115
            Số tiền: $soTien_115 VNĐ
            Ghi chú: ${ghiChu_115.ifBlank { "Không có" }}
            Trạng thái: $trangThai_115
            """.trimIndent()
            }
    }


}
