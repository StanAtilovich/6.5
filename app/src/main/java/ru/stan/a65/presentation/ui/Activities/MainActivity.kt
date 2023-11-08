package ru.stan.a65.presentation.ui.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.stan.a65.App
import ru.stan.a65.R
import ru.stan.a65.databinding.ActivityMainWithDrawerBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainWithDrawerBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWithDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.INSTANCE.permissionService.initMainActivityContext(this)
        App.INSTANCE.permissionService.checkPermissions()

        initAuth()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.activityMain.navView.setupWithNavController(navController)

        binding.drawerNavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.sing_up -> {
                    singUpIn()
                }


                R.id.sing_in -> {
                    singUpIn()
                }

                R.id.sing_out -> {
                    singOut()
                }
            }


            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
        //   MessagingUtils().logToken()

    }


    private fun initAuth() {
        App.INSTANCE.firebaseInstance.initAuthUtils(this)
    }

    private fun singUpIn() {
        App.INSTANCE.firebaseInstance.authUtils.singUpIn()
    }

    private fun singOut() {
        App.INSTANCE.firebaseInstance.authUtils.singOut()
    }


}