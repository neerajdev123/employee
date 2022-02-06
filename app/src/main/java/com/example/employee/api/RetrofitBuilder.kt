package com.example.employee.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {
        var retrofitBuilder : Retrofit? = null

        fun getInstance(baseUrl : String) : Retrofit {
            if(retrofitBuilder == null){
                retrofitBuilder = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofitBuilder!!
        }
    }
}