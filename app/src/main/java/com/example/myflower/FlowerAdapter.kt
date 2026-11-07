package com.example.myflower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myflower.databinding.ItemFlowerBinding
import java.text.DecimalFormat

class FlowerAdapter(
    private val flowerList: MutableList<Flower>,
    private val onItemClick: (flower: Flower, position: Int) -> Unit,
    private val onImageClick: (flower: Flower) -> Unit
) : RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {

    private val priceFormatter = DecimalFormat("#,###")

    inner class FlowerViewHolder(val binding: ItemFlowerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flower: Flower, position: Int) {
            binding.imgFlower.setImageResource(flower.imageResId)
            binding.tvFlowerName.text = flower.name
            binding.tvShortDesc.text = flower.shortDesc
            binding.tvPrice.text = "Giá: ${priceFormatter.format(flower.price)} VND"
            binding.tvUnit.text = "Đơn vị: ${flower.unit}"
            binding.cbSelect.isChecked = flower.isSelected

            // Tránh kích hoạt listener ngoài ý muốn khi tái sử dụng view
            binding.cbSelect.setOnCheckedChangeListener(null)
            binding.cbSelect.isChecked = flower.isSelected
            binding.cbSelect.setOnCheckedChangeListener { _, isChecked ->
                flower.isSelected = isChecked
            }

            // Click vào ảnh: Mở màn hình chi tiết (Giao diện 02)
            binding.imgFlower.setOnClickListener {
                onImageClick(flower)
            }

            // Click vào cả thẻ mục: Mở hộp thoại chỉnh sửa (Câu 3)
            binding.cardFlower.setOnClickListener {
                onItemClick(flower, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val binding = ItemFlowerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FlowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.bind(flowerList[position], position)
    }

    override fun getItemCount(): Int = flowerList.size
}
