package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.paging.EXTRA_MATCH
import hr.algebra.sofanba.databinding.FragmentTopPlayersBinding
import hr.algebra.sofanba.network.model.Match
import hr.algebra.sofanba.viewmodels.MatchDetailsViewModel

class TopPlayersFragment : Fragment(R.layout.fragment_top_players) {

    private lateinit var binding: FragmentTopPlayersBinding
    private val viewModel: MatchDetailsViewModel by viewModels()

    private lateinit var selectedMatch: Match

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopPlayersBinding.inflate(inflater, container, false)

        selectedMatch = requireActivity().intent.getSerializableExtra(EXTRA_MATCH) as Match

        viewModel.matchStatsList.observe(viewLifecycleOwner) {
            binding.fieldGoalsMade.setupPlayerStatsView("Field Goals Made",
                ArrayList(it.sortedByDescending { it.fgm }.take(3)), "fgm"
            )

            binding.fieldGoalsThreeMade.setupPlayerStatsView("Field Goal 3-pointers Made",
                ArrayList(it.sortedByDescending { it.fg3m }.take(3)), "fg3m"
            )

            binding.offensiveRebounds.setupPlayerStatsView("Offensive Rebounds",
                ArrayList(it.sortedByDescending { it.oreb }.take(3)), "oreb"
            )

            binding.rebounds.setupPlayerStatsView("Rebounds",
                ArrayList(it.sortedByDescending { it.reb }.take(3)), "reb"
            )

            binding.assists.setupPlayerStatsView("Assists",
                ArrayList(it.sortedByDescending { it.ast }.take(3)), "ast"
            )

            binding.turnover.setupPlayerStatsView("Turnover",
                ArrayList(it.sortedByDescending { it.turnover }.take(3)), "to"
            )
        }



        viewModel.getMatchStats(selectedMatch.id, 50, 0)
        return binding.root
    }

}