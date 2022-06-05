package hr.algebra.sofanba.fragments.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import hr.algebra.sofanba.databinding.BottomsheetAddPhotoBinding
import hr.algebra.sofanba.helpers.customValidate
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
    private var validationFields = ArrayList<TextInputEditText>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomsheetAddPhotoBinding.inflate(inflater, container, false)

        setupValidationFields()

        binding.btnAdd.setOnClickListener {
            if (formValid()) {
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

                clearForm()
            }
        }

        binding.btnCancel.setOnClickListener {
            this.dismiss()
        }

        return binding.root
    }

    private fun formValid(): Boolean {
        validationFields.forEach {
            if (!it.customValidate(requireContext())) {
                return false
            }
        }
        return true
    }

    private fun clearForm() {
        validationFields.forEach {
            it.text!!.clear()
        }
    }

    private fun setupValidationFields() {
        validationFields.add(binding.etUrl)
        validationFields.add(binding.etTitle)
    }

}