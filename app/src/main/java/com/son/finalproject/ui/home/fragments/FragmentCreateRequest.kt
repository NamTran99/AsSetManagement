package com.son.finalproject.ui.home.fragments;

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentCreateRequestBinding
import com.son.finalproject.ui.home.viewmodels.CreateRequestViewModel
import com.son.finalproject.utils.helper.showTimePickerDialog
import dagger.hilt.android.AndroidEntryPoint

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/22/2022
*/
@AndroidEntryPoint
public class FragmentCreateRequest : BaseFragment<FragmentCreateRequestBinding,CreateRequestViewModel>(),AdapterView.OnItemSelectedListener {
    private lateinit var adapter: ArrayAdapter<String>
    override val viewModel: CreateRequestViewModel by viewModels()
    override val layoutId: Int get() = R.layout.fragment_create_request

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ArrayAdapter(requireContext(), R.layout.item_asset_spinner)

        binding.apply {
            action = viewModel
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this@FragmentCreateRequest
            txtDate.setOnClickListener {
                showTimePickerDialog{
                    txtDate.text = it
                }
            }
        }

        viewModel.listAsset.observe(viewLifecycleOwner){ listAsset->
            listAsset.map {
                it.assetName
            }.let {
                adapter.clear()
                adapter.addAll(it)
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        viewModel.onSelectedAssetSpinner(p2)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //to do something
    }
}
