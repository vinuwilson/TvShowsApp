package com.example.tvshowsapp.utils

import com.example.tvshowsapp.data.TvShowsData

fun provideTestList(): List<TvShowsData> {
    val dummyList = arrayListOf<TvShowsData>()
    dummyList.add(
        TvShowsData(
            "https://res.cloudinary.com/augustomarcelo/image/upload/v1676219587/mcuapi/gallery/tv_shows/wandavision/posters/1.jpg",
            "Matt Shakman",
            1,
            "1234",
            "2021-03-05",
            10,
            "Marvel Studios captivating new series ",
            101,
            "2021-01-15",
            "Multiverse Saga",
            5,
            "WandaVision",
            "https://players.brightcove.net/5359769168001/BJemW31x6g_default/index.html?videoId=6215509803001"
        )
    )
    return dummyList
}