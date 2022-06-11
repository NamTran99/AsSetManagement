package com.son.finalproject.ui.management.asset.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.R
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.repository.ManageRepositoryImpl
import com.son.finalproject.utils.forceRefresh
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAssetViewModel @Inject constructor(
    application: Application,
    private val manageRepo: ManageRepositoryImpl
) : BaseViewModel(application) {

    val liveAsset = MutableLiveData(Asset())

    private var selectedCategoryID = 0
    private var currentCategory: Category? = null
    private var symbolsCategory = ""

    init {
        getListCategory()
    }

    val listCategoryName = MutableLiveData(listOf<Category>())
    // lấy dữ liệu category
    private fun getListCategory() = viewModelScope.launch {
        listCategoryName.value = manageRepo.getListCategoryName()
    }
    // THực hiện logic khi chọn thể loại Category
    fun onSelectedCategorySpinner(position: Int) = viewModelScope.launch {
        listCategoryName.value?.let {
            setAssetCategoryID(position)
            currentCategory = it[position]
            selectedCategoryID = it[position].categoryID
            symbolsCategory = it[position].categoryName.take(2)
        }
    }
    // lưu dữ liệu category khi ng dùng chọn
    private fun setAssetCategoryID(id: Int) {
        liveAsset.value?.categoryID = id
        liveAsset.forceRefresh()
    }
    // lưu dữ liệu status khi ng dùng chon
    fun setItemStatus(position: Int) {
        liveAsset.value?.status = position
        liveAsset.forceRefresh()
    }
    // lưu dữ liệu ngày tháng
    fun setDateTime(dateTime: String) {
        liveAsset.value?.installDate = dateTime
        liveAsset.forceRefresh()
    }
    // THực hiện logic khi nhấn nút save
    fun onClickSaveButton() = viewModelScope.launch {
        if (!isEditType) {
            var countItemWithCategory: String
            val listAsset =
                manageRepo.getAllAssetCodeByCategoryID(selectedCategoryID)

            countItemWithCategory = if (listAsset.isNotEmpty()) {
                (getIDBehindSymbol(listAsset[0]) + 1).toString().padStart(3, '0')
            } else {
                DEFAULT_ASSET_CODE_ID
            }

            val futureAssetID = "$symbolsCategory$countItemWithCategory"

            Log.d(TAG, "onClickSaveButton: $countItemWithCategory")

            liveAsset.value?.apply {
                assetCode = futureAssetID
                category = currentCategory ?: Category()
                manageRepo.insertSpecification(specification)
                manageRepo.insertAsset(this)
                showToast(R.string.create_asset_successfully)
                onBackStack()
            }
        } else {
            liveAsset.value?.let{
                manageRepo.updateAsset(it)
                showToast(R.string.update_user_successfully)
                onBackStack()
            }
        }
    }

    var isEditType = false

    fun setAssetID(assetID: String) = viewModelScope.launch {
        isEditType = !assetID.isNullOrEmpty()
        if(isEditType){
            liveAsset.value = manageRepo.getAssetByID(assetID)
        }
    }

    /*
    * giả sử assetcode: LA001 thì sẽ get ra 001
    * */
    private fun getIDBehindSymbol(value: String): Int {
        return value.drop(2).toInt()
    }

    companion object {
        const val DEFAULT_ASSET_CODE_ID = "001"
    }
}