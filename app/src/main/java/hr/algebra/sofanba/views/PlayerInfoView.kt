package hr.algebra.sofanba.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerInfoViewBinding
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.Player

class PlayerInfoView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    private var binding = PlayerInfoViewBinding.inflate(LayoutInflater.from(context), this, true)

    @SuppressLint("SetTextI18n")
    fun setupPlayerInfoView(player: Player) {
        binding.tvPlayerTeam.text = player.team.fullName
        loadTeamImage(context, player.team.abbreviation, binding.ivTeamImage, binding.imageContainer)
        when(player.position){
            "G" -> binding.tvPlayerPosition.text = context.getString(R.string.guard)
            "F" -> binding.tvPlayerPosition.text = context.getString(R.string.forward)
            "C" -> binding.tvPlayerPosition.text = context.getString(R.string.center)
        }
        binding.tvPlayerHeight.text = player.height_feet.toString() + "'" + player.height_inches + "\""
        binding.tvPlayerWeight.text = player.weightPounds.toString() + "lbs"
    }
}