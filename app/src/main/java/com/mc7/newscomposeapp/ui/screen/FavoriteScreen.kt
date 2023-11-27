package com.mc7.newscomposeapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mc7.newscomposeapp.R
import com.mc7.newscomposeapp.data.model.NewsEntity
import com.mc7.newscomposeapp.data.model.dummyNews
import com.mc7.newscomposeapp.ui.view.EmptyList
import com.mc7.newscomposeapp.ui.view.ListNews
import com.mc7.newscomposeapp.ui.viewmodel.FavoriteViewModel
import com.mc7.newscomposeapp.utils.ResultState

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
) {
    viewModel.resultState.collectAsState(initial = ResultState.Loading).value.let { result ->
        when (result) {
            is ResultState.Loading -> {
                viewModel.getFavoritedNews()
            }

            is ResultState.Success -> {
                FavoriteContent(
                    listNews = result.data,
                    onFavoriteIconClicked = { title, newState ->
                        viewModel.updateNews(title, newState)
                    },
                    navigateToDetail = navigateToDetail
                )
            }

            is ResultState.Error -> {}
            else -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    listNews: List<NewsEntity>,
    onFavoriteIconClicked: (title: String, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Column {
        if (listNews.isNotEmpty()) {
            ListNews(
                listNews = listNews,
                onFavoriteIconClicked = onFavoriteIconClicked,
                navigateToDetail = navigateToDetail,
                contentPaddingTop = 16.dp
            )
        } else {
            EmptyList(
                message = stringResource(R.string.empty_data),
                modifier = Modifier
                    .testTag("emptyList")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteContentPreview() {
    MaterialTheme {
        FavoriteContent(
            listNews = dummyNews,
            onFavoriteIconClicked = {_,_ ->},
            navigateToDetail = {}
        )
    }
}
