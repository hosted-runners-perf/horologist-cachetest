/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.sectionedlist.stateful

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.google.android.horologist.base.ui.components.StandardChip
import com.google.android.horologist.base.ui.components.StandardChipType
import com.google.android.horologist.base.ui.components.Title
import com.google.android.horologist.base.ui.util.DECORATIVE_ELEMENT_CONTENT_DESCRIPTION
import com.google.android.horologist.composables.PlaceholderChip
import com.google.android.horologist.composables.Section
import com.google.android.horologist.composables.SectionedList
import com.google.android.horologist.composables.SectionedListScope
import com.google.android.horologist.compose.tools.WearPreviewDevices
import com.google.android.horologist.sample.R
import com.google.android.horologist.sectionedlist.stateful.SectionedListStatefulScreenViewModel.Recommendation
import com.google.android.horologist.sectionedlist.stateful.SectionedListStatefulScreenViewModel.RecommendationSectionState
import com.google.android.horologist.sectionedlist.stateful.SectionedListStatefulScreenViewModel.Trending
import com.google.android.horologist.sectionedlist.stateful.SectionedListStatefulScreenViewModel.TrendingSectionState

@Composable
fun SectionedListStatefulScreen(
    modifier: Modifier = Modifier,
    viewModel: SectionedListStatefulScreenViewModel = viewModel(),
    scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState(),
    focusRequester: FocusRequester = remember { FocusRequester() }
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SectionedList(
        focusRequester = focusRequester,
        scalingLazyListState = scalingLazyListState,
        modifier = modifier
    ) {
        topMenuSection()

        recommendationsSection(state = state, viewModel = viewModel)

        trendingSection(state = state, viewModel = viewModel)

        bottomMenuSection()
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

private fun SectionedListScope.topMenuSection() {
    section(
        listOf(
            Pair(R.string.sectionedlist_downloads_button, Icons.Default.DownloadDone),
            Pair(R.string.sectionedlist_your_library_button, Icons.Default.LibraryMusic)
        )
    ) {
        loaded { (stringResId, icon) ->
            StandardChip(
                label = stringResource(stringResId),
                onClick = { },
                icon = icon,
                chipType = StandardChipType.Secondary
            )
        }
    }
}

private fun SectionedListScope.recommendationsSection(
    state: SectionedListStatefulScreenViewModel.UiState,
    viewModel: SectionedListStatefulScreenViewModel
) {
    val recommendationsState: Section.State<Recommendation> =
        when (val recommendationSectionState = state.recommendationSectionState) {
            RecommendationSectionState.Loading -> Section.State.Loading()
            is RecommendationSectionState.Loaded -> Section.State.Loaded(
                recommendationSectionState.list
            )

            RecommendationSectionState.Failed -> Section.State.Failed()
        }

    section(recommendationsState) {
        header {
            Title(
                text = stringResource(id = R.string.sectionedlist_recommendations_title),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        loaded { recommendation: Recommendation ->
            StandardChip(
                label = recommendation.playlistName,
                onClick = { },
                icon = recommendation.icon,
                chipType = StandardChipType.Secondary
            )
        }

        loading(count = 2) {
            Column {
                PlaceholderChip(colors = ChipDefaults.secondaryChipColors())
            }
        }

        failed {
            FailedView(onClick = { viewModel.loadRecommendations() })
        }

        footer {
            StandardChip(
                label = stringResource(id = R.string.sectionedlist_see_more_button),
                onClick = { },
                chipType = StandardChipType.Secondary
            )
        }
    }
}

private fun SectionedListScope.trendingSection(
    state: SectionedListStatefulScreenViewModel.UiState,
    viewModel: SectionedListStatefulScreenViewModel
) {
    val trendingState: Section.State<Trending> =
        when (val recommendationSectionState = state.trendingSectionState) {
            TrendingSectionState.Loading -> Section.State.Loading()
            is TrendingSectionState.Loaded -> Section.State.Loaded(
                recommendationSectionState.list
            )

            TrendingSectionState.Failed -> Section.State.Failed()
        }

    section(trendingState) {
        header {
            Title(
                text = stringResource(id = R.string.sectionedlist_trending_title),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        loaded { trending: Trending ->
            StandardChip(
                label = trending.name,
                onClick = { },
                secondaryLabel = trending.artist,
                icon = Icons.Default.MusicNote,
                chipType = StandardChipType.Secondary
            )
        }

        loading(count = 2) {
            Column {
                PlaceholderChip(colors = ChipDefaults.secondaryChipColors())
            }
        }

        failed {
            FailedView(onClick = { viewModel.loadTrending() })
        }

        footer {
            StandardChip(
                label = stringResource(
                    id = R.string.sectionedlist_see_more_button
                ),
                onClick = { },
                chipType = StandardChipType.Secondary
            )
        }
    }
}

private fun SectionedListScope.bottomMenuSection() {
    section {
        loaded {
            StandardChip(
                label = stringResource(R.string.sectionedlist_settings_button),
                onClick = { },
                icon = Icons.Default.Settings,
                chipType = StandardChipType.Secondary
            )
        }
    }
}

@Composable
private fun FailedView(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .height(108.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.CloudOff,
            contentDescription = DECORATIVE_ELEMENT_CONTENT_DESCRIPTION,
            modifier = Modifier
                .size(ChipDefaults.LargeIconSize)
                .clip(CircleShape),
            tint = Color.Gray
        )

        Text(
            text = stringResource(R.string.sectionedlist_failed_to_load),
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}

@WearPreviewDevices
@Composable
fun SectionedListStatefulScreenPreview() {
    SectionedListStatefulScreen()
}
