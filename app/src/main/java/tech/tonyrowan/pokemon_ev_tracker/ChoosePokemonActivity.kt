package tech.tonyrowan.pokemon_ev_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.DiffUtil
import tech.tonyrowan.pokemon_ev_tracker.data.Pokedex
import tech.tonyrowan.pokemon_ev_tracker.data.Pokemon
import tech.tonyrowan.pokemon_ev_tracker.databinding.ActivityChoosePokemonBinding

class ChoosePokemonActivity : ComponentActivity() {
    private lateinit var binding: ActivityChoosePokemonBinding

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

        Pokedex.loadPokemon(resources)

        binding = ActivityChoosePokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonAdapter = PokemonAdapter { pokemon -> adapterOnClick(pokemon) }
        binding.pokemonList.adapter = pokemonAdapter
        pokemonAdapter.submitList(Pokedex.pokemon.toList())
    }

    private fun adapterOnClick(pokemon: Pokemon) {
        setResult(pokemon.number)
        finish()
    }
}