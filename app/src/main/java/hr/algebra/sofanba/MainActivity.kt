package hr.algebra.sofanba

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import hr.algebra.sofanba.databinding.ActivityMainBinding
import hr.algebra.sofanba.fragments.*

class MainActivity : AppCompatActivity() {

    private val exploreFragment = ExploreFragment()
    private val favoritesFragment = FavoritesFragment()
    private val seasonsFragment = SeasonsFragment()
    private val settingsFragment = SettingsFragment()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        replaceFragment(exploreFragment)
        setupBottomNavigationListeners()

        val sharedPref = this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit {
            putString(MEASURE_UNIT, "Imperial")
            commit()
        }

    }

    private fun setupBottomNavigationListeners(){
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.icon_explore -> replaceFragment(exploreFragment)
                R.id.icon_favorites -> replaceFragment(favoritesFragment)
                R.id.icon_seasons -> replaceFragment(seasonsFragment)
                R.id.icon_settings -> replaceFragment(settingsFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}