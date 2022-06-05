package hr.algebra.sofanba.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.sofanba.adapters.MatchPlayerStatsRecyclerAdapter
import hr.algebra.sofanba.databinding.MatchPlayerStatsViewBinding
import hr.algebra.sofanba.network.model.GameStats
import hr.algebra.sofanba.network.model.PlayerImage

class MatchPlayerStatsView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    val binding = MatchPlayerStatsViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun setupPlayerStatsView(
        title: String,
        gameStats: ArrayList<GameStats>,
        imageList: ArrayList<ArrayList<PlayerImage>>,
        stat: String
    ) {
        binding.tvStatTitle.text = title

        binding.rvPlayerStats.layoutManager = LinearLayoutManager(context)
        val adapter = MatchPlayerStatsRecyclerAdapter(context, gameStats, imageList, stat)
        binding.rvPlayerStats.adapter = adapter
    }

}