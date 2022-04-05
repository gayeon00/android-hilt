/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.example.android.hilt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.hilt.LogApplication
import com.example.android.hilt.R
import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main activity of the application.
 *
 * Container for the Buttons & Logs fragments. This activity simply tracks clicks on buttons.
 */
@AndroidEntryPoint
//Fragment를 호스팅 하는 Activity를 Hilt에 알려주기
class MainActivity : AppCompatActivity() {

    @Inject lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** ServiceLocator에서 AppNavigator 인스턴스를 가져옵니다.
         * AppNavigator는 인터페이스이므로 생성자 삽입을 사용할 수 없습니다.
         * 인터페이스에 사용할 구현을 Hilt에 알리려면 Hilt 모듈 내 함수에 @Binds 주석을 사용
         */
/*
        //@Binds 주석은 추상 함수에 달아야 하고, 이 추상함수는 구현을 제공하려는 인터페이스를 반환(예: AppNavigator)
        navigator = (applicationContext as LogApplication).serviceLocator.provideNavigator(this)
*/

        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.BUTTONS)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}
