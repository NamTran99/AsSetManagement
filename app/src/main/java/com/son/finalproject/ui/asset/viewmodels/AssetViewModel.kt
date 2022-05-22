package com.son.finalproject.ui.asset.viewmodels

import android.app.Application
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
class AssetViewModel @Inject constructor(
    application: Application,
    private val manageRepo: ManageRepositoryImpl
) : BaseViewModel(application) {


    val liveAsset = MutableLiveData(Asset())
    var selectedCategoryID = 0
    private var currentCategory: Category? = null
    private var symbolsCategory = ""

    init {
        getListCategory()
    }

    val listCategoryName = MutableLiveData(listOf<Category>())

    private fun getListCategory() = viewModelScope.launch {
        listCategoryName.value = manageRepo.getListCategoryName()
    }

    fun onSelectedCategorySpinner(position: Int) = viewModelScope.launch {
        listCategoryName.value?.let {
            currentCategory = it[position]
            selectedCategoryID = it[position].categoryID
            symbolsCategory = it[position].categoryName.take(2)
        }
    }

    fun setItemStatus(position: Int) {
        liveAsset.value?.status = position
        liveAsset.forceRefresh()
    }

    fun setDateTime(dateTime: String){
        liveAsset.value?.installDate = dateTime
        liveAsset.forceRefresh()
    }

    fun onClickSaveButton() = viewModelScope.launch {
        val countItemWithCategory =
            (manageRepo.countAssetItemsByCategoryID(selectedCategoryID) + 1).toString()
                .padStart(3, '0')
        val futureAssetID = "$symbolsCategory$countItemWithCategory"
        liveAsset.value?.apply {
            assetCode = futureAssetID
            manageRepo.insertSpecification(specification)
            manageRepo.specificationAsset(this)
            showToast(R.string.create_asset_successfully)
            onBackStack()
        }
    }
}