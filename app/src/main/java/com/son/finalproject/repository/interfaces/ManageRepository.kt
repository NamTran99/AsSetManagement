package com.son.finalproject.repository.interfaces

import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.data.Specification

abstract class ManageRepository {
    abstract suspend fun insertSpecification(specification: Specification):Long
    abstract suspend fun specificationAsset(asset: Asset) : Long
    abstract suspend fun getLabel(label: String): List<String>
    abstract suspend fun getListCategoryName(): List<Category>
    abstract suspend fun countAssetItemsByCategoryID(categoryID: Int): Int
    abstract suspend fun loadAssetAndCategory(): Map<Category ,List<Asset> >
    abstract suspend fun getListAsset() : List<Asset>
}