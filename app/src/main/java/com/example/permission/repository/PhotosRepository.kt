package com.example.permission.repository

import com.example.permission.models.PhotosItem
import com.example.permission.retrofit.RetrofitApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val retrofitApi: RetrofitApi) {

    private val _photos = MutableStateFlow<List<PhotosItem>>(emptyList())
    val photos: StateFlow<List<PhotosItem>> = _photos

    suspend fun fetchPhotos() {
        _photos.value = retrofitApi.getPhotos().body()!!
    }
}
