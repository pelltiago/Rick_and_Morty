package com.example.rickandmorty.data

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Test

class CharactersRepositoryTest {

    private val charactersRepository = CharactersRepository()

    @Test
    fun getNullWhenPageIsInvalid() = runTest {
        val invalidPage = "999"
        val charactersResponseModel = charactersRepository.searchCharacters(invalidPage, "")
        assertNull(charactersResponseModel)
    }

}