package com.project.dentshealth.di

import android.content.Context
import androidx.room.Room
import com.project.dentshealth.model.local.HistoryDAO
import com.project.dentshealth.model.local.ReminderDAO
import com.project.dentshealth.model.local.STCDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideSTCDatabase(@ApplicationContext context: Context): STCDatabase {
        return Room
            .databaseBuilder(context, STCDatabase::class.java, "dhealth.db")
            .createFromAsset("dentsh.db")
            .build()
    }

    @Provides
    fun provideReminderDAO(stcDatabase: STCDatabase): ReminderDAO {
        return stcDatabase.getReminderDAO()
    }

    @Provides
    fun provideHistoryDAO(stcDatabase: STCDatabase): HistoryDAO {
        return stcDatabase.getHistoryDAO()
    }
}