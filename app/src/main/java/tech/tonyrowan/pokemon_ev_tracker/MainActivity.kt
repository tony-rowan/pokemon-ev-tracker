package tech.tonyrowan.pokemon_ev_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import tech.tonyrowan.pokemon_ev_tracker.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private var hp = 0
    private var attack = 0
    private var defense = 0
    private var specialAttack = 0
    private var specialDefense = 0
    private var speed = 0

    private val startForResult = registerForActivityResult(ChoosePokemon()) { result: Pokemon? ->
        result?.let {
            result.evYield.forEach {
                when (it.name) {
                    "HP" -> hp += it.amount
                    "Attack" -> attack += it.amount
                    "Defense" -> defense += it.amount
                    "Special Attack" -> specialAttack += it.amount
                    "Special Defense" -> specialDefense += it.amount
                    "Speed" -> speed += it.amount
                }

                syncStatsWithLabels()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trackDefeatedPokemon.setOnClickListener {
            startForResult.launch(null)
        }

        binding.reset.setOnClickListener {
            hp = 0
            attack = 0
            defense = 0
            specialAttack = 0
            specialDefense = 0
            speed = 0

            syncStatsWithLabels()
        }
    }

    private fun syncStatsWithLabels() {
        binding.hpStat.text = hp.toString()
        binding.attackStat.text = attack.toString()
        binding.defenseStat.text = defense.toString()
        binding.specialAttackStat.text = specialAttack.toString()
        binding.specialDefenseStat.text = specialDefense.toString()
        binding.speedStat.text = speed.toString()
    }
}