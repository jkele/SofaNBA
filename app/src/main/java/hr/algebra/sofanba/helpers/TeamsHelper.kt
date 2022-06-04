package hr.algebra.sofanba.helpers

import android.content.Context
import android.widget.ImageView
import androidx.cardview.widget.CardView
import coil.load
import com.google.android.gms.maps.model.LatLng
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

fun getTeamAbbr(teamId: Int): String {
    when(teamId) {
        1 -> return "ATL"
        2 -> return "BOS"
        3 -> return "BKN"
        4 -> return "CHA"
        5 -> return "CHI"
        6 -> return "CLE"
        7 -> return "DAL"
        8 -> return "DEN"
        9 -> return "DET"
        10 -> return "GSW"
        11 -> return "HOU"
        12 -> return "IND"
        13 -> return "LAC"
        14 -> return "LAL"
        15 -> return "MEM"
        16 -> return "MIA"
        17 -> return "MIL"
        18 -> return "MIN"
        19 -> return "NOP"
        20 -> return "NYK"
        21 -> return "OKC"
        22 -> return "ORL"
        23 -> return "PHI"
        24 -> return "PHX"
        25 -> return "POR"
        26 -> return "SAC"
        27 -> return "SAS"
        28 -> return "TOR"
        29 -> return "UTA"
        30 -> return "WAS"
    }
    return "ATL"
}

fun loadTeamMatchImage(team_abbr: String, imageView: ImageView){
    when(team_abbr){
        "ATL" -> imageView.load(R.drawable.ic_hawks)
        "BOS" -> imageView.load(R.drawable.ic_celtics)
        "BKN" -> imageView.load(R.drawable.ic_nets)
        "CHA" -> imageView.load(R.drawable.ic_hornets)
        "CHI" -> imageView.load(R.drawable.ic_bulls)
        "CLE" -> imageView.load(R.drawable.ic_cavaliers)
        "DAL" -> imageView.load(R.drawable.ic_mavericks)
        "DEN" -> imageView.load(R.drawable.ic_nuggets)
        "DET" -> imageView.load(R.drawable.ic_pistons)
        "GSW" -> imageView.load(R.drawable.ic_warriors)
        "HOU" -> imageView.load(R.drawable.ic_rockets)
        "IND" -> imageView.load(R.drawable.ic_pacers)
        "LAC" -> imageView.load(R.drawable.ic_clippers)
        "LAL" -> imageView.load(R.drawable.ic_lakers)
        "MEM" -> imageView.load(R.drawable.ic_grizzlies)
        "MIA" -> imageView.load(R.drawable.ic_heat)
        "MIL" -> imageView.load(R.drawable.ic_bucks)
        "MIN" -> imageView.load(R.drawable.ic_timberwolves)
        "NOP" -> imageView.load(R.drawable.ic_pelicans)
        "NYK" -> imageView.load(R.drawable.ic_knicks)
        "OKC" -> imageView.load(R.drawable.ic_thunder)
        "ORL" -> imageView.load(R.drawable.ic_magic)
        "PHI" -> imageView.load(R.drawable.ic_76ers)
        "PHX" -> imageView.load(R.drawable.ic_suns)
        "POR" -> imageView.load(R.drawable.ic_trailblazers)
        "SAC" -> imageView.load(R.drawable.ic_kings)
        "SAS" -> imageView.load(R.drawable.ic_spurs)
        "TOR" -> imageView.load(R.drawable.ic_raptors)
        "UTA" -> imageView.load(R.drawable.ic_jazz)
        "WAS" -> imageView.load(R.drawable.ic_wizards)
    }
}

fun getStadiumLocation(team_abbr: String): LatLng {
    when(team_abbr){
        "ATL" -> return LatLng(33.757222, -84.396389)
        "BOS" -> return LatLng(42.366303, -71.062228)
        "BKN" -> return LatLng(40.68265, -73.974689)
        "CHA" -> return LatLng(35.225, -80.839167)
        "CHI" -> return LatLng(41.880556, -87.674167)
        "CLE" -> return LatLng(41.496389, -81.688056)
        "DAL" -> return LatLng(32.790556, -96.810278)
        "DEN" -> return LatLng(39.748611, -105.0075)
        "DET" -> return LatLng(42.696944, -83.245556)
        "GSW" -> return LatLng(37.768056, -122.3875)
        "HOU" -> return LatLng(29.750833, -95.362222)
        "IND" -> return LatLng(39.763889, -86.155556)
        "LAC" -> return LatLng(34.043056, -118.267222)
        "LAL" -> return LatLng(34.043056, -118.267222)
        "MEM" -> return LatLng(35.138333, -90.050556)
        "MIA" -> return LatLng(25.781389, -80.188056)
        "MIL" -> return LatLng(43.043611, -87.916944)
        "MIN" -> return LatLng(44.979444, -93.276111)
        "NOP" -> return LatLng(29.948889, -90.081944)
        "NYK" -> return LatLng(40.750556, -73.993611)
        "OKC" -> return LatLng(35.463333, -97.515)
        "ORL" -> return LatLng(28.539167, -81.383611)
        "PHI" -> return LatLng(39.901111, -75.171944)
        "PHX" -> return LatLng(33.445833, -112.071389)
        "POR" -> return LatLng(45.531667, -122.666667)
        "SAC" -> return LatLng(38.649167, -121.518056)
        "SAS" -> return LatLng(29.426944, -98.4375)
        "TOR" -> return LatLng(43.643333, -79.379167)
        "UTA" -> return LatLng(40.768333, -111.901111)
        "WAS" -> return LatLng(38.898056, -77.020833)
    }
    return LatLng(33.757222, -84.396389)
}

fun getTeamColor(team_abbr: String): Int {
    when(team_abbr){
        "ATL" -> return R.color.team_hawks_primary
        "BOS" -> return R.color.team_celtics_primary
        "BKN" -> return R.color.team_nets_primary
        "CHA" -> return R.color.team_hornets_primary
        "CHI" -> return R.color.team_bulls_primary
        "CLE" -> return R.color.team_cavaliers_primary
        "DAL" -> return R.color.team_mavericks_primary
        "DEN" -> return R.color.team_nuggets_primary
        "DET" -> return R.color.team_pistons_primary
        "GSW" -> return R.color.team_warriors_primary
        "HOU" -> return R.color.team_rockets_primary
        "IND" -> return R.color.team_pacers_primary
        "LAC" -> return R.color.team_clippers_primary
        "LAL" -> return R.color.team_lakers_primary
        "MEM" -> return R.color.team_grizzlies_primary
        "MIA" -> return R.color.team_heat_primary
        "MIL" -> return R.color.team_bucks_primary
        "MIN" -> return R.color.team_timberwolves_primary
        "NOP" -> return R.color.team_pelicans_primary
        "NYK" -> return R.color.team_knicks_primary
        "OKC" -> return R.color.team_thunder_primary
        "ORL" -> return R.color.team_magic_primary
        "PHI" -> return R.color.team_76_ers_primary
        "PHX" -> return R.color.team_suns_primary
        "POR" -> return R.color.team_blazers_primary
        "SAC" -> return R.color.team_kings_primary
        "SAS" -> return R.color.team_spurs_primary
        "TOR" -> return R.color.team_raptors_primary
        "UTA" -> return R.color.team_jazz_primary
        "WAS" -> return R.color.team_wizards_primary
    }
    return R.color.color_primary
}

fun getTeamProgressColor(team_abbr: String): Int {
    when(team_abbr){
        "ATL" -> return R.color.team_hawks_progress
        "BOS" -> return R.color.team_celtics_progress
        "BKN" -> return R.color.team_nets_progress
        "CHA" -> return R.color.team_hornets_progress
        "CHI" -> return R.color.team_bulls_progress
        "CLE" -> return R.color.team_cavaliers_progress
        "DAL" -> return R.color.team_mavericks_progress
        "DEN" -> return R.color.team_nuggets_progress
        "DET" -> return R.color.team_pistons_progress
        "GSW" -> return R.color.team_warriors_progress
        "HOU" -> return R.color.team_rockets_progress
        "IND" -> return R.color.team_pacers_progress
        "LAC" -> return R.color.team_clippers_progress
        "LAL" -> return R.color.team_lakers_progress
        "MEM" -> return R.color.team_grizzlies_progress
        "MIA" -> return R.color.team_heat_progress
        "MIL" -> return R.color.team_bucks_progress
        "MIN" -> return R.color.team_timberwolves_progress
        "NOP" -> return R.color.team_pelicans_progress
        "NYK" -> return R.color.team_knicks_progress
        "OKC" -> return R.color.team_thunder_progress
        "ORL" -> return R.color.team_magic_progress
        "PHI" -> return R.color.team_76_ers_progress
        "PHX" -> return R.color.team_suns_progress
        "POR" -> return R.color.team_blazers_progress
        "SAC" -> return R.color.team_kings_progress
        "SAS" -> return R.color.team_spurs_progress
        "TOR" -> return R.color.team_raptors_progress
        "UTA" -> return R.color.team_jazz_progress
        "WAS" -> return R.color.team_wizards_progress
    }
    return R.color.color_primary
}

fun loadPlayerImagePlaceholder(position: Int, imageView: ImageView) {
    imageView.load(getPlayerImagePlaceholder(position))
}

fun getPlayerImagePlaceholder(position: Int): Int {
    val uiPosition = position + 1

    if (uiPosition % 2 == 0 && uiPosition % 3 != 0 && position % 3 != 0) {
        return R.drawable.ic_player_two
    } else if(uiPosition % 3 == 0) {
        return R.drawable.ic_player_three
    } else if (position % 3 == 0) {
        return R.drawable.ic_player_one
    } else {
        return R.drawable.ic_player_two
    }
}

private fun loadImage(context: Context, imageView: ImageView, imageContainer: CardView, color: Int, image: Int){
    imageContainer.setCardBackgroundColor(context.resources.getColor(color))
    imageView.load(image)
}



