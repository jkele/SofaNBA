package hr.algebra.sofanba.helpers

import android.annotation.SuppressLint
import android.widget.TextView

@SuppressLint("SetTextI18n")
fun showWeightText(value: Int, textView: TextView, weightType: String) {
    when(weightType) {
        "Imperial" -> textView.text = value.toString() + "lb"
        "Metric" -> {
            val weightInKg = convertLbsToKg(value)
            textView.text = weightInKg.toInt().toString() + "kg"
        }
    }
}

@SuppressLint("SetTextI18n")
fun showHeightText(valueFeet: Int, valueInches: Int, textView: TextView, weightType: String) {
    when(weightType) {
        "Imperial" -> textView.text = "$valueFeet'$valueInches\""
        "Metric" -> {
            val heightInCm = convertFeetToCm(valueFeet, valueInches)
            textView.text = heightInCm.toInt().toString() + "cm"
        }
    }
}

private fun convertLbsToKg(value: Int): Double {
    return value * 0.453592
}

private fun convertFeetToCm(valueFeet: Int, valueInches: Int): Double {
    val inches = (valueFeet * 12) + valueInches
    return inches * 2.54
}