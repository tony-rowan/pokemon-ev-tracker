package tech.tonyrowan.pokemon_ev_tracker.data

import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonStats(
    var hp: Int = 0,
    var attack: Int = 0,
    var defense: Int = 0,
    @JsonProperty("special_attack") var specialAttack: Int = 0,
    @JsonProperty("special_defense") var specialDefense: Int = 0,
    var speed: Int = 0
) {

    override fun toString(): String {
        return mapOf(
            "HP" to hp,
            "Attack" to attack,
            "Defense" to defense,
            "Special Attack" to specialAttack,
            "Special Defense" to specialDefense,
            "Speed" to speed
        ).filter { it.value > 0 }.map { "${it.value} ${it.key}" }
            .reduce { acc, next -> "$acc, $next" }
    }
}