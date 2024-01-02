package kia.example.min_projet

import java.io.Serializable

data class apinews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
): Serializable