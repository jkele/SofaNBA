package hr.algebra.sofanba.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerItemViewBinding
import hr.algebra.sofanba.network.model.Player

class FavoritePlayerRecyclerAdapter(
    private val context: Context,
    private val favoritesList: ArrayList<Player>
): RecyclerView.Adapter<FavoritePlayerRecyclerAdapter.FavoritePlayerViewHolder>() {

    class FavoritePlayerViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = PlayerItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePlayerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.player_item_view, parent, false)
        return FavoritePlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritePlayerViewHolder, position: Int) {
        val player = favoritesList[position]

        holder.binding.tvPlayerName.text = player.firstName + " " + player.lastName

    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

}