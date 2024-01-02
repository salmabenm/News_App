package kia.example.min_projet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kia.example.min_projet.databinding.FragmentScondBinding

class ScondFragment : Fragment() {
    private lateinit var binding: FragmentScondBinding
    private val args: ScondFragmentArgs by navArgs()
    private lateinit var dbHelper: ArticleHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val receivedArticle = args.article
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        // Vérifier si receivedArticle n'est pas null avant d'accéder à ses propriétés
        if (receivedArticle != null) {
            dbHelper = ArticleHelper(requireContext())
            binding = FragmentScondBinding.inflate(inflater, container, false)

            binding.textTitle.text = receivedArticle.title
            binding.source.text=receivedArticle.source.name

            binding.publ.text=receivedArticle.publishedAt
            binding.detail.text = receivedArticle.description
            binding.content.text = receivedArticle.content

            receivedArticle.urlToImage?.let {
                Picasso.get().load(it).into(binding.imageId)
            }

            binding.fab.setOnClickListener {
                Log.d("ScondFragment", "Button clicked")

                if (user != null) {
                    val uid = user.uid
                    dbHelper.insertArticle(
                        receivedArticle.title,
                        receivedArticle.description,
                        uid,
                        receivedArticle.author,
                        receivedArticle.content,
                        receivedArticle.publishedAt,
                        receivedArticle.source.name,
                        receivedArticle.url,
                        receivedArticle.urlToImage
                    )
                    Toast.makeText(requireContext(),"Article added to favorite with succes",Toast.LENGTH_SHORT).show();


                }
            }
        }
        return binding.root
    }
}