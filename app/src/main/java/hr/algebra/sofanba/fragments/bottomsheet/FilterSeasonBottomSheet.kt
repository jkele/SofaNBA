package hr.algebra.sofanba.fragments.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.BottomsheetFitlerSeasonBinding

class FilterSeasonBottomSheet(
    private val selectedSeason: String?,
    private val filterCallback: ((String) -> Unit)
): BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetFitlerSeasonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomsheetFitlerSeasonBinding.inflate(inflater, container, false)

        val seasons = resources.getStringArray(R.array.seasons)
        val arrayAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, seasons)
        binding.seasonsDropdown.adapter = arrayAdapter

        if (selectedSeason != null) {
            binding.seasonsDropdown.setSelection(arrayAdapter.getPosition(selectedSeason))
        }

        binding.btnApply.setOnClickListener {
            filterCallback.invoke(binding.seasonsDropdown.selectedItem.toString())
        }

        binding.btnCancel.setOnClickListener {
            this.dismiss()
        }

        return binding.root
    }

}