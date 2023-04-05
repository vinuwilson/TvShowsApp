package com.example.tvshowsapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import javax.annotation.Nullable

@Serializable
@Parcelize
@Entity(tableName = "tvshows")
data class TvShowsData(
    val cover_url: String?,
    val directed_by: String?,
    @PrimaryKey
    val id: Int,
    val imdb_id: String?,
    val last_aired_date: String?,
    val number_episodes: Int?,
    val overview: String?,
    val phase: Int?,
    val release_date: String?,
    val saga: String?,
    val season: Int?,
    val title: String?,
    val trailer_url: String?
) : Parcelable