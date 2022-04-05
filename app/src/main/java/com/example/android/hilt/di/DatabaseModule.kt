package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * .@Module은 Hilt에 모듈임을 알려 주고 @InstallIn은 어느 컨테이너에서 Hilt 구성요소를 지정하여 결합을 사용할 수 있는지 Hilt에 알려 줍니다.
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    //servicelocator에서처럼 room에서 db인스턴스 받아오는 것도 필요!
    // => provides로 Hilt에 알려주기
    @Provides
    @Singleton //항상 동일한 DB인스턴스 사용하도록
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase{
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }

    //Hilt 모듈에 있는 함수에 @Provides 주석을 달아 Hilt에 생성자가 삽입될 수 없는 유형의 제공 방법을 알려 줄 수 있습니다.
    //이 함수는 LoggerLocalDataSource의 생성자에서 필요한 매개변수의 타입과
    //servicelocator에서 logsDatabase.logDao()를 기반으로 만듦
    //매개변수로 들어가는 AppDatabase의 인스턴스 제공방법도 알려줘야 함!
    @Provides
    fun provideLogDao(database: AppDatabase): LogDao{
        return database.logDao()
    }

}