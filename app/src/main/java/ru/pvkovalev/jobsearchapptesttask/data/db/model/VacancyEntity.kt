package ru.pvkovalev.jobsearchapptesttask.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_works_table")
data class VacancyEntity(
    @PrimaryKey
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val town: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val isFavorite: Boolean
)