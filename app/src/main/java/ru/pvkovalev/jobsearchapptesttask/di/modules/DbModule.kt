package ru.pvkovalev.jobsearchapptesttask.di.modules

import android.app.Application
import ru.pvkovalev.jobsearchapptesttask.data.db.AppDatabase
import ru.pvkovalev.jobsearchapptesttask.data.db.SearchWorkDao
import ru.pvkovalev.jobsearchapptesttask.data.db.mapper.JobSearchAppDbMapper
import ru.pvkovalev.jobsearchapptesttask.data.db.repository.DbRepositoryImpl
import ru.pvkovalev.jobsearchapptesttask.domain.repository.DbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun providesRepository(
        searchWorkDao: SearchWorkDao,
        mapper: JobSearchAppDbMapper
    ): DbRepository =
        DbRepositoryImpl(searchWorkDao = searchWorkDao, mapper = mapper)

    @Singleton
    @Provides
    fun providesDao(
        application: Application
    ): SearchWorkDao {
        return AppDatabase.getInstance(application).searchWorkDao()
    }

}