package cat.itb.damv.m7.g2.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

/*
 * Aquí tenim el mateix  que a Main 3, però netejant una mica el codi i fent servir
 * concatenarIPosarAsteriscs, que ja està definida a Main3
 */

@Composable
fun app4() {

    class ListJoiner(private val list1: List<Int>, private val list2: List<Int>) {

        fun operarLists(operarItem: ( Int, Int) -> String): List<String> {
            val length = minOf(list1.size, list2.size)
            val result = mutableListOf<String>()
            for (i in 0 until length) {
                result.add(operarItem(list1[i] , list2[i]))
            }
            return result
        }
    }

    //Les tres llistes:
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(4, 5, 6)
    val listJoiner = ListJoiner(list1, list2)

    //Operem
    val joinedList = listJoiner.operarLists(::concatenarIPosarAsteriscs)

    //Per cada string de la llista, crear un component Text, que anirà dintre d'un column
    MaterialTheme {
        Column{
            Text(joinedList.first().toString())
            Text(joinedList[1])
            Text(joinedList[2])
        }
    }
}

//Programa principal
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app4()
    }
}


