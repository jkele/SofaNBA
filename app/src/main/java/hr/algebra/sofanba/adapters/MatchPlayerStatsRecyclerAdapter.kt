package hr.algebra.sofanba.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerStatsItemViewBinding
import hr.algebra.sofanba.helpers.getTeamAbbr
import hr.algebra.sofanba.helpers.loadPlayerImagePlaceholder
import hr.algebra.sofanba.network.model.GameStats
import hr.algebra.sofanba.network.model.PlayerImage

class MatchPlayerStatsRecyclerAdapter(
    private val context: Context,
    private val statsList: ArrayList<GameStats>,
    private val imagesList: ArrayList<ArrayList<PlayerImage>>,
    private val stat: String
): RecyclerView.Adapter<MatchPlayerStatsRecyclerAdapter.MatchPlayerStatsViewHolder>() {

    class MatchPlayerStatsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = PlayerStatsItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPlayerStatsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.player_stats_item_view, parent, false)
        return MatchPlayerStatsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MatchPlayerStatsViewHolder, position: Int) {
        val playerStats = statsList[position]

        holder.binding.tvItemOrder.text = (position + 1).toString()
        holder.binding.tvPlayerName.text = playerStats.player.firstName + " " + playerStats.player.lastName
        holder.binding.tvPlayerTeamAbbr.text = getTeamAbbr(playerStats.player.team_id)


        imagesList.forEach {
            it.forEach { playerImage ->
                if(playerImage.playerId == playerStats.player.id) {
                    Picasso.get().load(playerImage.imageUrl).fit().centerCrop().into(holder.binding.ivPlayerImage)
                }
                else {
                    loadPlayerImagePlaceholder(position, holder.binding.ivPlayerImage)
                }
            }
        }

        when(stat) {
            "fgm" -> holder.binding.tvPlayerStatValue.text = playerStats.fgm.toString()
            "fg3m" -> holder.binding.tvPlayerStatValue.text = playerStats.fg3m.toString()
            "oreb" -> holder.binding.tvPlayerStatValue.text = playerStats.oreb.toString()
            "reb" -> holder.binding.tvPlayerStatValue.text = playerStats.reb.toString()
            "ast" -> holder.binding.tvPlayerStatValue.text = playerStats.ast.toString()
            "to" -> holder.binding.tvPlayerStatValue.text = playerStats.turnover.toString()
        }
    }

    override fun getItemCount(): Int {
        return statsList.size
    }

}