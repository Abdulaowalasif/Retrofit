package com.example.permission.retrofit

import com.example.permission.models.PhotosItem
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/photos")
   suspend fun getPhotos(): Response<List<PhotosItem>>
}