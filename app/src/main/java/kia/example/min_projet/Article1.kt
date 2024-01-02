package kia.example.min_projet

import java.io.Serializable

class Article1(  val id:Int,
                 val author: String?,
                 val content: String?,
                 val description: String?,
                 val publishedAt: String?,
                 val source:String?,
                 val title: String?,
                 val url: String?,
                 val urlToImage: String?,): Serializable {
    override fun hashCode(): Int {
        return title.hashCode() + url.hashCode() // Exemple simple, assurez-vous d'ajuster selon vos besoins
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Article) {
            title == other.title && url == other.url // Exemple simple, assurez-vous d'ajuster selon vos besoins
        } else {
            false
        }
    }
}