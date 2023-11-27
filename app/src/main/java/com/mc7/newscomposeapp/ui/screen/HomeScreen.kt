package com.mc7.newscomposeapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mc7.newscomposeapp.R
import com.mc7.newscomposeapp.data.model.NewsEntity
import com.mc7.newscomposeapp.data.model.dummyNews
import com.mc7.newscomposeapp.ui.view.EmptyList
import com.mc7.newscomposeapp.ui.view.ListNews
import com.mc7.newscomposeapp.ui.view.SearchButton
import com.mc7.newscomposeapp.ui.viewmodel.HomeViewModel
import com.mc7.newscomposeapp.utils.ResultState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
) {
    val query by viewModel.querySearch

    viewModel.resultState.collectAsState(initial = ResultState.Loading).value.let { result ->
        when (result) {
            is ResultState.Loading -> {
                viewModel.searchNews(query)
            }

            is ResultState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::searchNews,
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
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listNews: List<NewsEntity>,
    onFavoriteIconClicked: (title: String, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Column {
        SearchButton(
            query = query,
            onQueryChange = onQueryChange
        )
        if (listNews.isNotEmpty()) {
            ListNews(
                listNews = listNews,
                onFavoriteIconClicked = onFavoriteIconClicked,
                navigateToDetail = navigateToDetail
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
fun HomeContentPreview() {
    MaterialTheme {
        HomeContent(
            query = "",
            onQueryChange = {},
            listNews = dummyNews,
            onFavoriteIconClicked = {_,_ ->},
            navigateToDetail = {}
        )
    }
}
