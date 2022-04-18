package com.example.foodian.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.foodian.R
import com.example.foodian.databinding.FragmentAccompanimentBinding
import com.example.foodian.reprository.FoodianRepository
import com.example.foodian.viewmodel.FoodianViewModel
import com.example.foodian.viewmodel.FoodianViewModelProviderFactory


class AccompanimentFragment : Fragment() {
    private lateinit var binding: FragmentAccompanimentBinding
    private lateinit var viewModel: FoodianViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_accompaniment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = FoodianRepository()
        val factory = FoodianViewModelProviderFactory(repository)
        val vm: FoodianViewModel by activityViewModels{ factory }
        this.viewModel  = vm
        binding.apply {
            lifecycleOwner = this@AccompanimentFragment
            mViewModel = viewModel
            btNext.setOnClickListener {
                findNavController().navigate(
                    AccompanimentFragmentDirections.actionAccompanimentFragmentToCheckoutFragment("Checkout")
                )
            }
            btCancel.setOnClickListener {
                cancel()
                findNavController().navigate(AccompanimentFragmentDirections.actionAccompanimentFragmentToStartOrderFragment())
            }
        }
    }

    private fun cancel(){
        viewModel.cancelOrder()
    }

}