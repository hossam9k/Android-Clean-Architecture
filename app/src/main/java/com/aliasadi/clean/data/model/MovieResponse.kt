package com.aliasadi.clean.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ali Asadi on 13/05/2020
 */
data class MovieResponse(
    @SerializedName("movies")
    val movies: List<MovieRemote>
)