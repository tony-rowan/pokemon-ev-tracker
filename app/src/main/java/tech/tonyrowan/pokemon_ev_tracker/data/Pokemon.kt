package tech.tonyrowan.pokemon_ev_tracker.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Pokemon(
    val number: Int,
    val name: String,
    @JsonProperty("ev_yield") val evYield: PokemonStats
)