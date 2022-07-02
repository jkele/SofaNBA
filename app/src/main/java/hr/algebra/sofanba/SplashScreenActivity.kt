package hr.algebra.sofanba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.sofanba.databinding.ActivitySplashScreenBinding
import hr.algebra.sofanba.helpers.callDelayed
import hr.algebra.sofanba.helpers.isOnline
import hr.algebra.sofanba.helpers.showCustomDialog
import hr.algebra.sofanba.helpers.startAnimation

private const val DELAY = 3000L

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun redirect() {
        callDelayed(DELAY) {
            if (isOnline()) {
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
            } else {
                showCustomDialog(getString(R.string.no_internet_connection), this)
            }
        }
    }

    private fun startAnimations() {
        binding.ivLogo.startAnimation(R.anim.blink)
    }
}