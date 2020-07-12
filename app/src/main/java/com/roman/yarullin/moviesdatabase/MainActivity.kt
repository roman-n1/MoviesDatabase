package com.roman.yarullin.moviesdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val navController get() = navHostFragment.findNavController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavManager()
    }

    private fun initNavManager() {
        MoviesDatabaseApplication.instance.appComponent.navManager()
            .setOnNavEvent { navController.navigate(it) }
    }
}