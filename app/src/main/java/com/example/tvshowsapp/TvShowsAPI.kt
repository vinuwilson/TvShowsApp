package com.example.tvshowsapp

import retrofit2.http.GET

interface TvShowsAPI {

    @GET("api/v1/tvshows")
    suspend fun getTvShowsFromServer(): TvShowsList

}
