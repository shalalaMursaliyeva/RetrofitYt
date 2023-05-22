package com.example.retrofittesting

import com.example.retrofittesting.model.DataFile
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET ("posts")
    fun myData() :Call<List<DataFile>>
}