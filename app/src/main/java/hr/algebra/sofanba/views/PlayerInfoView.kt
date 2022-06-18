package hr.algebra.sofanba.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerInfoViewBinding
import hr.algebra.sofanba.fragments.SHARED_PREF_NAME
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.helpers.showHeightText
import hr.algebra.sofanba.helpers.showWeightText
import hr.algebra.sofanba.network.model.Player

class PlayerInfoView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    private var binding = PlayerInfoViewBinding.inflate(LayoutInflater.from(context), this, true)

    @SuppressLint("SetTextI18n")
    fun setupPlayerInfoView(player: Player, unitPref: String?) {
        binding.tvPlayerTeam.text = player.team.fullName
        loadTeamImage(context, player.team.abbreviation, binding.ivTeamImage, binding.imageContainer)

        when(player.position){
            "G" -> binding.tvPlayerPosition.text = context.getString(R.string.guard)
            "F" -> binding.tvPlayerPosition.text = context.getString(R.string.forward)
            "C" -> binding.tvPlayerPosition.text = context.getString(R.string.center)
        }

        if(player.height_feet == null && player.weightPounds == null) {
            binding.tvPlayerHeight.text = context.getString(R.string.undefined)
            binding.tvPlayerWeight.text = context.getString(R.string.undefined)
        } else {
            showHeightText(player.height_feet!!, player.height_inches!!, binding.tvPlayerHeight, unitPref!!)
            showWeightText(player.weightPounds!!, binding.tvPlayerWeight, unitPref!!)
        }
    }
}