package com.son.finalproject.ui.management.assignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentCreateAssignmentBinding
import com.son.finalproject.ui.management.assignment.viewmodels.CreateAssignmentViewModel
import com.son.finalproject.utils.helper.showTimePickerDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_assignment.*

@AndroidEntryPoint
class CreateAssignmentFragment :
    BaseFragment<FragmentCreateAssignmentBinding, CreateAssignmentViewModel>() {
    override val viewModel: CreateAssignmentViewModel by viewModels()
    override val layoutId = R.layout.fragment_create_assignment
    private lateinit var userAdapter: ArrayAdapter<String>
    private lateinit var assetAdapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = ArrayAdapter(requireContext(), R.layout.item_asset_spinner)
        assetAdapter = ArrayAdapter(requireContext(), R.layout.item_asset_spinner)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner

            spinnerUser.adapter = userAdapter
            spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    viewModel.onSpinnerUserSelected(position)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //do nothing
                }

            }

            spinnerAsset.adapter = assetAdapter
            spinnerAsset.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    viewModel.onSpinnerAssetSelected(position)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //do nothing
                }

            }
        }

        edt_select_assigned_date_assignment.setOnClickListener {
            showTimePickerDialog {
                edt_select_assigned_date_assignment.text = it
                viewModel.setAssignedDate(it)
            }
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.listFilterAssetID.observe(viewLifecycleOwner) {
            Log.d(TAG, "initObserver: listAssetID: $it")
            assetAdapter.clear()
            assetAdapter.addAll(it.map { asset -> asset.assetName })
        }

        viewModel.listFilterUserID.observe(viewLifecycleOwner) {
            Log.d(TAG, "initObserver: listUserID: $it")
            userAdapter.clear()
            userAdapter.addAll(it.map { user -> user.userName })
        }
    }


}