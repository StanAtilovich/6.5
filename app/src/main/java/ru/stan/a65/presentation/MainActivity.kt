package ru.stan.a65.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.stan.a65.R
import ru.stan.a65.databinding.ActivityMainWithDrawerBinding
import ru.stan.a65.presentation.AllUtils.DataBaseUtils
import ru.stan.a65.presentation.AllUtils.FirebaseUtils


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainWithDrawerBinding
    private lateinit var navController: NavController
    lateinit var authUtils: FirebaseUtils
    lateinit var dataBaseUtils: DataBaseUtils


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWithDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        authUtils = FirebaseUtils(this)
        dataBaseUtils = DataBaseUtils(this)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.activityMain.navView.setupWithNavController(navController)

        binding.drawerNavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.sing_up -> {
                    authUtils.singUpIn()
                }

                R.id.sing_in -> {
                    authUtils.singUpIn()
                }

                R.id.sing_out -> {
                    authUtils.singOut()
                }
            }

            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }


    }


}