package com.example.projet

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projet.bdd.BDD
import com.example.projet.bdd.DatabaseFiller
import com.example.projet.bdd.viewModel.*
import com.example.projet.ui.theme.ProjetTheme

class Plongee : ComponentActivity() {
    companion object{
        private var context: Context? = null

        fun getAppContext(): Context? {
            return Plongee.context
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        Plongee.context = getApplicationContext();

        super.onCreate(savedInstanceState)

        Thread{
            // fill database if it is empty
            if(BDD.BDDNotFull()){
                Plongee.getAppContext()!!.deleteDatabase("BDD_plongeurs")
                BDD.getInstance(Plongee.getAppContext()!!)

                DatabaseFiller.FillBase();
            }
        }.start()

        setContent {
            val aptitudeVM by viewModels<AptitudeViewModel>()
            val ContentVM by viewModels<ContentViewModel>()
            val FormationVM by viewModels<FormationViewModel>()
            val LevelVM by viewModels<LevelViewModel>()
            val ParticipationVM by viewModels<ParticipationViewModel>()
            val SessionVM by viewModels<SessionViewModel>()
            val SkilVM by viewModels<SkillViewModel>()
            val StatusVM by viewModels<StatusViewModel>()
            val StudentVM by viewModels<StudentViewModel>()
            ProjetTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        tableauFormation()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun tableauFormation(){

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjetTheme {
        Greeting("Android")
    }
}