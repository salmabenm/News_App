package kia.example.min_projet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import kia.example.min_projet.databinding.FragmentFavDeatailsBinding
import kia.example.min_projet.databinding.FragmentFavoriteBinding
import kia.example.min_projet.databinding.FragmentScondBinding


class favDeatailsFragment : Fragment() {
    private lateinit var binding: FragmentFavDeatailsBinding
    private val args: favDeatailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view= inflater.inflate(R.layout.fragment_fav_deatails, container, false)
        binding = FragmentFavDeatailsBinding.inflate(inflater, container, false)
        val receivedArticle = args.article1
        if (receivedArticle != null) {


            binding.textTitle.text = receivedArticle.title
            binding.source.text=receivedArticle.source

            binding.publ.text=receivedArticle.publishedAt
            binding.detail.text = receivedArticle.description
            binding.content.text = receivedArticle.content

            receivedArticle.urlToImage?.let {
                Picasso.get().load(it).into(binding.imageId)
            }


    }
        return  binding.root
    }}
