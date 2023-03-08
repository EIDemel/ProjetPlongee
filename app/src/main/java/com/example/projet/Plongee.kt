package com.example.projet

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projet.bdd.DatabaseFiller
import com.example.projet.bdd.entity.AptitudeEntity
import com.example.projet.ui.theme.ProjetTheme
import com.google.firebase.crashlytics.buildtools.ndk.internal.dwarf.DWTag

class Plongee : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    DatabaseFiller.queryRest<AptitudeEntity>("aptitude");
                    DatabaseFiller.FillBase();
//                  Greeting(DatabaseFiller.main());
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjetTheme {
        Greeting("Android")
    }
}