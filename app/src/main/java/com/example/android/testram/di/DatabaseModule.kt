package com.example.android.testram.di

import android.content.Context
import androidx.room.Room
import com.example.android.testram.data.db.TestRaMDatabase
import com.example.android.testram.data.db.dao.CharacterDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val NAME_FILE_DB = "characters"

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoviesDao(database: TestRaMDatabase): CharacterDAO {
        return database.characterDAO()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TestRaMDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            TestRaMDatabase::class.java,
            NAME_FILE_DB
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
