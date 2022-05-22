package hr.algebra.sofanba.adapters.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerMatchItemViewBinding
import hr.algebra.sofanba.network.model.Game
import hr.algebra.sofanba.network.model.GameStats

class PlayerMatchesPagingAdapter(
    private val context: Context,
    diffCallback: DiffUtil.ItemCallback<GameStats>
): PagingDataAdapter<GameStats, PlayerMatchesPagingAdapter.PlayerMatchViewHolder>(diffCallback) {

    class PlayerMatchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = PlayerMatchItemViewBinding.bind(itemView)
    }

    override fun onBindViewHolder(holder: PlayerMatchViewHolder, position: Int) {
        val gameStats = getItem(position)

        holder.binding.tvHomeTeamPoints.text = gameStats!!.game.home_team_score.toString()
        holder.binding.tvAwayTeamPoints.text = gameStats.game.visitor_team_score.toString()

        holder.binding.ivHomeTeamAbbreviation.text = gameStats.team.abbreviation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerMatchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.player_match_item_view, parent, false)
        return PlayerMatchViewHolder(view)
    }

}