package tech.tonyrowan.pokemon_ev_tracker.data

import android.content.res.Resources
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import tech.tonyrowan.pokemon_ev_tracker.R


object Pokedex {
    lateinit var pokemon: Array<Pokemon>

    fun loadPokemon(resources: Resources) {
        if (Pokedex::pokemon.isInitialized) {
            // skip
        } else {
            val kotlinModule = KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build()
            val mapper = ObjectMapper(YAMLFactory()).registerModule(kotlinModule)
            pokemon = mapper.readValue(
                resources.openRawResource(R.raw.pokemon),
                Array<Pokemon>::class.java
            )
        }
    }

    fun find(number: Int): Pokemon? {
        return pokemon.find { it.number == number }
    }
}