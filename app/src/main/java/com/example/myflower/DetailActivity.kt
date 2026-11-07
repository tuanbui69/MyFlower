package com.example.myflower

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myflower.databinding.ActivityDetailBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.detailMain) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Nhận dữ liệu truyền từ Giao diện 01
        val flower = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_FLOWER, Flower::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_FLOWER) as? Flower
        }

        flower?.let { item ->
            binding.imgDetailFlower.setImageResource(item.imageResId)
            binding.tvDetailName.text = item.name
            binding.tvDetailDesc.text = item.detailDesc
            val formatter = DecimalFormat("#,###")
            binding.tvDetailPrice.text = "Giá: ${formatter.format(item.price)} VND"
            binding.tvDetailUnit.text = "Đơn vị: ${item.unit}"
        }

        // Xử lý nút Quay lại (Câu 1)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val EXTRA_FLOWER = "extra_flower"
    }
}
