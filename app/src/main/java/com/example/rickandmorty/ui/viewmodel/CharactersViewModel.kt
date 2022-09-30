package com.example.rickandmorty.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.CharactersResponseModel
import com.example.rickandmorty.domain.GetCharactersUseCase
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    var charactersResponseModel = MutableLiveData<CharactersResponseModel?>()

    var getCharactersUseCase = GetCharactersUseCase()

    fun onCreate(page: String) {
        viewModelScope.launch {
            val result = getCharactersUseCase(page)
            charactersResponseModel.postValue(result)
        }
    }
}