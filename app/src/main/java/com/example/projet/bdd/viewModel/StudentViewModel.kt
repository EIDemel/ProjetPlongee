package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.StudentEntity
import kotlinx.coroutines.launch

class StudentViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer un seul étudiant dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(student: StudentEntity) {
        Thread() { bdd.StudentDao().insertOne(student) }.start()
    }

    // Cette fonction permet d'insérer plusieurs étudiants dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg students: StudentEntity) {
        viewModelScope.launch {
            bdd.StudentDao().insert(*students)
        }
    }

    // Cette fonction permet d'insérer une liste d'étudiants dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(students: List<StudentEntity>) {
        viewModelScope.launch {
            bdd.StudentDao().insert(*students.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer tous les étudiants dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<StudentEntity>>? {
        var liste: LiveData<List<StudentEntity>>? = null;
        var thread = Thread() { liste = bdd.StudentDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}
