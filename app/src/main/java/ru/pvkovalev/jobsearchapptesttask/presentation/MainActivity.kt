package ru.pvkovalev.jobsearchapptesttask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ru.pvkovalev.jobsearchapptesttask.presentation.screens.main.MainScreen
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.JobSearchAppTestTaskTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobSearchAppTestTaskTheme {
                MainScreen()
            }
        }
    }
}
