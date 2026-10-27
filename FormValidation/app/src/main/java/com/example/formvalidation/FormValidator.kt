package com.example.formvalidation

import android.util.Patterns

object FormValidator {

    fun kiemTraHoTen(hoTen: String): Boolean {
        return hoTen.trim().length >= 2
    }

    fun kiemTraEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS
            .matcher(email.trim())
            .matches()
    }

    fun kiemTraMatKhau(matKhau: String): Boolean {
        return matKhau.length >= 6
    }

    fun kiemTraSoDienThoai(soDienThoai: String): Boolean {
        return soDienThoai.matches(Regex("^0[0-9]{9}$"))
    }
}