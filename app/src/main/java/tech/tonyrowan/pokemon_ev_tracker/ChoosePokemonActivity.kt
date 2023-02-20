package tech.tonyrowan.pokemon_ev_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.widget.addTextChangedListener
import tech.tonyrowan.pokemon_ev_tracker.data.Pokedex
import tech.tonyrowan.pokemon_ev_tracker.data.Pokemon
import tech.tonyrowan.pokemon_ev_tracker.databinding.ActivityChoosePokemonBinding

class ChoosePokemonActivity : ComponentActivity() {
    private lateinit var binding: ActivityChoosePokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Pokedex.loadPokemon(resources)

        binding = ActivityChoosePokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonAdapter = PokemonAdapter { pokemon -> onClickPokemon(pokemon) }
        binding.pokemonList.adapter = pokemonAdapter
        pokemonAdapter.submitList(Pokedex.pokemon.toList())

        binding.pokemonNameInput.addTextChangedListener { text ->
            val filteredPokemon = Pokedex.pokemon.filter { it.name.contains(text.toString(), ignoreCase = true) }
            pokemonAdapter.submitList(filteredPokemon.toList())
        }
    }

    private fun onClickPokemon(pokemon: Pokemon) {
        setResult(pokemon.number)
        finish()
    }
}