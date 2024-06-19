package com.pandoe.ui.register

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.pandoe.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            if (validateTextInput()) {
                // TODO: Do register
            }
        }

        binding.loginButton.setOnClickListener {
            finish()
        }
    }

    private fun validateTextInput(): Boolean {
        val name = binding.edRegisterName.text.toString().trim()
        val email = binding.edRegisterEmail.text.toString().trim()
        val password = binding.edRegisterPassword.text.toString().trim()
        val confirmPassword = binding.edRegisterConfirmPassword.text.toString().trim()

        var isValid = true

        if (TextUtils.isEmpty(name)) {
            binding.textInputLayoutName.error = "Nama tidak boleh kosong"
            isValid = false
        } else {
            binding.textInputLayoutName.error = null
        }

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

        if (TextUtils.isEmpty(confirmPassword)) {
            binding.textInputLayoutConfirmPassword.error = "Konfirmasi password tidak boleh kosong"
            isValid = false
        } else if (confirmPassword != password) {
            binding.textInputLayoutConfirmPassword.error = "Password tidak cocok"
            isValid = false
        } else {
            binding.textInputLayoutConfirmPassword.error = null
        }

        return isValid
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(Regex(emailPattern))
    }
}