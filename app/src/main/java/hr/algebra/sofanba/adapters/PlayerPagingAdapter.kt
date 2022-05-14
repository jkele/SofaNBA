package hr.algebra.sofanba.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerItemViewBinding
import hr.algebra.sofanba.network.model.Player

class PlayerPagingAdapter(
    private val context: Context,
    private val favouritePlayers: ArrayList<Player>?,
    diffCallback: DiffUtil.ItemCallback<Player>,
    private val insertCallback: (Player) -> Unit
) :
    PagingDataAdapter<Player, PlayerPagingAdapter.PlayerViewHolder>(diffCallback) {

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PlayerItemViewBinding.bind(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = getItem(position)

        holder.binding.tvPlayerName.text = player?.firstName + " " + player?.lastName
        holder.binding.tvPlayerTeam.text = player?.team?.abbreviation

        val exists = isPlayerFavorite(player!!.id)

        if (exists){
            holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_filled)
        } else {
            holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_outline)
        }

        holder.binding.btnFavorite.setOnClickListener {
            if (!exists) {
                holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_filled)
                insertCallback.invoke(player)
            } else {
                holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_outline)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.player_item_view, parent, false)
        return PlayerViewHolder(view)
    }

    fun loadPlayerImage() {

    }

    fun isPlayerFavorite(playerId: Int): Boolean {
        var exists = false
        favouritePlayers?.forEach {
            if (it.id.equals(playerId)) exists = true
        }
        return exists
    }
}