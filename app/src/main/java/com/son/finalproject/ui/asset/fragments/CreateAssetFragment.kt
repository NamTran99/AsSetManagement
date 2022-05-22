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
import com.son.finalproject.utils.helper.showTimePickerDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_asset.*

@AndroidEntryPoint
class CreateAssetFragment : BaseFragment<FragmentCreateAssetBinding, AssetViewModel>(){
    override val viewModel: AssetViewModel by viewModels()
    override val layoutId = R.layout.fragment_create_asset
    private lateinit var adapter: ArrayAdapter<String>

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            adapter = ArrayAdapter(requireContext(), R.layout.item_asset_spinner)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.onSelectedCategorySpinner(p2)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // do nothing
                }
            }
            groupRadio.setOnCheckedChangeListener { _, viewId ->
                val radioButton: View = groupRadio.findViewById(viewId)
                viewModel.setItemStatus(groupRadio.indexOfChild(radioButton))
            }
            btn_cancel_asset.setOnClickListener {
                onBackFragment()
            }
            edtInstallDateAsset.setOnClickListener { _ ->
                showTimePickerDialog{
                    Log.d(TAG, "onViewCreated: current date time - $it")
                    edtInstallDateAsset.text = it
                    viewModel.setDateTime(it)
                }
            }
        }

        viewModel.listCategoryName.observe(viewLifecycleOwner) { listCategory ->
            listCategory.map { category ->
                category.categoryName
            }.let {
                adapter.clear()
                adapter.addAll(it)
            }
        }
    }
}