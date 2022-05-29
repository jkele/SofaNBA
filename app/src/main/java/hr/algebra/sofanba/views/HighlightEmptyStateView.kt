package hr.algebra.sofanba.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import hr.algebra.sofanba.databinding.HighlightEmptyStateViewBinding
import hr.algebra.sofanba.databinding.HighlightItemViewBinding

class HighlightEmptyStateView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    val binding = HighlightEmptyStateViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun setupHighlightEmptyStateView() {
        binding.btnAddUrl.setOnClickListener {

        }
    }

}