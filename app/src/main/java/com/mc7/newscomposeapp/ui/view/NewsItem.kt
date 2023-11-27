package com.mc7.newscomposeapp.ui.view

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mc7.newscomposeapp.data.model.NewsEntity
import com.mc7.newscomposeapp.data.model.dummyNews

@Composable
fun NewsItem(
    urlToImage: String?,
    title: String,
    publishedAt: String,
    isFavorited: Boolean,
    onFavoriteIconClicked: (title: String, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Gray)
            ) {
                AsyncImage(
                    model = urlToImage,
                    contentDescription = "News Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Icon(
                    imageVector = if (isFavorited)
                        Icons.Filled.Favorite
                    else
                        Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite News Action",
                    tint = if (!isFavorited) Color.White else Color.Red,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .size(32.dp)
                        .testTag("item_favorite_button")
                        .clickable { onFavoriteIconClicked(title, !isFavorited) }
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = publishedAt,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListNews(
    listNews: List<NewsEntity>,
    onFavoriteIconClicked: (title: String, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    contentPaddingTop: Dp = 0.dp,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = contentPaddingTop
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .testTag("lazy_news")
    ) {
        items(listNews, key = { it.title }) { item ->
            NewsItem(
                title = item.title,
                publishedAt = item.publishedAt,
                urlToImage = item.urlToImage,
                isFavorited = item.isFavorited,
                onFavoriteIconClicked = onFavoriteIconClicked,
                modifier = Modifier
                    .animateItemPlacement(tween(durationMillis = 250))
                    .clickable { navigateToDetail(item.title) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListNewsPreview() {
    MaterialTheme {
        ListNews(
            listNews = dummyNews,
            onFavoriteIconClicked = {_,_ ->},
            navigateToDetail = {})
    }
}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    MaterialTheme {
        NewsItem(
            urlToImage = "",
            title = "Minuman Panas",
            publishedAt = "2023",
            isFavorited = false,
            onFavoriteIconClicked = {_,_ ->}
        )
    }
}