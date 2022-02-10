package com.example.worldcinema.model

data class FilmsModel(val movieId : String, val name : String, val description : String, val age : String, val images : List<String>, val poster : String, val tags : List<Tags>)
