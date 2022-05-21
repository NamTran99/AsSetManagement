package com.son.finalproject.ui.asset.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.repository.ManageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetViewModel @Inject constructor(
    application: Application,
    private val manageRepo: ManageRepositoryImpl
) : BaseViewModel(application) {

    val asset = MutableLiveData(Asset())
    var countItemWithCategory = 0
    private var currentCategory: Category? = null

    init {
        getListCategory()
    }

    val listCategoryName = MutableLiveData(listOf<Category>())

    private fun getListCategory()= viewModelScope.launch{
        listCategoryName.value = manageRepo.getListCategoryName()
    }

     fun onSelectedCategorySpinner(position: Int){
          listCategoryName.value?.let{
             currentCategory = it[position]
//              countItemWithCategory = manageRepo.
         }
    }

}