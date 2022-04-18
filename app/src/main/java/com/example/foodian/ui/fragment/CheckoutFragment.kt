package com.example.foodian.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.foodian.R
import com.example.foodian.databinding.FragmentAccompanimentBinding
import com.example.foodian.databinding.FragmentCheckoutBinding
import com.example.foodian.generated.callback.OnClickListener
import com.example.foodian.reprository.FoodianRepository
import com.example.foodian.viewmodel.FoodianViewModel
import com.example.foodian.viewmodel.FoodianViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar


class CheckoutFragment : Fragment() {
    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var viewModel: FoodianViewModel
    private var checkoutClickListener: CheckOutClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        checkoutClickListener = activity as? CheckOutClickListener

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_checkout,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = FoodianRepository()
        val factory = FoodianViewModelProviderFactory(repository)
        val vm: FoodianViewModel by activityViewModels { factory }
        this.viewModel = vm
        binding.apply {
            lifecycleOwner = this@CheckoutFragment
            mViewModel = viewModel
            btSubmitOrder.setOnClickListener {
//                val intent = Intent(Intent.Action_View)
            }
            btCancel.setOnClickListener {
                cancel()
                findNavController().navigate(CheckoutFragmentDirections.actionCheckoutFragmentToStartOrderFragment())
            }
            btSubmitOrder.setOnClickListener {
                val isOrderSubmitted = viewModel.submitOrder()
                if (isOrderSubmitted){
                    Snackbar.make(view, "Order successful submitted. \nThank you!", Snackbar.LENGTH_LONG).show()
                    checkoutClickListener?.onClick(viewModel.message.value)
//
                }
            }
        }
    }

    interface CheckOutClickListener{
        fun onClick(message: String?)
    }

    private fun cancel(){
        viewModel.cancelOrder()
    }
}


