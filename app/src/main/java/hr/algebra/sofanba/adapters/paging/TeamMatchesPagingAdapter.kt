package hr.algebra.sofanba.adapters.paging

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.MatchActivity
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.TeamMatchItemViewBinding
import hr.algebra.sofanba.helpers.loadTeamMatchImage
import hr.algebra.sofanba.network.model.Match
import hr.algebra.sofanba.network.model.Team
import java.text.SimpleDateFormat

class TeamMatchesPagingAdapter(
    private val context: Context,
    private val selectedTeam: Team,
    diffCallback: DiffUtil.ItemCallback<Match>
) : PagingDataAdapter<Match, TeamMatchesPagingAdapter.TeamMatchViewHolder>(diffCallback) {

    class TeamMatchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = TeamMatchItemViewBinding.bind(itemView)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: TeamMatchViewHolder, position: Int) {
        val match = getItem(position)

        holder.binding.tvHomeTeamPoints.text = match!!.homeTeamScore.toString()
        holder.binding.tvAwayTeamPoints.text = match.visitorTeamScore.toString()

        val matchDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(match.date)

        holder.binding.tvMatchDay.text = SimpleDateFormat("EEEE").format(matchDate!!).uppercase()
        holder.binding.tvMatchDate.text =
            matchDate.date.toString() + " " + SimpleDateFormat("MMM").format(matchDate).uppercase()


        if (match.homeTeamScore < match.visitorTeamScore) holder.binding.tvWinLose.text = "L"
        if (match.homeTeamScore > match.visitorTeamScore) holder.binding.tvWinLose.text = "W"

        if (selectedTeam.id != match.homeTeam.id) {
            //selected team is visitor
            if(match.visitorTeamScore > match.homeTeamScore){
                holder.binding.tvWinLose.text = "W"
                holder.binding.tvWinLose.setTextColor(context.resources.getColor(R.color.status_success))
            } else {
                holder.binding.tvWinLose.text = "L"
                holder.binding.tvWinLose.setTextColor(context.resources.getColor(R.color.status_error))
            }
            holder.binding.at.text = "@"
            holder.binding.tvTeamAbbr.text = match.homeTeam.abbreviation
            loadTeamMatchImage(match.homeTeam.abbreviation, holder.binding.ivOpponentTeamImage)
        } else {
            if(match.homeTeamScore > match.visitorTeamScore){
                holder.binding.tvWinLose.text = "W"
                holder.binding.tvWinLose.setTextColor(context.resources.getColor(R.color.status_success))
            } else {
                holder.binding.tvWinLose.text = "L"
                holder.binding.tvWinLose.setTextColor(context.resources.getColor(R.color.status_error))
            }
            holder.binding.at.text = context.getString(R.string.versus)
            holder.binding.tvTeamAbbr.text = match.visitorTeam.abbreviation
            loadTeamMatchImage(match.visitorTeam.abbreviation, holder.binding.ivOpponentTeamImage)
        }

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, MatchActivity::class.java).apply {
                putExtra(EXTRA_MATCH, match)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMatchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.team_match_item_view, parent, false)
        return TeamMatchViewHolder(view)
    }

}