package com.roman.yarullin.moviesdatabase.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.roman.yarullin.moviesdatabase.R
import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private lateinit var moviesListVewModel: MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesListVewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
        moviesListVewModel.fragment = this
        moviesListVewModel.getMoviesList()
    }

    fun showResult(data: List<MoviesDomainModel>) {
        view?.findViewById<TextView>(R.id.textView)?.text = data[0].title
    }

    fun showError() {
        view?.let { Snackbar.make(it, "Упс!", Snackbar.LENGTH_LONG) }
    }
}