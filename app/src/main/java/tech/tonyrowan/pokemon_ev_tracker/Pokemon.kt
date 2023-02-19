package tech.tonyrowan.pokemon_ev_tracker

import android.content.res.Resources
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

object Pokedex {
    lateinit var pokemon : Array<Pokemon>

    fun loadPokemon(resources: Resources) {
        if (::pokemon.isInitialized) {
            // skip
        } else {
            val mapper = YAMLMapper().registerModule(KotlinModule())
            pokemon = mapper.readValue(
                resources.openRawResource(R.raw.pokemon),
                Array<Pokemon>::class.java
            )
        }
    }

    fun find(number: Int) : Pokemon? {
        return pokemon.find { it.number == number }
    }
}

data class Pokemon(val number: Int, val name: String, @JsonProperty("ev_yield") val evYield: Stats) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pokemon

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }

    data class Stats(
        val hp: Int = 0,
        val attack: Int = 0,
        val defense: Int = 0,
        @JsonProperty("special-attack") val specialAttack: Int = 0,
        @JsonProperty("special-defense") val specialDefense: Int = 0,
        val speed: Int = 0) {

        override fun toString(): String {
            return mapOf(
                "HP" to hp,
                "Attack" to attack,
                "Defense" to defense,
                "Special Attack" to specialAttack,
                "Special Defense" to specialDefense,
                "Speed" to speed
            ).filter { it.value > 0 }
                .map { "${it.value} ${it.key}" }
                .reduce { acc, next -> "$acc, $next"}
        }
    }
}