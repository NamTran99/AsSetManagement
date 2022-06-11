package com.son.finalproject.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
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

    // Ản hiên các item trong drawablelayout
    override fun setVisibilityForNavigationFollowUser(type: UserType){
        when(type){
            UserType.User -> {
                binding.navView.menu.apply {
                    findItem(R.id.assetManageFragment).isVisible = false
                    findItem(R.id.userManagementFragment).isVisible = false
                    findItem(R.id.assignManagementFragment).isVisible = false
                }
            }
            UserType.Amin ->{
                binding.navView.menu.apply {
                    findItem(R.id.assetManageFragment).isVisible = true
                    findItem(R.id.userManagementFragment).isVisible = true
                    findItem(R.id.assignManagementFragment).isVisible = true
                }
            }
        }
    }

    // khởi tạo controller cho navigation để điều hướng các fragment
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
    // khởi tạo controller cho đrawerlayout
    private fun initDrawerLayout() {
        NavigationUI.setupWithNavController(binding.navView, controller)
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_log_out ->{
                    mySharePreference.clear()
                    controller.popBackStack(R.id.splashFragment, true)
                    controller.navigate(R.id.splashFragment)
                }
                else ->{
                    onNavDestinationSelected(it, controller)
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)

            return@setNavigationItemSelectedListener false
        }

        binding.drawerLayout.apply {
            headerBinding = HeaderBinding.inflate(layoutInflater)
        }
    }

    // kiểm tra user đã đăng nhập chưa nếu rồi tự động chuyển vào màn hình home
    private fun checkUserAlreadyLogIn() {
        if (mySharePreference.getUserEmail().isNotEmpty()) {
            navigateToDestination(R.id.homeFragment)
        }
    }
    // Hỗ trợ di chuyển sang màn hình mong muốn
    private fun navigateToDestination(action: Int, bundle: Bundle? = null) {
        bundle?.let {
            controller.navigate(action, it)
        } ?: controller.navigate(action)
    }


    private var doubleBackToExitPressedOnce = false

    // TÍnh năng back 2 lần ở màn hình home thì thoát app
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
    // xoay màn hình(hiện tại ko dùng)
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

enum class UserType{
    User, Amin
}