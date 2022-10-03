package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.CharactersResponseModel
import com.example.rickandmorty.data.model.Info
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class CharacterServiceTest {
    private lateinit var characterService: CharacterService

    @Mock
    private lateinit var responseMock: CharactersResponseModel

    @Before
    fun setUp(){
        responseMock = CharactersResponseModel()
    }

    @Test
    fun getNullWhenRequestPageIsInvalid() = runTest {
        characterService = CharacterService()
        val response = characterService.searchCharacters("999", "")

        assertNull(response)
    }

    @Test
    fun getNullPrevPageWhenRequestWithPageOneIsSuccessful() = runTest {
        characterService = CharacterService()

        val response = characterService.searchCharacters("1", "")

        responseMock.info?.prev = null

        assertEquals(responseMock.info?.prev, response?.info?.prev)
        assertNull(response?.info?.prev)
    }
}