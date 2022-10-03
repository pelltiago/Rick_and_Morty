package com.example.rickandmorty.ui.viewmodel

import com.example.rickandmorty.data.model.CharactersResponseModel
import com.example.rickandmorty.data.model.Info
import org.junit.Assert.assertEquals
import org.junit.Test

class CharactersViewModelTest {

    @Test
    fun onClickPrevButtonWhenActualPageIsEqualToOneThenNotDecreaseActualPage() {
        val charactersViewModel = CharactersViewModel()
        charactersViewModel.actualPage = 1
        charactersViewModel.onClickPrevButton()
        assertEquals(1, charactersViewModel.actualPage)
    }

    @Test
    fun onClickNextButtonWhenActualPageIsGreaterThanOrEqualToMaxPageThenNotIncrementActualPage() {
        val charactersViewModel = CharactersViewModel()
        charactersViewModel.actualPage = 9999
        charactersViewModel.maxPage = 9999
        charactersViewModel.onClickNextButton()
        assertEquals(9999, charactersViewModel.actualPage)
    }
}