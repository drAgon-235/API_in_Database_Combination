package com.example.api_in_database_combination.data.ui.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.api_in_database_combination.R
import com.example.api_in_database_combination.data.ui.ViewModel
import com.example.api_in_database_combination.databinding.FragmentBreedListBinding


class BreedListFragment : Fragment() {

    // Initialisiere binding & viewModel
    private lateinit var binding: FragmentBreedListBinding
    private val viewModel: ViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBreedListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.breedsListRV.hasFixedSize()

        //Lade die Breeds aus dem VM, das aus dem Repository lädt, das aus der API lädt:
        viewModel.loadBreedListVM()

        // Die Liste, die als LiveData permanent geladen wird, wird vom Adapter beobachtet - Nicht vom Fragment !!!
        viewModel.breedsList.observe(viewLifecycleOwner){
            // Hier ist der chronolisch logischste Punkt, einen Adapter zu bauen...
            binding.breedsListRV.adapter = BreedListRVMainAdapter(it)
        }

    }

}