package com.pandoe.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.pandoe.databinding.ActivityLoginBinding
import com.pandoe.ui.main.MainActivity
import com.pandoe.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            if (validateTextInput()) {
                // TODO: Do login
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateTextInput(): Boolean {
        val email = binding.edLoginEmail.text.toString().trim()
        val password = binding.edLoginPassword.text.toString().trim()

        var isValid = true

        if (TextUtils.isEmpty(email)) {
            binding.textInputLayoutEmail.error = "Email tidak boleh kosong"
            isValid = false
        } else if (!isValidEmail(email)) {
            binding.textInputLayoutEmail.error = "Email harus valid"
            isValid = false
        } else {
            binding.textInputLayoutEmail.error = null
        }

        if (TextUtils.isEmpty(password)) {
            binding.textInputLayoutPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 8) {
            binding.textInputLayoutPassword.error = "Password harus lebih dari 8 karakter"
            isValid = false
        } else {
            binding.textInputLayoutPassword.error = null
        }

        return isValid
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(Regex(emailPattern))
    }
}