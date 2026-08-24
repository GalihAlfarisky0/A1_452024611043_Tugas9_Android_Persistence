package com.example.tugas9persistence

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas9persistence.ui.UserScreen
import com.example.tugas9persistence.ui.UserViewModel
import com.example.tugas9persistence.ui.theme.Tugas9PersistenceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tugas9PersistenceTheme {
                val userViewModel: UserViewModel = viewModel()
                UserScreen(viewModel = userViewModel)
            }
        }
    }
}
