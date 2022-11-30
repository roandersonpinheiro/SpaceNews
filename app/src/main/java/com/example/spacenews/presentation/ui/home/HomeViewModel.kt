package com.example.spacenews.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacenews.core.Query
import com.example.spacenews.core.RemoteException
import com.example.spacenews.core.State
import com.example.spacenews.data.SpaceFlightNewsCategory
import com.example.spacenews.data.entities.model.Post
import com.example.spacenews.domain.GetLatestPostsTitleContainsUseCase
import com.example.spacenews.domain.GetLatestPostsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getLatestPostUseCase: GetLatestPostsUseCase,
    private val getLatestPostsTitleContainsUseCase: GetLatestPostsTitleContainsUseCase
) : ViewModel() {

    private val _progressBarVisible = MutableLiveData<Boolean>(false)
    val progressBarVisible: LiveData<Boolean>
        get() = _progressBarVisible

    fun showProgressBar() {
        _progressBarVisible.value = true
    }

    fun hideProgressBar() {
        _progressBarVisible.value = false
    }

    private val _snackbar = MutableLiveData<String?>(null)
    val snackbar: LiveData<String?>
        get() = _snackbar

    private val _category = MutableLiveData<SpaceFlightNewsCategory>().apply {
        value = SpaceFlightNewsCategory.ARTICLES
    }
    val category: LiveData<SpaceFlightNewsCategory>
        get() = _category

    fun onSnackBarShown() {
        _snackbar.value = null
    }

    private val _listPost = MutableLiveData<State<List<Post>>>()
    val listPost: LiveData<State<List<Post>>>
        get() = _listPost

    init {
        fetchLatest(_category.value ?: SpaceFlightNewsCategory.ARTICLES)
    }

    fun fetchLatest(category: SpaceFlightNewsCategory) {
        fetchPosts(Query(category.value))
    }

    private fun fetchPosts(query: Query) {
        viewModelScope.launch {
            getLatestPostUseCase(query).onStart {
                _listPost.postValue(State.Loading)
            }.catch {
                with(RemoteException("Could not connect to SpaceFlightNews API")) {
                    _listPost.postValue(State.Error(this))
                    _snackbar.value = this.message
                }
            }.collect {
                _listPost.postValue(State.Success(it))
                _category.value = enumValueOf<SpaceFlightNewsCategory>(query.type.uppercase())
            }
        }
    }

    private fun fetchPostTitleContains(query: Query) {
        viewModelScope.launch {
            getLatestPostsTitleContainsUseCase(query).onStart {
                _listPost.postValue(State.Loading)
            }.catch {
                with(RemoteException("Could not connect to SpaceFlightNews API")) {
                    _listPost.postValue(State.Error(this))
                    _snackbar.value = this.message
                }
            }.collect {
                _listPost.postValue(State.Success(it))
                _category.value = enumValueOf<SpaceFlightNewsCategory>(query.type.uppercase())
            }
        }
    }

    fun searchPostTitleContains(searchString: String) {
        fetchPostTitleContains(Query(_category.value.toString(), searchString))
    }

    val helloText = Transformations.map(listPost) {
        listPost.let {
            when (it.value) {
                State.Loading -> {
                    "🚀 Loading latest news..."
                }
                is State.Error -> {
                    "Houston, we've had a problem! :'("
                }
                else -> {
                    ""
                }
            }
        }
    }
}
