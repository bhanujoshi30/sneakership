package com.example.sneakers.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakers.models.Sneaker
import com.example.sneakers.repositories.SneakerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject  constructor(
    private var sneakerRepository: SneakerRepository
) : ViewModel() {

    private val _sneakers = MutableLiveData<List<Sneaker>>()
    val sneakers: LiveData<List<Sneaker>> = _sneakers
    fun getSneakers() {
        viewModelScope.launch {
            sneakerRepository.getSneakers().collect{
                _sneakers.value = it
            }
        }
    }
}