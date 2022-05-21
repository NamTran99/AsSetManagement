package com.son.finalproject.repository.interfaces

import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category

abstract class ManageRepository {
    abstract suspend fun createAsset(asset: Asset) : Long
    abstract suspend fun getLabel(label: String): List<String>
    abstract suspend fun getListCategoryName(): List<Category>
}