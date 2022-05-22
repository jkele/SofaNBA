package hr.algebra.sofanba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout
import hr.algebra.sofanba.adapters.paging.EXTRA_PLAYER
import hr.algebra.sofanba.adapters.section.PlayerSectionsPagerAdapter
import hr.algebra.sofanba.databinding.ActivityPlayerBinding
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.viewmodels.PlayerDetailsViewModel

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private val viewModel: PlayerDetailsViewModel by viewModels()

    private lateinit var selectedPlayer: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedPlayer = intent.getSerializableExtra(EXTRA_PLAYER) as Player
        val isFavorite = viewModel.isPlayerFavorite(selectedPlayer.id)

        binding.btnFavorite.isActivated = isFavorite

        setupActionBar()
        setupTabLayout()

        setButtonListener(isFavorite)
    }

    private fun setButtonListener(isFavorite: Boolean) {
        binding.btnFavorite.setOnClickListener {
            if (!isFavorite) {
                binding.btnFavorite.isActivated = true
                viewModel.insertFavoritePlayer(selectedPlayer)
            } else {
                binding.btnFavorite.isActivated = false
                viewModel.deleteFavoritePlayer(selectedPlayer)
            }
        }
    }

    private fun setupTabLayout() {
        val playerSectionsPagerAdapter = PlayerSectionsPagerAdapter(this, supportFragmentManager)
        val viewPager = binding.playerViewPager
        viewPager.adapter = playerSectionsPagerAdapter
        val tabs: TabLayout = binding.playerTabLayout
        tabs.setupWithViewPager(viewPager)
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.playerToolbar)
        supportActionBar?.title = selectedPlayer.firstName + " " + selectedPlayer.lastName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}