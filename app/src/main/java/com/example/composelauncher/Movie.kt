package com.example.composelauncher

data class Movie(
    val title : String,
    val image : Int,
    val desc : String
)

fun getMovieList() : List<Movie> {
    return listOf(
        Movie("Dragon Boy", R.drawable.featured_movie1, "Dragon"),
        Movie("Dragon Boy", R.drawable.featured_movie1, "Dragon"),
        Movie("Dragon Boy", R.drawable.featured_movie1, "Dragon"),
    )
}
