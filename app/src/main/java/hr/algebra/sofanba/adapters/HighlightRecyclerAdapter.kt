package hr.algebra.sofanba.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.HighlightItemViewBinding
import hr.algebra.sofanba.network.model.Highlight

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

        holder.binding.tvHighlightTitle.text = highlight.name
    }

    override fun getItemCount(): Int {
        return highlightsList.size
    }

}