package com.son.finalproject.ui.home.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Assignment
import com.son.finalproject.data.Request
import com.son.finalproject.repository.ManageRepositoryImpl
import com.son.finalproject.utils.Extension.getLocalDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl

) : BaseViewModel(application) {

    val isAdminModel = mySharedPreferences.getIsAdmin()

    val liveRequest = MutableLiveData(listOf<Request>())

    private var stateFilter = 0
    private var filterString = ""

    init {
        viewModelScope.launch {
            filterRequest()
        }
    }

    fun floatingActionButtonClick() {
        navigateToDestination(R.id.action_requestManagementFragment_to_fragmentCreateRequest)
    }

    // Hỗ trọ filter theo trạng thái
    fun actionItemSpinner(itemId: Int) {
        stateFilter = when (itemId) {
            R.id.menu_requesting -> 0
            R.id.menu_request_declined -> 1
            R.id.menu_request_completed -> 2
            else -> 0
        }
        filterRequest()
    }

    // Hỗ trọ filter trong search
    fun onSearchTextChange(text: String) {
        filterString = text
        filterRequest()
    }

    // Hỗ trợ filer
    fun filterRequest() = viewModelScope.launch {
        val allRequest = getRequestByUser()
        Log.d(TAG, "filterRequest: getRequest - $allRequest")
        liveRequest.value = allRequest.filter { request ->
            request.status == stateFilter && (request.asset.assetName.contains(filterString)
                    || request.assetCode.contains(filterString)
                    )
        }
    }

    fun onAcceptRequest(request: Request) = viewModelScope.launch {
        val assignBy = mySharedPreferences.getUserID()

        val assignment = Assignment(
            userCode = request.user.staffCode,
            assetCode = request.assetCode,
            assignedDate = getLocalDate(),
            status = 1, //completed
            asset = request.asset,
            user = request.user,
            assignedBy = assignBy,
        )

        manageRepositoryImpl.insertAssignment(assignment)
        manageRepositoryImpl.updateRequest(request.apply { status = 2 })
        manageRepositoryImpl.updateAsset(request.asset.apply { status = 2 })
        showToast(R.string.accept_request_successfully)
        filterRequest()
    }

    fun onRemoveRequest(request: Request) = viewModelScope.launch {
        if (manageRepositoryImpl.removeRequest(request) != 0  )
        {
            showToast(R.string.remove_request_successfully)
            filterRequest()
        }
    }

    fun onDeclinedRequest(request: Request) = viewModelScope.launch{
        val assignBy = mySharedPreferences.getUserID()

        val assignment = Assignment(
            userCode = request.user.staffCode,
            assetCode = request.assetCode,
            assignedDate = getLocalDate(),
            status = 0, //completed
            user = request.user,
            assignedBy = assignBy,
        )

        manageRepositoryImpl.insertAssignment(assignment)
        manageRepositoryImpl.updateRequest(request.apply { status = 1 })
        showToast(R.string.declined_request_successfully)
        filterRequest()
    }


    private suspend fun getRequestByUser() =  if (isAdminModel) getAllRequest() else getAllRequestByUser()

    private suspend fun getAllRequest() =
        manageRepositoryImpl.getAllRequest()

    private suspend fun getAllRequestByUser() =
        manageRepositoryImpl.getRequestByUserID(mySharedPreferences.getUserID())


}