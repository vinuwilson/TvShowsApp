package com.example.tvshowsapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tvshowsapp.local.TvShowsDao
import com.example.tvshowsapp.local.TvShowsDatabase
import com.example.tvshowsapp.utils.BaseUITest
import com.example.tvshowsapp.utils.provideTestList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest : BaseUITest() {

    private lateinit var database: TvShowsDatabase
    private lateinit var tvShowsDao: TvShowsDao

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, TvShowsDatabase::class.java
        ).build()
        tvShowsDao = database.tvShowDao()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadAlbum() {
        runBlocking {
            tvShowsDao.insertRecord(provideTestList())
            val tvShowsListFromDb = tvShowsDao.getAllRecords()
            assert(tvShowsListFromDb.size == 1)
            assert(tvShowsListFromDb[0].id == provideTestList()[0].id)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }
}