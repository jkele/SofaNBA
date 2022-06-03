package hr.algebra.sofanba.adapters

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.squareup.picasso.Picasso
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.PlayerPhotoItemViewBinding
import hr.algebra.sofanba.network.model.Highlight
import hr.algebra.sofanba.network.model.PlayerImage
import java.lang.Exception

class PlayerPhotoRecyclerAdapter(
    private val context: Context,
    private val imageList: ArrayList<PlayerImage>
): RecyclerView.Adapter<PlayerPhotoRecyclerAdapter.PlayerPhotoViewHolder>() {

    var editSwitch = false
    var deleteCallback: ((PlayerImage) -> Unit)? = null
    var updateSliderCallback: ((ArrayList<PlayerImage>) -> Unit)? = null
    var showErrorMessageCallback: ((String) -> Unit)? = null

    fun setImageList(newImageList: ArrayList<PlayerImage>) {
        imageList.clear()
        imageList.addAll(newImageList)
        notifyDataSetChanged()
    }

    fun clearImageList() {
        imageList.clear()
        notifyDataSetChanged()
    }

    class PlayerPhotoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = PlayerPhotoItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerPhotoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.player_photo_item_view, parent, false)
        return PlayerPhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerPhotoViewHolder, position: Int) {
        val playerImage = imageList[position]

        holder.binding.tvImageTitle.text = playerImage.imageCaption
        Picasso.get().load(playerImage.imageUrl).into(holder.binding.ivPlayerImage)

        if (editSwitch) {
            setViewMargin(holder, 64f)
            holder.binding.editContainer.visibility = View.VISIBLE
            holder.binding.btnDelete.setOnClickListener {
                try {
                    deleteCallback?.invoke(playerImage)
                    imageList.remove(playerImage)
                    updateSliderCallback?.invoke(imageList)
                    notifyDataSetChanged()
                } catch (e: Exception) {
                    showErrorMessageCallback?.invoke(context.getString(R.string.error_deleting_image))
                }
            }
        } else {
            setViewMargin(holder, 2f)
            holder.binding.editContainer.visibility = View.GONE
        }
    }

    private fun setViewMargin(holder: PlayerPhotoRecyclerAdapter.PlayerPhotoViewHolder, value: Float) {
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        )
        val param = holder.binding.photoItemContainer.layoutParams as ConstraintLayout.LayoutParams
        param.setMargins(0, 0, px.toInt(), 0)
        holder.binding.photoItemContainer.layoutParams = param
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}