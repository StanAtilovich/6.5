package ru.stan.a65.presentation.ui.fragmentCharacterList

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import ru.stan.a65.domain.model.CharacterItem


class CharacterListAdapter : androidx.recyclerview.widget.ListAdapter<CharacterItem, CharacterListAdapter.CharacterListViewHolder>(
    callBack
) {
    class CharacterListViewHolder(private val composeView: ComposeView) :
        RecyclerView.ViewHolder(composeView) {
        fun bind(characterItem: CharacterItem) {
            composeView
                .setContent {
                    CharacterItemCompose(characterItem)
                }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CharacterListViewHolder {
        val composeView = ComposeView(parent.context)
        return CharacterListViewHolder(composeView)
    }


    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val characterItem = getItem(position)
        holder.bind(characterItem)
    }
}