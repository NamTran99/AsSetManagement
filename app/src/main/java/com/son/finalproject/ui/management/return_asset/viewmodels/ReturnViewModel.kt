package com.son.finalproject.ui.management.return_asset.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Assignment
import com.son.finalproject.data.Assignment.Companion.STATE_COMPLETED
import com.son.finalproject.data.Assignment.Companion.STATE_RETURNED
import com.son.finalproject.data.Assignment.Companion.STATE_RETURNING
import com.son.finalproject.repository.ManageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReturnViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {
    val liveListAssignment = MutableLiveData(listOf<Assignment>())

    private var filterByState = 0
    private var filterByString = ""

    val isAdminType = mySharedPreferences.getIsAdmin()
    val userID = mySharedPreferences.getUserID()

    init {
        viewModelScope.launch {
            liveListAssignment.value = getAllAssignment()
        }
    }

    fun loadData() = viewModelScope.launch {
        liveListAssignment.value = getAllAssignment()
    }

    fun onClickSetAssignmentSateType(id: Int) {
        filterByState = when (id) {
            R.id.menu_return_holding -> 1
            R.id.menu_return_waiting -> 3
            R.id.menu_return_returned -> 4
            else -> 0
        }
        filterUser()
    }

    fun onSearchTextChange(text: String) {
        filterByString = text.lowercase()
        filterUser()
    }

    fun returnAsset(assignment: Assignment) = viewModelScope.launch {
        if (assignment.status == 3) {
            assignment.status = 1
            showToast(R.string.cancel_return_request_successfully)
        } else {
            assignment.status = 3
            showToast(R.string.create_return_request_successfully)
        }
        manageRepositoryImpl.updateAssignment(assignment)
        liveListAssignment.value = getAllAssignment()
    }

    fun onClickAccept(assignment: Assignment) = viewModelScope.launch{
        manageRepositoryImpl.removeAssignment(assignment)
        manageRepositoryImpl.removeRequestByAssetCode(assignment.assetCode)
        manageRepositoryImpl.updateAsset(assignment.asset.apply { status = 0})
        liveListAssignment.value = getAllAssignment()
        showToast(R.string.accept_return_request_successfully)
    }


    fun onCLickDecline(assignment: Assignment) = viewModelScope.launch {
        manageRepositoryImpl.updateAssignment(assignment.apply { status = STATE_COMPLETED })
        liveListAssignment.value = getAllAssignment()
        showToast(R.string.decline_return_request_successfully)
    }

    private fun filterUser() = viewModelScope.launch {
        val allAssignment = getAllAssignment()
        liveListAssignment.value = (if (filterByState != 3) {
            allAssignment.filter { assignment ->
                assignment.status == filterByState
            }
        } else allAssignment).filter {
            it.assetCode.lowercase().contains(filterByString) ||
                    it.asset.assetName.lowercase().contains(filterByString) ||
                    it.user.userName.lowercase().contains(filterByString) ||
                    it.assignedDate.lowercase().contains(filterByString)
        }
    }

    private suspend fun getAllAssignment() = (if(isAdminType) manageRepositoryImpl.getAllAssignment() else
        manageRepositoryImpl.getAssignmentByUserID(userID))
            .filter {
                getListStatusFilter().contains(it.status) }


    private fun getListStatusFilter() = if(isAdminType) {
        listOf(STATE_RETURNING)}else listOf(STATE_COMPLETED, STATE_RETURNING, STATE_RETURNED)
}