package com.roman.yarullin.moviesdatabase.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.roman.yarullin.moviesdatabase.R
import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private lateinit var moviesListVewModel: MoviesListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        adapter = MoviesListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        moviesListVewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        moviesListVewModel.fragment = this
        moviesListVewModel.getMoviesList()
    }

    fun showResult(data: List<MoviesDomainModel>) {
        adapter.movies = data
    }

    fun showError() {
        view?.let { Snackbar.make(it, "Упс!", Snackbar.LENGTH_LONG).show() }
    }
}