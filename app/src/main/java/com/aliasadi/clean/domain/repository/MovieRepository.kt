package com.aliasadi.clean.domain.repository

import com.aliasadi.clean.domain.entities.Movie
import com.aliasadi.clean.domain.util.Result

/**
 * Created by Ali Asadi on 13/05/2020
 */
interface MovieRepository {
    suspend fun getMovies(): Result<List<Movie>>
    suspend fun getMovie(movieId: Int): Result<Movie>
    suspend fun getFavoriteMovies(): Result<List<Movie>>
    suspend fun checkFavoriteStatus(movieId: Int): Result<Boolean>
    suspend fun addMovieToFavorite(movieId: Int)
    suspend fun removeMovieFromFavorite(movieId: Int)
}