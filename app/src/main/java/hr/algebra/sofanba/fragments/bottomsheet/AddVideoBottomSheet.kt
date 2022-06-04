package hr.algebra.sofanba.fragments.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import hr.algebra.sofanba.databinding.BottomsheetAddVideoBinding
import hr.algebra.sofanba.network.model.Highlight
import hr.algebra.sofanba.network.model.Match
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AddVideoBottomSheet(
    private val selectedMatch: Match,
    private val insertCallback: ((RequestBody) -> Unit)?,
    private val updateRecyclerViewCallback: ((Highlight) -> Unit)?,
    private val hideEmptyStateCallback: (() -> Unit)
) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetAddVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomsheetAddVideoBinding.inflate(inflater, container, false)

        binding.btnAdd.setOnClickListener {
            val highlight = Highlight(
                selectedMatch.id, binding.etTitle.text.toString(),
                binding.etUrl.text.toString(), null, null, 0
            )

            val jsonObject = JSONObject()
            jsonObject.put("eventId", highlight.eventId)
            jsonObject.put("startTimestamp", highlight.startTimestamp)
            jsonObject.put("name", highlight.name)
            jsonObject.put("url", highlight.url)
            jsonObject.put("playerIdList", highlight.playerIdList)

            val jsonObjectString = jsonObject.toString()
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            insertCallback?.invoke(requestBody)
            updateRecyclerViewCallback?.invoke(highlight)
            hideEmptyStateCallback.invoke()
        }

        return binding.root
    }

}