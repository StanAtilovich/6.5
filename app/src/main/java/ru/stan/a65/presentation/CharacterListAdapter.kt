package ru.stan.a65.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.stan.a65.databinding.CharacterItemBinding
import ru.stan.a65.domain.model.CharacterItem


class CharacterListAdapter
    : androidx.recyclerview.widget.ListAdapter<CharacterItem,CharacterListAdapter.CharacterListViewHolder>(
    callBack) {

    class CharacterListViewHolder(val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root)
    // {
    //     val imageView = itemView.findViewById<ImageView>(R.id.image_character)
    //     val textVName = itemView.findViewById<TextView>(R.id.tv_name)
    //     val textVHouse = itemView.findViewById<TextView>(R.id.tvHouse)
    // }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val characterItem = getItem(position)
        holder.binding.imageCharacter.load(characterItem.imageUrl)
       // holder.binding.tvname.text = characterItem.name
        holder.binding.tvHouse.text = characterItem.hogwartsHouse
    }
}