package hr.algebra.sofanba.helpers

import android.content.Context
import android.widget.ImageView
import androidx.cardview.widget.CardView
import coil.load
import hr.algebra.sofanba.R

fun loadTeamImage(context: Context, team_abbr: String, imageView: ImageView, imageContainer: CardView){
    when(team_abbr){
        "ATL" -> loadImage(context, imageView, imageContainer, R.color.team_hawks_primary, R.drawable.ic_hawks)
        "BOS" -> loadImage(context, imageView, imageContainer, R.color.team_celtics_primary, R.drawable.ic_celtics)
        "BKN" -> loadImage(context, imageView, imageContainer, R.color.team_nets_primary, R.drawable.ic_nets)
        "CHA" -> loadImage(context, imageView, imageContainer, R.color.team_hornets_primary, R.drawable.ic_hornets)
        "CHI" -> loadImage(context, imageView, imageContainer, R.color.team_bulls_primary, R.drawable.ic_bulls)
        "CLE" -> loadImage(context, imageView, imageContainer, R.color.team_cavaliers_primary, R.drawable.ic_cavaliers)
        "DAL" -> loadImage(context, imageView, imageContainer, R.color.team_mavericks_primary, R.drawable.ic_mavericks)
        "DEN" -> loadImage(context, imageView, imageContainer, R.color.team_nuggets_primary, R.drawable.ic_nuggets)
        "DET" -> loadImage(context, imageView, imageContainer, R.color.team_pistons_primary, R.drawable.ic_pistons)
        "GSW" -> loadImage(context, imageView, imageContainer, R.color.team_warriors_primary, R.drawable.ic_warriors)
        "HOU" -> loadImage(context, imageView, imageContainer, R.color.team_rockets_primary, R.drawable.ic_rockets)
        "IND" -> loadImage(context, imageView, imageContainer, R.color.team_pacers_primary, R.drawable.ic_pacers)
        "LAC" -> loadImage(context, imageView, imageContainer, R.color.team_clippers_primary, R.drawable.ic_clippers)
        "LAL" -> loadImage(context, imageView, imageContainer, R.color.team_lakers_primary, R.drawable.ic_lakers)
        "MEM" -> loadImage(context, imageView, imageContainer, R.color.team_grizzlies_primary, R.drawable.ic_grizzlies)
        "MIA" -> loadImage(context, imageView, imageContainer, R.color.team_heat_primary, R.drawable.ic_heat)
        "MIL" -> loadImage(context, imageView, imageContainer, R.color.team_bucks_primary, R.drawable.ic_bucks)
        "MIN" -> loadImage(context, imageView, imageContainer, R.color.team_timberwolves_primary, R.drawable.ic_timberwolves)
        "NOP" -> loadImage(context, imageView, imageContainer, R.color.team_pelicans_primary, R.drawable.ic_pelicans)
        "NYK" -> loadImage(context, imageView, imageContainer, R.color.team_knicks_primary, R.drawable.ic_knicks)
        "OKC" -> loadImage(context, imageView, imageContainer, R.color.team_thunder_primary, R.drawable.ic_thunder)
        "ORL" -> loadImage(context, imageView, imageContainer, R.color.team_magic_primary, R.drawable.ic_magic)
        "PHI" -> loadImage(context, imageView, imageContainer, R.color.team_76_ers_primary, R.drawable.ic_76ers)
        "PHX" -> loadImage(context, imageView, imageContainer, R.color.team_suns_primary, R.drawable.ic_suns)
        "POR" -> loadImage(context, imageView, imageContainer, R.color.team_blazers_primary, R.drawable.ic_trailblazers)
        "SAC" -> loadImage(context, imageView, imageContainer, R.color.team_kings_primary, R.drawable.ic_kings)
        "SAS" -> loadImage(context, imageView, imageContainer, R.color.team_spurs_primary, R.drawable.ic_spurs)
        "TOR" -> loadImage(context, imageView, imageContainer, R.color.team_raptors_primary, R.drawable.ic_raptors)
        "UTA" -> loadImage(context, imageView, imageContainer, R.color.team_jazz_primary, R.drawable.ic_jazz)
        "WAS" -> loadImage(context, imageView, imageContainer, R.color.team_wizards_primary, R.drawable.ic_wizards)
    }
}

private fun loadImage(context: Context, imageView: ImageView, imageContainer: CardView, color: Int, image: Int){
    imageContainer.setCardBackgroundColor(context.resources.getColor(color))
    imageView.load(image)
}