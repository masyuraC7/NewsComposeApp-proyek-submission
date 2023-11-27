package com.mc7.newscomposeapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mc7.newscomposeapp.R
import com.mc7.newscomposeapp.ui.viewmodel.NewsDetailViewModel
import com.mc7.newscomposeapp.utils.ResultState

@Composable
fun NewsDetailScreen(
    title: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsDetailViewModel = hiltViewModel()
) {
    viewModel.resultState.collectAsState(initial = ResultState.Loading).value.let { uiState ->
        when (uiState) {
            is ResultState.Loading -> {
                viewModel.getNewsDetail(title)
            }
            is ResultState.Success -> {
                val data = uiState.data
                NewsDetail(
                    title = data.title,
                    publishedAt = data.publishedAt,
                    urlToImage = data.urlToImage,
                    description = data.description.toString(),
                    isFavorited = data.isFavorited,
                    navigateBack = navigateBack,
                    onFavoriteButtonClicked = { title, state ->
                        viewModel.updateNews(title, state)
                    }
                )
            }
            is ResultState.Error -> {}
            else -> {}
        }
    }
}

@Composable
fun NewsDetail(
    urlToImage: String?,
    title: String,
    publishedAt: String,
    description: String,
    isFavorited: Boolean,
    navigateBack: () -> Unit,
    onFavoriteButtonClicked: (title: String, state: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            AsyncImage(
                model = urlToImage,
                contentDescription = "News Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Published At",
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = publishedAt,
                        modifier = Modifier
                            .padding(start = 2.dp, end = 8.dp)
                    )
                }
            }
            Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
            Text(
                text = description,
                fontSize = 16.sp,
                lineHeight = 28.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        IconButton(
            onClick = navigateBack,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .size(40.dp)
                .testTag("back_home")
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back_previous),
            )
        }
        IconButton(
            onClick = {
                onFavoriteButtonClicked(title, !isFavorited)
            },
            modifier = Modifier
                .padding(end = 16.dp, top = 8.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .size(40.dp)
                .background(Color.White)
                .testTag("favorite_detail_button")
        ) {
            Icon(
                imageVector = if (!isFavorited)
                    Icons.Default.FavoriteBorder
                else
                    Icons.Filled.Favorite,
                contentDescription = if (!isFavorited)
                    stringResource(R.string.add_favorite)
                else
                    stringResource(R.string.delete_favorite),
                tint = if (!isFavorited) Color.Black else Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsDetailScreenPreview(){
    MaterialTheme{
        NewsDetail(
            urlToImage = "",
            title = "Minuman",
            publishedAt = "2023",
            description = "Masih Hangat",
            isFavorited = false,
            navigateBack = {},
            onFavoriteButtonClicked = { _, _ ->}
        )
    }
}