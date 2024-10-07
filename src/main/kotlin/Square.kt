package isel.leic.tds.checkers.model

const val BOARD_DIM = 8

class Square(val row: Row, val column: Column) {
    val index = row.index * BOARD_DIM + column.index
    val black: Boolean
        get() = (row.index + column.index) % 2 == 1

    override fun toString(): String {
        return "${row.digit}${column.symbol}"
    }

    // Sobrescrevendo equals para comparar instâncias de Square com base nas propriedades
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Square) return false
        return row == other.row && column == other.column
    }

    // Sobrescrevendo hashCode para garantir que objetos iguais tenham o mesmo hash
    override fun hashCode(): Int {
        var result = row.hashCode()
        result = 31 * result + column.hashCode()
        return result
    }

    companion object {
        val values: List<Square> = Row.values.flatMap { row ->
            Column.values.map { col -> Square(row, col) }
        }
    }
}

// Função para converter String em Square
fun String.toSquareOrNull(): Square? {
    if (length != 2) return null
    val row = this[0].toRowOrNull() ?: return null
    val column = this[1].toColumnOrNull() ?: return null
    return Square(row, column)
}

fun String.toSquare(): Square {
    return toSquareOrNull() ?: throw IllegalArgumentException("Invalid square string: $this")
}
