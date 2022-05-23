package hr.algebra.sofanba.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.databinding.PlayerMatchStatsItemViewBinding

class PlayerMatchStatsView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    private val binding = PlayerMatchStatsItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun fillPlayerMatchStats(title: String, value: Int) {
        binding.tvTitle.text = title
        binding.tvValue.text = value.toString()
    }

}