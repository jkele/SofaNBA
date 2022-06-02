package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.paging.EXTRA_PLAYER
import hr.algebra.sofanba.databinding.FragmentPlayerStatisticsBinding
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.SeasonStats
import hr.algebra.sofanba.network.model.response.StatsResponse
import hr.algebra.sofanba.viewmodels.PlayerStatisticsViewModel
import hr.algebra.sofanba.views.PlayerStatisticsView
import kotlinx.android.synthetic.main.fragment_player_statistics.*
import java.lang.Exception

class PlayerStatisticsFragment: Fragment(R.layout.fragment_player_statistics) {

    private lateinit var binding: FragmentPlayerStatisticsBinding
    private val viewModel: PlayerStatisticsViewModel by activityViewModels()

    private lateinit var selectedPlayer: Player

    private var statViewsList = ArrayList<PlayerStatisticsView>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerStatisticsBinding.inflate(inflater, container, false)

        selectedPlayer = requireActivity().intent.getSerializableExtra(EXTRA_PLAYER) as Player

        binding.root.children.forEach {
            if (it is PlayerStatisticsView) {
                statViewsList.add(it)
            }
        }

        viewModel.playerSeasonAveragesList.observe(viewLifecycleOwner) {
            try {
                binding.progressBar.visibility = ProgressBar.VISIBLE

                val statsResponseList = it.take(4)
                val seasonAveragesList = arrayListOf<SeasonStats>()

                statsResponseList.forEach {
                    seasonAveragesList.add(it.data!![0])
                }

                setSeasonValues(seasonAveragesList)
                setStatValues(seasonAveragesList)

                binding.progressBar.visibility = ProgressBar.GONE
            } catch (e: Exception) {

            }
        }

        viewModel.getPlayerSeasonAveragesList(selectedPlayer.id)

        return binding.root
    }

    private fun setSeasonValues(seasonAveragesList: ArrayList<SeasonStats>) {
        if (seasonAveragesList.isEmpty()) return
        binding.tvSeasonOne.text = seasonAveragesList[0].season.toString()
        binding.tvSeasonTwo.text = seasonAveragesList[1].season.toString()
        binding.tvSeasonThree.text = seasonAveragesList[2].season.toString()
        binding.tvSeasonFour.text = seasonAveragesList[3].season.toString()
    }

    private fun setStatValues(seasonAveragesList: ArrayList<SeasonStats>) {
        if (seasonAveragesList.isEmpty()) return
        binding.statMin.setupPlayerStatisticsView(
            "MIN",
            seasonAveragesList[0].convertMinToDouble(),
            seasonAveragesList[1].convertMinToDouble(),
            seasonAveragesList[2].convertMinToDouble(),
            seasonAveragesList[3].convertMinToDouble()
        )

        binding.statFgm.setupPlayerStatisticsView(
            "FGM", seasonAveragesList[0].fgm,
            seasonAveragesList[1].fgm,
            seasonAveragesList[2].fgm, seasonAveragesList[3].fgm
        )

        binding.statFga.setupPlayerStatisticsView(
            "FGA", seasonAveragesList[0].fga, seasonAveragesList[1].fga,
            seasonAveragesList[2].fga, seasonAveragesList[3].fga
        )

        binding.statFgmThree.setupPlayerStatisticsView(
            "FG3M", seasonAveragesList[0].fg3m, seasonAveragesList[1].fg3m,
            seasonAveragesList[2].fg3m, seasonAveragesList[3].fg3m
        )

        binding.statFgaThree.setupPlayerStatisticsView(
            "FG3A", seasonAveragesList[0].fg3a, seasonAveragesList[1].fg3a,
            seasonAveragesList[2].fg3a, seasonAveragesList[3].fg3a
        )

        binding.statFtm.setupPlayerStatisticsView(
            "FTM", seasonAveragesList[0].ftm, seasonAveragesList[1].ftm,
            seasonAveragesList[2].ftm, seasonAveragesList[3].ftm
        )

        binding.statFta.setupPlayerStatisticsView(
            "FTA", seasonAveragesList[0].fta, seasonAveragesList[1].fta,
            seasonAveragesList[2].fta, seasonAveragesList[3].fta
        )

        binding.statOreb.setupPlayerStatisticsView(
            "OREB", seasonAveragesList[0].oreb, seasonAveragesList[1].oreb,
            seasonAveragesList[2].oreb, seasonAveragesList[3].oreb
        )

        binding.statDreb.setupPlayerStatisticsView(
            "DREB", seasonAveragesList[0].dreb, seasonAveragesList[1].dreb,
            seasonAveragesList[2].dreb, seasonAveragesList[3].dreb
        )

        binding.statReb.setupPlayerStatisticsView(
            "REB", seasonAveragesList[0].reb, seasonAveragesList[1].reb,
            seasonAveragesList[2].reb, seasonAveragesList[3].reb
        )

        binding.statAst.setupPlayerStatisticsView(
            "AST", seasonAveragesList[0].ast, seasonAveragesList[1].ast,
            seasonAveragesList[2].ast, seasonAveragesList[3].ast
        )

        binding.statStl.setupPlayerStatisticsView(
            "STL", seasonAveragesList[0].stl, seasonAveragesList[1].stl,
            seasonAveragesList[2].stl, seasonAveragesList[3].stl
        )

        binding.statBlk.setupPlayerStatisticsView(
            "BLK", seasonAveragesList[0].blk, seasonAveragesList[1].blk,
            seasonAveragesList[2].blk, seasonAveragesList[3].blk
        )

        binding.statTov.setupPlayerStatisticsView(
            "TOV", seasonAveragesList[0].turnover, seasonAveragesList[1].turnover,
            seasonAveragesList[2].turnover, seasonAveragesList[3].turnover
        )

        binding.statPf.setupPlayerStatisticsView(
            "PF", seasonAveragesList[0].pf, seasonAveragesList[1].pf,
            seasonAveragesList[2].pf, seasonAveragesList[3].pf
        )

        binding.statPts.setupPlayerStatisticsView(
            "PTS", seasonAveragesList[0].pts, seasonAveragesList[1].pts,
            seasonAveragesList[2].pts, seasonAveragesList[3].pts
        )

        binding.statFgPerc.setupPlayerStatisticsView(
            "FG%", seasonAveragesList[0].fg_pct, seasonAveragesList[1].fg_pct,
            seasonAveragesList[2].fg_pct, seasonAveragesList[3].fg_pct
        )

        binding.statFgThreePerc.setupPlayerStatisticsView(
            "FG3%", seasonAveragesList[0].fg3_pct, seasonAveragesList[1].fg3_pct,
            seasonAveragesList[2].fg3_pct, seasonAveragesList[3].fg3_pct
        )

        binding.statFtPerc.setupPlayerStatisticsView(
            "FT%", seasonAveragesList[0].ft_pct, seasonAveragesList[1].ft_pct,
            seasonAveragesList[2].ft_pct, seasonAveragesList[3].ft_pct
        )
    }

}