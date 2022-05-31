package hr.algebra.sofanba.fragments.bottomsheet

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import hr.algebra.sofanba.databinding.BottomsheetPlayerMatchStatsBinding
import hr.algebra.sofanba.helpers.getTeamAbbr
import hr.algebra.sofanba.helpers.loadTeamMatchImage
import hr.algebra.sofanba.network.model.GameStats
import java.text.SimpleDateFormat

class PlayerMatchStatsBottomSheet(val gameStats: GameStats): BottomSheetDialogFragment() {

    private lateinit var binding : BottomsheetPlayerMatchStatsBinding

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetPlayerMatchStatsBinding.inflate(inflater, container, false)

        loadTeamMatchImage(getTeamAbbr(gameStats.game.home_team_id), binding.ivHomeTeamLogo)
        loadTeamMatchImage(getTeamAbbr(gameStats.game.visitor_team_id), binding.ivAwayTeamLogo)

        binding.tvHomeTeamAbbreviation.text = getTeamAbbr(gameStats.game.home_team_id)
        binding.tvAwayTeamAbbreviation.text = getTeamAbbr(gameStats.game.visitor_team_id)

        val matchDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(gameStats.game.date)
        binding.tvMatchDate.text =
            matchDate!!.date.toString() + " " + SimpleDateFormat("MMM").format(matchDate).uppercase() +
                    " " +SimpleDateFormat("yyyy").format(matchDate)
        binding.tvPlayerName.text = gameStats.player.firstName + " " + gameStats.player.lastName

        if(gameStats.min != null) {
            binding.timePlayed.fillPlayerMatchStats("Time played (MIN)", gameStats.min.split(':')[0].toInt())
            binding.fieldGoalsMade.fillPlayerMatchStats("Field goals made (FGM)", gameStats.fgm)
            binding.fieldGoalsAttempts.fillPlayerMatchStats("Field goals attempts (FGA)", gameStats.fga)
            binding.fieldGoalsThreeMade.fillPlayerMatchStats("Field goal 3-pointers made (FG3M)", gameStats.fg3m)
            binding.fieldGoalsThreeAttempts.fillPlayerMatchStats("Field goal 3-pointers attempts (FG3A)", gameStats.fg3a)
            binding.freeThrowsMade.fillPlayerMatchStats("Free throws made (FTM)", gameStats.ftm)
            binding.freeThrowsAttempts.fillPlayerMatchStats("Free throws attempts (FTA)", gameStats.fta)
            binding.offensiveRebounds.fillPlayerMatchStats("Offensive rebounds (OREB)", gameStats.oreb)
            binding.defensiveRebounds.fillPlayerMatchStats("Defensive rebounds (DREB)", gameStats.dreb)
            binding.rebounds.fillPlayerMatchStats("Rebounds (REB)", gameStats.reb)
            binding.assists.fillPlayerMatchStats("Assists (AST)", gameStats.ast)
            binding.steals.fillPlayerMatchStats("Steals (STL)", gameStats.stl)
            binding.blocks.fillPlayerMatchStats("Blocks (BLK)", gameStats.blk)
            binding.turnover.fillPlayerMatchStats("Turnover (TOV)", gameStats.turnover)
            binding.personalFouls.fillPlayerMatchStats("Personal faults (PF)", gameStats.pf)
            binding.points.fillPlayerMatchStats("Points (PTS)", gameStats.pts)
            binding.fieldGoalPer.fillPlayerMatchStats("Field goal percentage (FG%)", gameStats.fg_pct.toInt())
            binding.fieldGoalThreePer.fillPlayerMatchStats("Field goal 3-pointer percentage (FG3%)", gameStats.fg3_pct.toInt())
            binding.freeThrowPer.fillPlayerMatchStats("Free throw percentage (FT%)", gameStats.ft_pct.toInt())
        }

        return binding.root
    }


}