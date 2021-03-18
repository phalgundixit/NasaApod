package com.example.nasaapod.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nasaapod.R
import com.example.nasaapod.adapter.ImageAdapter
import com.example.nasaapod.data.ImageResponseItem
import com.example.nasaapod.databinding.FragmentGalleryBinding
import com.example.nasaapod.viewModel.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment:Fragment(R.layout.fragment_gallery),ImageAdapter.OnItemClickListener {

    private val viewModel: ImagesViewModel by viewModels()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)

        val adapter = ImageAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter

        }
        viewModel.images.observe(viewLifecycleOwner, Observer {
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
            })

    }

    override fun onItemClick(photo: ImageResponseItem) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(photo)
        findNavController().navigate(action)
    }
}