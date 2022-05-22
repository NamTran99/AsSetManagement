package com.son.finalproject.ui.asset.viewmodels

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

    init{
        getAssetAndCategory()
    }

    val pairAssetAndCategory = MutableLiveData(mutableListOf<Pair<Asset, Category>>())

    private fun getAssetAndCategory() = viewModelScope.launch{
        val mapAssetCategory =  manageRepositoryImpl.loadAssetAndCategory()

        val mPairAssetAndCategory = mutableListOf<Pair<Asset, Category>>()
        mapAssetCategory.forEach{
            it.value.map {asset ->
                mPairAssetAndCategory.add(Pair(asset, it.key))
            }
        }

        Log.d(TAG, "getAssetAndCategory: ${mPairAssetAndCategory}")
        pairAssetAndCategory.value = mPairAssetAndCategory
    }

    fun onClickCreateAsset(){
        navigateToDestination(R.id.action_userManagementFragment_to_createAssetFragment)
    }
}