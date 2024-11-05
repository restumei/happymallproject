package com.example.happymall.presentation.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.happymall.R
import com.example.happymall.data.model.Category2
import com.example.happymall.data.util.Resource
import com.example.happymall.databinding.FragmentHomeBinding
import com.example.happymall.presentation.ui.home.adapter.BannerAdapter
import com.example.happymall.presentation.ui.home.adapter.HomeAdapter
import com.example.happymall.presentation.viewmodel.HomeViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var adapter: HomeAdapter

    private lateinit var binding: FragmentHomeBinding

    private var category2 = mutableListOf<Category2>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout and return the binding root
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample URLs for banner images
        val bannerImages = listOf(
            R.drawable.banner_1,
            R.drawable.banner_2,
            R.drawable.banner_3,
            R.drawable.banner_4,
            R.drawable.banner_5
        )

        // Set up BannerAdapter and attach it to the ViewPager2
        val bannerAdapter = BannerAdapter(bannerImages)
        binding.vpBanner.adapter = bannerAdapter

        // Optional: Auto-scroll banner every few seconds
        autoScrollBanner(bannerImages.size)

        // The rest of your HomeFragment setup (categories, products, click listeners, etc.)
        setupCategoriesAndProducts()
    }

    private fun autoScrollBanner(size: Int) {
        val autoScrollHandler = Handler(Looper.getMainLooper())
        val autoScrollRunnable = object : Runnable {
            override fun run() {
                val nextItem = (binding.vpBanner.currentItem + 1) % size
                binding.vpBanner.currentItem = nextItem
                autoScrollHandler.postDelayed(this, 3000) // 3 seconds delay
            }
        }
        autoScrollHandler.postDelayed(autoScrollRunnable, 3000)
    }

    private fun setupCategoriesAndProducts() {
        viewModel.getAllCategories()

        viewModel.categories.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    val categories = response.data
                    val chip = Chip(requireContext())
                    chip.text = "All"
                    chip.id = 0
                    chip.isChecked = true
                    category2.clear()
                    binding.chipGroup.removeAllViews()
                    category2.add(Category2(0, "All"))
                    binding.chipGroup.addView(chip)
                    categories?.forEachIndexed { index, category ->
                        val chip = Chip(requireContext())
                        chip.text = category
                        chip.id = index + 1
                        category2.add(Category2(index, category))
                        binding.chipGroup.addView(chip)
                    }
                }

                is Resource.Loading -> Log.i("HomeFragment", "Loading...")
                is Resource.Error -> Log.i("HomeFragment", "${response.message}")
            }
        }

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    adapter.differ.submitList(response.data)
                    binding.homeRecyclerView.visibility = View.VISIBLE
                }

                is Resource.Loading -> Log.i("HomeFragment", "Loading...")
                is Resource.Error -> Log.i("HomeFragment", "${response.message}")
            }
        }

        adapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.chipGroup.setOnCheckedStateChangeListener { group, _ ->
            group.isSingleSelection = true
            val chipid = group.checkedChipId
            val category = category2[chipid].category
            viewModel.getCategoryProducts(category)
        }

        binding.homeProfile.setOnClickListener {
            val profileFragment = ProfileFragment()
            profileFragment.show(parentFragmentManager, profileFragment.tag)
        }

        binding.homeCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        binding.homeRecyclerView.adapter = adapter
    }
}
