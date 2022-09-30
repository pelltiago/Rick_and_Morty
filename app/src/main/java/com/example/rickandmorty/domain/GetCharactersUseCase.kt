package com.example.rickandmorty.domain

import com.example.rickandmorty.data.CharactersRepository

class GetCharactersUseCase() {

    private val repository = CharactersRepository()

    suspend operator fun invoke(page: String) = repository.getAllCharacters(page)

}