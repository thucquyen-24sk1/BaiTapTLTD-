import java.util.Scanner

data class Student (
    val studentId: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val gpa: Double
)
{
    override fun toString(): String
    { return "ID: %-15s | Tên: %-20s | Tuổi: %-3d | Ngành học: %-15s | GPA: %.2f".format(
        studentId, fullName, age, major, gpa) }
}

class StudentManager {
    private val studentList = mutableListOf<Student>()
    init {
        studentList.add(Student("SV2415", "Nguyễn Thị Thục Quyên", 20 , "SPCN",7.94))
        studentList.add(Student("SV2411", "Trần Văn Anh", 20, "CNTT", 4.5))
        studentList.add(Student("SV2315", "Trà Như Quỳnh", 21, "SPCN", 8.5))
        studentList.add(Student("SV2202", "Phạm Minh Châu", 22, "SPCN", 8.7))
        studentList.add(Student("SV2301", "Võ Tấn Phát", 21, "CNTT", 8.2))
    }
    fun addStudent(scanner: Scanner) {
        print("Nhập Student ID: ")
        val id = scanner.nextLine().trim()
        print("Nhập Fullname: ")
        val name = scanner.nextLine().trim()
        print("Nhập Age:")
        val age = scanner.nextLine().toIntOrNull() ?: 0
        print("Nhập Major: ")
        val major = scanner.nextLine().trim()

        // Vòng lặp Validation
        val gpa: Double
        while(true){
            print("Nhập điểm GPA (0.0 -10.0): ")
            val inputGPA = scanner.nextLine().toDoubleOrNull()
            if(inputGPA != null && inputGPA in 0.0..10.0){
                gpa = inputGPA
                break
            } else { println("Lỗi: Điểm GPA không hợp lệ! Vui lòng nhập số trong khoảng 0.0 đến 10.0")}
        }
        val student  = Student(id, name, age, major, gpa)
        studentList.add(student)
        println("Thêm sinh viên thành công!")
    }

    fun displayAll() {
        if (studentList.isEmpty()) {
            println("Danh sách sinh viên trống.")
            return
        }
        println("------DANH SÁCH SINH VIÊN------")
        studentList.forEach {
            println(it)
        }
    }

    fun searchStudent(scanner: Scanner) {
        print("Nhập ID hoặc tên sinh viên cần tìm: ")
        val keyword = scanner.nextLine().trim().lowercase()
        val results = studentList.filter {
            it.studentId.lowercase().contains(keyword) || it.fullName.lowercase().contains(keyword)
        }
        printResults(results)
    }

    fun calculateAverageGpa() {
        if (studentList.isEmpty()) {
            println("Danh sách trống.")
            return
        }
        val avg = studentList.map { it.gpa }.average()
        println("GPA trung bình của tất cả sinh viên: %.2f".format(avg))
    }

    fun findHighestGpa() {
        val maxStudent = studentList.maxByOrNull { it.gpa }
        if (maxStudent != null) {
            println("Sinh viên có GPA cao nhất: ")
            println(maxStudent)
        } else
        { println("Danh sách trống.") }
    }

    fun removeStudent(scanner: Scanner) {
        print("Nhập Student ID cần xoá: ")
        val id = scanner.nextLine().trim()
        val removed = studentList.removeIf { it.studentId.equalsIgnoreCase(id) }
        if (removed) {
            println("Đã xoá sinh viên có ID: $id")
        } else { println("Không tìm thấy sinh viên có ID: $id")}
    }

    fun countHighGpa() {
        val count = studentList.count { it.gpa >= 8.0 }
        println("Số sinh viên có GPA >= 8.0 là: $count")
    }

    fun countLowGpa() {
        val count = studentList.count { it.gpa < 5.0 }
        println("Số sinh viên coa GPA < 5.0 là: $count")
    }

    fun avgGpaByMajor(scanner: Scanner) {
        print("Nhập ngành cần tính GPA trung bình: ")
        val majorInput = scanner.nextLine().trim()
        val filtered = studentList.filter { it.major.equalsIgnoreCase(majorInput)}
        if (filtered.isNotEmpty()) {
            val avg = filtered.map { it.gpa }.average()
            println("GPA trung bình ngành $majorInput: %.2f".format(avg))
        } else { println("Không tìm thấy sinh viên thuộc ngành $majorInput.") }
    }

    fun findOldestStudent() {
        val oldest = studentList.maxByOrNull { it.age }
        if (oldest != null ) {
            println("Sinh viên lớn tuổi nhất: ")
            println(oldest)
        } else { println("Danh sách trống.") }
    }

    fun findGpaInRange() {
        val results = studentList.filter { it.gpa in 7.0 .. 8.5}
        println("Sinh viên có GPA từ 7.0 đến 8.5 là: ")
        printResults(results)
    }

    fun findByMajor(scanner: Scanner) {
        print("Nhập ngành cần tìm: ")
        val majorInput = scanner.nextLine().trim()
        val results = studentList.filter { it.major.equalsIgnoreCase(majorInput)}
        println("Các sinh viên thuộc ngành $majorInput : ")
        printResults(results)
    }

    fun findByPartialName(scanner: Scanner) {
        print("Nhập từ khoá tên cần tìm: ")
        val keyword = scanner.nextLine().trim().lowercase()
        val results = studentList.filter { it.fullName.lowercase().contains(keyword)}
        println("Kết quả tìm kiếm theo tên: ")
        printResults(results)
    }

    fun sortByGpaDescending() {
        val sortedList = studentList.sortedByDescending { it.gpa }
        println("Danh sách sắp xếp theo GPA giảm dần:")
        printResults(sortedList)
    }

    fun top3Gpa() {
        val top3 = studentList.sortedByDescending { it.gpa }.take(3)
        println("Top 3 sinh viên có GPA cao nhất:")
        printResults(top3)
    }

    fun sortByAge() {
        val sortedList = studentList.sortedBy { it.age }
        println("Danh sách sắp xếp theo tuổi (tăng dần): ")
        printResults(sortedList)
    }

    fun sortByName() {
        println("\n--- DANH SÁCH SẮP XẾP THEO TÊN (A-Z) ---")
        val sortedList = studentList.sortedBy { student ->
            student.fullName.trim().split("\\s+".toRegex()).last().lowercase()
        }
        sortedList.forEach { println(it) }
    }
    fun findById(scanner: Scanner) {
        print("Nhập mã sinh viên cần tìm: ")
        val id = scanner.nextLine().trim()
        val result = studentList.find {it.studentId.equals(id, true)}
        if (result != null) {
            println("Tìm thây: $result")
        }else {
            println("Không tìm thấy sinh viên có mã: $id")
        }
    }
    fun filterScholarship() {
        println("\n--- DANH SÁCH SINH VIÊN NHẬN HỌC BỔNG (GPA > 9.0) ---")
        val scholarshipList = studentList.filter { it.gpa > 9.0 }

        if (scholarshipList.isEmpty()) {
            println("Không có sinh viên nào đủ điều kiện nhận học bổng.")
        } else {
            scholarshipList.forEach { println(it) }
        }
    }

    private fun printResults(list: List < Student >) {
        if (list.isEmpty()) {
            println("Không tìm thấy kết quả phù hợp.")
        } else {
            list.forEach{println(it)}
        }
    }
}
fun String.equalsIgnoreCase(other: String): Boolean = this.equals(other, ignoreCase = true)

fun main() {
    val scanner = Scanner(System.`in`)
    val manager = StudentManager()

    while(true) {
        println("================== STUDENT MANAGER =================")
        println("0. Exit")
        println("1. Add student.")
        println("2. Display all students.")
        println("3. Search student.")
        println("4. Calculate average GPA.")
        println("5. Find student with highest GPA.")
        println("6. Remove student.")
        println("-------------- CÁC CHỨC NĂNG ---------------")
        println("7. Đếm số sinh viên GPA >= 8.0.")
        println("8. Đếm số sinh viên GPA < 5.0.")
        println("9. Tính GPA trung bình của sinh viên ngành được giao.")
        println("10. Tìm sinh viên có GPA cao nhất:")
        println("11. Tìm sinh viên lớn tuổi nhất: ")
        println("12. Tìm sinh viên có GPA nằm trong khoảng 7.0 -> 8.5:")
        println("13. Tìm tất cả sinh viên thuộc một ngành:")
        println("14. Tìm sinh viên theo một phần tên:")
        println("15. Sắp xếp sinh viên theo GPA giảm dần.")
        println("16. Hiển thị 3 sinh vỉên có GPA cao nhất:")
        println("17. Sắp xếp sinh viên theo tuổi.")
        println("18. Sắp xếp sinh viên theo tên ABC.")
        println("19. Tìm theo mã sinh viên: ")
        println("20. Lọc ra danh sách nhận học bổng (GPA > 9)")
        println("Chọn: ")

        val choice = scanner.nextLine().trim()
        when(choice){
            "1" -> manager.addStudent(scanner)
            "2" -> manager.displayAll()
            "3" -> manager.searchStudent(scanner)
            "4" -> manager.calculateAverageGpa()
            "5" -> manager.findHighestGpa()
            "6" -> manager.removeStudent(scanner)
            "7" -> manager.countHighGpa()
            "8" -> manager.countLowGpa()
            "9" -> manager.avgGpaByMajor(scanner)
            "10"-> manager.findHighestGpa()
            "11"-> manager.findOldestStudent()
            "12"-> manager.findGpaInRange()
            "13"-> manager.findByMajor(scanner)
            "14"-> manager.findByPartialName(scanner)
            "15"-> manager.sortByGpaDescending()
            "16"-> manager.top3Gpa()
            "17"-> manager.sortByAge()
            "18"-> manager.sortByName()
            "19"-> manager.findById(scanner)
            "20"-> manager.filterScholarship()
            "0" -> {
                println("Đã thoát chương trình.")
                break
            }
            else -> println("Lựa chọn không hợp lệ, vui lòng chọn lại! ")
        }
    }
}