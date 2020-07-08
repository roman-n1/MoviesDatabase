package com.roman.yarullin.moviesdatabase.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.roman.yarullin.moviesdatabase.R

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private lateinit var moviesListVewModel: MoviesListViewModel
    private lateinit var adapter: MoviesListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        progressBar = view.findViewById(R.id.progressBar)
        adapter = MoviesListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        moviesListVewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        moviesListVewModel.stateLiveData.observe(this as LifecycleOwner) { newState ->
            adapter.movies = newState.albums
            if (newState.isError.data ?: false) {
                showError()
            }
            progressBar.visibility = if (newState.isLoading) View.VISIBLE else View.GONE
        }
        if (savedInstanceState == null) {
            moviesListVewModel.getMoviesList()
        }
    }

    fun showError() {
        view?.let { Snackbar.make(it, "Упс!", Snackbar.LENGTH_LONG).show() }
    }
}