package hr.algebra.sofanba.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.algebra.sofanba.AboutActivity
import hr.algebra.sofanba.R
import hr.algebra.sofanba.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.btnAbout.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity::class.java)
            requireContext().startActivity(intent)
        }

        return binding.root
    }

}