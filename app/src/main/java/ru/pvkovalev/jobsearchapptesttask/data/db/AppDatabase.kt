package ru.pvkovalev.jobsearchapptesttask.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.pvkovalev.jobsearchapptesttask.data.db.model.VacancyEntity

@Database(
    entities = [VacancyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchWorkDao(): SearchWorkDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "search_works.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME
            ).build()
            INSTANCE = db
            return db
        }
    }
}