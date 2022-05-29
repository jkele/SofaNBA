package hr.algebra.sofanba.adapters

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.squareup.picasso.Picasso
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.HighlightItemViewBinding
import hr.algebra.sofanba.network.model.Highlight

const val VIDEO_THUMBNAIL_URL = "http://img.youtube.com/vi/"
const val VIDEO_THUMBNAIL_QUALITY = "/mqdefault.jpg"

class HighlightRecyclerAdapter(
    private val context: Context,
    private val highlightsList: ArrayList<Highlight>
): RecyclerView.Adapter<HighlightRecyclerAdapter.HighlightViewHolder>() {

    class HighlightViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = HighlightItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.highlight_item_view, parent, false)
        return HighlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        val highlight = highlightsList[position]
        val videoId = highlight.url!!.substringAfter('=')

        Picasso.get().load(VIDEO_THUMBNAIL_URL + videoId + VIDEO_THUMBNAIL_QUALITY).fit().centerCrop()
            .into(holder.binding.ivHighlightThumbnail)
        holder.binding.tvHighlightTitle.text = highlight.name

        holder.binding.root.setOnClickListener {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(highlight.url))
            try {
                context.startActivity(appIntent)
            } catch (e: ActivityNotFoundException) {
                context.startActivity(webIntent)
            }
        }
    }

    override fun getItemCount(): Int {
        return highlightsList.size
    }

}