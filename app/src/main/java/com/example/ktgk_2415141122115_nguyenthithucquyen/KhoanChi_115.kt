package com.example.ktgk_2415141122115_nguyenthithucquyen

import java.io.Serializable

class KhoanChi_115(
    var maKhoanChi_115: String,
    var tenKhoanChi_115: String,
    var soTien_115: Double,
    var ghiChu_115: String,
    var daThanhToan_115: Boolean
) : Serializable {

    fun layThongTin_115(): String {
        val trangThaiText_115 = if (daThanhToan_115) "Đã thanh toán" else "Chưa thanh toán"
        return "Mã: $maKhoanChi_115\nTên: $tenKhoanChi_115\nSố tiền: $soTien_115 VNĐ\nGhi chú: $ghiChu_115\nTrạng thái: $trangThaiText_115"
    }

    fun laSoTienHopLe_115(): Boolean {
        return soTien_115 > 0.0
    }
}