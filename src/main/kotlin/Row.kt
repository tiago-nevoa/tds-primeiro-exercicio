package isel.leic.tds.checkers.model

@JvmInline
value class Row(val index : Int) {
    val digit: Char
        get() = '0'+ BOARD_DIM - index

    init {
        require(index in 0 until BOARD_DIM) { "Invalid row index: $index" }
    }

    companion object {
        val values: List<Row> = List(BOARD_DIM) { Row(it) }
    }
}

fun Char.toRowOrNull() : Row? {
    val index = BOARD_DIM - (this - '0')
    return if (index in 0 until BOARD_DIM) Row(index) else null
}
