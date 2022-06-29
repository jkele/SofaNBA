package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.paging.EXTRA_PLAYER
import hr.algebra.sofanba.databinding.FragmentPlayerStatisticsBinding
import hr.algebra.sofanba.helpers.isOnline
import hr.algebra.sofanba.helpers.showCustomDialog
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.SeasonStats
import hr.algebra.sofanba.viewmodels.PlayerStatisticsViewModel
import hr.algebra.sofanba.views.PlayerStatisticsView
import java.lang.Exception

class PlayerStatisticsFragment : Fragment(R.layout.fragment_player_statistics) {

    private lateinit var binding: FragmentPlayerStatisticsBinding
    private val viewModel: PlayerStatisticsViewModel by activityViewModels()

    private lateinit var selectedPlayer: Player

    private var statViewsList = ArrayList<PlayerStatisticsView>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerStatisticsBinding.inflate(inflater, container, false)

        selectedPlayer = requireActivity().intent.getSerializableExtra(EXTRA_PLAYER) as Player

        binding.root.children.forEach {
            if (it is PlayerStatisticsView) {
                statViewsList.add(it)
            }
        }

        viewModel.playerSeasonAveragesList.observe(viewLifecycleOwner) {
            try {
                val statsResponseList =
                    it.filter { it.data!!.size != 0 }.sortedByDescending { it.data!![0].season }
                val statsList = ArrayList<SeasonStats>()
                statsResponseList.forEach {
                    statsList.add(it.data!![0])
                }

                if (statsList.size > 4) {
                    val lastFourStatsList = ArrayList(statsList.take(4))
                    setSeasonValues(lastFourStatsList)
                    setStatValues(lastFourStatsList)
                } else {
                    setSeasonValues(statsList)
                    setStatValues(statsList)
                }
            } catch (e: Exception) {
                showCustomDialog("Error loading data. Try again later!", requireContext())
            }

        }

        if (requireContext().isOnline()) {
            viewModel.getSeasonAveragesList(selectedPlayer.id)
        } else {
            showCustomDialog(getString(R.string.no_internet_connection), requireContext())
        }

        return binding.root
    }

    private fun setSeasonValues(seasonAveragesList: ArrayList<SeasonStats>) {
        if (seasonAveragesList.isEmpty()) return
        when (seasonAveragesList.size) {
            1 -> binding.tvSeasonOne.text = seasonAveragesList[0].season.toString()
            2 -> {
                binding.tvSeasonOne.text = seasonAveragesList[0].season.toString()
                binding.tvSeasonTwo.text = seasonAveragesList[1].season.toString()
            }
            3 -> {
                binding.tvSeasonOne.text = seasonAveragesList[0].season.toString()
                binding.tvSeasonTwo.text = seasonAveragesList[1].season.toString()
                binding.tvSeasonThree.text = seasonAveragesList[2].season.toString()
            }
            4 -> {
                binding.tvSeasonOne.text = seasonAveragesList[0].season.toString()
                binding.tvSeasonTwo.text = seasonAveragesList[1].season.toString()
                binding.tvSeasonThree.text = seasonAveragesList[2].season.toString()
                binding.tvSeasonFour.text = seasonAveragesList[3].season.toString()
            }
        }
    }

    private fun setStatValues(seasonAveragesList: ArrayList<SeasonStats>) {
        if (seasonAveragesList.isEmpty()) return
        binding.statMin.setupPlayerStatisticsView(
            "MIN",
            seasonAveragesList[0].convertMinToDouble(),
            if (seasonAveragesList.size >= 2) seasonAveragesList[1].convertMinToDouble() else null,
            if (seasonAveragesList.size >= 3) seasonAveragesList[2].convertMinToDouble() else null,
            if (seasonAveragesList.size >= 4) seasonAveragesList[3].convertMinToDouble() else null
        )

        binding.statFgm.setupPlayerStatisticsView(
            "FGM",
            seasonAveragesList[0].fgm,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].fgm else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].fgm else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].fgm else null
        )

        binding.statFga.setupPlayerStatisticsView(
            "FGA",
            seasonAveragesList[0].fga,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].fga else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].fga else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].fga else null
        )

        binding.statFgmThree.setupPlayerStatisticsView(
            "FG3M",
            seasonAveragesList[0].fg3m,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].fg3m else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].fg3m else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].fg3m else null
        )

        binding.statFgaThree.setupPlayerStatisticsView(
            "FG3A",
            seasonAveragesList[0].fg3a,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].fg3a else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].fg3a else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].fg3a else null
        )

        binding.statFtm.setupPlayerStatisticsView(
            "FTM",
            seasonAveragesList[0].ftm,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].ftm else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].ftm else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].ftm else null
        )

        binding.statFta.setupPlayerStatisticsView(
            "FTA",
            seasonAveragesList[0].fta,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].fta else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].fta else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].fta else null
        )

        binding.statOreb.setupPlayerStatisticsView(
            "OREB",
            seasonAveragesList[0].oreb,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].oreb else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].oreb else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].oreb else null
        )

        binding.statDreb.setupPlayerStatisticsView(
            "DREB",
            seasonAveragesList[0].dreb,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].dreb else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].dreb else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].dreb else null
        )

        binding.statReb.setupPlayerStatisticsView(
            "REB",
            seasonAveragesList[0].reb,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].reb else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].reb else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].reb else null
        )

        binding.statAst.setupPlayerStatisticsView(
            "AST",
            seasonAveragesList[0].ast,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].ast else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].ast else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].ast else null
        )

        binding.statStl.setupPlayerStatisticsView(
            "STL",
            seasonAveragesList[0].stl,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].stl else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].stl else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].stl else null
        )

        binding.statBlk.setupPlayerStatisticsView(
            "BLK",
            seasonAveragesList[0].blk,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].blk else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].blk else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].blk else null
        )

        binding.statTov.setupPlayerStatisticsView(
            "TOV",
            seasonAveragesList[0].turnover,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].turnover else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].turnover else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].turnover else null
        )

        binding.statPf.setupPlayerStatisticsView(
            "PF",
            seasonAveragesList[0].pf,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].pf else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].pf else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].pf else null
        )

        binding.statPts.setupPlayerStatisticsView(
            "PTS",
            seasonAveragesList[0].pts,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].pts else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].pts else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].pts else null
        )

        binding.statFgPerc.setupPlayerStatisticsView(
            "FG%",
            seasonAveragesList[0].fg_pct,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].fg_pct else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].fg_pct else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].fg_pct else null
        )

        binding.statFgThreePerc.setupPlayerStatisticsView(
            "FG3%",
            seasonAveragesList[0].fg3_pct,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].fg3_pct else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].fg3_pct else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].fg3_pct else null
        )

        binding.statFtPerc.setupPlayerStatisticsView(
            "FT%",
            seasonAveragesList[0].ft_pct,
            if(seasonAveragesList.size >= 2) seasonAveragesList[1].ft_pct else null,
            if(seasonAveragesList.size >= 3) seasonAveragesList[2].ft_pct else null,
            if(seasonAveragesList.size >= 4) seasonAveragesList[3].ft_pct else null
        )
    }



}