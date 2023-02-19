package tech.tonyrowan.pokemon_ev_tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.tonyrowan.pokemon_ev_tracker.databinding.ActivityChoosePokemonBinding
import tech.tonyrowan.pokemon_ev_tracker.databinding.PokemonListItemBinding

class ChoosePokemonActivity : ComponentActivity() {
    private var pokemon : List<Pokemon> = listOf(
      Pokemon(1, "bulbasaur"),
      Pokemon(2, "Ivysaur"),
      Pokemon(3, "Venasaur")
    )

    private lateinit var binding: ActivityChoosePokemonBinding

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

                binding.pokemonNumber.text = pokemon.number.toString()
                binding.pokemonName.text = pokemon.name
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

    object PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.number == newItem.number
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosePokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonAdapter = PokemonAdapter { pokemon -> adapterOnClick(pokemon) }
        binding.pokemonList.adapter = pokemonAdapter
        pokemonAdapter.submitList(pokemon)
    }

    private fun adapterOnClick(pokemon: Pokemon) {
        setResult(pokemon.number)
        finish()
    }
}