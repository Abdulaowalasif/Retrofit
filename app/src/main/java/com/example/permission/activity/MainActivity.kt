package com.example.permission.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.permission.R
import com.example.permission.models.PhotosItem
import com.example.permission.viewmodels.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
    }

}

@Composable
fun MainScreen(viewModel: PhotosViewModel) {
    val photosState = viewModel.photos.collectAsState(initial = emptyList())
    val context = LocalContext.current
    LazyColumn {
        items(photosState.value) { item ->
            ListItems(item = item, onClick = {
                Toast.makeText(
                    context,
                    "Item ${item.id} clicked",
                    Toast.LENGTH_SHORT
                ).show()

            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItems(item: PhotosItem, onClick: () -> Unit) {
    Card(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(150.dp)
            .shadow(elevation = 15.dp, clip = true, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.url,
                contentDescription = "",
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = item.title, modifier = Modifier.padding(start = 8.dp))
        }
    }
    Spacer(modifier = Modifier.size(10.dp))

}
