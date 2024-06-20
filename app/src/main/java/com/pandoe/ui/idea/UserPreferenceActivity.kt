package com.pandoe.ui.idea

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.pandoe.R
import com.pandoe.databinding.ActivityUserPreferenceBinding

class UserPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserPreferenceBinding

    private val hobbySelection = mutableListOf<String>()
    private val experinceSelection = mutableListOf<String>()
    private val durationSelection = mutableListOf<String>()
    private val assetSelection = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addChipGroupSection("Hobi", listOf("Reading", "Traveling", "Cooking", "Hiking", "Gaming", "Swimming", "Photography", "Writing", "Painting", "Gardening"), hobbySelection)
        addChipGroupSection("Pengalaman", listOf("Internship", "Full-time Job", "Freelance", "Part-time Job", "Volunteer", "Research Assistant", "Teaching Assistant", "Consultant"), experinceSelection)
        addChipGroupSection("Lama Pengalaman", listOf("1-2th", "3-5th", "6-10th", ">10th"), durationSelection)
        addChipGroupSection("Asset", listOf("Rendah", "Menengah", "Tinggi"), assetSelection)

        binding.generateRecommendationButton.setOnClickListener {
            if (validateSelections()) {
                val intent = Intent(this, GenerateIdeaActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Harap lengkapi semua pilihan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addChipGroupSection(title: String, options: List<String>, selectionList: MutableList<String>) {
        val context = this
        val inflater = LayoutInflater.from(context)

        // Inflate the section title layout
        val titleView = inflater.inflate(R.layout.item_chip_section_title, binding.chipGroupContainer, false) as TextView
        titleView.text = title

        // Ensure unique IDs for the views
        titleView.id = View.generateViewId()

        // Inflate the ChipGroup layout
        val chipGroup = inflater.inflate(R.layout.item_chip, binding.chipGroupContainer, false) as ChipGroup

        // Ensure unique IDs for the ChipGroup
        chipGroup.id = View.generateViewId()

        // Add Chips to the ChipGroup
        options.forEach { option ->
            val chip = Chip(context).apply {
                text = option
                isCheckable = true
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        selectionList.add(option)
                    } else {
                        selectionList.remove(option)
                    }
                }
            }
            chipGroup.addView(chip)
        }

        // Add views to the container
        binding.chipGroupContainer.addView(titleView)
        binding.chipGroupContainer.addView(chipGroup)

        // Setup constraints for the new section
        val set = ConstraintSet()
        set.clone(binding.chipGroupContainer)

        // Add constraints for TextView
        set.connect(titleView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 24)
        set.connect(titleView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)

        // Add constraints for ChipGroup
        set.connect(chipGroup.id, ConstraintSet.TOP, titleView.id, ConstraintSet.BOTTOM)
        set.connect(chipGroup.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(chipGroup.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        // If there are previous sections, adjust constraints accordingly
        if (binding.chipGroupContainer.childCount > 2) {
            val lastView = binding.chipGroupContainer.getChildAt(binding.chipGroupContainer.childCount - 3)
            set.connect(titleView.id, ConstraintSet.TOP, lastView.id, ConstraintSet.BOTTOM)
        }

        set.applyTo(binding.chipGroupContainer)
    }

    private fun validateSelections(): Boolean {
        return hobbySelection.isNotEmpty() && experinceSelection.isNotEmpty() && durationSelection.isNotEmpty() && assetSelection.isNotEmpty()
    }
}