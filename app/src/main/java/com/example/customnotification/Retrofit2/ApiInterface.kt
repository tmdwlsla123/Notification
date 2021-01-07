package com.example.customnotification.Retrofit2

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ApiInterface{
    // base_url + "api/login" 으로 POST 통신
    //    @POST("api/login")
    //    Call<ResLoginData> requestPostLogin(@Body ReqLoginData reqLoginData );   // @Body : request 파라미터
    @Headers("Connection: close")
    @FormUrlEncoded
    @POST("{url}")
    fun requestPost(
        @Path("url") url: String?,
        @FieldMap params: HashMap<String, String?>
    ): Call<String>

    // base_url + "api/users" 으로 GET 통신
    @Headers("Connection: close")
    @GET("{url}")
    fun requestGet(@Path("url") url: String?): Call<String?>? // @Query : url에 쿼리 파라미터 추가, encoded - true

    @Headers("Connection: close")
    @Multipart
    @POST("{url}")
    fun requestFilePost(
        @Path("url") url: String?,
        @PartMap params: HashMap<String?, String?>?,
        @Part file: MultipartBody.Part?
    ): Call<String?>?

}