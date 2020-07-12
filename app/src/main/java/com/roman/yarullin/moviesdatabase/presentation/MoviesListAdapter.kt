package com.roman.yarullin.moviesdatabase.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.yarullin.moviesdatabase.R
import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>(){

    var movies: List<MoviesDomainModel> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(movie: MoviesDomainModel) {
            title.text = movie.title
            description.text = movie.overview
            Picasso.get()
                .load(movie.posterPath)
                .transform(RoundedCornersTransformation(itemView.resources.getDimensionPixelSize(R.dimen.img_corner_radius), 0))
                .fit()
                .centerCrop()
                .into(imageView)
        }
    }
}