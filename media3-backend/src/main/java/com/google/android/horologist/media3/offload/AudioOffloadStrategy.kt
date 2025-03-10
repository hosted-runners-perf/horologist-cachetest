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

package com.google.android.horologist.media3.offload

import android.annotation.SuppressLint
import androidx.media3.exoplayer.ExoPlayer
import com.google.android.horologist.media3.ExperimentalHorologistMedia3BackendApi
import com.google.android.horologist.media3.logging.ErrorReporter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@SuppressLint("UnsafeOptInUsageError")
@ExperimentalHorologistMedia3BackendApi
/**
 * Strategy for enabling or disabling the audio offload mode.
 */
public interface AudioOffloadStrategy {
    public val offloadEnabled: Boolean

    public fun applyIndefinitely(exoPlayer: ExoPlayer, errorReporter: ErrorReporter): Flow<String>

    public object Always : AudioOffloadStrategy {
        override val offloadEnabled: Boolean
            get() = true

        override fun applyIndefinitely(
            exoPlayer: ExoPlayer,
            errorReporter: ErrorReporter
        ): Flow<String> = flow {
            exoPlayer.experimentalSetOffloadSchedulingEnabled(true)
            emit("Always")
        }

        override fun toString(): String = "Always"
    }

    public object Never : AudioOffloadStrategy {
        override val offloadEnabled: Boolean
            get() = false

        override fun applyIndefinitely(
            exoPlayer: ExoPlayer,
            errorReporter: ErrorReporter
        ): Flow<String> = flow {
            exoPlayer.experimentalSetOffloadSchedulingEnabled(false)
            emit("Never")
        }

        override fun toString(): String = "Never"
    }
}
