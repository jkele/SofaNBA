package hr.algebra.sofanba

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import com.google.android.material.tabs.TabLayout
import hr.algebra.sofanba.adapters.EXTRA_TEAM
import hr.algebra.sofanba.adapters.sectionPager.TeamSectionsPagerAdapter
import hr.algebra.sofanba.databinding.ActivityTeamBinding
import hr.algebra.sofanba.helpers.getTeamColor
import hr.algebra.sofanba.network.model.Team
import hr.algebra.sofanba.viewmodels.TeamDetailsViewModel

class TeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamBinding
    private val viewModel: TeamDetailsViewModel by viewModels()

    private lateinit var selectedTeam: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedTeam = intent.getSerializableExtra(EXTRA_TEAM) as Team
        val isFavorite = viewModel.isTeamFavorite(selectedTeam.id)

        binding.btnFavorite.isActivated = isFavorite

        setupActionBar()
        setupTabLayout()

        setButtonListener(isFavorite)
    }

    private fun setupTabLayout() {
        val teamSectionsPagerAdapter = TeamSectionsPagerAdapter(this, supportFragmentManager)
        val viewPager = binding.teamViewPager
        viewPager.adapter = teamSectionsPagerAdapter
        val tabs: TabLayout = binding.teamTabLayout
        tabs.setupWithViewPager(viewPager)
    }

    private fun setButtonListener(isFavorite: Boolean) {
        binding.btnFavorite.setOnClickListener {
            if (!isFavorite) {
                binding.btnFavorite.isActivated = true
                viewModel.insertFavoriteTeam(selectedTeam)
            } else {
                binding.btnFavorite.isActivated = false
                viewModel.deleteFavoriteTeam(selectedTeam)
            }
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.teamToolbar)
        supportActionBar?.title = selectedTeam.fullName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(resources.getColor(getTeamColor(selectedTeam.abbreviation)))
        )
        binding.teamTabLayout.setBackgroundDrawable(
            ColorDrawable(resources.getColor(getTeamColor(selectedTeam.abbreviation)))
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}