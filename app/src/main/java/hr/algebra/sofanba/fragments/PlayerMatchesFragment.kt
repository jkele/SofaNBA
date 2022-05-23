package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
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

        lifecycleScope.launch {
            val flow = viewModel.getPlayerMatchesFlow(2017, selectedPlayer.id, false)
            flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }


        return binding.root
    }

}