package com.example.retrofittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittesting.databinding.ActivityMainBinding
import com.example.retrofittesting.model.DataFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerview.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayoutManager


        // fetching data

        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
            .create(Api::class.java)

        val retrofitData = retrofitBuilder.myData()

        retrofitData.enqueue(object : Callback<List<DataFile>?> {
            override fun onResponse(call: Call<List<DataFile>?>, response: Response<List<DataFile>?>) {

                val responseBody = response.body()!!
                for(myData in responseBody){
                    myAdapter = MyAdapter(baseContext, responseBody)
                    myAdapter.notifyDataSetChanged()
                    binding.recyclerview.adapter = myAdapter
                }




            }

            override fun onFailure(call: Call<List<DataFile>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "WRong", Toast.LENGTH_LONG).show()

            }
        })
    }
}