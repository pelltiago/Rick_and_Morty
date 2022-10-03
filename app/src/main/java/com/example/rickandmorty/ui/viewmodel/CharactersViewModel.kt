package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.CharactersResponseModel
import com.example.rickandmorty.data.model.Results
import com.example.rickandmorty.domain.SearchCharactersUseCase
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    var actualPage: Int = 1
    var maxPage: Int = 9999
    var charactersList = listOf<Results>()
    var searchName: String? = null

    var charactersResponseModel = MutableLiveData<CharactersResponseModel?>()


    var searchCharactersUseCase = SearchCharactersUseCase()


    fun onClickNextButton() {
        if (actualPage < maxPage) {
            searchCharacter(searchName, ++actualPage)
        }
    }

    fun onClickPrevButton() {
        if (actualPage > 1) {
            searchCharacter(searchName, --actualPage)
        }
    }

    fun onCreate() {
        viewModelScope.launch {
            val result = searchCharactersUseCase(actualPage, searchName)
            if (result != null) {
                maxPage = result.info?.pages!!
                charactersList = result.results
            }
            charactersResponseModel.postValue(result)
        }
    }

    fun searchCharacter(name: String?, page: Int?) {
        viewModelScope.launch {
            val result = searchCharactersUseCase(page, name)
            if (result != null) {
                maxPage = result.info?.pages!!
                charactersList = result.results
                searchName = name
            }
            if (page != null) {
                actualPage =  page
            }
            charactersResponseModel.postValue(result)
        }
    }
}