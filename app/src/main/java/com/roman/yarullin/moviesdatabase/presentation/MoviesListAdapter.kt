package com.roman.yarullin.moviesdatabase.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.roman.yarullin.moviesdatabase.R
import com.roman.yarullin.moviesdatabase.domain.model.MoviesDomainModel
import com.squareup.picasso.Picasso

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

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)

        fun bind(movie: MoviesDomainModel) {
            Picasso.get()
                .load(movie.posterPath)
                .into(image)
            title.text = movie.title
            description.text = movie.overview
        }
    }
}