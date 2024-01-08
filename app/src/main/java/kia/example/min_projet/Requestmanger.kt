package kia.example.min_projet

import android.content.Context
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Requestmanger(var context:Context) {


    // Créez une instance de Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getNewsHeadlines(
        listener: OnFetchDataListener<apinews>,
        category: String,
        query: String?
    ) {
        val callnew = retrofit.create(callNewsapi::class.java)
        val call=callnew.getNews("in",category,query,"6d625c6aa62a459f95bce7d311d9edb7")
        call.enqueue(object : Callback<apinews> {
                override fun onResponse(call: Call<apinews>, response: Response<apinews>) {
                    if (response.isSuccessful) {

                        response.body()
                            ?.let { listener.onDataFetched(it.articles, response.message()) }
                    } else {


                        Toast.makeText(context, "erreur", Toast.LENGTH_SHORT).show();
                    }
                }

                override fun onFailure(call: Call<apinews>, t: Throwable) {
                    listener.onError("Request Failed");
                }


            });

    }
    interface  callNewsapi{
       @GET("top-headlines")
       fun getNews(
           @Query("country") country:String,
           @Query("category") category:String,
           @Query("q") query: String?,
           @Query("apiKey") apiKey: String,

       ): Call<apinews>
   }
}