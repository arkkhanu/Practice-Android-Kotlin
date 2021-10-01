package com.ark.allinone.ui

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.ark.allinone.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {


    /*private val permissions: Array<String>
        get() = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.CAMERA
        )*/

//    lateinit var permissionsHandle: PermissionsHandle
//    lateinit var offlineLatLng: OfflineLatLng

    //    lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)
        setUpViews()


    }

    private fun setUpViews() {

       val navHostFragment = supportFragmentManager.findFragmentById( R.id.nav_host_fragment) as NavHostFragment
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)
        navController = navHostFragment.navController


        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open,
            R.string.close
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        navigationView.setupWithNavController(navController)


        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {

                    drawerLayout.close()
                    lifecycleScope.launch (Dispatchers.Main){
                        if (isDuplicateFragment(navController, it.itemId)) {
//                        navController.navigate(R.id.homeFragment)
                            delay(200)
                            navController.popBackStack(R.id.homeFragment, true)
                            navController.navigate(R.id.homeFragment)
                        }
                    }


                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.mapFragment -> {
                    drawerLayout.close()
                    lifecycleScope.launch (Dispatchers.Main){
                        if (isDuplicateFragment(navController, it.itemId)) {
//                        navController.navigate(R.id.homeFragment)
                            delay(500)
                            navController.navigate(R.id.mapFragment)
                        }
                    }


//                    Toast.makeText(this, "Another", Toast.LENGTH_SHORT).show()
                }
                R.id.home1 -> {
                    drawerLayout.close()
//                    navController.popBackStack(R.id.homeFragment,true)
//                    val navOption: NavOptions = NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
                    if (isDuplicateFragment(navController, it.itemId)) {
                        navController.popBackStack(R.id.homeFragment, true)
                        navController.navigate(R.id.homeFragment)
                    }
//                    navController.navigate(R.id.homeFragment, null, navOption)
//                    navController.graph
                    Toast.makeText(this, "Home1", Toast.LENGTH_SHORT).show()
                }
                R.id.another1 -> {
                    drawerLayout.close()
                    if (isDuplicateFragment(navController, it.itemId)) {
                        navController.navigate(R.id.mapFragment)
//                        navController.popBackStack(R.id.mapFragment, true)
                    }
//                    navController.popBackStack(R.id.homeFragment, true)
//                    navController.navigate(R.id.mapFragment)

                    Toast.makeText(this, "Another 1", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)

    }

    private fun isDuplicateFragment(_navController: NavController, itemId: Int): Boolean {
        return _navController.currentDestination?.id != itemId
    }

    /*private fun initializeProperites() {
        permissionsHandle =
        PermissionsHandle()
        offlineLatLng = OfflineLatLng(this)

    }*/


    /*override fun onStart() {
        super.onStart()
        Log.d("ARK", "OnStart")
        if (!permissionsHandle.hasPermission(this, *permissions)) {
            Log.d("ARK", "Has PErmission")
            permissionsHandle.requestPermissions(this, 9001, *permissions)
        }
    }*/


    /*override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 9001) {
            if (grantResults.isNotEmpty()) {
                for (i in grantResults.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.d("ARK", "onRequestPermissionsResult: Granted ${permissions[i]}")
                        Toast.makeText(this, "Granted ${permissions[i]}", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.d("ARK", "onRequestPermissionsResult: Not Granted ${permissions[i]}")
                        Toast.makeText(this, "Not Granted ${permissions[i]}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }*/


}