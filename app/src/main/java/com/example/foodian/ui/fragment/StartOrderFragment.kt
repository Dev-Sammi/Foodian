package com.example.foodian.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodian.R
import com.example.foodian.databinding.FragmentStartOrderBinding
import com.google.android.material.snackbar.Snackbar

class StartOrderFragment:Fragment(R.layout.fragment_start_order) {

    private var _binding: FragmentStartOrderBinding? = null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStartOrderBinding.bind(view)

        binding.btStartOrder.setOnClickListener {
            findNavController().navigate(
                StartOrderFragmentDirections.actionStartOrderFragmentToEntreeMenuFragment(getString(R.string.entree_menu))
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_app_menu -> {
                Snackbar.make(requireView(), "Menu Item",Snackbar.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}