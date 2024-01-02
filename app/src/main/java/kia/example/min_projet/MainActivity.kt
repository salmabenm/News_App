package kia.example.min_projet

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kia.example.min_projet.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        applySavedNightMode()


        // Attacher le NavController à la BottomNavigationView pour la navigation automatique
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    private fun applySavedNightMode() {
        val savedNightMode = sharedPreferences.getInt("night_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(savedNightMode)
    }

    private fun saveNightMode(mode: Int) {
        with(sharedPreferences.edit()) {
            putInt("night_mode", mode)
            apply()
        }
    }

    fun dark(item: MenuItem) {
        val currentNightMode = AppCompatDelegate.getDefaultNightMode()

        val newNightMode = when (currentNightMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
            else -> AppCompatDelegate.MODE_NIGHT_YES
        }

        AppCompatDelegate.setDefaultNightMode(newNightMode)
        saveNightMode(newNightMode)
    }

    fun logout(item: MenuItem){
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }

    }

}
