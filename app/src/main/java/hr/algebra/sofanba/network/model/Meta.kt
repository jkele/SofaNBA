package hr.algebra.sofanba.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meta(
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("next_page")
    val nextPage: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total_count")
    val totalCount: Int,
): Serializable {
}