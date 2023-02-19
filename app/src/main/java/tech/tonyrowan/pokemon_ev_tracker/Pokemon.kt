package tech.tonyrowan.pokemon_ev_tracker

data class Pokemon(val number: Int, val name: String, val evYield: Array<Stat>) {
    companion object {
             val list : List<Pokemon> = listOf(
                Pokemon(1, "Bulbasaur", arrayOf(Stat("Special Defense", 1))),
                Pokemon(2, "Ivysaur", arrayOf(Stat("Special Defense", 2))),
                Pokemon(3, "Venasaur", arrayOf(Stat("Special Defense", 1), Stat("Special Attack", 1)))
            )

        fun find(number: Int) : Pokemon? {
            return list.find { it.number == number }
        }
    }

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

    fun evYieldAsString(): String {
        return evYield.map { "${it.amount} ${it.name}" }.reduce { acc, next -> "$acc, $next" }
    }

    data class Stat(val name: String, val amount: Int)
}