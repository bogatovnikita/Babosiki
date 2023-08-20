package com.bogatovnikita.babosiki.screens.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogatovnikita.babosiki.R
import com.bogatovnikita.babosiki.adapters.PopularCurrencyAdapter
import com.bogatovnikita.babosiki.databinding.FragmentPopularBinding
import com.bogatovnikita.babosiki.models.MainState
import com.bogatovnikita.babosiki.view_model.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularFragment : Fragment(R.layout.fragment_popular) {
    private val binding: FragmentPopularBinding by viewBinding()
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.screenState.collect {
                    renderState(it)
                }
            }
        }
    }

    private fun renderState(state: MainState) {
        val adapter = PopularCurrencyAdapter {
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(state.currencyList)
    }
}