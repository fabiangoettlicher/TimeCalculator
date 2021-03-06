/* Copyright 2017 Tailored Media GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package com.fagoett.timecalc.util

import android.content.Intent
import android.net.Uri


object Intents {

    fun web(url: String): Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    fun mail(mail: String): Intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$mail"))
    fun call(number: String): Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
    fun maps(location: String): Intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.co.in/maps?q=$location"))

}
