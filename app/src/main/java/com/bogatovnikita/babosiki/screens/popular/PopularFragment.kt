package com.bogatovnikita.babosiki.screens.popular

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
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
import com.bogatovnikita.babosiki.models.CurrencyItem
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
        initClickListener()
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
        binding.loadingGroup.isVisible = state.loading
        binding.groupRecyclerView.isVisible = !state.loading && !state.error
        binding.error.isVisible = state.error
        binding.requestBtn.isClickable = !state.loading && !state.error
        binding.updateBtn.isClickable = !state.loading && !state.error

        if (!state.loading && !state.error) {
            initRecyclerView(state.currencyList)
        }
    }

    private fun initRecyclerView(currencyList: List<CurrencyItem>) {
        val adapter = PopularCurrencyAdapter {}
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(currencyList)
    }

    private fun initClickListener() {
        binding.requestBtn.setOnClickListener {
            val value = binding.inputText.text.toString()

            if (value.isNotEmpty()) {
                if (value.length == 3) {
                    viewModel.requestNewCurrency(value)
                    binding.inputText.text!!.clear()
                } else {
                    Toast.makeText(
                        requireContext(),
                        R.string.enter_the_currency_you_need,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.the_field_should_not_be_empty,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.updateBtn.setOnClickListener {
            viewModel.updateCurrency()
        }
    }

}