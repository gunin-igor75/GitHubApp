package com.github.gunin_igor75.githubapp.presentation.users

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.paging.compose.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.gunin_igor75.githubapp.getComponentApp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreenContent(
    onClickItem: (String) -> Unit,
) {
    val component = getComponentApp()
    val viewModel: UsersViewModel = viewModel(factory = component.getViewModelFactory())
    val users = viewModel.usersPagingFlow.collectAsLazyPagingItems()
    val swipeState = rememberPullToRefreshState()
    if (swipeState.isRefreshing) {
        users.refresh()
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = users.loadState) {
        if (users.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (users.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(Modifier.nestedScroll(swipeState.nestedScrollConnection)) {
        if (users.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!swipeState.isRefreshing) {
                    items(
                        count = users.itemCount,
                        key = users.itemKey { it.id }
                    ) { index ->
                        val user = users[index]
                        user?.let {
                            UserItem(
                                user = it,
                                modifier = Modifier.fillMaxWidth(),
                                onClickItem = onClickItem
                            )
                        }
                    }
                    item {
                        if (users.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = swipeState,
            )
        }
    }
}