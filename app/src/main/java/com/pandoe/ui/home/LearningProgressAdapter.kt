package com.pandoe.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pandoe.data.model.LearningProgress
import com.pandoe.databinding.ItemLearningProgressBinding

class LearningProgressAdapter : RecyclerView.Adapter<LearningProgressAdapter.LearningProgressViewHolder>() {

    private val learningProgressList = mutableListOf<LearningProgress>()

    fun submitList(list: List<LearningProgress>) {
        learningProgressList.clear()
        learningProgressList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningProgressViewHolder {
        val binding = ItemLearningProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LearningProgressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LearningProgressViewHolder, position: Int) {
        holder.bind(learningProgressList[position])
    }

    override fun getItemCount(): Int = learningProgressList.size

    class LearningProgressViewHolder(private val binding: ItemLearningProgressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(learningProgress: LearningProgress) {
            binding.tvLearningStatus.text = learningProgress.status
            binding.tvLearningTitle.text = learningProgress.className
        }
    }
}