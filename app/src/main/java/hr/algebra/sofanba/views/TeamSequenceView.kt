package hr.algebra.sofanba.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.adapters.TeamSequenceRecyclerAdapter
import hr.algebra.sofanba.databinding.TeamSequenceViewBinding
import hr.algebra.sofanba.network.model.Team

class TeamSequenceView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var binding: TeamSequenceViewBinding = TeamSequenceViewBinding.inflate(
        LayoutInflater.from(context),
        this, true
    )

    fun setupTeamSequenceView(
        context: Context, teamsList: ArrayList<Team>, isForFavorites: Boolean,
        insertCallback: ((Team) -> Unit)?
    ) {
        binding.rvSequence.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        val adapter =
            TeamSequenceRecyclerAdapter(context, teamsList, isForFavorites, insertCallback)
        binding.rvSequence.adapter = adapter
    }
}