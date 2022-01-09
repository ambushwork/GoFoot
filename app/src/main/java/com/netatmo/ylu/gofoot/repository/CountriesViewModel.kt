package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.Country
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {
    
    val countryLiveData: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>().also {
            load()
        }
    }

    fun setValue(countries: List<Country>) {
        countryLiveData.value = countries
    }

    fun getValue(): MutableLiveData<List<Country>> {
        return countryLiveData
    }

    private fun load() {
        viewModelScope.launch {
            val body = RequestClient.getCountry("england")
            setValue(body.response)
        }
    }
}