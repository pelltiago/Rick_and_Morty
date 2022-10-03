package com.example.rickandmorty.domain

import com.example.rickandmorty.data.CharactersRepository

class SearchCharactersUseCase {

        private val repository = CharactersRepository()
        suspend operator fun invoke(page: Int?, name: String?) = repository.searchCharacters(page.toString(), name)
    }
