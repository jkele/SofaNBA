package hr.algebra.sofanba.adapters.section

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.algebra.sofanba.fragments.PlayerDetailsFragment
import hr.algebra.sofanba.fragments.PlayerMatchesFragment

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
                PlayerMatchesFragment()
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