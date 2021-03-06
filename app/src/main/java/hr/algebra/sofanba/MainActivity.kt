package hr.algebra.sofanba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import hr.algebra.sofanba.databinding.ActivityMainBinding
import hr.algebra.sofanba.fragments.ExploreFragment
import hr.algebra.sofanba.fragments.FavoritesFragment
import hr.algebra.sofanba.fragments.SeasonsFragment
import hr.algebra.sofanba.fragments.SettingsFragment

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