package com.example.rickandmorty.data.network

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CharacterServiceTest {

    private lateinit var characterService: CharacterService


    @Test
    fun getNullWhenRequestIsNotSuccessful() = runTest{
        characterService = CharacterService()
        val response = characterService.getCharacters("999")

        assertNull(response)
    }

    @Test
    fun getCharactersWhenRequestIsSuccessful() = runTest {
        characterService = CharacterService()

        val response = characterService.getCharacters("1")

        assertEquals(response?.info?.count, 826)
        assertEquals(response?.info?.pages, 42)
        assertEquals(response?.info?.next, "https://rickandmortyapi.com/api/character/?page=2")
        assertEquals(response?.info?.prev, null)
        assertEquals(response?.results?.size, 20)
    }

 }