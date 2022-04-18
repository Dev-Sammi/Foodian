package com.example.foodian.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodian.data.MenuItem
import com.example.foodian.reprository.FoodianRepository

const val  TAG = "FOODIANVIEWMODEL"
class FoodianViewModel(repository: FoodianRepository) : ViewModel() {
    //data from the repository
    val menuItem = repository.data.menuItems



    // for setting the checkout fragment
    private var _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    private var _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    private var _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment


    private var _tax = MutableLiveData<Double>(0.0)
    val tax: LiveData<Double> = _tax

    private var _subtotal = MutableLiveData<Double>(0.0)
    val subtotal: LiveData<Double> = _subtotal

    private var _totalPrice = MutableLiveData<Double>(0.0)
    val totalPrice: LiveData<Double> = _totalPrice

    private var _message = MutableLiveData<String>("")
    val message: LiveData<String> = _message



    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    private var currentEntreePrice = 0.0
    private var currentSidePrice = 0.0
    private var currentAccompanimentPrice = 0.0

    fun setEntree(selected: String) {
        val entreeSelected = menuItem[selected]
        if (entreeSelected != null) {
            this._entree.value = entreeSelected
            currentEntreePrice = entreeSelected.price
            _subtotal.value = _subtotal.value?.minus(previousEntreePrice)
            previousEntreePrice = currentEntreePrice
            setSubtotal(currentEntreePrice)
        }
    }

    fun setSide(selected: String) {
        val sideSelected = menuItem[selected]
        if (sideSelected != null) {
            this._side.value = sideSelected
            currentSidePrice = sideSelected.price
            _subtotal.value = _subtotal.value?.minus(previousSidePrice)
            previousSidePrice = currentSidePrice
            setSubtotal(currentSidePrice)
        }
    }

    fun setAccompaniment(selected: String) {
        val accompanimentSelected = menuItem[selected]
        if (accompanimentSelected != null) {
            this._accompaniment.value = accompanimentSelected
            currentAccompanimentPrice = accompanimentSelected.price
            _subtotal.value = _subtotal.value?.minus(previousAccompanimentPrice)
            previousAccompanimentPrice = currentAccompanimentPrice
            setSubtotal(currentAccompanimentPrice)
        }
    }

    private fun setSubtotal(number: Double) {
        _subtotal.value = _subtotal.value?.plus(number)
        setTotalPrice(_subtotal.value)
    }

    private fun setTotalPrice(subtotal: Double?) {
        if(subtotal != null) {
            val rate = RATE.div(100)
            val tax = subtotal.times(rate)
            this._tax.value = tax
            val totalPrice = subtotal.plus(tax)
            _totalPrice.value = totalPrice
        }
    }

    fun cancelOrder(){
        _entree.value = null
        _side.value = null
        _accompaniment.value = null
        _subtotal.value = 0.0
        _totalPrice.value = 0.0
        _tax.value = 0.0
        previousEntreePrice = 0.0
        previousSidePrice = 0.0
        previousAccompanimentPrice = 0.0
        currentEntreePrice = 0.0
        currentSidePrice = 0.0
        currentAccompanimentPrice = 0.0
    }

    fun submitOrder(): Boolean{
        val message = "My order \nEntree: ${_entree.value?.name} \nSide: ${_side.value?.name} " +
                "\nAccompaniment: ${_accompaniment.value?.name} \nTotal Price: \$${String.format("%.2f", _totalPrice.value)}" +
                "\nThank you! \n@Samuel Kwasi Gyasi"
        Log.i(TAG, message)
        this._message.value = message
        return true
    }

//    fun calculateTax

    companion object {
        const val RATE = 0.85
    }
}