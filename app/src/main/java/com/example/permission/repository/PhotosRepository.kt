package com.example.permission.repository

import com.example.permission.models.PhotosItem
import com.example.permission.retrofit.RetrofitApi
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val retrofitApi: RetrofitApi) {


    suspend fun fetchPhotos(): Response<List<PhotosItem>> {
        return retrofitApi.getPhotos()
    }
}
