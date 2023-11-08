package ru.stan.a65.presentation.ui.fragmentCharacterList

import androidx.recyclerview.widget.DiffUtil
import ru.stan.a65.domain.model.CharacterItem


val callBack = object : DiffUtil.ItemCallback<CharacterItem>(){
    override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
        return oldItem == newItem
    }

}