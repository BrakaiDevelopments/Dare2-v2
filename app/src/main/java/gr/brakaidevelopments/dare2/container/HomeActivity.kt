/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.dare2.container

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import gr.brakaidevelopments.dare2.R
import gr.brakaidevelopments.dare2.base.BaseActivity
import gr.brakaidevelopments.dare2.databinding.ActivityHomeBinding
import gr.brakaidevelopments.domain.utils.RC_SIGN_IN
import kotlinx.android.synthetic.main.nav_header.view.*

class HomeActivity : BaseActivity(), NavDrawerInterface {

    override fun isNavigationDrawerEnabled(enabled: Boolean) {
        if (enabled) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private val authIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(
            arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )
        )
        .setIsSmartLockEnabled(true)
        .setLogo(R.drawable.launcher_image)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_home
        )
        drawerLayout = binding.drawerLayout

        navController = Navigation.findNavController(this, R.id.main_nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navigationView.setupWithNavController(navController)

        Glide.with(this).load(mAuth.currentUser?.photoUrl).into(binding.navigationView.getHeaderView(0).profile_picture)
        binding.navigationView.getHeaderView(0).profile_username.text = mAuth.currentUser?.email


        if (mAuth.currentUser == null)
            authenticate()
    }

    private fun authenticate() {
        startActivityForResult(
            authIntent,
            RC_SIGN_IN
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
            } else {
                Toast.makeText(this, "Login failed: ${response?.error?.localizedMessage}", Toast.LENGTH_LONG).show()
                startActivityForResult(
                    authIntent,
                    RC_SIGN_IN
                )
            }
        }
    }
}
