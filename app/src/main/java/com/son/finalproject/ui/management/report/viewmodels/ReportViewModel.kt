package com.son.finalproject.ui.management.report.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.son.finalproject.base.BaseViewModel
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Report
import com.son.finalproject.repository.ManageRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    application: Application,
    private val manageRepositoryImpl: ManageRepositoryImpl
) : BaseViewModel(application) {

    private val listReport = mutableListOf<Report>()
    val liveReport = MutableLiveData(listOf<Report>())
    init{
        viewModelScope.launch {
            allAsset = getAllAsset()

            allAsset.forEach { asset ->
                listReport.find { it.category == asset.category.categoryName }?.let{
                    when(asset.status){
                        0 -> it.available++
                        1 -> it.notAvailable++
                        2 -> it.assigned++
                    }
                    it.total++
                }
                ?: run {
                    val report = Report()
                    when(asset.status){
                        0 -> report.available++
                        1 -> report.notAvailable++
                        2 -> report.assigned++
                    }
                    report.category = asset.category.categoryName
                    listReport.add(report)
                }
            }
            liveReport.value = listReport
        }
    }
    var allAsset = listOf<Asset>()

    private suspend fun getAllAsset() = manageRepositoryImpl.getAllAsset()
}