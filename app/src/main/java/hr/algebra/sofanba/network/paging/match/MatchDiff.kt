package hr.algebra.sofanba.network.paging.match

import androidx.recyclerview.widget.DiffUtil
import hr.algebra.sofanba.network.model.Match

object MatchDiff: DiffUtil.ItemCallback<Match>() {
    override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem == newItem
    }
}