package kia.example.min_projet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kia.example.min_projet.databinding.FragmentPrincipleBinding

class PrincipleFragment : Fragment(), adapter.onclick, View.OnClickListener {
    lateinit var binding: FragmentPrincipleBinding
    lateinit var requestManager:Requestmanger

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Utilisez le layout du fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_principle, container, false)
        requestManager = Requestmanger(requireContext())
        requestManager.getNewsHeadlines(listener, "general", null)

        binding.business.setOnClickListener(this)
        binding.general.setOnClickListener(this)
        binding.sports.setOnClickListener(this)
        binding.science.setOnClickListener(this)
        binding.health.setOnClickListener(this)
        binding.technology.setOnClickListener(this)
        binding.entertainment.setOnClickListener(this)
        return binding.root
    }

    val listener: OnFetchDataListener<apinews> = object : OnFetchDataListener<apinews> {
        override fun onDataFetched(articles: List<Article>, message: String) {
            if (articles.isEmpty()) {
                Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show()
            } else {
                val recyclerView = binding.recyclerView
                recyclerView?.adapter = adapter(articles,this@PrincipleFragment)
                val layoutManager = LinearLayoutManager(requireContext())
                recyclerView?.layoutManager = layoutManager
            }
        }

        override fun onError(message: String) {
            Toast.makeText(requireContext(), "Error!!!", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onClick(v: View?) {
        val button = v as Button
        val category = button.text.toString()
        requestManager.getNewsHeadlines(listener, category, null)
    }

    override fun onclickitem(position: Article) {
        val action=PrincipleFragmentDirections.actionPrincipleFragmentToScondFragment(position)
        requireView().findNavController().navigate(action)
    }
}
