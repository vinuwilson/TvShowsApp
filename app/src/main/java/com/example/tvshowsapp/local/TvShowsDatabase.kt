package com.example.tvshowsapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tvshowsapp.data.TvShowsData

@Database(entities = [TvShowsData::class], version = 1)
abstract class TvShowsDatabase : RoomDatabase() {
    abstract fun tvShowDao() : TvShowsDao

    companion object {
        @Volatile private var instance : TvShowsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TvShowsDatabase::class.java,
            "tvshows"
        ).build()
    }
}
