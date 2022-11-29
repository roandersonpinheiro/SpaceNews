package com.example.spacenews.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.spacenews.R
import com.example.spacenews.core.State
import com.example.spacenews.data.SpaceFlightNewsCategory
import com.example.spacenews.databinding.HomeFragmentBinding
import com.example.spacenews.presentation.adapter.PostListAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initBinding()
        initSnackbar()
        initRecyclerView()
        initOptionMenu()
        initSearchBar()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initQueryObserver()
    }

    private fun initOptionMenu() {
        with(binding.homeToolbar) {
            this.inflateMenu(R.menu.options_menu)
            menu.findItem(R.id.action_get_articles).setOnMenuItemClickListener {
                viewModel.fetchLatest(SpaceFlightNewsCategory.ARTICLES)
                true
            }
            menu.findItem(R.id.action_get_blogs).setOnMenuItemClickListener {
                viewModel.fetchLatest(SpaceFlightNewsCategory.BLOGS)
                true
            }
            menu.findItem(R.id.action_get_reports).setOnMenuItemClickListener {
                viewModel.fetchLatest(SpaceFlightNewsCategory.REPORTS)
                true
            }
        }
    }

    private fun initSnackbar() {
        viewModel.snackbar.observe(viewLifecycleOwner) {
            it?.let { errorMessage ->
                Snackbar.make(
                    binding.root, errorMessage,
                    Snackbar.LENGTH_LONG
                ).show()
                viewModel.onSnackBarShown()
            }
        }
    }

    private fun initQueryObserver() {
        viewModel.category.observe(viewLifecycleOwner) {
            searchView.queryHint = "${getString(R.string.search_in)} " + when (it) {
                SpaceFlightNewsCategory.ARTICLES -> getString(R.string.news)
                SpaceFlightNewsCategory.BLOGS -> getString(R.string.blogs)
                SpaceFlightNewsCategory.REPORTS -> getString(R.string.reports)
            }
        }
    }

    fun initSearchBar() {
        with(binding.homeToolbar) {
            val searchItem = menu.findItem(R.id.action_search)
            searchView = searchItem.actionView as SearchView

            searchView.isIconified = false

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    val searchString = searchView.query.toString()
                    viewModel.searchPostTitleContains(searchString)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        viewModel.searchPostTitleContains(it)
                    }
                    return true
                }
            })
        }
    }

    private fun initRecyclerView() {

        val adapter = PostListAdapter()
        binding.homeRv.adapter = adapter

        viewModel.listPost.observe(viewLifecycleOwner) {
            when (it) {
                State.Loading -> {
                    viewModel.showProgressBar()
                }
                is State.Error -> {
                    viewModel.hideProgressBar()
                }
                is State.Success -> {
                    viewModel.hideProgressBar()
                    adapter.submitList(it.result)
                }
            }
        }
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
