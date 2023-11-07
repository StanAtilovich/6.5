package ru.stan.a65.presentation

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import androidx.lifecycle.ViewModel
import ru.stan.a65.App
import ru.stan.a65.R
import ru.stan.a65.domain.usecase.SendMessageUseCase


class ForumViewModel(
    private val SendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    fun getRecyclerAdapter(): ForumAdapter {
        val options = App.INSTANCE.firebaseInstance.getFirebaseRecyclerOptions()
        return ForumAdapter(options)
    }

    fun sendTextToFirebaseDb(text: String) {
        SendMessageUseCase(text)
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