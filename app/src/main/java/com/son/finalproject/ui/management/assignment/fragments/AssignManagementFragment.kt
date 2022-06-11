package com.son.finalproject.ui.management.assignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentAssignManagementBinding
import com.son.finalproject.ui.management.assignment.adapter.AssignmentAdapter
import com.son.finalproject.ui.management.return_asset.adapter.ReturnAdapter
import com.son.finalproject.ui.management.assignment.viewmodels.AssignManagementViewModel
import com.son.finalproject.utils.dialog.DeleteDialog
import com.son.finalproject.utils.helper.showPopUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssignManagementFragment :
    BaseFragment<FragmentAssignManagementBinding, AssignManagementViewModel>() {
    override val viewModel: AssignManagementViewModel by viewModels()
    override val layoutId = R.layout.fragment_assign_management
    private lateinit var assignmentAdapter: AssignmentAdapter
    private lateinit var dialog: DeleteDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            assignmentAdapter = AssignmentAdapter().setOnClickRemove {
                dialog = DeleteDialog().setOnClickYes {
                    Log.d(TAG, "NamTD8 - onViewCreated: $viewModel")
                    viewModel.removeAssignment(it)
                }.setTitle("assignment with ID ${it.assignmentID}" )
                dialog.show(childFragmentManager, DeleteDialog.TAG)
            }
            recyclerAssignment.adapter = assignmentAdapter

            edtTypeUser.showPopUp ( R.menu.menu_assignment_state, viewModel::onClickSetAssignmentSateType)
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
        viewModel.liveListAssignment.observe(viewLifecycleOwner){
            assignmentAdapter.updateItems(it.toMutableList())
        }
    }
}