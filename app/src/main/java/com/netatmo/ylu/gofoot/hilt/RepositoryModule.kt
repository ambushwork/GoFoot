package com.netatmo.ylu.gofoot.hilt

import android.content.Context
import com.netatmo.ylu.gofoot.repository.LeagueRepository
import com.netatmo.ylu.gofoot.repository.PlayerRepository
import com.netatmo.ylu.gofoot.repository.TeamRepository
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.room.GoFootRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    fun provideLeagueRepository(
        database: GoFootRoomDatabase,
        requestClient: RequestClient
    ): LeagueRepository {
        return LeagueRepository(database.leagueDao(), requestClient)
    }

    @Provides
    fun provideTeamRepository(
        requestClient: RequestClient,
        database: GoFootRoomDatabase
    ): TeamRepository {
        return TeamRepository(database.teamDao(), requestClient)
    }

    @Provides
    fun providePlayerRepository(
        requestClient: RequestClient,
        database: GoFootRoomDatabase
    ): PlayerRepository {
        return PlayerRepository(database, requestClient)
    }

    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): GoFootRoomDatabase {
        return GoFootRoomDatabase.getDatabase(applicationContext)
    }
}