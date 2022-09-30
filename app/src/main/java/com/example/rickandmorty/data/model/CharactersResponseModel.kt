package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class CharactersResponseModel(
    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
)