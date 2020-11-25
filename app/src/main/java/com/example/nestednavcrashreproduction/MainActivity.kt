package com.example.nestednavcrashreproduction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.*

import com.example.nestednavcrashreproduction.ui.NestedNavCrashReproductionTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedNavCrashReproductionTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "MainScreen1") {

                    composable("MainScreen1") {
                        MainScreen1 {
                            navController.navigate("Nested")
                        }
                    }

                    navigation(startDestination = "ChildScreen1", route = "Nested") {
                        composable("ChildScreen1") {
                            ChildScreen1()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen1(
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Main Screen 1")
        Button(onClick = onClick) {
            Text(text = "Go To Main Screen 2")
        }
    }
}

@Composable
fun ChildScreen1() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Child Screen 1")
    }
}
