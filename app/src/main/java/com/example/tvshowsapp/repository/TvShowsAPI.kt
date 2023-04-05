package com.example.tvshowsapp.repository

import com.example.tvshowsapp.data.TvShowsList
import retrofit2.http.GET

interface TvShowsAPI {

    @GET("api/v1/tvshows")
    suspend fun getTvShowsFromServer(): TvShowsList

}
