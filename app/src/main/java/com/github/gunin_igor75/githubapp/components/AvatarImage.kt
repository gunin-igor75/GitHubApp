package com.github.gunin_igor75.githubapp.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.github.gunin_igor75.githubapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AvatarImage(
    url: String?,
    size: Dp,
    modifier: Modifier = Modifier
) {
    val modifierImage = modifier
        .size(size)
        .clip(CircleShape)

    if (url != null) {
        GlideImage(
            model = url,
            contentDescription = stringResource(R.string.avatar_user),
            modifier = modifier.then(modifierImage)

        )
    } else {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.account),
            contentDescription = "Icon account",
            modifier = modifier.then(modifierImage)
        )
    }
}