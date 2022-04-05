package com.example.android.hilt.di

import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 *
 * - AppNavigator에는 활동의 특정 정보가 필요합니다(AppNavigatorImpl은 Activity를 종속 항목으로 포함하기 때문).
 * 따라서, Application 컨테이너가 아닌 Activity 정보를 사용할 수 있는 Activity 컨테이너에 설치해야 합니다.
 *
 * - 동일한 클래스에 @Binds와 @Provides 주석을 배치하면 안 됩니다.
 */
@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {
    /**
     * - AppNavigator인터페이스의 구현을 Hilt에 알리기 위한 함수
     * - 구현은 인터페이스 구현 유형(예: AppNavigatorImpl)으로 고유한 매개변수를 추가하여 지정됩니다.
     * - AppNavigatorImpl 인스턴스 제공 방법도 알려줘야 함! => 생성자에 inject추가하자!
     * */
    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}