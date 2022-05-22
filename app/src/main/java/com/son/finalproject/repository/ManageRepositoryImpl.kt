package com.son.finalproject.repository

import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.data.Specification
import com.son.finalproject.repository.interfaces.ManageRepository
import com.son.finalproject.room.dao.AssetDAO
import com.son.finalproject.room.dao.CategoryDAO
import com.son.finalproject.room.dao.SpecificationDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ManageRepositoryImpl @Inject constructor(
    private val assetDAO: AssetDAO,
    private val categoryDAO: CategoryDAO,
    private val specificationDAO: SpecificationDAO
) : ManageRepository() {
    override suspend fun specificationAsset(asset: Asset): Long {
        return assetDAO.insert(asset)
    }

    override suspend fun getLabel(label: String): List<String> {
        return categoryDAO.findAllCategoryContainWithSymBol(label)
    }

    override suspend fun getListCategoryName(): List<Category> {
        return categoryDAO.getListCategoryName()
    }

    override suspend fun countAssetItemsByCategoryID(categoryID: Int): Int{
        return assetDAO.countItemByCategoryID(categoryID)
    }

    override suspend fun insertSpecification(specification: Specification):Long{
        return specificationDAO.insert(specification)
    }

    override suspend fun loadAssetAndCategory(): Map<Category ,List<Asset> >{
        return categoryDAO.loadAssetAndCategory()
    }

    override suspend fun getListAsset(): List<Asset> {
        return assetDAO.getListAsset()
    }
}