package hr.algebra.sofanba.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.TeamItemViewBinding
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.Team

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
            holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_filled)
            holder.binding.btnFavorite.setOnClickListener {
                onFavoriteTeamButtonClick(exists, holder, team)
            }
        } else {
            if (exists){
                holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_filled)
            } else {
                holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_outline)
            }
            holder.binding.btnFavorite.setOnClickListener {
                onRegularTeamButtonClick(exists, holder, team)
            }
        }

    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

    fun isTeamFavorite(teamId: Int): Boolean {
        var exists = false
        favoriteTeams?.forEach {
            if (it.id.equals(teamId)) exists = true
        }
        return exists
    }

    fun onFavoriteTeamButtonClick(exists: Boolean, holder: TeamViewHolder, team: Team) {
        holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_outline)
        deleteCallback?.invoke(team)
        teamsList.remove(team)
        notifyDataSetChanged()
    }

    fun onRegularTeamButtonClick(exists: Boolean, holder: TeamViewHolder, team: Team) {
        if (!exists) {
            holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_filled)
            insertCallback?.invoke(team)
        } else {
            holder.binding.btnFavorite.setImageResource(R.drawable.ic_star_outline)
            deleteCallback?.invoke(team)
        }
    }
}