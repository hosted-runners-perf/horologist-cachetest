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

package com.google.android.horologist.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.android.horologist.audio.ui.VolumeScreen
import com.google.android.horologist.auth.AuthMenuScreen
import com.google.android.horologist.auth.oauth.devicegrant.AuthDeviceGrantScreen
import com.google.android.horologist.auth.oauth.pkce.AuthPKCEScreen
import com.google.android.horologist.composables.DatePicker
import com.google.android.horologist.composables.TimePicker
import com.google.android.horologist.composables.TimePickerWith12HourClock
import com.google.android.horologist.datalayer.DataLayerNodesScreen
import com.google.android.horologist.datalayer.DataLayerNodesViewModel
import com.google.android.horologist.networks.NetworkScreen
import com.google.android.horologist.rotary.RotaryMenuScreen
import com.google.android.horologist.rotary.RotaryScrollScreen
import com.google.android.horologist.rotary.RotaryScrollWithFlingOrSnapScreen
import com.google.android.horologist.sectionedlist.SectionedListMenuScreen
import com.google.android.horologist.sectionedlist.expandable.SectionedListExpandableScreen
import com.google.android.horologist.sectionedlist.stateful.SectionedListStatefulScreen
import com.google.android.horologist.sectionedlist.stateless.SectionedListStatelessScreen
import java.time.LocalDateTime

@Composable
fun SampleWearApp() {
    val navController = rememberSwipeDismissableNavController()

    var time by remember { mutableStateOf(LocalDateTime.now()) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        SwipeDismissableNavHost(
            navController = navController,
            startDestination = Screen.Menu.route
        ) {
            composable(route = Screen.Menu.route) {
                MenuScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToRoute = { route -> navController.navigate(route) },
                    time = time
                )
            }
            composable(Screen.DataLayerNodes.route) {
                DataLayerNodesScreen(
                    viewModel = viewModel(factory = DataLayerNodesViewModel.Factory)
                )
            }
            composable(Screen.Network.route) {
                NetworkScreen()
            }
            composable(Screen.FillMaxRectangle.route) {
                FillMaxRectangleScreen()
            }
            composable(Screen.Volume.route) {
                VolumeScreen()
            }
            composable(Screen.ScrollAway.route) {
                ScrollScreenLazyColumn()
            }
            composable(Screen.ScrollAwaySLC.route) {
                ScrollAwayScreenScalingLazyColumn()
            }
            composable(Screen.ScrollAwayColumn.route) {
                ScrollAwayScreenColumn()
            }
            composable(Screen.DatePicker.route) {
                DatePicker(
                    date = time.toLocalDate(),
                    onDateConfirm = {
                        time = time.toLocalTime().atDate(it)
                        navController.popBackStack()
                    }
                )
            }
            composable(Screen.TimePicker.route) {
                TimePickerWith12HourClock(
                    time = time.toLocalTime(),
                    onTimeConfirm = {
                        time = time.toLocalDate().atTime(it)
                        navController.popBackStack()
                    }
                )
            }
            composable(Screen.TimeWithSecondsPicker.route) {
                TimePicker(
                    time = time.toLocalTime(),
                    onTimeConfirm = {
                        time = time.toLocalDate().atTime(it)
                        navController.popBackStack()
                    }
                )
            }
            composable(Screen.TimeWithoutSecondsPicker.route) {
                TimePicker(
                    time = time.toLocalTime(),
                    onTimeConfirm = {
                        time = time.toLocalDate().atTime(it)
                        navController.popBackStack()
                    },
                    showSeconds = false
                )
            }
            composable(route = Screen.SectionedListMenuScreen.route) {
                SectionedListMenuScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToRoute = { route -> navController.navigate(route) }
                )
            }
            composable(Screen.SectionedListStatelessScreen.route) {
                SectionedListStatelessScreen()
            }
            composable(Screen.SectionedListStatefulScreen.route) {
                SectionedListStatefulScreen()
            }
            composable(Screen.SectionedListExpandableScreen.route) {
                SectionedListExpandableScreen()
            }
            composable(route = Screen.RotaryMenuScreen.route) {
                RotaryMenuScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToRoute = { route -> navController.navigate(route) }
                )
            }
            composable(route = Screen.RotaryScrollScreen.route) {
                RotaryScrollScreen()
            }
            composable(route = Screen.RotaryScrollWithFlingScreen.route) {
                RotaryScrollWithFlingOrSnapScreen(isFling = true, isSnap = false)
            }
            composable(route = Screen.RotarySnapListScreen.route) {
                RotaryScrollWithFlingOrSnapScreen(isFling = false, isSnap = true)
            }
            composable(route = Screen.AuthMenuScreen.route) {
                AuthMenuScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToRoute = { route -> navController.navigate(route) }
                )
            }
            composable(route = Screen.AuthPKCEScreen.route) {
                AuthPKCEScreen()
            }
            composable(route = Screen.AuthDeviceGrantScreen.route) {
                AuthDeviceGrantScreen()
            }
        }
    }
}
