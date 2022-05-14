package hr.algebra.sofanba.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hr.algebra.sofanba.network.model.Team

class PlayerTypeConverter {

    @TypeConverter
    fun fromPlayer(value: Team): String{
        val gson = Gson()
        val type = object : TypeToken<Team>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTeam(value: String): Team {
        val gson = Gson()
        val type = object: TypeToken<Team>() {}.type
        return gson.fromJson(value, type)
    }

}