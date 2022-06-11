package com.son.finalproject.ui.management.assignment.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Asset
import com.son.finalproject.data.AssetStatus
import com.son.finalproject.data.Assignment
import com.son.finalproject.data.User
import com.son.finalproject.repository.ManageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAssignmentViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {

    private val liveAssign = MutableLiveData(Assignment())

    val listFilterUserID = MutableLiveData<List<User>>()
    val listFilterAssetID = MutableLiveData<List<Asset>>()

    init {
        viewModelScope.launch {
            listFilterUserID.value = getAllUserID()
            listFilterAssetID.value = getAllAvailableAssetID()
        }
    }

    fun setAssignedDate(date: String) {
        liveAssign.value?.assignedDate = date
    }

    fun onSpinnerUserSelected(position: Int) {
        listFilterUserID.value?.get(position)?.apply {
            Log.d(TAG, "onSpinnerUserSelected: user $this")
            liveAssign.value?.user = this
            liveAssign.value?.userCode = this.staffCode
        }
    }

    fun onSpinnerAssetSelected(position: Int) {
        listFilterAssetID.value?.get(position)?.apply {
            Log.d(TAG, "onSpinnerUserSelected: asset $this")
            liveAssign.value?.asset = this
            liveAssign.value?.assetCode = this.assetCode
        }
    }

    fun onClickSaveAssignment() = viewModelScope.launch {
        liveAssign.value?.apply {
            if (manageRepositoryImpl.insertAssignment(this) >= 0
            ) {
                showToast(R.string.create_assignment_successfully)
                onBackStack()
            }
        }
    }

    private suspend fun getAllUserID() = manageRepositoryImpl.getAllUser()
    private suspend fun getAllAvailableAssetID() = manageRepositoryImpl.getAssetByStatus(AssetStatus.Available)
}