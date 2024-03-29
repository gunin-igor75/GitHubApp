package com.github.gunin_igor75.githubapp.presentation.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.github.gunin_igor75.githubapp.R
import com.github.gunin_igor75.githubapp.domain.model.User


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier,
    onClickItem: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .clickable {
                onClickItem(user.title)
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(vertical = 8.dp)
        ) {
            val modifierImage = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .weight(1f)
            if (user.avatar != null) {
                GlideImage(
                    model = user.avatar,
                    contentDescription = stringResource(R.string.avatar_user),
                    modifier = Modifier.then(modifierImage)

                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.account),
                    contentDescription = "Icon account",
                    modifier = Modifier.then(modifierImage)
                )
            }
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = user.title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.id.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserItem(
        user = User(
            id = 12554L,
            title = "igor-gunin75",
            avatar = null
        ),
        onClickItem = {}
    )
}

