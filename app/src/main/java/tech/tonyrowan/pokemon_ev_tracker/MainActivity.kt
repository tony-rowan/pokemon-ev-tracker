package tech.tonyrowan.pokemon_ev_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.tonyrowan.pokemon_ev_tracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}