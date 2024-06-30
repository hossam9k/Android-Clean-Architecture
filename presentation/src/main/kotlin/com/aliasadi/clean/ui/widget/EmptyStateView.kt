package com.aliasadi.clean.ui.widget

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliasadi.clean.R
import com.aliasadi.clean.util.preview.PreviewContainer

@Composable
fun EmptyStateView(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int? = null,
    @StringRes titleRes: Int? = null,
    @StringRes subtitleRes: Int? = null,
    iconSize: Dp = 200.dp,
    titleTextSize: TextUnit = 22.sp,
    subtitleTextSize: TextUnit = 20.sp,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        iconRes?.let {
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(id = it),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                contentDescription = null
            )
        }
        titleRes?.let {
            Text(
                text = stringResource(id = it),
                textAlign = TextAlign.Center,
                fontSize = titleTextSize,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        subtitleRes?.let {
            Text(
                text = stringResource(id = it),
                fontSize = subtitleTextSize,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Preview("Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EmptyStateViewPreview() {
    PreviewContainer {
        Surface {
            EmptyStateView(
                iconRes = R.drawable.bg_empty_favorite,
                titleRes = R.string.no_favorite_movies_title,
                subtitleRes = R.string.no_favorite_movies_subtitle,
            )
        }
    }
}