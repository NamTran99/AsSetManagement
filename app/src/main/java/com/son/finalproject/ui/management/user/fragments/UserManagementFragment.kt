package com.son.finalproject.ui.management.user.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.base.BaseRecyclerViewAdapter
import com.son.finalproject.databinding.FragmentUserManagementBinding
import com.son.finalproject.ui.management.asset.adapter.UserAdapter
import com.son.finalproject.ui.management.user.viewmodels.UserManagementViewModel
import com.son.finalproject.utils.helper.showPopUp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_management.*

@AndroidEntryPoint
class UserManagementFragment :
    BaseFragment<FragmentUserManagementBinding, UserManagementViewModel>() {
    override val viewModel: UserManagementViewModel by viewModels()
    override val layoutId = R.layout.fragment_user_management
    private lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner

            userAdapter = UserAdapter(viewModel)
            recyclerUser.apply {
                adapter = userAdapter
            }
            edt_type_user.showPopUp ( R.menu.menu_user_type, viewModel::onClickSetUserType)
            edtSearchUser.doAfterTextChanged {
                viewModel.onSearchNameTextChange(it.toString())
            }
        }

        initObserve()
    }

    private fun initObserve() {
        viewModel.listFilterUser.observe(viewLifecycleOwner){
            Log.d(TAG, "initObserve: list user: $it")
            userAdapter.updateItems(it)
        }
    }
}