package hr.algebra.sofanba.network.paging.playerMatch

import androidx.recyclerview.widget.DiffUtil
import hr.algebra.sofanba.network.model.GameStats

object PlayerMatchDiff: DiffUtil.ItemCallback<GameStats>() {
    override fun areItemsTheSame(oldItem: GameStats, newItem: GameStats): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GameStats, newItem: GameStats): Boolean {
        return oldItem == newItem
    }
}