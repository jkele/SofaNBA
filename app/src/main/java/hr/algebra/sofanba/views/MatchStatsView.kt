package hr.algebra.sofanba.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.MatchStatsViewBinding

class MatchStatsView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding = MatchStatsViewBinding.inflate(LayoutInflater.from(context), this, true)

    @SuppressLint("SetTextI18n")
    fun setupMatchStatsView(
        title: String,
        homeStatValue: Double,
        awayStatValue: Double,
        homeTeamColor: Int,
        awayTeamColor: Int,
        statIsPerc: Boolean
    ) {
        binding.tvStatTitle.text = title
        if (statIsPerc) {
            val homeStatPerc = homeStatValue * 100
            val awayStatPerc = awayStatValue * 100

            binding.tvHomeStatValue.text = homeStatPerc.toInt().toString()
            setupStatProgressBar(
                binding.homeStatProgressBar,
                homeStatPerc,
                homeTeamColor,
                true
            )

            binding.tvAwayStatValue.text = awayStatPerc.toInt().toString()
            setupStatProgressBar(
                binding.awayStatProgressBar,
                awayStatPerc,
                awayTeamColor,
                true
            )
        } else {
            val homeValue = homeStatValue / (homeStatValue + awayStatValue)
            val awayValue = awayStatValue / (homeStatValue + awayStatValue)

            binding.tvHomeStatValue.text = homeStatValue.toInt().toString()
            setupStatProgressBar(
                binding.homeStatProgressBar,
                homeValue,
                homeTeamColor,
                false
            )
            binding.tvAwayStatValue.text = awayStatValue.toInt().toString()
            setupStatProgressBar(
                binding.awayStatProgressBar,
                awayValue,
                awayTeamColor,
                false
            )
        }
    }

    private fun setupStatProgressBar(
        progressBar: ProgressBar,
        value: Double,
        progressBarColor: Int,
        statIsPerc: Boolean
    ) {
        progressBar.progressTintList =
            ColorStateList.valueOf(resources.getColor(progressBarColor))

        if (statIsPerc) {
            val animation = ProgressBarAnimation(progressBar, 0.0, value)
            animation.duration = 1000
            progressBar.startAnimation(animation)
        } else {
            val animation = ProgressBarAnimation(progressBar, 0.0, value * 100)
            animation.duration = 1000
            progressBar.startAnimation(animation)
        }
    }

    class ProgressBarAnimation(
        val progressBar: ProgressBar,
        val from: Double,
        val to: Double
    ) : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            val value = from + (to - from) * interpolatedTime
            progressBar.progress = value.toInt()
        }
    }
}