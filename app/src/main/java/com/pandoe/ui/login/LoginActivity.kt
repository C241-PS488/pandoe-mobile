package com.pandoe.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pandoe.data.model.User
import com.pandoe.data.result.Result
import com.pandoe.databinding.ActivityLoginBinding
import com.pandoe.ui.main.MainActivity
import com.pandoe.ui.register.RegisterActivity
import com.pandoe.ui.start.StartActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()

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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupViewModel() {
        val factory = LoginViewModelFactory.getInstance(this)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    private fun saveUserData(user: User) {
        loginViewModel.saveUser(user)
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            if (validateTextInput()) {
                val email = binding.edLoginEmail.text.toString()
                val password = binding.edLoginPassword.text.toString()
                loginViewModel.userLogin(email, password).observe(this) { result ->
                    when (result) {
                        is Result.Success -> {
                            showLoading(false)
                            val response = result.data
                            saveUserData(
                                User(
                                    response.data?.user?.id.toString(),
                                    response.data?.user?.name.toString(),
                                    response.data?.user?.email.toString(),
                                    response.data?.token.toString(),
                                    true
                                )
                            )
                            Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                            if (response.data?.user?.businesses?.size!! > 0) {
                                Intent(this, MainActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(this)
                                }
                                finishAffinity()
                            } else {
                                Intent(this, StartActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(this)
                                }
                                finishAffinity()
                            }
                        }
                        is Result.Loading -> showLoading(true)
                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}