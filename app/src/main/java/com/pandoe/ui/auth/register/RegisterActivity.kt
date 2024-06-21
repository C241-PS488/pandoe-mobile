package com.pandoe.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pandoe.data.result.Result
import com.pandoe.databinding.ActivityRegisterBinding
import com.pandoe.ui.auth.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()

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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupViewModel() {
        val factory = RegisterViewModelFactory.getInstance(this)
        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]
    }

    private fun setupAction() {
        binding.registerButton.setOnClickListener {
            if (validateTextInput()) {
                val name = binding.edRegisterName.text.toString()
                val email = binding.edRegisterEmail.text.toString()
                val password = binding.edRegisterPassword.text.toString()
                registerViewModel.userRegister(name, email, password).observe(this) { result ->
                    when (result) {
                        is Result.Success -> {
                            showLoading(false)
                            Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                            Intent(this, LoginActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(this)
                            }
                        }
                        is Result.Loading -> showLoading(true)
                        is Result.Error -> {
                            showLoading(false)
                            if (result.error.contains("Email is already registered. Please use a different email.", ignoreCase = true)) {
                                Toast.makeText(this, "Email telah terdaftar", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}