package kia.example.min_projet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kia.example.min_projet.databinding.FragmentFavoriteBinding
import kia.example.min_projet.databinding.FragmentScondBinding

class FavoriteFragment : Fragment(),adapter1.onclick {

   private lateinit var binding:FragmentFavoriteBinding
    private lateinit var dbHelper: ArticleHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val articleList = mutableListOf<Article1>()
        var view=inflater.inflate(R.layout.fragment_favorite, container, false)
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        // Vérifier si receivedArticle n'est pas null avant d'accéder à ses propriétés

        dbHelper = ArticleHelper(requireContext())
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        if (user != null) {
            val uid = user.uid
            val cursor = dbHelper.selectArticlesByUserId(uid)
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Obtenez les valeurs de chaque colonne pour la ligne actuelle
                    val idIndex = cursor.getColumnIndex(Principale.ArticleContract.COLUMN_ID)
                    val titleIndex = cursor.getColumnIndex(Principale.ArticleContract.COLUMN_TITLE)
                    val descriptionIndex =
                        cursor.getColumnIndex(Principale.ArticleContract.COLUMN_DESCRIPTION)
                    val authorIndex =
                        cursor.getColumnIndex(Principale.ArticleContract.COLUMN_AUTHOR)
                    val contentIndex =
                        cursor.getColumnIndex(Principale.ArticleContract.COLUMN_CONTENT)
                    val publishedAtIndex =
                        cursor.getColumnIndex(Principale.ArticleContract.COLUMN_PUBLISHED_AT)
                    val sourceIndex =
                        cursor.getColumnIndex(Principale.ArticleContract.COLUMN_SOURCE)
                    val urlIndex =
                        cursor.getColumnIndex(Principale.ArticleContract.COLUMN_URL)
                    val urlToImageIndex =
                        cursor.getColumnIndex(Principale.ArticleContract.COLUMN_URL_TO_IMAGE)

                    // Obtenez les valeurs des colonnes pour la ligne actuelle
                    val id = cursor.getInt(idIndex)
                    val title = cursor.getString(titleIndex)
                    val description = cursor.getString(descriptionIndex)
                    val author = cursor.getString(authorIndex)
                    val content = cursor.getString(contentIndex)
                    val publishedAt = cursor.getString(publishedAtIndex)
                    val sourceName = cursor.getString(sourceIndex)
                    val url = cursor.getString(urlIndex)
                    val urlToImage = cursor.getString(urlToImageIndex)

                    // Créez un nouvel objet Article avec toutes les valeurs du curseur
                    val article = Article1(
                        id = id,
                        title = title,
                        description = description,
                        author = author,
                        content = content,
                        publishedAt = publishedAt,
                        source = sourceName,
                        url = url,
                        urlToImage = urlToImage
                    )

                    // Ajoutez l'objet Article à la liste
                    articleList.add(article)
                } while (cursor.moveToNext())
            }
            cursor?.close()
            val recyclerView = binding.rtl
            recyclerView?.adapter = adapter1(articleList, this)
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView?.layoutManager = layoutManager

            // Ajouter le support ItemTouchHelper ici
            val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val article = articleList[position]
                    dbHelper.deleteArticle(article.id)
                    (binding.rtl.adapter as? adapter1)?.removeItem(position)
                }
            })

            itemTouchHelper.attachToRecyclerView(recyclerView)
        }

        return binding.root
    }

    override fun onclickitem(position: Article1) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToFavDeatailsFragment(position)
        requireView().findNavController().navigate(action)
    }


}

