package com.example.todoapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.entity.ToDos
import com.example.todoapp.databinding.FragmentMainBinding
import com.example.todoapp.ui.adapter.ToDosAdapter
import com.example.todoapp.ui.viewmodel.MainViewModel
import com.example.todoapp.ui.viewmodel.UpdateViewModel
import com.example.todoapp.utils.transition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel.toDosList.observe(viewLifecycleOwner){
            val toDosAdapter = ToDosAdapter(requireContext(),it,viewModel)
            binding.toDosRecyclerView.adapter = toDosAdapter
        }

        binding.toDosRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.floatingActionButton.setOnClickListener {
            Navigation.transition(it,R.id.toSaveScreen)
        }

        /*binding.buttonToUpdate.setOnClickListener {
            val toDo = ToDos(1,"Sport")
            val toUpdateScreen = MainFragmentDirections.toUpdateScreen(toDo = toDo)
            Navigation.findNavController(it).navigate(toUpdateScreen)
        }*/

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {//Harf girdikçe ve sildikçe çalışır.
                viewModel.search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {//Klavye ara buttonu ile çalışır.
                viewModel.search(query)
                return true
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadToDos()
    }
}

