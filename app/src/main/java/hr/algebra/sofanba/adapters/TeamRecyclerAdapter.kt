package hr.algebra.sofanba.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.TeamItemViewBinding
import hr.algebra.sofanba.network.model.Team

class TeamRecyclerAdapter(
    private val context: Context,
    private val teamsList: ArrayList<Team>
): RecyclerView.Adapter<TeamRecyclerAdapter.TeamViewHolder>() {

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = TeamItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.team_item_view, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teamsList[position]

        holder.binding.tvTeamName.text = team.fullName

    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

}