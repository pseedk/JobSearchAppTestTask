package ru.pvkovalev.jobsearchapptesttask.data.db

import androidx.room.*
import ru.pvkovalev.jobsearchapptesttask.data.db.model.VacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchWorkDao {

    @Query("SELECT * FROM search_works_table")
    fun getVacancies(): Flow<List<VacancyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVacancy(vacancyEntity: VacancyEntity)

    @Delete
    suspend fun deleteVacancy(vacancyEntity: VacancyEntity)
}