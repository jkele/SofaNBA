package hr.algebra.sofanba.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.TeamActivity
import hr.algebra.sofanba.databinding.TeamItemViewBinding
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.Team

const val EXTRA_TEAM = "hr.algebra.sofanba.extraTeam"

class TeamRecyclerAdapter(
    private val context: Context,
    private val teamsList: ArrayList<Team>,
    private val favoriteTeams: ArrayList<Team>?,
    private val usedForFavorites: Boolean,
    private val insertCallback: ((Team) -> Unit)?,
    private val deleteCallback: ((Team) -> Unit)?
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
        loadTeamImage(context, team.abbreviation, holder.binding.ivTeamImage, holder.binding.imageContainer)

        val exists = isTeamFavorite(team.id)

        if (usedForFavorites){
            holder.binding.btnFavorite.isActivated = true
            holder.binding.btnFavorite.setOnClickListener {
                onFavoriteTeamButtonClick(team)
            }
        } else {
            holder.binding.btnFavorite.isActivated = exists
            holder.binding.btnFavorite.setOnClickListener {
                onRegularTeamButtonClick(exists, holder, team)
            }
        }

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, TeamActivity::class.java).apply {
                putExtra(EXTRA_TEAM, team)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

    private fun isTeamFavorite(teamId: Int): Boolean {
        var exists = false
        favoriteTeams?.forEach {
            if (it.id == teamId) exists = true
        }
        return exists
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onFavoriteTeamButtonClick(team: Team) {
        deleteCallback?.invoke(team)
        teamsList.remove(team)
        notifyDataSetChanged()
    }

    private fun onRegularTeamButtonClick(exists: Boolean, holder: TeamViewHolder, team: Team) {
        if (!exists) {
            holder.binding.btnFavorite.isActivated = true
            insertCallback?.invoke(team)
        } else {
            holder.binding.btnFavorite.isActivated = false
            deleteCallback?.invoke(team)
        }
    }
}