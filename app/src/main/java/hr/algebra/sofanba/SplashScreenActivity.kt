package hr.algebra.sofanba

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.sofanba.databinding.ActivitySplashScreenBinding
import hr.algebra.sofanba.helpers.*
import hr.algebra.sofanba.viewmodels.SplashScreenViewModel

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.algebra.sofanba.dataImported"

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun redirect() {
        if (getBooleanPreference(DATA_IMPORTED)) {
            callDelayed(DELAY) { startActivity<LoginActivity>() }
        } else {
            callDelayed(DELAY) {
                if (isOnline()) {
                    viewModel.insertTeamsList()
                    this.setBooleanPreference(DATA_IMPORTED, true)
                    this.startActivity<LoginActivity>()
                } else {
                    showCustomDialog(getString(R.string.no_internet_connection), this)
                }
            }
        }
    }

    private fun startAnimations() {
        binding.ivLogo.startAnimation(R.anim.blink)
    }
}