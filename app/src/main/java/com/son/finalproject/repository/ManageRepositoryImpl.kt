package com.son.finalproject.repository

import android.util.Log
import com.son.finalproject.data.*
import com.son.finalproject.repository.interfaces.ManageRepository
import com.son.finalproject.room.dao.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ManageRepositoryImpl @Inject constructor(
    private val assetDAO: AssetDAO,
    private val categoryDAO: CategoryDAO,
    private val specificationDAO: SpecificationDAO,
    private val userDAO: UserDAO,
    private val assignmentDAO: AssignmentDAO,
    private val requestDAO: RequestDAO
) : ManageRepository() {

    // Asset
    override suspend fun insertAsset(asset: Asset): Long {
        return assetDAO.insert(asset)
    }

    override suspend fun getAllAssetCodeByCategoryID(categoryID: Int): List<String> {
        return assetDAO.getAllAssetCodeByCategoryID(categoryID)
    }

    override suspend fun getAllAsset(): List<Asset> {
        return assetDAO.getListAsset()
    }

    override suspend fun getAssetByStatus(assetStatus: AssetStatus): List<Asset> {
        return assetDAO.getAssetByStatus(assetStatus.inx)
    }

    override suspend fun getAllAssetID(): List<String> {
        return assetDAO.getAllAssetCode()
    }

    override suspend fun getAssetByID(assetID: String): Asset {
        return assetDAO.getAssetByID(assetID)
    }

    override suspend fun removeAsset(asset: Asset): Int {
        return assetDAO.delete(asset)
    }

    override suspend fun updateAsset(asset: Asset): Int {
        return assetDAO.update(asset)
    }

    suspend fun getAssetForRequest(userID: String): List<Asset> {
        return assetDAO.getAssetAvailableForRequest(userID)
    }

//    suspend fun getAllAssetAssignmentByUserID(userID: String): List<Asset>{
//        return assetDAO.
//    }

    // Category
    override suspend fun getLabel(label: String): List<String> {
        return categoryDAO.findAllCategoryContainWithSymBol(label)
    }

    override suspend fun getListCategoryName(): List<Category> {
        return categoryDAO.getListCategoryName()
    }

    // Specification
    override suspend fun insertSpecification(specification: Specification): Long {
        return specificationDAO.insert(specification)
    }

    // Helper: Join Table
    override suspend fun loadAssetAndCategory(): Map<Category, List<Asset>> {
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

    override suspend fun getAllUserID(): List<String> {
        return userDAO.getAllStaffCode()
    }

    override suspend fun removeUser(user: User): Int {
        return userDAO.delete(user)
    }

    override suspend fun getUserByID(userID: String): User {
        return userDAO.getUserByID(userID)
    }

    override suspend fun updateUser(user: User): Int {
        return userDAO.update(user)
    }

    // Assignment
    override suspend fun insertAssignment(assignment: Assignment): Long {
        return assignmentDAO.insert(assignment)
    }

    override suspend fun getAllAssignment(): List<Assignment> {
        return assignmentDAO.getAllAssignment()
    }

    override suspend fun removeAssignment(assignment: Assignment): Int {
        return assignmentDAO.delete(assignment)
    }

    suspend fun getAssignmentByUserID(userID: String): List<Assignment> {
        return assignmentDAO.getAssignmentByUserID(userID)
    }

    suspend fun updateAssignment(assignment: Assignment):Int{
        return assignmentDAO.update(assignment)
    }

    //Request

    override suspend fun insertRequest(request: Request): Long {
        return requestDAO.insert(request)
    }

    override suspend fun removeRequest(request: Request): Int {
        return requestDAO.delete(request)
    }

    suspend fun removeRequestByAssetCode(assetCode: String){
        Log.d("TAG", "removeRequestByAssetCode: $assetCode")
        return requestDAO.deleteRequestByAssetCode(assetCode)
    }

    suspend fun getRequestByUserID(userID: String): List<Request> {
        return requestDAO.getRequestByUserID(userID)
    }

    override suspend fun getAllRequest(): List<Request> {
        return requestDAO.getAllRequest()
    }

    suspend fun getAllRequestByUserID(userID: String): List<Request>{
        return requestDAO.getAllRequestByUserID(userID)
    }

    suspend fun updateRequest(request: Request): Int {
        return requestDAO.update(request)
    }
}