package com.example.internettest

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url ="https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

        findViewById<Button>(R.id.btnHTTP).setOnClickListener{
            val json = URL(url)
            Thread {
                try{
                    val connection = json.openConnection() as HttpURLConnection
                    val data = connection.inputStream.bufferedReader().readText()
                    connection.disconnect()
                    Log.d("Flickr Cats", data);
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }.start()
        }

        findViewById<Button>(R.id.btnOkHTTP).setOnClickListener{
            val json = URL(url)
            Thread {
                try{
                    val connection = json.openConnection() as HttpURLConnection
                    val data = connection.inputStream.bufferedReader().readText()
                    connection.disconnect()
                    Log.d("Flickr OkCats", data);
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }.start()
        }
    }

    private fun getAsyncCall(url: String){
        val httpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e(TAG, "error in getting response using async okhttp call")
            }

            @Throws(IOException::class)
            override fun onResponse(call: okhttp3.Call, response: Response) {
                val responseBody = response.body
                if (!response.isSuccessful) {
                    throw IOException("Error response $response")
                }
                Log.i(TAG, responseBody!!.string())
            }
        })
    }
}