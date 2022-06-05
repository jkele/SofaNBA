package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.paging.EXTRA_MATCH
import hr.algebra.sofanba.databinding.FragmentTopPlayersBinding
import hr.algebra.sofanba.network.model.Match
import hr.algebra.sofanba.viewmodels.MatchDetailsViewModel
import hr.algebra.sofanba.views.MatchPlayerStatsView

class TopPlayersFragment : Fragment(R.layout.fragment_top_players) {

    private lateinit var binding: FragmentTopPlayersBinding
    private val viewModel: MatchDetailsViewModel by viewModels()

    private lateinit var selectedMatch: Match
    private var topPlayersViewList = ArrayList<MatchPlayerStatsView>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopPlayersBinding.inflate(inflater, container, false)

        binding.root.children.forEach {
            if (it is MatchPlayerStatsView) {
                topPlayersViewList.add(it)
            }
        }

        selectedMatch = requireActivity().intent.getSerializableExtra(EXTRA_MATCH) as Match

        viewModel.matchStatsList.observe(viewLifecycleOwner) { statsList ->
            topPlayersViewList.forEach { it.visibility = View.INVISIBLE }
            binding.progressBar.visibility = ProgressBar.VISIBLE
            viewModel.playerImagesList.observe(viewLifecycleOwner) { imagesList ->

                binding.fieldGoalsMade.setupPlayerStatsView("Field Goals Made",
                    ArrayList(statsList.sortedByDescending { it.fgm }.take(3)), imagesList
                    , "fgm"
                )

                binding.fieldGoalsThreeMade.setupPlayerStatsView("Field Goal 3-pointers Made",
                    ArrayList(statsList.sortedByDescending { it.fg3m }.take(3)), imagesList
                    , "fg3m"
                )

                binding.offensiveRebounds.setupPlayerStatsView("Offensive Rebounds",
                    ArrayList(statsList.sortedByDescending { it.oreb }.take(3)), imagesList
                    , "oreb"
                )

                binding.rebounds.setupPlayerStatsView("Rebounds",
                    ArrayList(statsList.sortedByDescending { it.reb }.take(3)), imagesList
                    , "reb"
                )

                binding.assists.setupPlayerStatsView("Assists",
                    ArrayList(statsList.sortedByDescending { it.ast }.take(3)), imagesList
                    , "ast"
                )

                binding.turnover.setupPlayerStatsView("Turnover",
                    ArrayList(statsList.sortedByDescending { it.turnover }.take(3)), imagesList
                    , "to"
                )

            }
            binding.progressBar.visibility = ProgressBar.GONE
            topPlayersViewList.forEach { it.visibility = View.VISIBLE }
        }


        viewModel.getMatchStatsAndPlayerImages(selectedMatch.id, 50, 0)
        return binding.root
    }

}