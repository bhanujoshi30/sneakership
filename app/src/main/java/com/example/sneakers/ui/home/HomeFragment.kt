package com.example.sneakers.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sneakers.R
import com.example.sneakers.databinding.FragmentHomeBinding
import com.example.sneakers.models.Sneaker
import com.example.sneakers.ui.ViewModel.SharedDataViewModel
import com.example.sneakers.ui.adapters.SneakerAdapter
import com.example.sneakers.utils.SneakerAdapterClickType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnQueryTextListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: SharedDataViewModel
    private var adapter: SneakerAdapter? = null
    private lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedDataViewModel::class.java]
        initUi()
        setObservers()
        return binding.root
    }

    private fun initUi() {
        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        binding.toolbar.apply {
            title = getString(R.string.app_name_allcaps)
        }

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.sneakers.value?.let { adapter?.updateData(it) }
                binding.sortSpinner.setSelection(0)
            }
        }
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

                adapter?.sort(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun setObservers() {
        viewModel.sneakers.observe(viewLifecycleOwner) { sneakers ->
            adapter =
                SneakerAdapter(sneakers) { sneakerClicked: Sneaker, sneakerSelectType: SneakerAdapterClickType ->
                    when (sneakerSelectType) {
                        SneakerAdapterClickType.OPEN_DETAILS -> {
                            viewModel.setSelectedSneakerDetail(sneakerClicked)
                        }
                        SneakerAdapterClickType.ADD_TO_CART -> {
                            viewModel.addSneakerToCart(sneakerClicked.id)
                        }
                    }
                }
            binding.recyclerGridView.adapter = adapter
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            adapter?.filter(query)
        }
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(searchView.windowToken, 0)

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText.isNullOrEmpty()){
            viewModel.sneakers.value?.let { adapter?.updateData(it) }
            binding.sortSpinner.setSelection(0)
        }
        return true
    }


}