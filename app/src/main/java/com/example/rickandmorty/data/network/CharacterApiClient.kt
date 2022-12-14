package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.model.CharactersResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiClient {
    @GET("character/")
    suspend fun searchCharacters(@Query("page") page: String?, @Query("name") name: String?): Response<CharactersResponseModel>
}