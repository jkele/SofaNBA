package hr.algebra.sofanba.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.databinding.MatchInfoViewBinding
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.Match
import java.text.SimpleDateFormat

class MatchInfoView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding = MatchInfoViewBinding.inflate(LayoutInflater.from(context), this, true)

    @SuppressLint("SimpleDateFormat")
    fun setupMatchInfoView(match: Match) {
        val matchDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(match.date)

        binding.tvMatchDate.text = SimpleDateFormat("dd/MM/yyyy").format(matchDate!!)
        binding.tvMatchStatus.text = match.status

        binding.tvHomeTeamAbbreviation.text = match.homeTeam.abbreviation
        binding.tvAwayTeamAbbreviation.text = match.visitorTeam.abbreviation
        loadTeamImage(
            context,
            match.homeTeam.abbreviation,
            binding.ivHomeTeamImage,
            binding.homeImageContainer
        )
        loadTeamImage(
            context,
            match.visitorTeam.abbreviation,
            binding.ivAwayTeamImage,
            binding.awayImageContainer
        )

        binding.tvHomeTeamPoints.text = match.homeTeamScore.toString()
        binding.tvAwayTeamPoints.text = match.visitorTeamScore.toString()

        if (match.homeTeamScore > match.visitorTeamScore) {
            binding.tvHomeTeamPoints.isActivated = true
            binding.tvAwayTeamPoints.isActivated = false
        } else {
            binding.tvHomeTeamPoints.isActivated = false
            binding.tvAwayTeamPoints.isActivated = true
        }

    }

}