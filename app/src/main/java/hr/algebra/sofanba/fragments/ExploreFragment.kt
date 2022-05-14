package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.PlayerPagingAdapter
import hr.algebra.sofanba.adapters.TeamRecyclerAdapter
import hr.algebra.sofanba.databinding.FragmentExploreBinding
import hr.algebra.sofanba.network.paging.PlayerDiff
import hr.algebra.sofanba.viewmodels.ExploreViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private val viewModel: ExploreViewModel by activityViewModels()

    private lateinit var binding: FragmentExploreBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)

        binding.rvPlayers.layoutManager = LinearLayoutManager(requireContext())

        val spinnerItems = resources.getStringArray(R.array.SpinnerItems)
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> getPlayersList()
                    1 -> getTeamsList()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }



        return binding.root
    }

    fun getPlayersList(){
        binding.tvAll.text = getString(R.string.all_players)
        val pagingAdapter = PlayerPagingAdapter(requireContext(), PlayerDiff)
        binding.rvPlayers.adapter = pagingAdapter
        lifecycleScope.launch {
            binding.progressBar.visibility = ProgressBar.VISIBLE
            viewModel.flow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
        binding.progressBar.visibility = ProgressBar.GONE
    }

    fun getTeamsList(){
        binding.tvAll.text = getString(R.string.all_teams)
        viewModel.teamsList.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = ProgressBar.VISIBLE
            val adapter = TeamRecyclerAdapter(requireContext(), it)
            binding.rvPlayers.adapter = adapter
            binding.progressBar.visibility = ProgressBar.GONE
        }
        viewModel.getTeamsList()
    }

}