package com.son.finalproject.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.son.finalproject.R
import com.son.finalproject.base.IActivityApplication
import com.son.finalproject.base.WindowRotateType
import com.son.finalproject.databinding.ActivityMainBinding
import com.son.finalproject.databinding.HeaderBinding
import com.son.finalproject.utils.MyPreference
import com.son.finalproject.utils.helper.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IActivityApplication {

    private lateinit var binding: ActivityMainBinding
    private val mySharePreference: MyPreference by lazy { MyPreference(application) }
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var controller: NavController
    private lateinit var headerBinding: HeaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        setUpController()
        checkUserAlreadyLogIn()
        initDrawerLayout()
    }

    private fun setUpController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment
        controller = navHostFragment.navController

        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signInFragment, R.id.splashFragment, R.id.signUpFragment -> {
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
        }
    }

    private fun initDrawerLayout() {
        binding.navView.setupWithNavController(controller)
        binding.drawerLayout.apply {
            headerBinding = HeaderBinding.inflate(layoutInflater)
        }
    }

    private fun checkUserAlreadyLogIn() {
        if (mySharePreference.getUserEmail().isNotEmpty()) {
            navigateToDestination(R.id.homeFragment)
        }
    }

    private fun navigateToDestination(action: Int, bundle: Bundle? = null) {
        bundle?.let {
            controller.navigate(action, it)
        } ?: controller.navigate(action)
    }


    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        when (controller.currentDestination?.id) {
            R.id.homeFragment -> {
                if (doubleBackToExitPressedOnce) {
                    finish()
                }

                this.doubleBackToExitPressedOnce = true
                showToast(R.string.back_2_time)
                Handler(Looper.getMainLooper()).postDelayed(
                    { doubleBackToExitPressedOnce = false },
                    2000
                )
            }
            else ->
                super.onBackPressed()
        }
    }

    override fun rotateWindow(windowRotateType: WindowRotateType) {
        Log.d(TAG, "rotateWindow: ${windowRotateType.name}")
        requestedOrientation = when(windowRotateType){
            WindowRotateType.Horizontal ->{
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
            else -> {
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
    }

    companion object{
        const val TAG = "activity"
    }
}