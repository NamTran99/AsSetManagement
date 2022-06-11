package com.son.finalproject.ui.management.assignment.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Assignment
import com.son.finalproject.repository.ManageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssignManagementViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {

    val liveListAssignment = MutableLiveData(listOf<Assignment>())

    private var filterByState = 0
    private var filterByString = ""

    init {
        viewModelScope.launch {
            liveListAssignment.value = getAllAssignment()
        }
    }

    fun loadData() = viewModelScope.launch {
        liveListAssignment.value = getAllAssignment()
    }

    fun onCLickNewAssignment() {
        navigateToDestination(R.id.action_assignManagementFragment_to_createAssignmentFragment)
    }

    fun onClickSetAssignmentSateType(id: Int) {
        filterByState = when (id) {
            R.id.menu_declined -> 0
            R.id.menu_completed -> 1
            R.id.menu_waiting -> 2
            R.id.menu_all -> 3
            else -> 0
        }
        filterUser()
    }

    fun onSearchTextChange(text: String) {
        filterByString = text
        filterUser()
    }

    fun removeAssignment(assignment: Assignment) = viewModelScope.launch {
        if (manageRepositoryImpl.removeAssignment(assignment) > 0) {
            showToast(R.string.remove_assignment_successfully)
            liveListAssignment.value = getAllAssignment()
        }
    }

    private fun filterUser() = viewModelScope.launch {
        val allAssignment = getAllAssignment()
        liveListAssignment.value = (if (filterByState != 3) {
            allAssignment.filter { assignment ->
                assignment.status == filterByState
            }
        } else allAssignment).filter {
            it.assetCode.contains(filterByString) ||
                    it.asset.assetName.contains(filterByString) ||
                    it.user.userName.contains(filterByString) ||
                    it.assignedDate.contains(filterByString)
        }
    }

    private suspend fun getAllAssignment() =
        manageRepositoryImpl.getAllAssignment()
}