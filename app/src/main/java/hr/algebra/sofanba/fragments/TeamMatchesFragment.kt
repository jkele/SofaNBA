package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.EXTRA_TEAM
import hr.algebra.sofanba.adapters.paging.TeamMatchesPagingAdapter
import hr.algebra.sofanba.databinding.FragmentTeamMatchesBinding
import hr.algebra.sofanba.fragments.bottomsheet.FilterSeasonBottomSheet
import hr.algebra.sofanba.helpers.isOnline
import hr.algebra.sofanba.helpers.showCustomDialog
import hr.algebra.sofanba.network.model.Team
import hr.algebra.sofanba.network.paging.match.MatchDiff
import hr.algebra.sofanba.viewmodels.TeamMatchesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TeamMatchesFragment : Fragment(R.layout.fragment_team_matches) {

    private lateinit var binding: FragmentTeamMatchesBinding
    private val viewModel: TeamMatchesViewModel by activityViewModels()

    private lateinit var selectedTeam: Team
    private var selectedSeason: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamMatchesBinding.inflate(inflater, container, false)

        binding.rvMatches.layoutManager = LinearLayoutManager(requireContext())
        selectedTeam = requireActivity().intent.getSerializableExtra(EXTRA_TEAM) as Team

        val pagingAdapter = TeamMatchesPagingAdapter(requireContext(), selectedTeam, MatchDiff)
        binding.rvMatches.adapter = pagingAdapter

        selectedSeason = "2021"

        if (requireContext().isOnline()) {
            submitPagingAdapterData(pagingAdapter, "2021", false)
            setButtonListeners(pagingAdapter)
        } else {
            showCustomDialog(getString(R.string.no_internet_connection), requireContext())
        }


        return binding.root
    }

    private fun setButtonListeners(pagingAdapter: TeamMatchesPagingAdapter) {
        binding.btnRegularSeason.isActivated = true
        binding.btnRegularSeason.setOnClickListener {
            regularSeasonButtonClick(pagingAdapter, "2021")
            selectedSeason = "2021"
        }

        binding.btnPlayoffs.setOnClickListener {
            playoffsButtonClick(pagingAdapter,"2021")
            selectedSeason = "2021"
        }

        binding.btnFilter.setOnClickListener {
            openFilterBottomSheet(pagingAdapter)
        }
    }

    private fun submitPagingAdapterData(
        pagingAdapter: TeamMatchesPagingAdapter,
        season: String,
        postseason: Boolean
    ) {
        lifecycleScope.launch {
            val flow = viewModel.getMatchesFlow(selectedTeam.id, season.toInt(), postseason)
            flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun openFilterBottomSheet(pagingAdapter: TeamMatchesPagingAdapter) {
        val bottomSheet = FilterSeasonBottomSheet(selectedSeason) { season ->
            selectedSeason = season
            submitPagingAdapterData(pagingAdapter, season, false)

            binding.btnRegularSeason.setOnClickListener {
                regularSeasonButtonClick(pagingAdapter, season)
            }

            binding.btnPlayoffs.setOnClickListener {
                playoffsButtonClick(pagingAdapter, season)
            }
        }
        bottomSheet.show(requireActivity().supportFragmentManager, "FilterSeason")
    }

    private fun regularSeasonButtonClick(pagingAdapter: TeamMatchesPagingAdapter, season: String) {
        binding.btnRegularSeason.isActivated = true
        binding.btnPlayoffs.isActivated = false
        submitPagingAdapterData(pagingAdapter, season,false)
    }

    private fun playoffsButtonClick(pagingAdapter: TeamMatchesPagingAdapter, season: String) {
        binding.btnPlayoffs.isActivated = true
        binding.btnRegularSeason.isActivated = false
        submitPagingAdapterData(pagingAdapter, season, true)
    }

}