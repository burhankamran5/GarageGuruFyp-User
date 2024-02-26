package com.bkcoding.garagegurufyp_user.di
import android.app.Application
import android.content.Context
import com.bkcoding.garagegurufyp_user.sharedpref.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideSharedPref(context: Context): PreferencesManager {
        return PreferencesManager(context)
    }


}