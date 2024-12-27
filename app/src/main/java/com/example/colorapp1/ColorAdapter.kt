package com.example.colorapp1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.colorapp1.databinding.ItemColorCardBinding



class ColorAdapter(private val colorList: List<ColorCard>) :
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    // ViewHolder class
    inner class ColorViewHolder(private val binding: ItemColorCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(colorCard: ColorCard) {
            binding.tvColorCode.text = colorCard.colorCode
            binding.tvCreatedDate.text = colorCard.createdDate
            binding.cardView.setCardBackgroundColor(Color.parseColor(colorCard.colorCode))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colorList[position])
    }

    override fun getItemCount(): Int = colorList.size
}
