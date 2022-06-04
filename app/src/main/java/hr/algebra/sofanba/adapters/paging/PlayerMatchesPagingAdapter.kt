package hr.algebra.sofanba.adapters.paging

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerMatchItemViewBinding
import hr.algebra.sofanba.fragments.bottomsheet.PlayerMatchStatsBottomSheet
import hr.algebra.sofanba.helpers.getTeamAbbr
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.GameStats
import java.text.SimpleDateFormat

class PlayerMatchesPagingAdapter(
    private val context: Context,
    private val supportFragmentManager: FragmentManager,
    diffCallback: DiffUtil.ItemCallback<GameStats>
) : PagingDataAdapter<GameStats, PlayerMatchesPagingAdapter.PlayerMatchViewHolder>(diffCallback) {

    class PlayerMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PlayerMatchItemViewBinding.bind(itemView)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: PlayerMatchViewHolder, position: Int) {
        val gameStats = getItem(position)

        val matchDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(gameStats!!.game.date)

        holder.binding.tvMatchDate.text = SimpleDateFormat("dd").format(matchDate!!).toString() +
                " " +SimpleDateFormat("MMM").format(matchDate).uppercase() + " " +
                SimpleDateFormat("yyyy"). format(matchDate)

        holder.binding.tvHomeTeamPoints.text = gameStats.game.home_team_score.toString()
        holder.binding.tvAwayTeamPoints.text = gameStats.game.visitor_team_score.toString()

        holder.binding.tvHomeTeamAbbreviation.text = getTeamAbbr(gameStats.game.home_team_id)
        holder.binding.tvAwayTeamAbbreviation.text = getTeamAbbr(gameStats.game.visitor_team_id)

        if (gameStats.game.home_team_score > gameStats.game.visitor_team_score) {
            holder.binding.tvHomeTeamPoints.isActivated = true
            holder.binding.tvAwayTeamPoints.isActivated = false
        } else {
            holder.binding.tvHomeTeamPoints.isActivated = false
            holder.binding.tvAwayTeamPoints.isActivated = true
        }

        loadTeamImage(
            context,
            getTeamAbbr(gameStats.game.home_team_id),
            holder.binding.ivHomeTeamImage,
            holder.binding.homeImageContainer
        )
        loadTeamImage(
            context,
            getTeamAbbr(gameStats.game.visitor_team_id),
            holder.binding.ivAwayTeamImage,
            holder.binding.awayImageContainer
        )

        holder.binding.btnPlayerStats.setOnClickListener {
            val bottomSheet = PlayerMatchStatsBottomSheet(gameStats)
            bottomSheet.show(supportFragmentManager, "PlayerStats")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerMatchViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.player_match_item_view, parent, false)
        return PlayerMatchViewHolder(view)
    }


}