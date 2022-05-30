package hr.algebra.sofanba.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.databinding.HighlightEmptyStateViewBinding
import hr.algebra.sofanba.databinding.HighlightItemViewBinding
import okhttp3.RequestBody

class HighlightEmptyStateView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    val binding = HighlightEmptyStateViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun setupHighlightEmptyStateView(
        bottomSheetCallback: (() -> Unit)?
    ) {
        binding.btnAddUrl.setOnClickListener {
            bottomSheetCallback?.invoke()
        }
    }

}