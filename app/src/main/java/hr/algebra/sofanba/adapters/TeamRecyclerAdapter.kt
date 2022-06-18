package hr.algebra.sofanba.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.TeamActivity
import hr.algebra.sofanba.databinding.TeamItemViewBinding
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.Team

const val EXTRA_TEAM = "hr.algebra.sofanba.extraTeam"

class TeamRecyclerAdapter(
    private val context: Context,
    private var teamsList: ArrayList<Team>,
    private val favoriteTeams: ArrayList<Team>?,
    private val usedForFavorites: Boolean,
    private val insertCallback: ((Team) -> Unit)?,
    private val deleteCallback: ((Team) -> Unit)?
) : RecyclerView.Adapter<TeamRecyclerAdapter.TeamViewHolder>(), Filterable {

    private var filterList = teamsList

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TeamItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.team_item_view, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = filterList[position]

        holder.binding.tvTeamName.text = team.fullName
        loadTeamImage(
            context,
            team.abbreviation,
            holder.binding.ivTeamImage,
            holder.binding.imageContainer
        )

        val exists = isTeamFavorite(team.id)

        if (usedForFavorites) {
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
        return filterList.size
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
        filterList.remove(team)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(text: CharSequence?): FilterResults {
                val filterResults = FilterResults()

                if (text.isNullOrEmpty()) {
                    filterResults.values = ArrayList<Team>(teamsList)
                    filterList = teamsList
                } else {
                    val searchChar = text.toString().lowercase()
                    val searchItems = ArrayList<Team>()
                    for (it in filterList) {
                        if (it.name.lowercase().contains(searchChar) ||
                            it.city.lowercase().contains(searchChar)) {
                            searchItems.add(it)
                        }
                    }

                    filterResults.values = searchItems
                }
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                if (p0.isNullOrEmpty()) filterList = teamsList
                filterList = filterResults!!.values as ArrayList<Team>
                notifyDataSetChanged()
            }

        }
    }
}