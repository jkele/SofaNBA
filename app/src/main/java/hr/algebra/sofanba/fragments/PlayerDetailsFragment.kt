package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.EXTRA_PLAYER
import hr.algebra.sofanba.adapters.HighlightRecyclerAdapter
import hr.algebra.sofanba.databinding.FragmentPlayerDetailsBinding
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.viewmodels.PlayerDetailsViewModel

class PlayerDetailsFragment : Fragment(R.layout.fragment_player_details) {

    private lateinit var binding: FragmentPlayerDetailsBinding
    private val viewModel: PlayerDetailsViewModel by activityViewModels()

    private lateinit var selectedPlayer: Player

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerDetailsBinding.inflate(layoutInflater, container, false)

        selectedPlayer = requireActivity().intent.getSerializableExtra(EXTRA_PLAYER) as Player

        binding.playerInfoView.setupPlayerInfoView(selectedPlayer)

        binding.rvHighlights.layoutManager = LinearLayoutManager(requireContext())
        viewModel.highlightsList.observe(viewLifecycleOwner) {
            val adapter = HighlightRecyclerAdapter(requireContext(), it)
            binding.rvHighlights.adapter = adapter
        }


        viewModel.getHighlightsForPlayer(selectedPlayer.id)

        return binding.root
    }

}