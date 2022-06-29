package hr.algebra.sofanba.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import hr.algebra.sofanba.R
import hr.algebra.sofanba.adapters.EXTRA_TEAM
import hr.algebra.sofanba.databinding.FragmentTeamDetailsBinding
import hr.algebra.sofanba.helpers.getStadiumLocation
import hr.algebra.sofanba.helpers.isOnline
import hr.algebra.sofanba.helpers.showCustomDialog
import hr.algebra.sofanba.network.model.Team
import hr.algebra.sofanba.viewmodels.TeamDetailsViewModel

class TeamDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTeamDetailsBinding
    private val viewModel: TeamDetailsViewModel by activityViewModels()

    private lateinit var selectedTeam: Team

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamDetailsBinding.inflate(inflater, container, false)

        selectedTeam = requireActivity().intent.getSerializableExtra(EXTRA_TEAM) as Team

        binding.teamInfoView.setupTeamInfoView(requireContext(), selectedTeam)
        binding.tvCityName.text = selectedTeam.city


        viewModel.teamsList.observe(viewLifecycleOwner) { teamsList ->
            binding.progressBar.visibility = ProgressBar.VISIBLE

            val divisionTeams = teamsList.filter { it.division == selectedTeam.division } as ArrayList
            divisionTeams.remove(selectedTeam)
            binding.rvDivisionTeams.setupTeamSequenceView(requireContext(), divisionTeams, false)

            binding.progressBar.visibility = ProgressBar.GONE
        }

        if(requireContext().isOnline()) {
            viewModel.getTeamsList()
        } else {
            showCustomDialog(getString(R.string.no_internet_connection), requireContext())
        }

        return binding.root
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val location = getStadiumLocation(selectedTeam.abbreviation)
        val zoomLevel = 15f
        googleMap.addMarker(MarkerOptions().position(location))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}