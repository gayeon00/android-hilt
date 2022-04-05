package com.example.android.hilt.di

import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerInMemoryDataSource
import com.example.android.hilt.data.LoggerLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * 현재 Hilt는 LoggerInMemoryDataSource와 LoggerLocalDataSource의 인스턴스 제공 방법은 알고 있지만, LoggerDataSource의 경우는 어떨까요? Hilt는 LoggerDataSource를 요청 시 사용할 구현에 관해 모릅니다.
 * 이전 섹션에서 살펴본 것처럼, 모듈에서 @Binds 주석을 사용하여 Hilt에 사용할 구현을 알려 줄 수 있습니다.
 * 하지만, **동일한 프로젝트에서 두 구현을 모두 제공**해야 한다면 어떻게 할까요?
 * 예를 들어, 앱이 실행되는 동안 LoggerInMemoryDataSource를 사용하고 Service에서는 LoggerLocalDataSource를 사용하는 경우입니다.
 * */

//Hilt에 동일한 유형의 다른 구현(여러 개의 결합)을 제공하는 방법을 알리려면 한정자를 사용하면 됩니다.
@Qualifier
annotation class InMemoryLogger

@Qualifier
annotation class DatabaseLogger

@InstallIn(SingletonComponent::class)
@Module
abstract class LoggingDatabaseModule {

    @DatabaseLogger
    @Singleton
    @Binds
    abstract fun bindDatabaseLogger(impl: LoggerLocalDataSource): LoggerDataSource
}

@InstallIn(ActivityComponent::class)
@Module
abstract class LoggingInMemoryModule {

    @InMemoryLogger
    @ActivityScoped
    @Binds
    abstract fun bindInMemoryLogger(impl: LoggerInMemoryDataSource): LoggerDataSource
}