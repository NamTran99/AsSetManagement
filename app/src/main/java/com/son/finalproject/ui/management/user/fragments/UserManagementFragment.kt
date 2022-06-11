package com.son.finalproject.ui.management.user.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentUserManagementBinding
import com.son.finalproject.ui.management.asset.adapter.UserAdapter
import com.son.finalproject.ui.management.user.fragments.CreateUserFragment.Companion.ARG_STAFF_CODE
import com.son.finalproject.ui.management.user.viewmodels.UserManagementViewModel
import com.son.finalproject.utils.dialog.DeleteDialog
import com.son.finalproject.utils.helper.showPopUp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_management.*

@AndroidEntryPoint
class UserManagementFragment :
    BaseFragment<FragmentUserManagementBinding, UserManagementViewModel>() {
    override val viewModel: UserManagementViewModel by viewModels()
    override val layoutId = R.layout.fragment_user_management
    private lateinit var userAdapter: UserAdapter
    private lateinit var dialog: DeleteDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner

            userAdapter = UserAdapter().setOnClickRemove {
                dialog = DeleteDialog().setOnClickYes {
                    Log.d(TAG, "NamTD8 - onViewCreated: $viewModel")
                    viewModel.removeUser(it)
                }.setTitle(it.userName)
                dialog.show(childFragmentManager, DeleteDialog.TAG)
            }.setOnClickEdit {
                navigateToDestination(R.id.action_userManagementFragment_to_createUserFragment, bundleOf(ARG_STAFF_CODE to it.staffCode))
            }
            recyclerUser.apply {
                adapter = userAdapter
            }
            edt_type_user.showPopUp(R.menu.menu_user_type, viewModel::onClickSetUserType)
            edtSearchUser.doAfterTextChanged {
                viewModel.onSearchNameTextChange(it.toString())
            }
        }

        initObserve()
    }

    private fun initObserve() {
        viewModel.listFilterUser.observe(viewLifecycleOwner) {
            Log.d(TAG, "initObserve: list user: $it")
            userAdapter.updateItems(it)
        }
    }
}