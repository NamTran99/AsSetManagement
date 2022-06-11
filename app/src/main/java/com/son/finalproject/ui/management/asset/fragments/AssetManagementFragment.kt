package com.son.finalproject.ui.management.asset.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentAssetManagementBinding
import com.son.finalproject.ui.management.asset.adapter.AssetAdapter
import com.son.finalproject.ui.management.asset.fragments.CreateAssetFragment.Companion.ARG_ASSET_ID
import com.son.finalproject.ui.management.asset.viewmodels.AssetManagementViewModel
import com.son.finalproject.utils.dialog.DeleteDialog
import com.son.finalproject.utils.helper.showPopUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetManagementFragment :
    BaseFragment<FragmentAssetManagementBinding, AssetManagementViewModel>() {
    override val viewModel: AssetManagementViewModel by viewModels()
    override val layoutId = R.layout.fragment_asset_management
    private lateinit var adapter: AssetAdapter
    private lateinit var dialog: DeleteDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            adapter = AssetAdapter().setOnClickRemove {
                dialog = DeleteDialog().setOnClickYes {
                    Log.d(TAG, "NamTD8 - onViewCreated: $viewModel")
                    viewModel.removeAsset(it)
                }.setTitle(it.assetName)
                dialog.show(childFragmentManager, DeleteDialog.TAG)
            }.setOnClickEdit {
                navigateToDestination(
                    R.id.action_assetManageFragment_to_createAssetFragment,
                    bundleOf(ARG_ASSET_ID to it.assetCode)
                )
            }


            recyclerAsset.adapter = adapter

            txtStateAsset.showPopUp(R.menu.menu_asset_state, viewModel::onClickStatusFilterItem)
            edtName.doAfterTextChanged {
                viewModel.onSearchNameTextChange(it.toString())
            }
        }

        initObserver()
    }

    override fun onResume() {
//        rotateWindow(WindowRotateType.Horizontal)
        viewModel.getAssetAndCategory()
        super.onResume()
    }

    override fun onStop() {
//        rotateWindow(WindowRotateType.Vertical)
        super.onStop()
    }

    private fun initObserver() {
        viewModel.pairAssetAndCategory.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }
    }
}