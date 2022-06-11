package com.son.finalproject.base

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.son.finalproject.utils.MyPreference
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    ItemMenuAction {
    protected val TAG by lazy { this::class.java.name }
    protected val evenSender = Channel<AppEvent>()
    val eventReceiver = evenSender.receiveAsFlow().conflate()

    // My SharePreferences: Kho data ở local trong android
    val mySharedPreferences = MyPreference(application.applicationContext)

    // tắt app
    override fun onClickClose() {
        viewModelScope.launch {
            evenSender.send(AppEvent.OnCloseApp)
        }
    }

    // quay về màn hình trước
    override fun onBackStack() {
        viewModelScope.launch {
            evenSender.send(AppEvent.OnBackScreen)
        }
    }

    fun getString(idString: Int) = getApplication<Application>().resources.getString(idString)

    // Chuyển hướng sang screen mong muốn
    fun navigateToDestination(action: Int, bundle: Bundle? = null) = viewModelScope.launch {
        evenSender.send(
            AppEvent.OnNavigation(action, bundle)
        )
    }

    fun closeApp() = viewModelScope.launch {
        evenSender.send(
            AppEvent.OnCloseApp
        )
    }

    // hiện toast màn hình
    fun showToast(content: String)= viewModelScope.launch{
        evenSender.send(
            AppEvent.OnShowToast(content)
        )
    }
    // hiện toast màn hình
    fun showToast(contentID: Int) = viewModelScope.launch {
        evenSender.send(
            AppEvent.OnShowToast(getString(contentID))
        )
    }
}

sealed class AppEvent {
    class OnNavigation(val destination: Int, val bundle: Bundle? = null) : AppEvent()
    object OnCloseApp : AppEvent()
    object OnBackScreen : AppEvent()
    class OnShowToast(val content: String, val type: Int = Toast.LENGTH_SHORT) : AppEvent()
}