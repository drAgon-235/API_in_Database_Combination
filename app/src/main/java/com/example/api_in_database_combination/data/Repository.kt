package de.syntaxinstitut.android_ww_template.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.api_in_database_combination.data.db.BreedDatabase
import com.example.api_in_database_combination.data.model.Breed
import com.example.api_in_database_combination.data.remote.BreedApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TAG = "RepositoryTAG"


class Repository(private val breedsApi: BreedApi, private val database: BreedDatabase){
// 1. den Konstrukor mit 'database' ergänzen

    // 2. Live Data die sich permanent die Daten von der Database holt:
    //private val _breedsList: MutableLiveData<List<Breed>> = MutableLiveData<List<Breed>>()
    val breedsList: LiveData<List<Breed>> = database.breedDao.getAll()
    //    get() = _breedsList

    suspend fun loadBreedListRepository(){
        Log.d(TAG, "loadBreedListRepository")
        try {
            // withContext war der Bringer !!!
            withContext(Dispatchers.IO){
                // Erst daten von API laden
                val loadedBreeds = breedsApi.apiService.getBreedsFromAPI()
                // die Daten vom API werden in die database gesteckt und dabei auch entpackt in das Listen-Format:
                database.breedDao.insertItems(loadedBreeds.data)
                Log.d(TAG, "loadBreedListRepository DONE")
            }
        }catch (e: Exception){
            Log.e(TAG, "ERROR: while loadBreedListRepository")
        }

        // Alter Code ohne Database (zum Vergleich):
        // dann in Format einer Liste "umwandeln" (entpacken gem. .data.model), die der Adapter benötigt:
        //val workingBreedsList = loadedBreeds.data
        // letztendlich in die LiveData '_breedsList' des Repository packen, die im ViewModel benutzt wird:
        //_breedsList.postValue(workingBreedsList)
    }

    suspend fun deleteAllBreeds() {
        try {
            database.breedDao.deleteAll()
            Log.d(TAG, "PRE-deleting DONE")

        } catch (e: java.lang.Exception) {
            Log.e("ERROR", "Error deleting all breeds: $e")
        }
    }


}