package com.son.finalproject.repository

import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.repository.interfaces.ManageRepository
import com.son.finalproject.room.dao.AssetDAO
import com.son.finalproject.room.dao.CategoryDAO
import javax.inject.Inject

class ManageRepositoryImpl @Inject constructor(
    private val assetDAO: AssetDAO,
    private val categoryDAO: CategoryDAO
) : ManageRepository() {
    override suspend fun createAsset(asset: Asset): Long {
        return assetDAO.insert(asset)
    }

    override suspend fun getLabel(label: String): List<String> {
        return categoryDAO.findAllCategoryContainWithSymBol(label)
    }

    override suspend fun getListCategoryName(): List<Category> {
        return categoryDAO.getListCategoryName()
    }

//    suspend fun count
}