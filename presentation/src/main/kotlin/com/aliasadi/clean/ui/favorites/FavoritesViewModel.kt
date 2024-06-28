package com.aliasadi.clean.ui.favorites

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.aliasadi.clean.entities.MovieListItem
import com.aliasadi.clean.mapper.toMovieListItem
import com.aliasadi.clean.ui.base.BaseViewModel
import com.aliasadi.clean.util.singleSharedFlow
import com.aliasadi.data.util.DispatchersProvider
import com.aliasadi.domain.usecase.GetFavoriteMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * @author by Ali Asadi on 03/08/2022
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getFavoriteMovies: GetFavoriteMovies,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    val movies: Flow<PagingData<MovieListItem>> = getFavoriteMovies(30)
        .map { pagingData ->
            pagingData.map { movieEntity ->
                movieEntity.toMovieListItem()
            }
        }.cachedIn(viewModelScope)

    private val _uiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationState: MutableSharedFlow<FavoritesNavigationState> = singleSharedFlow()
    val navigationState = _navigationState.asSharedFlow()

    fun onMovieClicked(movieId: Int) =
        _navigationState.tryEmit(FavoritesNavigationState.MovieDetails(movieId))

    fun onLoadStateUpdate(loadState: CombinedLoadStates, itemCount: Int) {
        val showLoading = loadState.refresh is LoadState.Loading
        val showNoData = loadState.append.endOfPaginationReached && itemCount < 1

        _uiState.update {
            it.copy(
                isLoading = showLoading,
                noDataAvailable = showNoData
            )
        }
    }
}
