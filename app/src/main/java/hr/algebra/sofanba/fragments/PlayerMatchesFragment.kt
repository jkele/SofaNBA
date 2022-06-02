package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.paging.EXTRA_PLAYER
import hr.algebra.sofanba.adapters.paging.PlayerMatchesPagingAdapter
import hr.algebra.sofanba.databinding.FragmentPlayerMatchesBinding
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.paging.playerMatch.PlayerMatchDiff
import hr.algebra.sofanba.viewmodels.PlayerMatchesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlayerMatchesFragment: Fragment(R.layout.fragment_player_matches) {

    private lateinit var binding: FragmentPlayerMatchesBinding
    private val viewModel: PlayerMatchesViewModel by activityViewModels()

    private lateinit var selectedPlayer: Player

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerMatchesBinding.inflate(inflater, container, false)

        binding.rvPlayerMatches.layoutManager = LinearLayoutManager(requireContext())
        selectedPlayer = requireActivity().intent.getSerializableExtra(EXTRA_PLAYER) as Player

        val pagingAdapter = PlayerMatchesPagingAdapter(requireContext(), requireActivity().supportFragmentManager, PlayerMatchDiff)
        binding.rvPlayerMatches.adapter = pagingAdapter
        binding.emptyStateView.visibility = View.GONE

        pagingAdapter.addLoadStateListener {
            if (it.append.endOfPaginationReached) {
                if (pagingAdapter.itemCount < 1 && binding.btnPlayoffs.isActivated) {
                    binding.emptyStateView.visibility = View.VISIBLE
                    binding.emptyStateView.setupEmptyStateView(selectedPlayer.firstName + " "
                            + selectedPlayer.lastName + " hasn't played in the playoffs.")
                } else {
                    binding.emptyStateView.visibility = View.VISIBLE
                    binding.emptyStateView.setupEmptyStateView(selectedPlayer.firstName + " "
                            + selectedPlayer.lastName + " hasn't played this season.")
                }
            }
            if (it.refresh is LoadState.Loading){
                binding.progressBar.visibility = ProgressBar.VISIBLE
            } else {
                binding.progressBar.visibility = ProgressBar.GONE
            }
        }

        submitPagingAdapterData(pagingAdapter, false)
        setButtonListeners(pagingAdapter)

        return binding.root
    }

    private fun setButtonListeners(pagingAdapter: PlayerMatchesPagingAdapter) {
        binding.btnRegularSeason.isActivated = true
        binding.btnRegularSeason.setOnClickListener {
            binding.btnRegularSeason.isActivated = true
            binding.btnPlayoffs.isActivated = false
            submitPagingAdapterData(pagingAdapter, false)
        }

        binding.btnPlayoffs.setOnClickListener {
            binding.btnRegularSeason.isActivated = false
            binding.btnPlayoffs.isActivated = true
            submitPagingAdapterData(pagingAdapter, true)
        }
    }

    private fun submitPagingAdapterData(pagingAdapter: PlayerMatchesPagingAdapter, postseason: Boolean) {
        lifecycleScope.launch {
            val flow = viewModel.getPlayerMatchesFlow(2017, selectedPlayer.id, postseason)
            flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }
}