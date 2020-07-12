package com.roman.yarullin.moviesdatabase.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.roman.yarullin.moviesdatabase.R
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private lateinit var moviesListVewModel: MoviesListViewModel
    private lateinit var adapter: MoviesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MoviesListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        moviesListVewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        moviesListVewModel.stateLiveData.observe(this as LifecycleOwner) { newState ->
            adapter.movies = newState.movies
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