package com.example.studentprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofile.databinding.ActivityMainBinding
import com.example.studentprofile.model.Student
import com.example.studentprofile.utils.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Dữ liệu sinh viên ban đầu
    private var currentStudent = Student(
        id = "2415141122103",
        name = "Nguyen Thi Thuc Quyen",
        className = "24sk1",
        email = "2415141122115@ute.udn.vn",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị thông tin ban đầu lên màn hình
        bindStudentData(currentStudent)

        // Xử lý sự kiện bấm nút "CẬP NHẬT THÔNG TIN"
        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            // Kiểm tra tính hợp lệ của GPA (0.0 đến 4.0)
            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            // Cập nhật gpa
            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent)

            // Thông báo thành công
            toast("Cập nhật điểm thành công!")
        }
    }

    // Hàm cập nhật dữ liệu sinh viên lên các View
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa}\nGPA"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}