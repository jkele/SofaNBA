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
import hr.algebra.sofanba.network.model.Team
import hr.algebra.sofanba.network.paging.match.MatchDiff
import hr.algebra.sofanba.viewmodels.TeamMatchesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TeamMatchesFragment : Fragment(R.layout.fragment_team_matches) {

    private lateinit var binding: FragmentTeamMatchesBinding
    private val viewModel: TeamMatchesViewModel by activityViewModels()

    private lateinit var selectedTeam: Team

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
        submitPagingAdapterData(pagingAdapter, false)


        setButtonListeners(pagingAdapter)


        return binding.root
    }

    private fun setButtonListeners(pagingAdapter: TeamMatchesPagingAdapter) {
        binding.btnRegularSeason.isActivated = true
        binding.btnRegularSeason.setOnClickListener {
            binding.btnRegularSeason.isActivated = true
            binding.btnPlayoffs.isActivated = false
            submitPagingAdapterData(pagingAdapter, false)
        }

        binding.btnPlayoffs.setOnClickListener {
            binding.btnPlayoffs.isActivated = true
            binding.btnRegularSeason.isActivated = false
            submitPagingAdapterData(pagingAdapter, true)
        }
    }

    private fun submitPagingAdapterData(
        pagingAdapter: TeamMatchesPagingAdapter,
        postseason: Boolean
    ) {
        lifecycleScope.launch {
            val flow = viewModel.getMatchesFlow(selectedTeam.id, postseason)
            flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

}