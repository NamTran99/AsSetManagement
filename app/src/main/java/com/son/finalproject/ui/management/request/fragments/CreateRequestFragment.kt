package com.son.finalproject.ui.management.request.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentCreateRequestBinding
import com.son.finalproject.ui.management.request.viewmodels.CreateRequestViewModel
import com.son.finalproject.utils.helper.showTimePickerDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateRequestFragment : BaseFragment<FragmentCreateRequestBinding, CreateRequestViewModel>() {
    override val viewModel: CreateRequestViewModel by viewModels()
    override val layoutId = R.layout.fragment_create_request
    private lateinit var adapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            adapter = ArrayAdapter(requireContext(), R.layout.item_asset_spinner)
            spinner.adapter = adapter
            spinner.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                   viewModel.onSelectedAssetSpinner(position)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            txtDate.setOnClickListener {
                showTimePickerDialog{
                    txtDate.text = it
                    viewModel.setDateTimeRequest(it)
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
}