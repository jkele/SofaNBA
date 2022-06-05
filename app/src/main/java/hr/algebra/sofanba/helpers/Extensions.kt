package hr.algebra.sofanba.helpers

import android.content.Context
import com.google.android.material.textfield.TextInputEditText
import hr.algebra.sofanba.R

fun TextInputEditText.customValidate(context: Context): Boolean {
    val valid = true

    if (this.text!!.isBlank()) {
        this.error = context.getString(R.string.please_insert_value)
        this.requestFocus()
        return false
    }

    when(this.tag) {
        "URL"-> {
            if (!this.text!!.contains("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex())) {
                this.error = context.getString(R.string.enter_valid_url)
                return false
            }
        }
        "YTURL" -> {
            if (!this.text!!.contains("^(https?://)?(www\\.youtube\\.com|youtu\\.be)/.+\$".toRegex())) {
                this.error = context.getString(R.string.youtube_url)
                return false
            }
        }
    }
    return valid
}