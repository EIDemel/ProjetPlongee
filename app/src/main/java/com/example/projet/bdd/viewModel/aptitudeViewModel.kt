package com.example.projet.bdd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.projet.bdd.BDD
import com.example.projet.bdd.entity.AptitudeEntity

class aptitudeViewModel(app : Application): AndroidViewModel(app) {

    var Bdd = BDD.getInstance(app)


    fun insertOne(aptitude: AptitudeEntity) {
        Thread() { Bdd.AptitudeDao().insertOne(aptitude) }.start()
    }

    fun getAll(): LiveData<List<AptitudeEntity>>? {
        var liste: LiveData<List<AptitudeEntity>>? = null;
        var thread = Thread() { liste = Bdd.AptitudeDao().getAll() }
        thread.start()
        thread.join()
        return liste
    }


}