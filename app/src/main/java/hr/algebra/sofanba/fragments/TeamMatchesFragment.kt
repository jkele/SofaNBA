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
import hr.algebra.sofanba.adapters.TeamMatchesPagingAdapter
import hr.algebra.sofanba.databinding.FragmentTeamMatchesBinding
import hr.algebra.sofanba.network.model.Team
import hr.algebra.sofanba.network.paging.match.MatchDiff
import hr.algebra.sofanba.viewmodels.TeamMatchesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TeamMatchesFragment: Fragment(R.layout.fragment_team_matches) {

    private lateinit var binding: FragmentTeamMatchesBinding
    private val viewModel: TeamMatchesViewModel by activityViewModels()

    private lateinit var selectedTeam: Team
    private var postSeason = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamMatchesBinding.inflate(inflater, container, false)

        binding.rvMatches.layoutManager = LinearLayoutManager(requireContext())
        selectedTeam = requireActivity().intent.getSerializableExtra(EXTRA_TEAM) as Team

        val pagingAdapter = TeamMatchesPagingAdapter(requireContext(), selectedTeam, MatchDiff)
        binding.rvMatches.adapter = pagingAdapter
        submitPagingAdapterData(pagingAdapter)


        setButtonListeners(pagingAdapter)


        return binding.root
    }

    private fun setButtonListeners(pagingAdapter: TeamMatchesPagingAdapter){
        binding.btnRegularSeason.setOnClickListener {
            postSeason = false
            submitPagingAdapterData(pagingAdapter)
        }

        binding.btnPlayoffs.setOnClickListener {
            postSeason = true
            submitPagingAdapterData(pagingAdapter)
        }
    }

    private fun submitPagingAdapterData(pagingAdapter: TeamMatchesPagingAdapter) {
        lifecycleScope.launch {
            val flow = viewModel.getMatchesFlow(selectedTeam.id, postSeason)
            flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

}