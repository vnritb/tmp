package cat.itb.damv.m7.g2.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

/*
 * Aquí en comptes de fer servir alguna de les funcions definides a Main 3, la implementem 'al vuelo'
 * I la implementen de manera que concateni els números amb emojis pel mig
 * Com que la implementem al vuelo, no necessitem que tingui nom.  D'això s'endiu una lambda
 * TODO Una lambda és laimplementació d'una funció que no te nom,
 * per que s'implementa 'al vuelo' i no es neccesri posar-li nom
 */

@Composable
fun app5() {

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

    // Llista d'opcions d'emojis:
    val emojiList = listOf("😀", "😃", "😄", "😁", "😆", "😅", "😂", "😊", "😎", "😍")

    //Operem
    //TODO Aquí li pasem la lambda a operarLists
    val joinedList = listJoiner.operarLists({ a, b ->
        val randomEmoji = emojiList.random()
        "$a$randomEmoji$b"
    }
    )

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
        app5()
    }
}


