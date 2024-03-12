package com.example.volleydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.volleydemo.Constant.URL
import com.example.volleydemo.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.btnNextDog.setOnClickListener{
            makeAPICall()

        }
    }

    private fun makeAPICall() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val request = StringRequest(
            Request.Method.GET,
            URL,{
                    response ->
                val typeToken = object : TypeToken<DogResponse>(){}
                val gson= Gson()
                val dogResponse: DogResponse = gson.fromJson(response,typeToken.type)
                setImageOfRandomDog(dogResponse.url)
            },{
                Log.e("TAG","Error: $it")
            }
        )
        requestQueue.add(request)
    }

    private fun setImageOfRandomDog(url: String) {
        Picasso.get().load(url).into(binding.imageOfDog)
    }

}