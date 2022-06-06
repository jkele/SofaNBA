package hr.algebra.sofanba.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerStatisticsViewBinding

class PlayerStatisticsView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    private val binding = PlayerStatisticsViewBinding.inflate(LayoutInflater.from(context), this, true)

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setupPlayerStatisticsView(title: String, valueOne: Double?, valueTwo: Double?, valueThree: Double?, valueFour: Double?) {
        binding.tvStatTitle.text = title
        binding.tvStatValueOne.text = valueOne?.toString()
        binding.tvStatValueTwo.text = valueTwo?.toString()
        binding.tvStatValueThree.text = valueThree?.toString()
        binding.tvStatValueFour.text = valueFour?.toString()

        val valuesList: DoubleArray

        if (valueOne != null && valueTwo != null && valueThree != null && valueFour != null) {
            valuesList = doubleArrayOf(valueOne, valueTwo, valueThree, valueFour)
        } else if (valueOne != null && valueTwo != null && valueThree != null) {
            valuesList = doubleArrayOf(valueOne, valueTwo, valueThree)
        } else if (valueOne != null && valueTwo != null) {
            valuesList = doubleArrayOf(valueOne, valueTwo)
        } else {
            valuesList = doubleArrayOf(valueOne!!)
        }

        val textViewList = ArrayList<TextView>()
        val maxValue = valuesList.maxOrNull()

        textViewList.add(binding.tvStatValueOne)
        textViewList.add(binding.tvStatValueTwo)
        textViewList.add(binding.tvStatValueThree)
        textViewList.add(binding.tvStatValueFour)

        for (i in valuesList.indices) {
            textViewList[i].text = valuesList[i].toString()
            if (valuesList[i] == maxValue) {
                textViewList[i].background = context.getDrawable(R.drawable.background_stat_item)
                textViewList[i].setTextColor(context.getColor(R.color.color_primary))
            }
        }

    }

}