package hr.algebra.sofanba.adapters.section

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.algebra.sofanba.fragments.MatchDetailsFragment
import hr.algebra.sofanba.fragments.PlayerDetailsFragment
import hr.algebra.sofanba.fragments.PlayerMatchesFragment
import hr.algebra.sofanba.fragments.TopPlayersFragment

class MatchSectionsPagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MatchDetailsFragment()
            else -> {
                TopPlayersFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Details"
            else -> {
                return "Top players"
            }
        }
    }
}