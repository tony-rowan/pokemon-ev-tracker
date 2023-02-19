package tech.tonyrowan.pokemon_ev_tracker

data class Pokemon(val number: Int, val name: String) {
    companion object {
             val list : List<Pokemon> = listOf(
                Pokemon(1, "bulbasaur"),
                Pokemon(2, "Ivysaur"),
                Pokemon(3, "Venasaur")
            )

        fun find(number: Int) : Pokemon? {
            return list.find { it.number == number }
        }
    }
}