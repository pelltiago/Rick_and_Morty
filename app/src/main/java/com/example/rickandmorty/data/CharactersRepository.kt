package com.example.rickandmorty.data

import com.example.rickandmorty.data.model.CharactersResponseModel
import com.example.rickandmorty.data.network.CharacterService

class CharactersRepository {

    private val api = CharacterService()

    suspend fun getAllCharacters(page: String): CharactersResponseModel? {
        return api.getCharacters(page)
    }
}