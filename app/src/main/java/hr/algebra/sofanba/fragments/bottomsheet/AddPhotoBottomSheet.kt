package hr.algebra.sofanba.fragments.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import hr.algebra.sofanba.databinding.BottomsheetAddPhotoBinding
import hr.algebra.sofanba.network.model.Highlight
import hr.algebra.sofanba.network.model.Player
import hr.algebra.sofanba.network.model.PlayerImage
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AddPhotoBottomSheet(
    private val selectedPlayer: Player,
    private val insertCallback: ((RequestBody) -> Unit)?,
    private val updateImageSlider: ((PlayerImage) -> Unit)?,
    private val hideEmptyStateCallback: (() -> Unit)
): BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetAddPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetAddPhotoBinding.inflate(inflater, container, false)

        binding.btnAdd.setOnClickListener {
            val playerImage = PlayerImage(selectedPlayer.id, binding.etUrl.text.toString(),
                binding.etTitle.text.toString(), null)

            val jsonObject = JSONObject()
            jsonObject.put("playerId", playerImage.playerId)
            jsonObject.put("imageUrl", playerImage.imageUrl)
            jsonObject.put("imageCaption", playerImage.imageCaption)

            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            insertCallback?.invoke(requestBody)
            updateImageSlider?.invoke(playerImage)
            hideEmptyStateCallback.invoke()
        }


        return binding.root
    }

}