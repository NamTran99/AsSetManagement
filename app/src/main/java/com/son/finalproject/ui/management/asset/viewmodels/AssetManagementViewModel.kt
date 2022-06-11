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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetManagementViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {

    private val _pairAssetAndCategory = mutableListOf<Pair<Asset, Category>>()
    val pairAssetAndCategory = MutableLiveData(_pairAssetAndCategory)

    fun getAssetAndCategory() = viewModelScope.launch {
        val mapAssetCategory = manageRepositoryImpl.loadAssetAndCategory()

        _pairAssetAndCategory.clear()

        mapAssetCategory.forEach {
            it.value.map { asset ->
                _pairAssetAndCategory.add(Pair(asset, it.key))
            }
        }

        Log.d(TAG, "getAssetAndCategory: $_pairAssetAndCategory")
        pairAssetAndCategory.value = _pairAssetAndCategory
    }
    // CHuyển sang màn hình tạo asset khi nhấn nút "tạo asset"
    fun onClickCreateAsset() {
        navigateToDestination(R.id.action_assetManageFragment_to_createAssetFragment)
    }

    var filterStatusID = FILTER_DEFAULT_STATUS_ID
    var filterName = EMPTY_STRING

    // position 2 là show all
    fun onClickStatusFilterItem(statusID: Int) {
        filterStatusID = when (statusID) {
            R.id.menu_available -> 0
            R.id.menu_not_available -> 1
            else -> 2
        }

        filterAssetList()
    }

    // filter khi ô search thay đổi chữ
    fun onSearchNameTextChange(name: String) {
        filterName = name.lowercase()
        filterAssetList()
    }

    // tiến hành filter
    private fun filterAssetList() {
        pairAssetAndCategory.value = (if (filterStatusID != FILTER_DEFAULT_STATUS_ID) {
            _pairAssetAndCategory.filter { pair ->
                pair.first.status == filterStatusID
            }
        } else {
            _pairAssetAndCategory
        }).filter {
            it.first.assetCode.lowercase().contains(filterName) ||
                    it.first.assetName.lowercase().contains(filterName) ||
                    it.second.categoryName.lowercase().contains(
                        filterName
                    )
        }.toMutableList()
    }

    // xóa Asset
    fun removeAsset(asset: Asset) = viewModelScope.launch{
        if (manageRepositoryImpl.removeAsset(asset) >=0){
            showToast(R.string.remove_asset_successfully)
            getAssetAndCategory()
        }
    }

    companion object {
        const val FILTER_DEFAULT_STATUS_ID = 2
        const val EMPTY_STRING = ""
    }
}
