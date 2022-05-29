package hr.algebra.sofanba.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
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
                true,
                homeStatPerc > awayStatPerc
            )

            binding.tvAwayStatValue.text = awayStatPerc.toInt().toString()
            setupStatProgressBar(
                binding.awayStatProgressBar,
                awayStatPerc,
                true,
                awayStatPerc > homeStatPerc
            )
        } else {
            val homeValue = homeStatValue / (homeStatValue + awayStatValue)
            val awayValue = awayStatValue / (homeStatValue + awayStatValue)

            binding.tvHomeStatValue.text = homeStatValue.toInt().toString()
            setupStatProgressBar(
                binding.homeStatProgressBar,
                homeValue,
                false,
                homeValue > awayValue
            )
            binding.tvAwayStatValue.text = awayStatValue.toInt().toString()
            setupStatProgressBar(
                binding.awayStatProgressBar,
                awayValue,
                false,
                awayValue > homeValue
            )
        }
    }

    private fun setupStatProgressBar(
        progressBar: ProgressBar,
        value: Double,
        statIsPerc: Boolean,
        statIsHigher: Boolean
    ) {
        if (statIsHigher) {
            progressBar.progressTintList =
                ColorStateList.valueOf(resources.getColor(R.color.progress_bar_green))
        } else {
            progressBar.progressTintList =
                ColorStateList.valueOf(resources.getColor(R.color.progress_bar_red))
        }

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