package com.son.finalproject.ui.home.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Asset
import com.son.finalproject.data.DataUiHome
import com.son.finalproject.data.Request
import com.son.finalproject.repository.AuthRepositoryImpl
import com.son.finalproject.repository.ManageRepositoryImpl
import com.son.finalproject.utils.forceRefresh
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepositoryImpl,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {
    private val dataUi = DataUiHome()
    private val _homeData = MutableLiveData<DataUiHome>()
    val homeData get() = _homeData
    val liveUserType = MutableLiveData(0)

    var isAdminType = true
    var userID = ""

    init {
        // khởi tạo dữ liệu
        viewModelScope.launch {
            authRepository.getUserByEmail(mySharedPreferences.getUserEmail())?.let {
                userID = it.staffCode
                dataUi.fullName = "${it.firstName} ${it.lastName}"
                if (it.type == TYPE_USER) {
                    isAdminType = false
                    mySharedPreferences.saveIsAdmin(isAdminType)
                    dataUi.textBtn = R.string.create_request
                } else {
                    isAdminType = true
                    mySharedPreferences.saveIsAdmin(isAdminType)
                }
                liveUserType.value = it.type
            }
            dataUi.dateTime = SimpleDateFormat("E dd/MM/yyyy").format(Date())
            _homeData.postValue(dataUi)
        }
    }

    // di chuyển màn hình theo loại user(admin, staff) ở màn hình HOME, nút CREATE
    fun onClickCreateButton() = viewModelScope.launch {
        liveUserType.value?.let {
            if (it == TYPE_USER) {
                navigateToDestination(R.id.action_homeFragment_to_fragmentCreateRequest)
            } else {
                navigateToDestination(R.id.action_homeFragment_to_createAssetFragment)
            }
        }
    }

    // di chuyển vào màn hình Asset, mấy màn khác tương tự
    fun onClickAsset() {
        navigateToDestination(R.id.assetManageFragment)
    }

    fun onClickRequest() {
        navigateToDestination(R.id.requestManagementFragment)
    }

    fun onCLickUser() {
        navigateToDestination(R.id.userManagementFragment)
    }

    fun onClickAssignment() {
        navigateToDestination(R.id.assignManagementFragment)
    }

    fun onClickReport() {
        navigateToDestination(R.id.reportFragment2)
    }

    // lấy data để truyền vào màn hình home
    fun loadData() = viewModelScope.launch {
        val assetCount = getAllAsset().size
        val userCount = getAllUser().size
        val assignmentCount = getAllAssignment().size
        val allRequestCount = manageRepositoryImpl.getAllRequest().size
        _homeData.value?.apply {
            requestPending = getAllRequest().filter {
                it.status == Request.REQUEST_STATUS_REQUESTING
            }.size

            requestAccept = getAllRequest().filter {
                it.status == Request.REQUEST_STATUS_COMPLETE
            }.size
            assetsAvaiable = getAllAsset().filter { it.status == Asset.ASSET_STATUS_AVAILABLE }.size

            content1 = "$assetCount new asset added"
            content2 = "$userCount new user added"
            content3 = "$allRequestCount new request added"
            content4 = "$assignmentCount new assignment added"
        }
        _homeData.forceRefresh()
    }

    private suspend fun getAllRequest() =
        if (isAdminType) manageRepositoryImpl.getAllRequest() else manageRepositoryImpl.getAllRequestByUserID(
            userID
        )

    private suspend fun getAllAsset() = manageRepositoryImpl.getAllAsset()
    private suspend fun getAllUser() = manageRepositoryImpl.getAllUser()
    private suspend fun getAllAssignment() = manageRepositoryImpl.getAllAssignment()

    companion object {
        private const val TYPE_ADMIN = 0
        private const val TYPE_USER = 1
    }
}