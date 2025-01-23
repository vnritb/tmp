import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    val list1 = listOf(1, 2, 3)
    val list2 = listOf(4, 5, 6)
    val listJoiner = ListJoiner(list1, list2)
    val joinedList = listJoiner.sumLists()

// Imprimir el resultado
    println(joinedList)

    MaterialTheme {
        Column{
            Text(joinedList.first().toString())
            Text(joinedList[1].toString())
            Text(joinedList[2].toString())
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

class ListJoiner(private val list1: List<Int>, private val list2: List<Int>) {
    fun sumLists(): List<Int> {
        val length = minOf(list1.size, list2.size)
        val result = mutableListOf<Int>()
        for (i in 0 until length) {
            result.add(list1[i] + list2[i])
        }
        return result
    }
}

