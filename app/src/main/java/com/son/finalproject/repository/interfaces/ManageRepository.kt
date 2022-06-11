package com.son.finalproject.repository.interfaces

import com.son.finalproject.data.*

abstract class ManageRepository {
    abstract suspend fun insertSpecification(specification: Specification): Long
    abstract suspend fun getLabel(label: String): List<String>
    abstract suspend fun getListCategoryName(): List<Category>
    abstract suspend fun loadAssetAndCategory(): Map<Category, List<Asset>>

    // user
    abstract suspend fun getHighestUserID(): String?
    abstract suspend fun insertUser(user: User): Long
    abstract suspend fun getAllUser(): List<User>
    abstract suspend fun getAllUserID(): List<String>
    abstract suspend fun removeUser(user: User): Int
    abstract suspend fun getUserByID(userID: String): User
    abstract suspend fun updateUser(user: User): Int

    // asset
    abstract suspend fun insertAsset(asset: Asset): Long
    abstract suspend fun getAllAssetCodeByCategoryID(categoryID: Int): List<String>
    abstract suspend fun getAllAsset(): List<Asset>
    abstract suspend fun removeAsset(asset: Asset): Int
    abstract suspend fun getAllAssetID(): List<String>
    abstract suspend fun getAssetByStatus(assetStatus: AssetStatus): List<Asset>
    abstract suspend fun getAssetByID(assetID: String): Asset
    abstract suspend fun updateAsset(asset: Asset): Int

    // assignment
    abstract suspend fun removeAssignment(assignment: Assignment): Int
    abstract suspend fun insertAssignment(assignment: Assignment): Long
    abstract suspend fun getAllAssignment(): List<Assignment>

    //request
    abstract suspend fun insertRequest(request: Request): Long
    abstract suspend fun removeRequest(request: Request): Int
    abstract suspend fun getAllRequest(): List<Request>
}