package com.pandoe.ui.fund

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandoe.R
import com.pandoe.databinding.FragmentFundBinding

class FundFragment : Fragment() {
    private var _binding: FragmentFundBinding? = null
    private val binding get() = _binding!!

    private lateinit var fundViewModel: FundViewModel
    private lateinit var fundAdapter: FundAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFundBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        val factory = FundViewModelFactory.getInstance(requireActivity())
        fundViewModel = ViewModelProvider(this, factory)[FundViewModel::class.java]
    }

    private fun setupRecyclerView() {
        fundAdapter = FundAdapter()

        binding.rvFund.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = fundAdapter
        }

        fundViewModel.getFunds().observe(viewLifecycleOwner) {
            fundAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}