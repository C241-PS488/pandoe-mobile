package com.pandoe.ui.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pandoe.R
import com.pandoe.databinding.ActivityStartBinding
import com.pandoe.ui.userpreference.UserPreferenceActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rbNewBusiness.setOnClickListener { onRadioButtonClicked(binding.rbNewBusiness) }
        binding.rbGrowthBusiness.setOnClickListener { onRadioButtonClicked(binding.rbGrowthBusiness) }
        binding.startButton.setOnClickListener { onStartButtonClicked() }
    }

    private fun onRadioButtonClicked(view: RadioButton) {
        binding.rbNewBusiness.isChecked = false
        binding.rbGrowthBusiness.isChecked = false

        view.isChecked = true
    }

    private fun onStartButtonClicked() {
        if (binding.rbNewBusiness.isChecked) {
            val intent = Intent(this, UserPreferenceActivity::class.java)
            startActivity(intent)
            finish()
        } else if (binding.rbGrowthBusiness.isChecked) {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.select_business_first), Toast.LENGTH_SHORT).show()
        }
    }
}