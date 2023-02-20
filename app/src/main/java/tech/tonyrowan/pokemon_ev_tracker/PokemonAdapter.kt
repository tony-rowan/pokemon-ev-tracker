package tech.tonyrowan.pokemon_ev_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.tonyrowan.pokemon_ev_tracker.data.Pokemon
import tech.tonyrowan.pokemon_ev_tracker.databinding.PokemonListItemBinding

class PokemonAdapter(private val onClick: (Pokemon) -> Unit) :
    ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonDiffCallback) {

    class PokemonViewHolder(itemView: View, val onClick: (Pokemon) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = PokemonListItemBinding.bind(itemView)
        private var currentPokemon: Pokemon? = null

        init {
            itemView.setOnClickListener {
                currentPokemon?.let {
                    onClick(it)
                }
            }
        }

        fun bind(pokemon: Pokemon) {
            currentPokemon = pokemon

            binding.number.text = "#%03d".format(pokemon.number)
            binding.name.text = pokemon.name
            binding.evYield.text = "Yield: ${pokemon.evYield}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }
}