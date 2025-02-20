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

package com.google.android.horologist.base.ui.components

import androidx.compose.runtime.Composable
import com.google.android.horologist.compose.tools.WearPreviewDevices

@WearPreviewDevices
@Composable
fun AlertDialogAlertPreview() {
    AlertDialogAlert(
        title = "Title",
        body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        onCancelButtonClick = { },
        onOKButtonClick = { },
        okButtonContentDescription = "Ok",
        cancelButtonContentDescription = "Cancel"
    )
}

@WearPreviewDevices
@Composable
fun AlertDialogAlertWithLongBodyPreview() {
    AlertDialogAlert(
        title = "Title",
        body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        onCancelButtonClick = { },
        onOKButtonClick = { },
        okButtonContentDescription = "Ok",
        cancelButtonContentDescription = "Cancel"
    )
}
