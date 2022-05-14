package hr.algebra.sofanba.network.paging

import androidx.recyclerview.widget.DiffUtil
import hr.algebra.sofanba.network.model.Player

object PlayerDiff: DiffUtil.ItemCallback<Player>() {

    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}