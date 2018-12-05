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

package com.fagoett.timecalc.util.extensions

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.fagoett.timecalc.ui.base.view.MvvmView
import com.fagoett.timecalc.ui.base.viewmodel.MvvmViewModel
import com.fagoett.timecalc.ui.base.viewmodel.NoOpViewModel


// Bundle

fun <T : Parcelable> Bundle.getParcelable(key: String, defaultObject: T): T = if (containsKey(key)) {
    getParcelable(key)!!
} else {
    defaultObject
}


// ViewModel

fun <V : MvvmView> MvvmViewModel<V>.attachViewOrThrowRuntimeException(view: MvvmView, savedInstanceState: Bundle?) {
    try {
        @Suppress("UNCHECKED_CAST")
        this.attachView(view as V, savedInstanceState)
    } catch (e: ClassCastException) {
        if (this !is NoOpViewModel<*>) {
            throw RuntimeException(javaClass.simpleName + " must implement MvvmView subclass as declared in " + this.javaClass.simpleName)
        }
    }
}


// Fragment

inline fun Fragment.withArgs(argsFun: Bundle.() -> Unit) = apply { arguments = Bundle().apply(argsFun) }

// EditText

fun EditText.onTextChange(oTC: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            oTC.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    })
}
