package hr.algebra.sofanba.adapters

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
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

    var editSwitch = false
    var deleteCallback: ((Highlight) -> Unit)? = null
    var showMessageCallback: ((String) -> Unit)? = null

    class HighlightViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = HighlightItemViewBinding.bind(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<Highlight>) {
        highlightsList.clear()
        highlightsList.addAll(newList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateHighlightsListItems(highlight: Highlight) {
        highlightsList.add(highlight)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.highlight_item_view, parent, false)
        return HighlightViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
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

        holder.binding.editContainer.visibility = CardView.GONE

        if (editSwitch) {
            setViewMargin(holder, 48f)
            holder.binding.editContainer.visibility = CardView.VISIBLE
            holder.binding.btnDelete.setOnClickListener {
                try {
                    deleteCallback!!.invoke(highlight)
                    highlightsList.remove(highlight)
                    notifyDataSetChanged()
                } catch (e: Exception) {
                    showMessageCallback!!.invoke(context.getString(R.string.error_deleting_highlight))
                }
            }
        } else {
            setViewMargin(holder, 2f)
            holder.binding.editContainer.visibility = CardView.GONE
        }
    }

    private fun setViewMargin(holder: HighlightViewHolder, value: Float) {
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        )
        val param = holder.binding.highlightViewContainer.layoutParams as ConstraintLayout.LayoutParams
        param.setMargins(0, 0, px.toInt(), 0)
        holder.binding.highlightViewContainer.layoutParams = param
    }

    override fun getItemCount(): Int {
        return highlightsList.size
    }

}