package com.son.finalproject.ui.asset.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentCreateAssetBinding
import com.son.finalproject.ui.asset.viewmodels.AssetViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateAssetFragment : BaseFragment<FragmentCreateAssetBinding, AssetViewModel>() {
    override val viewModel: AssetViewModel by viewModels()
    override val layoutId = R.layout.fragment_create_asset
    private lateinit var  adapter:ArrayAdapter<String>

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            adapter =  ArrayAdapter(requireContext(), R.layout.item_asset_spinner)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.onSelectedCategorySpinner(p2)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                   // do nothing
                }

            }
        }

        viewModel.listCategoryName.observe(viewLifecycleOwner){ listCategory ->
            listCategory.map { category ->
                category.categoryName
            }.let {
                adapter.clear()
                adapter.addAll(it)
            }
        }
    }
}