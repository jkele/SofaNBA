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
import hr.algebra.sofanba.adapters.paging.SeasonMatchesPagingAdapter
import hr.algebra.sofanba.databinding.FragmentSeasonsBinding
import hr.algebra.sofanba.fragments.bottomsheet.FilterSeasonBottomSheet
import hr.algebra.sofanba.network.paging.season.SeasonMatchDiff
import hr.algebra.sofanba.viewmodels.SeasonsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SeasonsFragment: Fragment(R.layout.fragment_seasons) {

    private lateinit var binding: FragmentSeasonsBinding
    private val viewModel: SeasonsViewModel by activityViewModels()

    private val pagingAdapter by lazy { SeasonMatchesPagingAdapter(requireContext(), SeasonMatchDiff) }
    private var selectedSeason: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeasonsBinding.inflate(inflater, container, false)

        binding.rvSeasonMatches.layoutManager = LinearLayoutManager(requireContext())

        binding.btnFilter.setOnClickListener {
            openFilterBottomSheet()
        }

        binding.rvSeasonMatches.adapter = pagingAdapter

        pagingAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading){
                binding.seasonProgressBar.visibility = ProgressBar.VISIBLE
            } else {
                binding.seasonProgressBar.visibility = ProgressBar.GONE
            }
        }

        selectedSeason = "2021"
        submitPagingAdapterData(pagingAdapter, "2021",false)
        setButtonListeners()

        return binding.root
    }

    private fun setButtonListeners() {
        binding.btnRegularSeason.isActivated = true
        binding.btnRegularSeason.setOnClickListener {
            regularSeasonButtonClick("2021")
            selectedSeason = "2021"
        }

        binding.btnPlayoffs.setOnClickListener {
            playoffsButtonClick("2021")
            selectedSeason = "2021"
        }
    }

    private fun submitPagingAdapterData(pagingAdapter: SeasonMatchesPagingAdapter, season: String, postseason: Boolean) {
        lifecycleScope.launch {
            val flow = viewModel.getSeasonMatchesFlow(season.toInt(), postseason)
            flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun openFilterBottomSheet() {
        val bottomSheet = FilterSeasonBottomSheet(selectedSeason) { season ->
            selectedSeason = season
            submitPagingAdapterData(pagingAdapter, season, false)

            binding.btnRegularSeason.setOnClickListener {
                regularSeasonButtonClick(season)
            }

            binding.btnPlayoffs.setOnClickListener {
                playoffsButtonClick(season)
            }
        }
        bottomSheet.show(requireActivity().supportFragmentManager, "FilterSeason")
    }

    private fun regularSeasonButtonClick(season: String) {
        binding.btnRegularSeason.isActivated = true
        binding.btnPlayoffs.isActivated = false
        submitPagingAdapterData(pagingAdapter, season,false)
    }

    private fun playoffsButtonClick(season: String) {
        binding.btnPlayoffs.isActivated = true
        binding.btnRegularSeason.isActivated = false
        submitPagingAdapterData(pagingAdapter, season, true)
    }

}