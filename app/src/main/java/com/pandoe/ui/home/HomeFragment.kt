package com.pandoe.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.pandoe.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var learningProgressAdapter: LearningProgressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setUserData()
        setupViews()
    }

    private fun setupViewModel() {
        val factory = HomeViewModelFactory.getInstance(requireActivity())
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun setUserData() {
        homeViewModel.getUser().observe(viewLifecycleOwner) { user ->
            val greeting = "Halo, ${user.name} 👋"
            binding.tvName.text = greeting
        }
    }

    private fun setupViews() {
        articleAdapter = ArticleAdapter()
        learningProgressAdapter = LearningProgressAdapter()

        setupRecyclerView()
        setupLearningProgressRecyclerView()

        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = articleAdapter
        }
    }

    private fun setupRecyclerView() {
        homeViewModel.getArticles().observe(viewLifecycleOwner) {
            showLoading(true)
            articleAdapter.submitList(it)
            showLoading(false)
        }
    }

    private fun setupLearningProgressRecyclerView() {
        binding.rvLearningProgress.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = learningProgressAdapter
        }

        homeViewModel.getLearningProgress().observe(viewLifecycleOwner) {
            learningProgressAdapter.submitList(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}