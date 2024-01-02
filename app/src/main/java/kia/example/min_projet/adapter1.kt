package kia.example.min_projet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso

import kia.example.min_projet.databinding.ItemsBinding


class adapter1(var list:List<Article1>,private val listener:adapter1.onclick): RecyclerView.Adapter<adapter1.holder1>() {
    inner class holder1(val binding:ItemsBinding): ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {val position1=adapterPosition
                if(position1!=RecyclerView.NO_POSITION){
                    listener.onclickitem(list[position1])
                } }

        }

    }
    interface onclick{
        fun onclickitem(position:Article1);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder1 {
        var tmp=LayoutInflater.from(parent.context)
        var item=ItemsBinding.inflate(tmp,parent,false)
        return holder1(item)
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: holder1, position: Int) {
        var news=list.get(position)
        holder.binding.text1.text=news.title
        holder.binding.time.text=news.publishedAt


        if(news.urlToImage!=null){
            Picasso.get().load(news.urlToImage).into( holder.binding.cardImageView)
        }
    }
    fun removeItem(position: Int) {
        val mutableList = list.toMutableList()
        mutableList.removeAt(position)
        list = mutableList.toList()
        notifyItemRemoved(position)
    }

}