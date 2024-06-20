package com.pandoe.ui.idea

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pandoe.databinding.ItemIdeaBinding

class IdeaAdapter(private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<IdeaAdapter.IdeaViewHolder>() {

    private val ideaList = mutableListOf<String>()
    private var selectedPosition = -1

    fun submitList(list: List<String>) {
        ideaList.clear()
        ideaList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaViewHolder {
        val binding = ItemIdeaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IdeaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IdeaViewHolder, position: Int) {
        holder.bind(ideaList[position], position, selectedPosition, onItemClick) { newPosition ->
            selectedPosition = newPosition
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = ideaList.size

    class IdeaViewHolder(private val binding: ItemIdeaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(idea: String, position: Int, selectedPosition: Int, onItemClick: (String) -> Unit, onItemSelected: (Int) -> Unit) {
            binding.tvIdea.text = idea
            binding.rbIdea.isChecked = position == selectedPosition

            binding.root.setOnClickListener {
                onItemSelected(position)
                onItemClick(idea)
            }

            binding.rbIdea.setOnClickListener {
                onItemSelected(position)
                onItemClick(idea)
            }
        }
    }
}