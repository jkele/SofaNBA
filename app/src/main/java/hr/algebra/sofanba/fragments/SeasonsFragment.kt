package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.paging.SeasonMatchesPagingAdapter
import hr.algebra.sofanba.databinding.FragmentSeasonsBinding
import hr.algebra.sofanba.network.paging.season.SeasonMatchDiff
import hr.algebra.sofanba.viewmodels.SeasonsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SeasonsFragment: Fragment(R.layout.fragment_seasons) {

    private lateinit var binding: FragmentSeasonsBinding
    private val viewModel: SeasonsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeasonsBinding.inflate(inflater, container, false)

        binding.rvSeasonMatches.layoutManager = LinearLayoutManager(requireContext())

        val pagingAdapter = SeasonMatchesPagingAdapter(requireContext(), SeasonMatchDiff)
        binding.rvSeasonMatches.adapter = pagingAdapter

        pagingAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading){
                binding.seasonProgressBar.visibility = ProgressBar.VISIBLE
            } else {
                binding.seasonProgressBar.visibility = ProgressBar.GONE
            }
        }

        lifecycleScope.launch {
            val flow = viewModel.getSeasonMatchesFlow(2021, "2021-10-10" , false)
            flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }

        return binding.root
    }


}