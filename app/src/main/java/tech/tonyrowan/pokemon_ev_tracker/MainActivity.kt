package tech.tonyrowan.pokemon_ev_tracker

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import tech.tonyrowan.pokemon_ev_tracker.data.Pokedex
import tech.tonyrowan.pokemon_ev_tracker.data.Pokemon
import tech.tonyrowan.pokemon_ev_tracker.data.PokemonStats
import tech.tonyrowan.pokemon_ev_tracker.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private val currentStats = PokemonStats()

    private val startForResult = registerForActivityResult(ChoosePokemonContract()) { result: Pokemon? ->
        result?.let {
            currentStats.hp += result.evYield.hp
            currentStats.attack += result.evYield.attack
            currentStats.defense += result.evYield.defense
            currentStats.specialAttack += result.evYield.specialAttack
            currentStats.specialDefense += result.evYield.specialDefense
            currentStats.speed += result.evYield.speed

            val preferences = getPreferences(MODE_PRIVATE).edit()
            preferences.putInt("hp", currentStats.hp)
            preferences.putInt("attack", currentStats.attack)
            preferences.putInt("defense", currentStats.defense)
            preferences.putInt("special-attack", currentStats.specialAttack)
            preferences.putInt("special-defense", currentStats.specialDefense)
            preferences.putInt("speed", currentStats.speed)
            preferences.apply()

            syncStatsWithLabels()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Pokedex.loadPokemon(resources)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trackDefeatedPokemon.setOnClickListener {
            startForResult.launch(null)
        }

        binding.reset.setOnClickListener {
            currentStats.hp = 0
            currentStats.attack = 0
            currentStats.defense = 0
            currentStats.specialAttack = 0
            currentStats.specialDefense = 0
            currentStats.speed = 0

            syncStatsWithLabels()
        }

        val preferences = getPreferences(MODE_PRIVATE)
        currentStats.hp = preferences.getInt("hp", 0)
        currentStats.attack = preferences.getInt("attack", 0)
        currentStats.defense = preferences.getInt("defense", 0)
        currentStats.specialAttack = preferences.getInt("special-attack", 0)
        currentStats.specialDefense = preferences.getInt("special-defense", 0)
        currentStats.speed = preferences.getInt("speed", 0)

        syncStatsWithLabels()
    }

    private fun syncStatsWithLabels() {
        binding.hpStat.text = currentStats.hp.toString()
        binding.attackStat.text = currentStats.attack.toString()
        binding.defenseStat.text = currentStats.defense.toString()
        binding.specialAttackStat.text = currentStats.specialAttack.toString()
        binding.specialDefenseStat.text = currentStats.specialDefense.toString()
        binding.speedStat.text = currentStats.speed.toString()
    }
}