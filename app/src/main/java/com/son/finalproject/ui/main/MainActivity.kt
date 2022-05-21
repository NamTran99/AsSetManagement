package com.son.finalproject.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.son.finalproject.R
import com.son.finalproject.databinding.ActivityMainBinding
import com.son.finalproject.utils.MyPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mySharePreference: MyPreference by lazy { MyPreference(application) }
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var controller: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment
        controller = navHostFragment.navController

        checkUserAlreadyLogIn()
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
        if(controller.currentDestination?.id == R.id.homeFragment){
            if (doubleBackToExitPressedOnce) {
                finish()
            }

            this.doubleBackToExitPressedOnce = true
            showToast(R.string.back_2_time)
            Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }else{
            super.onBackPressed()
        }
    }

    private fun showToast(contentID: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(applicationContext, getString(contentID), duration).show()
    }

}