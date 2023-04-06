package com.example.sneakers.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sneakers.R
import com.example.sneakers.databinding.FragmentHomeBinding
import com.example.sneakers.ui.adapters.SneakerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var adapter: SneakerAdapter? = null
    private lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        observers()
        referencesUi()
        return binding.root
    }

    private fun referencesUi() {
        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        binding.recyclerGridView.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                if (position != 0) {
                    binding.textView.text = parent.getItemAtPosition(position) as String

                } else {
                    binding.textView.text = getString(R.string.sortby_popular)
                }
                adapter?.sort(position, "")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }


        val menuHost: MenuHost = requireActivity()
        val search = menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items
                menuInflater.inflate(R.menu.actionbar_menu, menu)

                val search = menu.findItem(R.id.action_search)
                val searchView = search.actionView as SearchView
                searchView.isSubmitButtonEnabled = true
                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {

                        }
                        return true
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        if (query != null) {

                        }
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        // clearCompletedTasks()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun observers() {
        viewModel.getSneakers()
        viewModel.sneakers.observe(viewLifecycleOwner) { sneakers ->
            adapter = SneakerAdapter(sneakers)
            binding.recyclerGridView.adapter = adapter
        }
    }




}