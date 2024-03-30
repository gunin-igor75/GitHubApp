package com.github.gunin_igor75.githubapp.presentation.details

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.gunin_igor75.githubapp.R
import com.github.gunin_igor75.githubapp.components.AvatarImage
import com.github.gunin_igor75.githubapp.domain.model.UserDetails
import com.github.gunin_igor75.githubapp.domain.model.UserDetailsState
import com.github.gunin_igor75.githubapp.getComponentApp

private const val DEFAULT_VALUE = ""

@Composable
fun UserDetailsScreenContent(
    name: String,
    onClickBack: () -> Unit,
) {
    val component = getComponentApp()
        .getUserDetailsComponentFactory()
        .create(name)
    val viewModel: UserDetailsViewModel = viewModel(factory = component.getViewModelFactory())
    val stateUserDetails by viewModel.userDetails.collectAsState()
    val context = LocalContext.current

    when (val state = stateUserDetails) {
        is UserDetailsState.Error -> {
            Toast.makeText(
                context,
                "Error: " + state.throwable.message,
                Toast.LENGTH_LONG
            ).show()
        }

        UserDetailsState.Loading -> {
            ProgressItem()
        }

        is UserDetailsState.Success -> {
            UserDetailsCard(
                item = state.userDetails,
                onClickBack = onClickBack
            )
        }

        UserDetailsState.Initial -> {}
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsCard(
    modifier: Modifier = Modifier,
    item: UserDetails,
    onClickBack: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (header, title, body) = createRefs()
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(id = R.string.profile)) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.icon_back),
                    modifier = Modifier.clickable { onClickBack() }
                )
            },
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            }
        )
        AvatarItem(
            item = item,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(header.bottom, margin = 32.dp)
                centerHorizontallyTo(parent)
            }
        )
        CharacteristicsItem(
            item = item,
            modifier = Modifier.constrainAs(body) {
                top.linkTo(title.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 40.dp)
            }
        )
    }
}

@Composable
fun AvatarItem(
    modifier: Modifier = Modifier,
    item: UserDetails,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (image, title) = createRefs()
        AvatarImage(
            url = item.avatar,
            size = 80.dp,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            }
        )
        Text(
            text = item.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(image.bottom)
                centerHorizontallyTo(parent)
            }
        )
    }
}

@Composable
fun CharacteristicsItem(
    modifier: Modifier = Modifier,
    item: UserDetails,
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (
            email, organisation, following, followers, createdAt,
            emailValue, organisationValue, followingValue, followersValue, createdAtValue,
        ) = createRefs()
        val barrier = createEndBarrier(createdAt, margin = 32.dp)
        val guide = createGuidelineFromEnd(0.2f)
        Text(
            text = stringResource(id = R.string.email),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(email) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = stringResource(id = R.string.organisation),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(organisation) {
                top.linkTo(email.bottom, margin = 4.dp)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = stringResource(id = R.string.following),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(following) {
                top.linkTo(organisation.bottom, margin = 4.dp)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = stringResource(id = R.string.followers),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(followers) {
                top.linkTo(following.bottom, margin = 4.dp)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = stringResource(id = R.string.createdAt),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(createdAt) {
                top.linkTo(followers.bottom, margin = 4.dp)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = item.email ?: DEFAULT_VALUE,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(emailValue) {
                top.linkTo(parent.top)
                start.linkTo(barrier)
                width = Dimension.preferredWrapContent
            }
        )
        Text(
            text = item.organization ?: DEFAULT_VALUE,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(organisationValue) {
                top.linkTo(emailValue.bottom, margin = 4.dp)
                start.linkTo(barrier)
                end.linkTo(guide)
                width = Dimension.preferredWrapContent
            },
        )
        Text(
            text = item.following.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(followingValue) {
                top.linkTo(organisationValue.bottom, margin = 4.dp)
                start.linkTo(barrier)
            }
        )
        Text(
            text = item.followers.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(followersValue) {
                top.linkTo(followingValue.bottom, margin = 4.dp)
                start.linkTo(barrier)
            }
        )
        Text(
            text = item.createdAt,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(createdAtValue) {
                top.linkTo(followersValue.bottom, margin = 4.dp)
                start.linkTo(barrier)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProgressItem() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun AvatarItemPreview() {
    AvatarItem(item = UserDetails.USER_DETAILS)
}

@Preview(showBackground = true)
@Composable
fun CharacteristicsItemPreview() {
    CharacteristicsItem(item = UserDetails.USER_DETAILS)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserDetailsCardPreview() {
    UserDetailsCard(
        item = UserDetails.USER_DETAILS,
        onClickBack = {}
    )
}
