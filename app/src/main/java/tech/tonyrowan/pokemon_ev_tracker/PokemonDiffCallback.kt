package tech.tonyrowan.pokemon_ev_tracker

import androidx.recyclerview.widget.DiffUtil
import tech.tonyrowan.pokemon_ev_tracker.data.Pokemon

object PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.number == newItem.number
    }
}