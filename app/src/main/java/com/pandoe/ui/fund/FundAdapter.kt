package com.pandoe.ui.fund

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandoe.data.model.Fund
import com.pandoe.databinding.ItemFundBinding

class FundAdapter : RecyclerView.Adapter<FundAdapter.FundViewHolder>() {

    private val fundList = mutableListOf<Fund>()

    fun submitList(funds: List<Fund>) {
        fundList.clear()
        fundList.addAll(funds)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundViewHolder {
        val binding = ItemFundBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FundViewHolder, position: Int) {
        holder.bind(fundList[position])
    }

    override fun getItemCount(): Int = fundList.size

    inner class FundViewHolder(private val binding: ItemFundBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fund: Fund) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(fund.thumbnail)
                    .into(ivItemImage)
                tvItemTitle.text = fund.title
                tvItemDescription.text = fund.executiveSummary
            }
        }
    }
}