package com.son.finalproject.repository

import android.util.Log
import com.son.finalproject.data.Asset
import com.son.finalproject.data.Category
import com.son.finalproject.data.Specification
import com.son.finalproject.data.User
import com.son.finalproject.repository.interfaces.ManageRepository
import com.son.finalproject.room.dao.AssetDAO
import com.son.finalproject.room.dao.CategoryDAO
import com.son.finalproject.room.dao.SpecificationDAO
import com.son.finalproject.room.dao.UserDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ManageRepositoryImpl @Inject constructor(
    private val assetDAO: AssetDAO,
    private val categoryDAO: CategoryDAO,
    private val specificationDAO: SpecificationDAO,
    private val userDAO: UserDAO
) : ManageRepository() {

    // Asset
    override suspend fun insertAsset(asset: Asset): Long {
        return assetDAO.insert(asset)
    }

    override suspend fun getAllAssetCodeByCategoryID(categoryID: Int): List<String>{
        return assetDAO.getAllAssetCodeByCategoryID(categoryID)
    }

    override suspend fun getListAsset(): List<Asset> {
        return assetDAO.getListAsset()
    }

    // Category
    override suspend fun getLabel(label: String): List<String> {
        return categoryDAO.findAllCategoryContainWithSymBol(label)
    }

    override suspend fun getListCategoryName(): List<Category> {
        return categoryDAO.getListCategoryName()
    }

    // Specification
    override suspend fun insertSpecification(specification: Specification):Long{
        return specificationDAO.insert(specification)
    }

    // Helper: Join Table
    override suspend fun loadAssetAndCategory(): Map<Category ,List<Asset> >{
        return categoryDAO.loadAssetAndCategory()
    }

    // User
    override suspend fun getHighestUserID(): String? {
        return userDAO.getHighestUserID()
    }

    override suspend fun insertUser(user: User): Long {
        return userDAO.insert(user)
    }

    override suspend fun getAllUser(): List<User> {
        Log.d("TAG", "getAllUser: ")
        return userDAO.getListUser()
    }
}