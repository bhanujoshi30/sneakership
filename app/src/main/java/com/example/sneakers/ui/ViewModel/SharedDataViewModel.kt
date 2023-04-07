package com.example.sneakers.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers.models.Sneaker
import com.example.sneakers.repositories.ISneakerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedDataViewModel @Inject constructor(
    private var sneakerRepository: ISneakerRepository
) : ViewModel() {

    init {
        getSneakers()
        getCartAddedSneakers()
    }

    private val _sneakers = MutableLiveData<List<Sneaker>>()
    private val _cartAddedSneakers = MutableLiveData<List<Sneaker>>()
    private val _cartAddedSneakersAmount = MutableLiveData(0)
    private val _selectedSneaker = MutableLiveData<Sneaker>()
    val sneakers: LiveData<List<Sneaker>> = _sneakers
    val cartSneakers: LiveData<List<Sneaker>> = _cartAddedSneakers
    val sumOfCart: LiveData<Int> = _cartAddedSneakersAmount
    val slectedSneakerDetail: LiveData<Sneaker> = _selectedSneaker
    fun setSelectedSneakerDetail(sneaker: Sneaker) {
        viewModelScope.launch {
            _selectedSneaker.value = sneaker
        }
    }

    private fun getSneakers() {
        viewModelScope.launch {
            sneakerRepository.getSneakers().collect {
                _sneakers.value = it
            }
        }
    }

    fun addSneakerToCart(sneakerId: Int) {
        viewModelScope.launch {
            sneakerRepository.addSneakerToCart(sneakerId)
        }
    }

    fun removeSneakerFromCart(sneakerId: Int) {
        viewModelScope.launch {
            sneakerRepository.removeSneakerFromCart(sneakerId)
        }
    }

    private fun getCartAddedSneakers() {
        viewModelScope.launch {
            sneakerRepository.getCartAddedSneakers().collect { cartSneakers ->
                _cartAddedSneakers.value = cartSneakers
                _cartAddedSneakersAmount.value = cartSneakers.sumOf { it.retailPrice ?: 0 }
            }
        }
    }


}