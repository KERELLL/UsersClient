package com.example.usersclient.utils

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign

class NavHostFragment: NavHostFragment() {
    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)
        navController.navigatorProvider += CustomNavigator(context!!, childFragmentManager, id)
    }
}