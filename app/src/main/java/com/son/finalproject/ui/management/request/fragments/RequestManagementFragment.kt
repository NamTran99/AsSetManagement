package com.son.finalproject.ui.management.request.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.son.finalproject.R
import com.son.finalproject.base.BaseFragment
import com.son.finalproject.databinding.FragmentRequestManagmentBinding
import com.son.finalproject.ui.home.viewmodels.RequestViewModel
import com.son.finalproject.ui.management.request.adapters.RequestAdapter
import com.son.finalproject.utils.helper.showPopUp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_request_managment.*

@AndroidEntryPoint
class RequestManagementFragment :
    BaseFragment<FragmentRequestManagmentBinding, RequestViewModel>() {
    override val viewModel: RequestViewModel by viewModels()
    override val layoutId = R.layout.fragment_request_managment
    private lateinit var requestAdapter: RequestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            action = viewModel
            lifecycleOwner = viewLifecycleOwner
            isAdmin = viewModel.isAdminModel
            requestAdapter = RequestAdapter(viewModel.isAdminModel).setAcceptClickButton {
                viewModel.onAcceptRequest(it)
            }.setDeclineClickButton {   viewModel.onDeclinedRequest(it)  }.setRemoveClickButton {
                viewModel.onRemoveRequest(it)
            }
            recyclerRequest.adapter = requestAdapter
            txtStateRequest.showPopUp(R.menu.menu_request_state ,action = viewModel::actionItemSpinner)
            edt_search_request.doAfterTextChanged {
                viewModel.onSearchTextChange(it.toString())
            }
        }

        initObserve()
    }

    override fun onResume() {
        super.onResume()
        viewModel.filterRequest()
    }

    private fun initObserve() {
        viewModel.liveRequest.observe(viewLifecycleOwner){
            requestAdapter.updateItems(it.toMutableList())
        }
    }
}