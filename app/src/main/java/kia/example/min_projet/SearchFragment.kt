package kia.example.min_projet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kia.example.min_projet.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), adapter.onclick, View.OnClickListener {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var requestManager: Requestmanger

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        requestManager = Requestmanger(requireContext())

        binding.search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                requestManager.getNewsHeadlines(listene, "general", query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return binding.root
    }

    val listene: OnFetchDataListener<apinews> = object : OnFetchDataListener<apinews> {
        override fun onDataFetched(articles: List<Article>, message: String) {
            if (articles.isEmpty()) {
                Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show()
            } else {
                val recyclerView = binding.recyclerView
                recyclerView?.adapter = adapter(articles, this@SearchFragment)
                val layoutManager = LinearLayoutManager(requireContext())
                recyclerView?.layoutManager = layoutManager
            }
        }

        override fun onError(message: String) {
            Toast.makeText(requireContext(), "Error!!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onclickitem(position: Article) {
        val action=SearchFragmentDirections.actionSearchFragmentToScondFragment(position)
        requireView().findNavController().navigate(action)
    }

    override fun onClick(v: View?) {
        // Implémentez le traitement lorsque le bouton est cliqué
    }
}
