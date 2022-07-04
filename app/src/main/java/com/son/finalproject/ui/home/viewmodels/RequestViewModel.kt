package com.son.finalproject.ui.home.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Assignment
import com.son.finalproject.data.Request
import com.son.finalproject.data.Request.Companion.REQUEST_STATUS_COMPLETE
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
        filterString = text.lowercase()
        filterRequest()
    }

    // Hỗ trợ filer
    fun filterRequest() = viewModelScope.launch {
        val allRequest = getRequestByUser()
        Log.d(TAG, "filterRequest: getRequest - $allRequest")
        liveRequest.value = allRequest.filter { request ->
            request.status == stateFilter && (request.asset.assetName.lowercase().contains(filterString)
                    || request.assetCode.lowercase().contains(filterString)
                    )
        }
    }

    //Thực hiện logic khi nhấn nút accept Request
    fun onAcceptRequest(request: Request) = viewModelScope.launch {
        val adminID = mySharedPreferences.getUserID()
        val assignBy = manageRepositoryImpl.getUserByID(adminID).fullName

        val assignment = Assignment(
            userCode = request.user.staffCode,
            assetCode = request.assetCode,
            assignedDate = getLocalDate(),
            status = Assignment.STATE_COMPLETED, //completed
            asset = request.asset,
            user = request.user,
            assignedBy = assignBy,
        )

        manageRepositoryImpl.insertAssignment(assignment)
        manageRepositoryImpl.getAllAssignment().filter { it.assetCode == request.assetCode && it.status == Assignment.STATE_WAITING }.map {
            it.status = Assignment.STATE_CANCELED
            it
        }.let{
            manageRepositoryImpl.updateAssignment(it)
        }


        manageRepositoryImpl.updateAsset(request.asset.apply { status = Asset.ASSET_STATUS_ASSIGNED })
        manageRepositoryImpl.getAllRequestByAssetCode(request.assetCode).map {
            it.status = Request.REQUEST_STATUS_DECLINED
            it
        }.let {
            manageRepositoryImpl.updateRequest(it)
        }
        manageRepositoryImpl.updateRequest(request.apply { status = REQUEST_STATUS_COMPLETE })
        showToast(R.string.accept_request_successfully)
        filterRequest()
    }
    //Thực hiện logic khi nhấn nút remove Request
    fun onRemoveRequest(request: Request) = viewModelScope.launch {
        if (manageRepositoryImpl.removeRequest(request) != 0  )
        {
            showToast(R.string.remove_request_successfully)
            filterRequest()
        }
    }
    //Thực hiện logic khi nhấn nút decline Request
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
        manageRepositoryImpl.updateRequest(request.apply { status = Request.REQUEST_STATUS_DECLINED })
        showToast(R.string.declined_request_successfully)
        filterRequest()
    }

    // lấy dữ liệu request theo loại user đang đăng nhập
    private suspend fun getRequestByUser() =  if (isAdminModel) getAllRequest() else getAllRequestByUserID()

    // lấy tất cả dữ liệu request
    private suspend fun getAllRequest() =
        manageRepositoryImpl.getAllRequest()
    // lấy tất cả dữ liệu request theo staff ID
    private suspend fun getAllRequestByUserID() =
        manageRepositoryImpl.getRequestByUserID(mySharedPreferences.getUserID())
}