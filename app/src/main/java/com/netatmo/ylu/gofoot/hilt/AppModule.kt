package com.netatmo.ylu.gofoot.hilt

import android.content.Context
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideRequestClient(@ApplicationContext context: Context): RequestClient {
        return RequestClient(context)
    }
}