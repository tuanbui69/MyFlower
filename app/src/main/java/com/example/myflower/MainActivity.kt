package com.example.myflower

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myflower.databinding.ActivityMainBinding
import com.example.myflower.databinding.DialogEditFlowerBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var flowerAdapter: FlowerAdapter
    private val flowerList = mutableListOf<Flower>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initData()
        setupRecyclerView()
    }

    // Khởi tạo danh sách hoa
    private fun initData() {
        flowerList.clear()
        flowerList.add(
            Flower(
                id = 1,
                name = "Hoa hướng dương",
                shortDesc = "Mô tả ngắn về hoa",
                detailDesc = "Đặc điểm: Hoa hướng dương là loài hoa nổi bật với màu vàng tươi sáng, luôn hướng về phía mặt trời. Chúng có thân cao và lá lớn, thường được trồng để trang trí trong các vườn hoa hoặc làm nguyên liệu trong các sản phẩm thực phẩm. Hoa hướng dương tượng trưng cho sự kiên cường, tươi mới và năng lượng tích cực.",
                price = 100000.0,
                unit = "bó",
                imageResId = R.drawable.img_sunflower
            )
        )
    }

    private fun setupRecyclerView() {
        flowerAdapter = FlowerAdapter(
            flowerList = flowerList,
            onItemClick = { flower, position ->
                // Câu 3: Nhấn vào mục hiển thị hộp thoại chỉnh sửa
                showEditDialog(flower, position)
            },
            onImageClick = { flower ->
                // Câu 1: Chuyển sang Giao diện 02 xem chi tiết
                openDetailActivity(flower)
            }
        )

        binding.rvFlowers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = flowerAdapter
        }
    }

    // Câu 1: Mở Giao diện 02 mô tả chi tiết hoa
    private fun openDetailActivity(flower: Flower) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_FLOWER, flower)
        }
        startActivity(intent)
    }

    // Câu 3: Hộp thoại chỉnh sửa thông tin hoa (6 điểm)
    private fun showEditDialog(flower: Flower, position: Int) {
        val dialogBinding = DialogEditFlowerBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        // Hiển thị dữ liệu hiện tại lên các ô nhập
        dialogBinding.edtFlowerName.setText(flower.name)
        dialogBinding.edtFlowerPrice.setText(String.format(Locale.US, "%.0f", flower.price))
        dialogBinding.edtFlowerDesc.setText(flower.shortDesc)

        // Nút xem chi tiết (Giao diện 02)
        dialogBinding.btnViewDetail.setOnClickListener {
            dialog.dismiss()
            openDetailActivity(flower)
        }

        // Nút Hủy
        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        // Nút Lưu
        dialogBinding.btnSave.setOnClickListener {
            val inputName = dialogBinding.edtFlowerName.text.toString().trim()
            val inputPriceStr = dialogBinding.edtFlowerPrice.text.toString().trim()
            val inputDesc = dialogBinding.edtFlowerDesc.text.toString().trim()

            // 1. Kiểm tra rỗng cho Tên
            if (inputName.isEmpty()) {
                dialogBinding.edtFlowerName.error = "Vui lòng nhập tên hoa!"
                dialogBinding.edtFlowerName.requestFocus()
                return@setOnClickListener
            }

            // 2. Kiểm tra Giá (Câu 3 - 2 điểm: đảm bảo giá là kiểu số)
            if (inputPriceStr.isEmpty()) {
                dialogBinding.edtFlowerPrice.error = "Vui lòng nhập giá hoa!"
                dialogBinding.edtFlowerPrice.requestFocus()
                return@setOnClickListener
            }

            val inputPrice = inputPriceStr.toDoubleOrNull()
            if (inputPrice == null || inputPrice <= 0) {
                dialogBinding.edtFlowerPrice.error = "Giá hoa phải là kiểu số hợp lệ và lớn hơn 0!"
                dialogBinding.edtFlowerPrice.requestFocus()
                return@setOnClickListener
            }

            // 3. Kiểm tra Mô tả
            if (inputDesc.isEmpty()) {
                dialogBinding.edtFlowerDesc.error = "Vui lòng nhập mô tả hoa!"
                dialogBinding.edtFlowerDesc.requestFocus()
                return@setOnClickListener
            }

            // Câu 3 (2 điểm): Lưu những thông tin người dùng đã nhập vào thành phần đã chọn trên Giao diện 01
            flower.name = inputName
            flower.price = inputPrice
            flower.shortDesc = inputDesc
            // Cập nhật lại mô tả chi tiết nếu cần đồng bộ
            flower.detailDesc = "Đặc điểm: $inputName. $inputDesc"

            // Cập nhật lại giao diện RecyclerView tại vị trí position
            flowerAdapter.notifyItemChanged(position)

            Toast.makeText(this, "Đã cập nhật \"$inputName\" thành công!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }
}
