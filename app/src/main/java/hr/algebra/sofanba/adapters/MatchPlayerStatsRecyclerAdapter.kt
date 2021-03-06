package hr.algebra.sofanba.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.sofanba.PlayerActivity
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.paging.EXTRA_PLAYER
import hr.algebra.sofanba.databinding.PlayerStatsItemViewBinding
import hr.algebra.sofanba.helpers.getTeamAbbr
import hr.algebra.sofanba.helpers.loadPlayerImagePlaceholder
import hr.algebra.sofanba.network.model.GameStats
import hr.algebra.sofanba.network.model.Player
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
            for (i in 0..it.size) {
                if (it[0].playerId == playerStats.player.id) {
                    Picasso.get().load(it[0].imageUrl).fit().centerCrop()
                        .into(holder.binding.ivPlayerImage)
                } else {
                    loadPlayerImagePlaceholder(position, holder.binding.ivPlayerImage)
                }
            }
        }

        val player = Player(playerStats.player.id, playerStats.player.firstName, playerStats.player.height_feet,
        playerStats.player.height_inches, playerStats.player.lastName, playerStats.player.position,
        playerStats.team, playerStats.player.weightPounds)

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra(EXTRA_PLAYER, player)
            }
            context.startActivity(intent)
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