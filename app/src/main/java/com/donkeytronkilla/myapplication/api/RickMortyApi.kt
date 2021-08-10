package com.donkeytronkilla.myapplication.api

import com.donkeytronkilla.myapplication.model.ApiResponse
import retrofit2.http.GET

interface RickMortyApi {
    companion object {
        const val PAGE_SIZE = 20
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
    @GET("character")
    suspend fun getCharacters(): ApiResponse
}