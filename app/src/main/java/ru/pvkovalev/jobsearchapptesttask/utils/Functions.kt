package ru.pvkovalev.jobsearchapptesttask.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.CompanyIconTintColor
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.math.absoluteValue

object Functions {

    @Composable
    fun LoadingItem() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = CompanyIconTintColor
            )
        }
    }

    @Composable
    fun ErrorItem(errorText: String) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = errorText,
                color = Color.White
            )
        }
    }

    fun String.parseToDate(): String =
        try {
            val date = LocalDate.parse(this)
            val pattern = "d MMMM"
            date.format(DateTimeFormatter.ofPattern(pattern))
        } catch (e: DateTimeParseException) {
            if (this.isNotBlank()) {
                this.split("-")
                    .filterNot { it == "00" || it == "0000" }
                    .joinToString(".")
            } else ""
        }

    fun openUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun Int.getLookingNumberText() =
        try {
            val number = this.absoluteValue
            val peopleText = when {
                number % 10 == 1 && number % 100 != 11 -> "человек"
                number % 10 in 2..4 && (number % 100 !in 12..14) -> "человека"
                else -> "человек"
            }
            "Сейчас просматривает $number $peopleText"
        } catch (e: java.lang.NumberFormatException) {
            Constants.EMPTY_STRING
        }

    @Composable
    fun Int.parseVacanciesCount() =
        try {
            when {
                this % 10 in 2..4 && this % 100 !in 12..14 -> "$this вакансии"
                this % 10 == 1 && this % 100 != 11 -> "$this вакансия"
                else -> "$this вакансий"
            }

        } catch (e: NumberFormatException) {
            Constants.EMPTY_STRING
        }

}