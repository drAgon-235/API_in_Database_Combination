package com.example.api_in_database_combination.data.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_in_database_combination.data.db.BreedDatabase.Companion.getDatabase
import com.example.api_in_database_combination.data.remote.BreedApi
import de.syntaxinstitut.android_ww_template.data.Repository
import kotlinx.coroutines.launch

const val TAG = "ViewModelTAG"


class ViewModel(application: Application) : AndroidViewModel(application) {

    // Neue Parameter im repository
    val repository = Repository(BreedApi, getDatabase(application))

    val breedsList= repository.breedsList


    init{
        // loadBreedListVM()
    }

    fun loadBreedListVM() {
        Log.d(TAG, "Load Breed List from Repisitory to VM")
        viewModelScope.launch {
            repository.deleteAllBreeds()
            repository.loadBreedListRepository()
            Log.d(TAG, " Load Breed List from Repisitory to VM DONE")

        }
    }

}