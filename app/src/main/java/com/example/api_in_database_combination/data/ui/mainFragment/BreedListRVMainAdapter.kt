package com.example.api_in_database_combination.data.ui.mainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_in_database_combination.data.model.Breed
import com.example.api_in_database_combination.databinding.BreedItemBinding

class BreedListRVMainAdapter (
    private val datasetBreedList: List<Breed>
    // Ist es dem Adapter egal, ob das eine LiveData ist ?

    //Bevor die Extension geschrieben werden kann, muss eine innder class (s.u.) geschrieben werden - ItemBinding!!!!
) : RecyclerView.Adapter<BreedListRVMainAdapter.ItemViewHolder>() {

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: BreedItemBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = BreedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return datasetBreedList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        // EINEN Breed aus der Liste holen:
        val breed = datasetBreedList[position]

        // Daten in die View einsetzen: XML-View = Object.Attribute (caution! the datatypes! (here String, all) )
        holder.binding.breedsTV.text = breed.breed
        holder.binding.countryTV.text = breed.country
        holder.binding.originTV.text = breed.origin
        holder.binding.coatTV.text = breed.coat
        holder.binding.patternTV.text = breed.pattern


        // Als nächstes die Navigation zum EditBreedFragment:



    }


}