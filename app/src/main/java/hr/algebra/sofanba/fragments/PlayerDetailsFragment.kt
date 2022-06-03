package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.HighlightRecyclerAdapter
import hr.algebra.sofanba.adapters.image.ImageSliderAdapter
import hr.algebra.sofanba.adapters.paging.EXTRA_PLAYER
import hr.algebra.sofanba.adapters.paging.EXTRA_PLAYER_IMAGE_PLACEHOLDER
import hr.algebra.sofanba.databinding.FragmentPlayerDetailsBinding
import hr.algebra.sofanba.fragments.bottomsheet.AddPhotoBottomSheet
import hr.algebra.sofanba.fragments.bottomsheet.EditPhotosBottomSheet
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.PlayerImage
import hr.algebra.sofanba.viewmodels.PlayerDetailsViewModel

class PlayerDetailsFragment : Fragment(R.layout.fragment_player_details) {

    private lateinit var binding: FragmentPlayerDetailsBinding
    private val viewModel: PlayerDetailsViewModel by activityViewModels()

    private val imageSliderAdapter by lazy { ImageSliderAdapter(requireContext(), arrayListOf()) }
    private var playerImageList = ArrayList<PlayerImage>()

    private lateinit var selectedPlayer: Player

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerDetailsBinding.inflate(layoutInflater, container, false)

        selectedPlayer = requireActivity().intent.getSerializableExtra(EXTRA_PLAYER) as Player
        val placeholderImage = requireActivity().intent.getIntExtra(
            EXTRA_PLAYER_IMAGE_PLACEHOLDER,
            R.drawable.ic_player_one
        )

        binding.playerInfoView.setupPlayerInfoView(selectedPlayer)
        binding.imageProgressBar.visibility = View.VISIBLE

        binding.rvHighlights.layoutManager = LinearLayoutManager(requireContext())
        viewModel.highlightsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.tvHighlightsTitle.visibility = TextView.VISIBLE
                binding.rvHighlights.visibility = RecyclerView.VISIBLE

                val adapter = HighlightRecyclerAdapter(requireContext(), it)
                binding.rvHighlights.adapter = adapter
            }
        }

        if (imageSliderAdapter.count == 0) binding.ivPlaceholder.load(placeholderImage)

        binding.imageSliderViewPager.adapter = imageSliderAdapter

        viewModel.imagesList.observe(viewLifecycleOwner) { imageList ->
            binding.imageProgressBar.visibility = View.VISIBLE

            playerImageList.addAll(imageList)

            binding.ivPlaceholder.visibility = View.GONE
            imageSliderAdapter.setImageList(playerImageList)
            binding.indicator.setViewPager(binding.imageSliderViewPager)

            binding.imageProgressBar.visibility = View.GONE

            binding.btnEditImage.setOnClickListener {
                openEditPhotoBottomSheet(playerImageList)
            }
        }

        binding.btnAddImage.setOnClickListener {
            openAddPhotoBottomSheet()
        }

        viewModel.getHighlightsForPlayer(selectedPlayer.id)
        viewModel.getPlayerImages(selectedPlayer.id)

        return binding.root
    }

    private fun openAddPhotoBottomSheet() {
        val bottomSheet = AddPhotoBottomSheet(selectedPlayer, {
            viewModel.addImageForPlayer(it)
        }, {
            imageSliderAdapter.updateImageList(it)
            playerImageList.add(it)
            binding.indicator.setViewPager(binding.imageSliderViewPager)
        }, {
            binding.ivPlaceholder.visibility = View.GONE
            binding.imageProgressBar.visibility = View.GONE
        })
        bottomSheet.show(requireActivity().supportFragmentManager, "AddPhoto")
    }

    private fun openEditPhotoBottomSheet(imageList: ArrayList<PlayerImage>) {
        val bottomSheet = EditPhotosBottomSheet(imageList, {
            viewModel.deletePlayerImage(it.id!!)
            playerImageList.remove(it)
        }, {
            imageSliderAdapter.setImageList(it)
            playerImageList.clear()
            playerImageList.addAll(it)
            binding.indicator.setViewPager(binding.imageSliderViewPager)
        },
            {
                viewModel.deleteAllPlayerImages(it)
                playerImageList.removeAll(it)
                imageSliderAdapter.clearImageList(it)
                if (playerImageList.isEmpty()) binding.ivPlaceholder.visibility = View.VISIBLE
                binding.indicator.setViewPager(binding.imageSliderViewPager)
            }, {
                val snack = Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT)
                snack.view.setBackgroundResource(R.drawable.background_custom_snackbar)
                snack.show()
            })
        bottomSheet.show(requireActivity().supportFragmentManager, "EditPhoto")
    }

}