package com.example.customnotification.Retrofit2

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {
    companion object {
        private var retrofit: Retrofit? = null

        // Http 통신을 위한 Retrofit 객체반환
        fun getRetrofit(): Retrofit? {
            if (retrofit == null) {
                val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()
                val builder = Retrofit.Builder()
                builder.baseUrl("http://119.196.216.223/") //http://ccit2020.cafe24.com:8082/http://10.0.2.2
                //            builder.addConverterFactory( GsonConverterFactory.create() );  // 받아오는 Json 구조의 데이터를 객체 형태로 변환
                builder.addConverterFactory(ScalarsConverterFactory.create()) // String 등 처리시
                builder.client(okHttpClient)
                retrofit = builder.build()
            }
            return retrofit
        }

    }
}