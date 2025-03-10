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

@file:OptIn(ExperimentalHorologistPaparazziApi::class)

package com.google.android.horologist.base.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import com.google.android.horologist.base.ui.util.rememberVectorPainter
import com.google.android.horologist.compose.tools.coil.FakeImageLoader
import com.google.android.horologist.paparazzi.ExperimentalHorologistPaparazziApi
import com.google.android.horologist.paparazzi.WearPaparazzi
import org.junit.Rule
import org.junit.Test

class SecondaryChipTest {

    @get:Rule
    val paparazzi = WearPaparazzi()

    @Test
    fun default() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withSecondaryLabel() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    secondaryLabel = "Secondary label",
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = Icons.Default.Image,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withLargeIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = Icon32dp,
                    largeIcon = true,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withSecondaryLabelAndIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    secondaryLabel = "Secondary label",
                    icon = Icons.Default.Image,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withSecondaryLabelAndLargeIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    secondaryLabel = "Secondary label",
                    icon = Icon32dp,
                    largeIcon = true,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun disabled() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    secondaryLabel = "Secondary label",
                    icon = Icons.Default.Image,
                    chipType = StandardChipType.Secondary,
                    enabled = false
                )
            }
        }
    }

    @Test
    fun withLongText() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label very very very very very very very very very very very very very very very very very long text",
                    onClick = { },
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withSecondaryLabelAndLongText() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label very very very very very very very very long text",
                    onClick = { },
                    secondaryLabel = "Secondary label very very very very very very very very very long text",
                    icon = Icons.Default.Image,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun usingSmallIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = Icon12dp,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withLargeIconUsingSmallIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = Icon12dp,
                    largeIcon = true,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun usingExtraLargeIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = Icon48dp,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withLargeIconUsingExtraLargeIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = Icon48dp,
                    largeIcon = true,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withPlaceholderIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = Icons.Default.Image,
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withProgressIcon() {
        paparazzi.snapshot {
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = {
                        StandardChipIconWithProgress(
                            progress = 75f,
                            icon = Icon48dp,
                            largeIcon = true
                        )
                    },
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun withSquareIcon() {
        paparazzi.snapshot {
            // This was made to showcase that the icon can be any composable in this version of
            // StandardChip.
            Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
                StandardChip(
                    label = "Primary label",
                    onClick = { },
                    icon = {
                        Box(
                            Modifier
                                .size(32.dp)
                                .background(Color.White)
                        )
                    },
                    chipType = StandardChipType.Secondary
                )
            }
        }
    }

    @Test
    fun disabledWithIconPlaceholder() {
        paparazzi.snapshot {
            FakeImageLoader.Never.override {
                // In inspection mode will jump to placeholder
                CompositionLocalProvider(LocalInspectionMode.provides(true)) {
                    Box(
                        modifier = Modifier.background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        StandardChip(
                            label = "Primary label",
                            onClick = { },
                            secondaryLabel = "Secondary label",
                            icon = "iconUri",
                            placeholder = rememberVectorPainter(
                                image = Icons.Default.Image,
                                tintColor = Color.Black
                            ),
                            chipType = StandardChipType.Secondary,
                            enabled = false
                        )
                    }
                }
            }
        }
    }

    companion object {

        private val Icon12dp: ImageVector
            get() = ImageVector.Builder(
                name = "Icon Small",
                defaultWidth = 12f.dp,
                defaultHeight = 12f.dp,
                viewportWidth = 12f,
                viewportHeight = 12f
            )
                .materialPath {
                    horizontalLineToRelative(12.0f)
                    verticalLineToRelative(12.0f)
                    horizontalLineTo(0.0f)
                    close()
                }
                .build()

        private val Icon32dp: ImageVector
            get() = ImageVector.Builder(
                name = "Icon Large",
                defaultWidth = 32f.dp,
                defaultHeight = 32f.dp,
                viewportWidth = 32f,
                viewportHeight = 32f
            )
                .materialPath {
                    horizontalLineToRelative(32.0f)
                    verticalLineToRelative(32.0f)
                    horizontalLineTo(0.0f)
                    close()
                }
                .build()

        private val Icon48dp: ImageVector
            get() = ImageVector.Builder(
                name = "Icon Extra Large",
                defaultWidth = 48f.dp,
                defaultHeight = 48f.dp,
                viewportWidth = 48f,
                viewportHeight = 48f
            )
                .materialPath {
                    horizontalLineToRelative(48.0f)
                    verticalLineToRelative(48.0f)
                    horizontalLineTo(0.0f)
                    close()
                }
                .build()
    }
}
