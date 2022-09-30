package com.example.rickandmorty.data.network

import com.example.rickandmorty.core.RetrofitHelper
import com.example.rickandmorty.data.model.CharactersResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharacters(page: String): CharactersResponseModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CharacterApiClient::class.java).getAllCharacters(page)
            response.body()
        }
    }
}