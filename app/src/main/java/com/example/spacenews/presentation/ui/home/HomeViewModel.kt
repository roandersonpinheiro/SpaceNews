package com.example.spacenews.presentation.ui.home

import androidx.lifecycle.*
import com.example.spacenews.core.RemoteException
import com.example.spacenews.core.State
import com.example.spacenews.data.SpaceFlightNewsCategory
import com.example.spacenews.data.model.Post
import com.example.spacenews.domain.GetLatestPostsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class HomeViewModel(private val getLatestPostUseCase: GetLatestPostsUseCase) : ViewModel() {

    private val _progressBarVisible = MutableLiveData<Boolean>(false)
    val progressBarVisible: LiveData<Boolean>
        get() = _progressBarVisible


    private val _category = MutableLiveData<SpaceFlightNewsCategory>().apply {
        value = SpaceFlightNewsCategory.ARTICLES
    }

    val category: LiveData<SpaceFlightNewsCategory> get() = _category


    fun showProgressBar() {
        _progressBarVisible.value = true
    }

    fun hideProgressBar() {
        _progressBarVisible.value = false
    }


    private val _snackbar = MutableLiveData<String?>(null)
    val snackbar: LiveData<String?>
        get() = _snackbar


    fun onSnackBarShown() {
        _snackbar.value = null
    }


    private val _listPost = MutableLiveData<State<List<Post>>>()
    val listPost: LiveData<State<List<Post>>>
        get() = _listPost

    init {
        fetchLatest(_category.value ?: SpaceFlightNewsCategory.ARTICLES)
    }

    fun fetchLatest(category:  SpaceFlightNewsCategory) {
        fetchPosts(category.value)
        _category.value = category
    }


    private fun fetchPosts(query: String) {
        viewModelScope.launch {
            getLatestPostUseCase(query)
                .onStart {
                    _listPost.postValue(State.Loading)
                    delay(800) //apenas cosmético
                }.catch {
                    with(RemoteException("Could not connect to SpaceFlightNews API")) {
                        _listPost.postValue(State.Error(this))
                        _snackbar.value = this.message
                    }
                }
                .collect {
                    _listPost.postValue(State.Success(it))
                }
        }
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