/* Copyright 2016 Patrick Löwenstein
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

package com.fagoett.timecalc.ui.main

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Bindable
import com.fagoett.timecalc.R
import com.fagoett.timecalc.databinding.ActivityMainBinding
import com.fagoett.timecalc.injection.qualifier.ActivityContext
import com.fagoett.timecalc.injection.scopes.PerActivity
import com.fagoett.timecalc.ui.base.BaseActivity
import com.fagoett.timecalc.ui.base.view.MvvmView
import com.fagoett.timecalc.ui.base.viewmodel.BaseStateViewModel
import com.fagoett.timecalc.ui.base.viewmodel.BaseViewModel
import com.fagoett.timecalc.ui.base.viewmodel.MvvmViewModel
import com.fagoett.timecalc.util.extensions.onTextChange
import javax.inject.Inject


interface MainMvvm {

    interface View : MvvmView

    interface ViewModel : MvvmViewModel<View> {
        @get:Bindable
        var timeInput: String
        var calculatedTime: String
        fun onCalcClick()
        fun applyPattern(string: String)
    }
}


class MainActivity : BaseActivity<ActivityMainBinding, MainMvvm.ViewModel>(), MainMvvm.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAndBindContentView(savedInstanceState, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        //Pattern während des schreibens ändern
        //TODO: binding.etTime.onTextChange { viewModel.applyPattern(it) }
    }

}


@PerActivity
class MainViewModel
@Inject
constructor(@ActivityContext context: Context) : BaseViewModel<MainMvvm.View>(), MainMvvm.ViewModel {
    //Edit Text AccessObject
    override var timeInput: String = ""

    //Text View AccessObject
    override var calculatedTime: String = ""

    override fun applyPattern(string: String) {
        //TODO:
    }

    override fun onCalcClick() {
        val splittedTimeInput = timeInput.split(":")
        val calculatedHours = splittedTimeInput[0].toDouble() + (splittedTimeInput[1].toDouble() / 60)
        calculatedTime = "${"%.2f".format(calculatedHours)} Hours"
        notifyChange()
    }

}

