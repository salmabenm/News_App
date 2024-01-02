package kia.example.min_projet

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ArticleHelper(cont: Context): SQLiteOpenHelper(cont,DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Principale.createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Principale.SQL_DELETE)
    }

    companion object {
        const val DATABASE_NAME = "article.db"
        const val DATABASE_VERSION = 1
    }

    fun insertArticle(
        title: String?,
        description: String?,
        idUtilisateur:  String?,
        author: String?,
        content: String?,
        publishedAt: String?,
        source: String?,
        url: String?,
        urlToImage: String?
    ) {
        Log.i("DatabaseInsert", "Inserting article with title: ")
        val db = writableDatabase
        val values = ContentValues().apply {
            put(Principale.ArticleContract.COLUMN_TITLE, title)
            put(Principale.ArticleContract.COLUMN_DESCRIPTION, description)
            put(Principale.ArticleContract.COLUMN_ID_UTILISATEUR, idUtilisateur)
            put(Principale.ArticleContract.COLUMN_AUTHOR, author)
            put(Principale.ArticleContract.COLUMN_CONTENT, content)
            put(Principale.ArticleContract.COLUMN_PUBLISHED_AT, publishedAt)
            put(Principale.ArticleContract.COLUMN_SOURCE, source)
            put(Principale.ArticleContract.COLUMN_URL, url)
            put(Principale.ArticleContract.COLUMN_URL_TO_IMAGE, urlToImage)
        }
        Log.i("DatabaseInsert", "Inserting article with title: $title")
        db.insert(Principale.ArticleContract.TABLE_NAME, null, values)

        db.close()
    }

    fun deleteArticle(articleId: Int) {
        val db = writableDatabase
        val selection = "${Principale.ArticleContract.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(articleId.toString())

        db.delete(Principale.ArticleContract.TABLE_NAME, selection, selectionArgs)
        db.close()
    }

    fun selectArticlesByUserId(userId: String): Cursor? {
        val db = readableDatabase
        val projection = arrayOf(
            Principale.ArticleContract.COLUMN_ID,
            Principale.ArticleContract.COLUMN_TITLE,
            Principale.ArticleContract.COLUMN_DESCRIPTION,
            Principale.ArticleContract.COLUMN_ID_UTILISATEUR,
            Principale.ArticleContract.COLUMN_AUTHOR,
            Principale.ArticleContract.COLUMN_CONTENT,
            Principale.ArticleContract.COLUMN_PUBLISHED_AT,
            Principale.ArticleContract.COLUMN_SOURCE,
            Principale.ArticleContract.COLUMN_URL,
            Principale.ArticleContract.COLUMN_URL_TO_IMAGE
        )
        val selection = "${Principale.ArticleContract.COLUMN_ID_UTILISATEUR} LIKE ?"
        val selectionArgs = arrayOf(userId)
        Log.d("DatabaseSelect", "Selecting articles for user ID: $userId")

        val cursor = db.query(
            Principale.ArticleContract.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        Log.d("DatabaseSelect", "Number of rows in cursor: ${cursor?.count}")

        // Assurez-vous que le curseur est positionné sur la première ligne
        cursor?.moveToFirst()

        return cursor
    }

}
