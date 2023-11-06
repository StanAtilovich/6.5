package ru.stan.a65.presentation

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModel
import ru.stan.a65.R
import ru.stan.a65.presentation.AllUtils.DataBaseUtils

class ForumViewModel : ViewModel() {

    fun sendTextToFirebaseDb(text: String, databaseUtils: DataBaseUtils) {
        databaseUtils.sendTextToDb(text)
    }

    fun retreiveDataForumBDd(textView: TextView, databaseUtils: DataBaseUtils) {
        databaseUtils.retreiveDataFromDb(textView)
    }

    inner class TextWatcherForEditText(
        private val imageButton: ImageButton
    ) : TextWatcher {

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            if (text?.trim()!!.isNotEmpty()) {
                imageButton.isEnabled = true
                imageButton.setImageResource(R.drawable.send_blue)
            } else {
                imageButton.isEnabled = false
                imageButton.setImageResource(R.drawable.send_gray)
            }

        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}

    }
}

data class ForumItem(
    val text: String? = "",
    val user: String? = ""
)