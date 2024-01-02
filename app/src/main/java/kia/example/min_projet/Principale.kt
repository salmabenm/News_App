package kia.example.min_projet

object Principale {
    object ArticleContract {
        const val TABLE_NAME = "favoree"
        const val COLUMN_ID = "id"
        const val COLUMN_ID_UTILISATEUR = "id_utilisateur"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PUBLISHED_AT = "published_at"
        const val COLUMN_SOURCE = "source"
        const val COLUMN_TITLE = "title"
        const val COLUMN_URL = "url"
        const val COLUMN_URL_TO_IMAGE = "url_to_image"
    }

    const val createTableQuery =
        "CREATE TABLE ${ArticleContract.TABLE_NAME} (" +
                "${ArticleContract.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${ArticleContract.COLUMN_ID_UTILISATEUR} INTEGER," + // Ajout du champ id_utilisateur
                "${ArticleContract.COLUMN_AUTHOR} TEXT," +
                "${ArticleContract.COLUMN_CONTENT} TEXT," +
                "${ArticleContract.COLUMN_DESCRIPTION} TEXT," +
                "${ArticleContract.COLUMN_PUBLISHED_AT} TEXT," +
                "${ArticleContract.COLUMN_SOURCE} TEXT," +
                "${ArticleContract.COLUMN_TITLE} TEXT," +
                "${ArticleContract.COLUMN_URL} TEXT," +
                "${ArticleContract.COLUMN_URL_TO_IMAGE} TEXT)"

    const val SQL_DELETE = "DROP TABLE IF EXISTS ${ArticleContract.TABLE_NAME}"

}
