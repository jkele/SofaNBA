package hr.algebra.sofanba.adapters.sectionPager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.algebra.sofanba.fragments.TeamDetailsFragment
import hr.algebra.sofanba.fragments.TeamMatchesFragment

class TeamSectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TeamDetailsFragment()
            }
            else -> {
                TeamMatchesFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Details"
            else -> {
                return "Matches"
            }
        }
    }

}