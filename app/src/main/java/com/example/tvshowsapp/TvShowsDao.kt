package com.example.tvshowsapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TvShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(tvShowsData: List<TvShowsData>)

    @Query("SELECT * FROM tvshows")
    suspend fun getAllRecords(): List<TvShowsData>

    @Query("DELETE FROM tvshows")
    suspend fun deleteAllRecords()

}
