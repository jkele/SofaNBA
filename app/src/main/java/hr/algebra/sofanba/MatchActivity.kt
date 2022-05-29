package hr.algebra.sofanba

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import hr.algebra.sofanba.adapters.paging.EXTRA_MATCH
import hr.algebra.sofanba.adapters.section.MatchSectionsPagerAdapter
import hr.algebra.sofanba.databinding.ActivityMatchBinding
import hr.algebra.sofanba.network.model.Match

class MatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchBinding

    private lateinit var selectedMatch: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedMatch = intent.getSerializableExtra(EXTRA_MATCH) as Match

        setupActionBar()
        setupTabLayout()

    }

    private fun setupTabLayout() {
        val matchSectionsPagerAdapter = MatchSectionsPagerAdapter(this, supportFragmentManager)
        val viewPager = binding.matchViewPager
        viewPager.adapter = matchSectionsPagerAdapter
        val tabs: TabLayout = binding.matchTabLayout
        tabs.setupWithViewPager(viewPager)
    }

    @SuppressLint("SetTextI18n")
    private fun setupActionBar() {
        setSupportActionBar(binding.matchToolbar)
        supportActionBar?.title = ""
        binding.tvToolbarMatch.text =
            selectedMatch.homeTeam.fullName + " vs. " + selectedMatch.visitorTeam.fullName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}