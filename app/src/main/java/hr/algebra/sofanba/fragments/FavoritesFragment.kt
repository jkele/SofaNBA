package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.FavoritePlayerRecyclerAdapter
import hr.algebra.sofanba.adapters.TeamRecyclerAdapter
import hr.algebra.sofanba.databinding.FragmentFavoritesBinding
import hr.algebra.sofanba.viewmodels.FavoritesViewModel

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private lateinit var binding: FragmentFavoritesBinding

    private val viewModel: FavoritesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.rvFavoritePlayers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteTeams.layoutManager = LinearLayoutManager(requireContext())

        loadFavoritePlayers()
        loadFavoriteTeams()

        viewModel.getFavoritePlayersAndImages()
        viewModel.getFavoriteTeams()

        return binding.root
    }

    private fun loadFavoritePlayers() {
        viewModel.favoritePlayers.observe(viewLifecycleOwner) { playerList ->
            viewModel.testPlayerImages.observe(viewLifecycleOwner) { imagesList ->
                binding.progressBar.visibility = ProgressBar.VISIBLE

                val adapter = FavoritePlayerRecyclerAdapter(
                    requireContext(), playerList,
                    imagesList
                ) { player ->
                    viewModel.deleteFavoritePlayer(player)
                }
                binding.rvFavoritePlayers.adapter = adapter

                binding.progressBar.visibility = ProgressBar.GONE
            }
            setEmptyState(binding.playersEmptyState, playerList.isNotEmpty())
        }
    }

    private fun loadFavoriteTeams() {
        viewModel.favoriteTeams.observe(viewLifecycleOwner) {
            setEmptyState(binding.teamsEmptyState, it.isNotEmpty())
            val adapter = TeamRecyclerAdapter(
                requireContext(), it, null, true,
                null
            ) { team ->
                viewModel.deleteFavoriteTeam(team)
            }
            binding.rvFavoriteTeams.adapter = adapter
        }
    }

    private fun setEmptyState(view: View, isNotEmpty: Boolean) {
        if (isNotEmpty) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }
}