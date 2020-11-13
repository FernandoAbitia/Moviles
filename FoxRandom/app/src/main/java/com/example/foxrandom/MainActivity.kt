package com.example.foxrandom

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageViewFox)
        textView = findViewById(R.id.textViewUrl)
        swipeRefresh = findViewById(R.id.refreshLayout)

        swipeRefresh.setOnRefreshListener {
            this.refreshData()
        }
        this.refreshData()

    }
    fun refreshData(){
        RetrofitCreate.foxService.getRandomFox().enqueue(object: Callback<Fox>{
            override fun onResponse(call: Call<Fox>, response: Response<Fox>) {
                if (response.isSuccessful){
                    val fox = response.body()
                    fox?.apply {
                        Glide.with(this@MainActivity)
                            .load(this.image)
                            .into(imageView)

                        textView.text = this.link
                    }

                    swipeRefresh.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<Fox>, t: Throwable) {
                Log.e(TAG,t.message,t)
                Toast.makeText(this@MainActivity,"Ocurrió un error",Toast.LENGTH_LONG).show()
            }

        })
    }
}