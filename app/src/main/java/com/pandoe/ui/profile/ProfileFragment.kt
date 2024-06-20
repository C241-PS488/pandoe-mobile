package com.pandoe.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.pandoe.databinding.FragmentProfileBinding
import com.pandoe.ui.login.LoginActivity
import com.pandoe.utils.generateProfilePictureUrl

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setUserData()

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun setupViewModel() {
        val factory = ProfileViewModelFactory.getInstance(requireActivity())
        profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
    }

    private fun setUserData() {
        profileViewModel.getUser().observe(viewLifecycleOwner) { user ->
            Glide.with(this)
                .load(generateProfilePictureUrl(user.name))
                .into(binding.ivProfilePicture)
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
        }
    }

    private fun logout() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Logout")
            setMessage("Apakah Kamu Yakin Untuk Keluar?")
            setPositiveButton("Ya") { dialog, which ->
                profileViewModel.logout()
                Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                requireActivity().finishAffinity()
            }
            setNegativeButton("Tidak") { dialog, which ->
                dialog.dismiss()
            }
            create().show()
        }
    }
}