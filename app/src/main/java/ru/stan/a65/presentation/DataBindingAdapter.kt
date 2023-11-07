package ru.stan.a65.presentation

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("characterNameOfHarryPotter")
fun bindCharacterNameOfHarryPotter(textView: TextView, strName: String) {
    textView.text = strName
}

@BindingAdapter("hogwartsHouse")
fun bindHogwartsHouse(textView: TextView, strHouse: String) {
    textView.text = strHouse
}

@BindingAdapter("imageHarryPotterCharacter")
fun bindImageOfCharater(appCompatImageView: AppCompatImageView, imageUrl: String) {
    appCompatImageView.load(imageUrl)
}

@BindingAdapter("visibilityOfProgressBar")
fun bindVisisbikityProgressBar(progressBar: ProgressBar, state: ProgressState) {
    progressBar.isVisible = state is ProgressState.Loading
}

@BindingAdapter("onBtnRandomClickListener")
fun bindOnBtnRandomClickListener(btn: Button, clickListener: () -> Unit) {
    btn.setOnClickListener {
        clickListener()
    }
}
