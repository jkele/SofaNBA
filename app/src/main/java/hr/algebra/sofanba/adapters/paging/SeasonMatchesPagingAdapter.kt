package hr.algebra.sofanba.adapters.paging

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.MatchActivity
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.SeasonMatchItemViewBinding
import hr.algebra.sofanba.helpers.loadTeamImage
import hr.algebra.sofanba.network.model.Match
import java.text.SimpleDateFormat
import java.util.*

const val EXTRA_MATCH = "hr.algebra.sofanba.extraMatch"

class SeasonMatchesPagingAdapter(
    private val context: Context,
    diffCallback: DiffUtil.ItemCallback<Match>
) : PagingDataAdapter<Match, SeasonMatchesPagingAdapter.SeasonMatchViewHolder>(diffCallback) {

    class SeasonMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SeasonMatchItemViewBinding.bind(itemView)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: SeasonMatchViewHolder, position: Int) {
        val match = getItem(position)
        val matchDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(match!!.date)

        setTitle(position, matchDate!!, holder)

        holder.binding.tvMatchDate.text = SimpleDateFormat("dd").format(matchDate).toString() +
                " " +SimpleDateFormat("MMM").format(matchDate).uppercase() + " " +
                SimpleDateFormat("yyyy"). format(matchDate)

        holder.binding.tvMatchStatus.text = match.status

        loadTeamImage(
            context,
            match.homeTeam.abbreviation,
            holder.binding.ivHomeTeamImage,
            holder.binding.homeImageContainer
        )

        loadTeamImage(
            context,
            match.visitorTeam.abbreviation,
            holder.binding.ivAwayTeamImage,
            holder.binding.awayImageContainer
        )

        holder.binding.tvHomeTeamPoints.text = match.homeTeamScore.toString()
        holder.binding.tvAwayTeamPoints.text = match.visitorTeamScore.toString()
        holder.binding.tvHomeTeamAbbreviation.text = match.homeTeam.abbreviation
        holder.binding.tvAwayTeamAbbreviation.text = match.visitorTeam.abbreviation

        if (match.homeTeamScore > match.visitorTeamScore) {
            holder.binding.tvHomeTeamPoints.isActivated = true
            holder.binding.tvAwayTeamPoints.isActivated = false
        } else {
            holder.binding.tvHomeTeamPoints.isActivated = false
            holder.binding.tvAwayTeamPoints.isActivated = true
        }

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, MatchActivity::class.java).apply {
                putExtra(EXTRA_MATCH, match)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonMatchViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.season_match_item_view, parent, false)
        return SeasonMatchViewHolder(view)
    }

    private fun setViewMargin(holder: SeasonMatchViewHolder, value: Float) {
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        )
        val param = holder.binding.matchItem.layoutParams as ConstraintLayout.LayoutParams
        param.setMargins(0, px.toInt(), 0, 0)
        holder.binding.matchItem.layoutParams = param
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun setTitleText(holder: SeasonMatchViewHolder, matchDate: Date) {
        holder.binding.tvMonthYear.visibility = TextView.VISIBLE
        holder.binding.tvMonthYear.text =
            SimpleDateFormat("MMMM").format(matchDate) + " " +
                    SimpleDateFormat("yyyy").format(matchDate)

        setViewMargin(holder, 18f)
    }

    @SuppressLint("SimpleDateFormat")
    private fun setTitle(position: Int, matchDate: Date, holder: SeasonMatchViewHolder) {
        if (position - 1 == -1) {
            setTitleText(holder, matchDate)
        } else {
            val previousMatch = getItem(position - 1)
            val previousMatchDate =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(previousMatch!!.date)

            if (matchDate.month != previousMatchDate!!.month) {
                setTitleText(holder, matchDate)
            } else {
                holder.binding.tvMonthYear.visibility = TextView.GONE
                setViewMargin(holder, 0f)
            }
        }
    }

}