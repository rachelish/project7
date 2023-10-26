package com.example.project7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var nasaList: MutableList<String>
    private lateinit var rvNasa: RecyclerView

    var nasaImageURL = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNasa = findViewById(R.id.nasa_list)
        nasaList = mutableListOf()

//        val image = findViewById<ImageView>(R.id.img)

        getNasaImageURL()
    }

    private fun getNasaImageURL() {
        val client = AsyncHttpClient()

        client["https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=1&api_key=FYHvbrxnqEhwmARVfNNrQlqBaFa9Jx3quE7dnIdF", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                Log.d("Dog", "response successful$json")

                val idList = mutableListOf<String>()
                val solList = mutableListOf<String>()
                val dateList = mutableListOf<String>()
                val nasaImageArray = json.jsonObject.getJSONArray("photos")

                for (i in 0 until nasaImageArray.length()) {
                    val temp = nasaImageArray.getJSONObject(i)
                    val photo : String = temp.getString("img_src")

                    val date = temp.getString("earth_date")
                    val id = temp.getString("id")
                    val sol = temp.getString("sol")


                    nasaList.add(photo)
                    solList.add(sol)
                    idList.add(id)
                    dateList.add(date)
                    Log.d("nasaList","$photo")
                }

                val adapter = nasaAdapter(nasaList, idList, solList, dateList)
                rvNasa.adapter = adapter
                rvNasa.layoutManager = LinearLayoutManager(this@MainActivity)

                Log.d("nasaImageURL", "$nasaImageArray")

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }




}