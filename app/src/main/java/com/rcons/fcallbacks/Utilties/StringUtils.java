/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rcons.fcallbacks.Utilties;

import android.text.TextUtils;

public class StringUtils {

    public static boolean isEmpty(String text) {

        if (text == null || text.equalsIgnoreCase("null") || text.equalsIgnoreCase("")) {

            return true;
        }

        return TextUtils.isEmpty(text.trim());
    }

    public static boolean isEmpty(CharSequence text) {

        if (text == null) {

            return true;
        }

        return isEmpty(text.toString());
    }



}
