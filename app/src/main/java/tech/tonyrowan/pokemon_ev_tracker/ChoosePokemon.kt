package tech.tonyrowan.pokemon_ev_tracker;

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ChoosePokemon : ActivityResultContract<Void?, Pokemon?>() {
    override fun createIntent(context: Context, input: Void?): Intent {
        return Intent(context, ChoosePokemonActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?) : Pokemon? {
        return Pokemon.find(resultCode)
    }
}
