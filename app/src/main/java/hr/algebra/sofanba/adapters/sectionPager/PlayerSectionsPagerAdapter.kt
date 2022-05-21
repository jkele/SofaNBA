package hr.algebra.sofanba.adapters.sectionPager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.algebra.sofanba.fragments.PlayerDetailsFragment
import hr.algebra.sofanba.fragments.TeamDetailsFragment
import hr.algebra.sofanba.fragments.TeamMatchesFragment

class PlayerSectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PlayerDetailsFragment()
            1 -> PlayerDetailsFragment()
            else -> {
                PlayerDetailsFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Details"
            1 -> "Statistics"
            else -> {
                return "Matches"
            }
        }
    }
}