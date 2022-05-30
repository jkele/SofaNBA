package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.HighlightRecyclerAdapter
import hr.algebra.sofanba.adapters.paging.EXTRA_MATCH
import hr.algebra.sofanba.databinding.FragmentMatchDetailsBinding
import hr.algebra.sofanba.fragments.bottomsheet.AddVideoBottomSheet
import hr.algebra.sofanba.helpers.getStadiumLocation
import hr.algebra.sofanba.network.model.Match
import hr.algebra.sofanba.viewmodels.MatchDetailsViewModel

class MatchDetailsFragment : Fragment(R.layout.fragment_match_details) {

    private lateinit var binding: FragmentMatchDetailsBinding
    private val viewModel: MatchDetailsViewModel by viewModels()

    private lateinit var selectedMatch: Match
    private val adapter by lazy { HighlightRecyclerAdapter(requireContext(), arrayListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchDetailsBinding.inflate(inflater, container, false)

        selectedMatch = requireActivity().intent.getSerializableExtra(EXTRA_MATCH) as Match

        binding.matchInfoView.setupMatchInfoView(selectedMatch)
        binding.tvCityName.text = selectedMatch.homeTeam.city

        setupMatchStats()

        binding.rvHighlights.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHighlights.adapter = adapter

        viewModel.matchHighlightsList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.highlightsEmptyState.visibility = View.VISIBLE
                binding.btnAddVideo.visibility = View.GONE
            } else {
                binding.highlightsEmptyState.visibility = View.GONE
                binding.btnAddVideo.visibility = View.VISIBLE
                adapter.updateList(it)
            }

        }

        binding.btnAddVideo.setOnClickListener {
            val bottomSheet = AddVideoBottomSheet(selectedMatch, {
                viewModel.addHighlightForMatch(it)
            }, {
                    adapter.updateHighlightsListItems(it)
                })
            bottomSheet.show(requireActivity().supportFragmentManager, "AddVideo")
        }


        viewModel.getMatchStats(selectedMatch.id, 50, 0)
        viewModel.getMatchHighlights(selectedMatch.id)

        return binding.root
    }

    private fun setupMatchStats() {
        var totalHomeMade = 0.0
        var totalHomeAt = 0.0
        var totalAwayMade = 0.0
        var totalAwayAt = 0.0
        var totalHomeThreeMade = 0.0
        var totalHomeThreeAt = 0.0
        var totalAwayThreeMade = 0.0
        var totalAwayThreeAt = 0.0
        var homeReb = 0.0
        var awayReb = 0.0
        var homeAssists = 0.0
        var awayAssists = 0.0
        var homeTurnover = 0.0
        var awayTurnover = 0.0
        var homeOffensiveReb = 0.0
        var awayOffensiveReb = 0.0

        viewModel.matchStatsList.observe(viewLifecycleOwner) { gameStatsList ->

            gameStatsList.forEach {
                if (it.player.team_id == selectedMatch.homeTeam.id) {
                    totalHomeMade += it.fgm
                    totalHomeAt += it.fga

                    totalHomeThreeMade += it.fg3m
                    totalHomeThreeAt += it.fg3a

                    homeReb += it.dreb
                    homeAssists += it.ast
                    homeTurnover += it.turnover
                    homeOffensiveReb += it.oreb
                } else {
                    totalAwayMade += it.fgm
                    totalAwayAt += it.fga

                    totalAwayThreeMade += it.fg3m
                    totalAwayThreeAt += it.fg3a

                    awayReb += it.dreb
                    awayAssists += it.ast
                    awayTurnover += it.turnover
                    awayOffensiveReb += it.oreb
                }
            }

            binding.statFieldGoal.setupMatchStatsView(
                "FG%", totalHomeMade / totalHomeAt,
                totalAwayMade / totalAwayAt, true
            )
            binding.statThreePoint.setupMatchStatsView(
                "FG3%", totalHomeThreeMade / totalHomeThreeAt,
                totalAwayThreeMade / totalAwayThreeAt, true
            )
            binding.statRebound.setupMatchStatsView(
                "REB",
                homeReb,
                awayReb,
                false
            )
            binding.statAssists.setupMatchStatsView(
                "AST",
                homeAssists,
                awayAssists,
                false
            )
            binding.statTurnovers.setupMatchStatsView(
                "TOV", homeTurnover, awayTurnover,
                false
            )
            binding.statOffensiveRebound.setupMatchStatsView(
                "OREB", homeOffensiveReb, awayOffensiveReb,
                false
            )
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val location = getStadiumLocation(selectedMatch.homeTeam.abbreviation)
        val zoomLevel = 15f
        googleMap.addMarker(MarkerOptions().position(location))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}