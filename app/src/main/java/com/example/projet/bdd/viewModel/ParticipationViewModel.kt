package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.ParticipationEntity
import kotlinx.coroutines.launch

class ParticipationViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)

    // Cette fonction permet d'insérer une seule participation dans la base de données
    // Elle lance une nouvelle Thread pour effectuer l'insertion, car les requêtes sur le thread principal sont interdites
    fun insertOne(participation: ParticipationEntity) {
        Thread() { bdd.ParticipationDao().insertOne(participation) }.start()
    }

    // Cette fonction permet d'insérer plusieurs participations dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(vararg participations: ParticipationEntity) {
        viewModelScope.launch {
            bdd.ParticipationDao().insert(*participations)
        }
    }

    // Cette fonction permet d'insérer une liste de participations dans la base de données
    // Elle utilise le viewModelScope pour exécuter l'insertion sur une coroutine, car les requêtes sur le thread principal sont interdites
    fun insert(participations: List<ParticipationEntity>) {
        viewModelScope.launch {
            bdd.ParticipationDao().insert(*participations.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer toutes les participations dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): LiveData<List<ParticipationEntity>>? {
        var liste: LiveData<List<ParticipationEntity>>? = null;
        var thread = Thread() { liste = bdd.ParticipationDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }
}
