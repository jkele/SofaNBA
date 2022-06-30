package hr.algebra.sofanba.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import hr.algebra.sofanba.AboutActivity
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.FragmentSettingsBinding
import hr.algebra.sofanba.viewmodels.SettingsViewModel

const val SHARED_PREF_NAME = "hr.algebra.sofanba.sharedPrefs"
const val MEASURE_UNIT = "hr.algebra.sofanba.measureUnit"

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val sharedPref = activity?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val unitPref = sharedPref?.getString(MEASURE_UNIT, "Imperial")

        when(unitPref){
            "Imperial" -> binding.unitRadioButtonGroup.check(binding.rbImperial.id)
            "Metric" -> binding.unitRadioButtonGroup.check(binding.rbMetric.id)
        }

        binding.unitRadioButtonGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId) {
                binding.rbMetric.id -> {
                    sharedPref?.edit {
                        putString(MEASURE_UNIT, "Metric")
                        commit()
                    }
                }
                binding.rbImperial.id -> {
                    sharedPref?.edit {
                        putString(MEASURE_UNIT, "Imperial")
                        commit()
                    }
                }
            }
        }

        binding.btnAbout.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity::class.java)
            requireContext().startActivity(intent)
        }

        binding.btnClearFavorites.setOnClickListener {
            showCustomDialog(getString(R.string.clear_favorites_list))
        }

        return binding.root
    }

    private fun showCustomDialog(title: String) {
        val dialog = Dialog(requireContext()).apply {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.setCancelable(true)
            this.setContentView(R.layout.custom_dialog)
            this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        val tvTitle = dialog.findViewById<TextView>(R.id.tvDialogTitle)
        val btnOk = dialog.findViewById<Button>(R.id.btnDialogOk)
        val btnCancel = dialog.findViewById<Button>(R.id.btnDialogCancel)

        tvTitle.text = title
        btnOk.text = getString(R.string.clear)
        btnCancel.text = getString(R.string.cancel)

        btnOk.setOnClickListener {
            viewModel.clearFavoritesList()
            dialog.dismiss()
            showSnack()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    @SuppressLint("ShowToast")
    private fun showSnack() {
        val snack = Snackbar.make(binding.root, getString(R.string.favorites_list_is_clear), Snackbar.LENGTH_SHORT)
        snack.view.setBackgroundResource(R.drawable.background_custom_snackbar)
        snack.show()
    }

}