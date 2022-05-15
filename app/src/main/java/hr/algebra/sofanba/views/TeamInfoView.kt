package hr.algebra.sofanba.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.databinding.TeamInfoViewBinding
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.Team

class TeamInfoView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    private var binding = TeamInfoViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun setupTeamInfoView(context: Context, selectedTeam: Team){
        binding.tvTeamAbbr.text = selectedTeam.abbreviation
        binding.tvTeamName.text = selectedTeam.fullName
        loadTeamImage(context, selectedTeam.abbreviation, binding.ivTeamImage, binding.imageContainer)
        binding.tvLocation.text = selectedTeam.city
        binding.tvConference.text = selectedTeam.conference
        binding.tvDivision.text = selectedTeam.division
    }

}