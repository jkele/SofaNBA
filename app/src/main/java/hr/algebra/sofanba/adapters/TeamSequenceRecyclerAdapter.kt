package hr.algebra.sofanba.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.TeamActivity
import hr.algebra.sofanba.databinding.TeamSimpleItemViewBinding
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.Team

class TeamSequenceRecyclerAdapter(
    private val context: Context,
    private val teamsList: ArrayList<Team>,
    private val isForFavorites: Boolean
): RecyclerView.Adapter<TeamSequenceRecyclerAdapter.TeamSequenceViewHolder>() {

    class TeamSequenceViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = TeamSimpleItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamSequenceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.team_simple_item_view, parent, false)
        return TeamSequenceViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamSequenceViewHolder, position: Int) {
        val team = teamsList[position]
        if (isForFavorites){
            holder.binding.tvTeamName.text = team.name
            loadTeamImage(context, team.abbreviation, holder.binding.ivTeamImage, holder.binding.imageContainer)
        } else {
            holder.binding.tvTeamName.text = team.abbreviation
            loadTeamImage(context, team.abbreviation, holder.binding.ivTeamImage, holder.binding.imageContainer)

            holder.binding.root.setOnClickListener {
                val intent = Intent(context, TeamActivity::class.java).apply {
                    putExtra(EXTRA_TEAM, team)
                }
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

}