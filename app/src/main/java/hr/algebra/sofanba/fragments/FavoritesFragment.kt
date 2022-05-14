package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.FavoritePlayerRecyclerAdapter
import hr.algebra.sofanba.databinding.FragmentFavoritesBinding
import hr.algebra.sofanba.viewmodels.FavoritesViewModel

class FavoritesFragment: Fragment(R.layout.fragment_favorites) {

    private lateinit var binding: FragmentFavoritesBinding

    private val viewModel: FavoritesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.rvFavoritePlayers.layoutManager = LinearLayoutManager(requireContext())

        viewModel.favoritePlayers.observe(viewLifecycleOwner) {
            val adapter = FavoritePlayerRecyclerAdapter(requireContext(), it){ player ->
                viewModel.deleteFavoritePlayer(player)
            }
            binding.rvFavoritePlayers.adapter = adapter
        }

        viewModel.getFavoritePlayers()

        return binding.root
    }

}