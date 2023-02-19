package tech.tonyrowan.pokemon_ev_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import tech.tonyrowan.pokemon_ev_tracker.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    private val startForResult = registerForActivityResult(ChoosePokemon()) { result: Pokemon? ->
        result?.let {
            // Add stats to tracker
            Log.d("Pokemon","Added defeated pokemon $result")

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trackDefeatedPokemon.setOnClickListener {
            startForResult.launch(null)
        }
    }
}