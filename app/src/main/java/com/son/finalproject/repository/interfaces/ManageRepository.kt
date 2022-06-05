package com.son.finalproject.repository.interfaces

import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.data.Specification
import com.son.finalproject.data.User

abstract class ManageRepository {
    abstract suspend fun insertSpecification(specification: Specification):Long
    abstract suspend fun insertAsset(asset: Asset) : Long
    abstract suspend fun getLabel(label: String): List<String>
    abstract suspend fun getListCategoryName(): List<Category>
    abstract suspend fun getAllAssetCodeByCategoryID(categoryID: Int): List<String>
    abstract suspend fun loadAssetAndCategory(): Map<Category ,List<Asset> >
    abstract suspend fun getListAsset() : List<Asset>

    // user
    abstract suspend fun getHighestUserID(): String?
    abstract suspend fun insertUser(user: User): Long
    abstract suspend fun getAllUser(): List<User>
}