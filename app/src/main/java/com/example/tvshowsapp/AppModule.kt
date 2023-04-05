package com.example.tvshowsapp

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_URL = "https://mcuapi.herokuapp.com/"

@Module
@InstallIn(FragmentComponent::class)
class AppModule {

    @Provides
    fun getTvShowsAPI(retrofit: Retrofit) : TvShowsAPI = retrofit.create(TvShowsAPI::class.java)

    @Provides
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun tvShowsDB(@ApplicationContext context: Context) : TvShowsDatabase {
        return TvShowsDatabase.invoke(context)
    }

    @Provides
    fun tvShowsDao(tvShowsDatabase: TvShowsDatabase) : TvShowsDao {
        return tvShowsDatabase.tvShowDao()
    }
}