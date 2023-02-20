package tech.tonyrowan.pokemon_ev_tracker

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import tech.tonyrowan.pokemon_ev_tracker.data.Pokedex
import tech.tonyrowan.pokemon_ev_tracker.data.Pokemon

class ChoosePokemon : ActivityResultContract<Void?, Pokemon?>() {
    override fun createIntent(context: Context, input: Void?): Intent {
        return Intent(context, ChoosePokemonActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Pokemon? {
        return Pokedex.find(resultCode)
    }
}
