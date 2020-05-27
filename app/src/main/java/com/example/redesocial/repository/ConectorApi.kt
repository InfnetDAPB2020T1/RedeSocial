package com.example.redesocial.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConectorApi {

    companion object {
        private var retrofitInstance : Retrofit? = null
        fun getRetrofitInstance(path : String?) : Retrofit {
            if(retrofitInstance == null || path != null)
            {
                retrofitInstance = Retrofit.Builder()
                    .baseUrl(path!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofitInstance as Retrofit
        }
    }
}