package com.example.permission.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.permission.models.PhotosItem
import com.example.permission.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val repository: PhotosRepository) : ViewModel() {
    private val _photos = MutableStateFlow<List<PhotosItem>>(emptyList())
    val photos: StateFlow<List<PhotosItem>> = _photos

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                repository.fetchPhotos()
                _photos.value = repository.photos.value
            } catch (_: Exception) {
            }
        }
    }
}
