package ru.stan.a65.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import ru.stan.a65.R
import ru.stan.a65.databinding.ForumItemBinding

class ForumAdapter(
    private val options: FirebaseRecyclerOptions<ForumItem>
) : FirebaseRecyclerAdapter<ForumItem, ForumAdapter.ForumViewHolder>(options) {


    inner class ForumViewHolder(
        private val binding: ForumItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(forumItem: ForumItem){
            binding.tvForumMessage.text = forumItem.text

            binding.tvCurrentUser.text = forumItem.user ?: ANONYMOUS
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ForumViewHolder {
        return ForumViewHolder(
            ForumItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.forum_item, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int, model: ForumItem) {
        holder.bind(model)

    }
    companion object{
        private const val ANONYMOUS = "АНОНИМ"
    }
}