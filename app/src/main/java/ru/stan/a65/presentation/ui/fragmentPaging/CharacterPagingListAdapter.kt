package ru.stan.a65.presentation.ui.fragmentPaging

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.stan.a65.domain.model.CharacterPagingItem
import ru.stan.a65.presentation.ui.fragmentCharacterList.CharacterItemCompose


class CharacterPagingListAdapter
    : PagingDataAdapter<CharacterPagingItem,
        CharacterPagingListAdapter.CharacterListViewHolder>(callback) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CharacterListViewHolder {
        return CharacterListViewHolder(
            ComposeView(parent.context)
        )
    }

    override fun onBindViewHolder(
        holder: CharacterListViewHolder, position: Int
    ) {
        val characterItem = getItem(position)
        characterItem?.let { holder.bind(it) }
    }

    inner class CharacterListViewHolder(
        private val composeView: ComposeView
    ) : RecyclerView.ViewHolder(composeView) {
        fun bind(characterPagingItem: CharacterPagingItem) {
            composeView.setContent { 
                CharacterItemCompose(characterPagingItem = characterPagingItem)
            }
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<CharacterPagingItem>() {
            override fun areItemsTheSame(
                oldItem: CharacterPagingItem,
                newItem: CharacterPagingItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CharacterPagingItem,
                newItem: CharacterPagingItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}