package hr.algebra.sofanba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import hr.algebra.sofanba.databinding.ActivityTeamPickBinding
import hr.algebra.sofanba.helpers.startActivity
import hr.algebra.sofanba.viewmodels.SplashScreenViewModel

class TeamPickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamPickBinding
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPickBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonListeners()

        viewModel.teamsList.observe(this) { teamsList ->
            val easternTeams = teamsList.filter { it.conference ==  "East"} as ArrayList
            binding.rvEasternTeams.setupTeamSequenceView(this, easternTeams, true) {
                viewModel.insertFavoriteTeam(it)
            }

            val westernTeams = teamsList.filter { it.conference == "West" } as ArrayList
            binding.rvWesternTeams.setupTeamSequenceView(this, westernTeams, true) {
                viewModel.insertFavoriteTeam(it)
            }
        }

        viewModel.getTeamsList()
    }

    private fun setButtonListeners() {
        binding.btnContinue.setOnClickListener {
            startActivity<MainActivity>()
        }

        binding.btnSkip.setOnClickListener {
            startActivity<MainActivity>()
        }
    }
}