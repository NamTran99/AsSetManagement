package com.son.finalproject.ui.management.return_asset.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentReturnManagementBinding
import com.son.finalproject.ui.management.return_asset.adapter.ReturnAdapter
import com.son.finalproject.ui.management.return_asset.viewmodels.ReturnViewModel
import com.son.finalproject.utils.helper.showPopUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReturnManagementFragment : BaseFragment<FragmentReturnManagementBinding, ReturnViewModel>() {
    override val viewModel: ReturnViewModel by viewModels()
    override val layoutId = R.layout.fragment_return_management
    private lateinit var returnAdapter: ReturnAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            isAdminType = viewModel.isAdminType
            returnAdapter = ReturnAdapter(viewModel.isAdminType).setOnCLickReturn {
//                dialog = DeleteDialog().setOnClickYes {
//                    Log.d(TAG, "NamTD8 - onViewCreated: $viewModel")
//                    viewModel.removeAssignment(it)
//                }.setTitle("assignment with ID ${it.assignmentID}" )
                viewModel.returnAsset(it)
            }.setOnClickAccept { viewModel.onClickAccept(it) }.setOnClickDecline {  viewModel.onCLickDecline(it)}
            recyclerAssignment.adapter = returnAdapter

            edtTypeUser.showPopUp(
                R.menu.menu_return_state,
                viewModel::onClickSetAssignmentSateType
            )
            edtSearch.doAfterTextChanged {
                viewModel.onSearchTextChange(it.toString())
            }
        }

        initObserve()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun initObserve() {
        viewModel.liveListAssignment.observe(viewLifecycleOwner) {
            returnAdapter.updateItems(it.toMutableList())
        }
    }
}