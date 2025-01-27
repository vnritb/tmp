package cat.itb.damv.m7.g2.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

/*
 * Finalment, resumim el codi tot el que es pot aplicant les regles de Kotlin
 * I apliquem la mateixa lògica pera mostrar els elements del composable
 */

@Composable
fun app6() {

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
    //TODO Es pot reduir el codi anterior fent el següent:
    // - treure els parèntesis d'operarLists
    // - cridar directament a emojiList.random()
    // - treure les claus de la lambda per qué ara el seu codi només tè una línia
    // - treure el return de la lambda, per qué és implicit
    // - Fins i tot, si només tingués un paràmetre, es podriaeliminar
    //   la part esquerra de la fletxa, i fer servir it
    val joinedList = listJoiner.operarLists{ a, b ->"$a$emojiList.random()$b"}

    //Per cada string de la llista, crear un component Text, que anirà dintre d'un column
    MaterialTheme {
        Column{

            // TODO versió llarga de la lambda
            // joinedList.map({
            //      item -> {
            //         Text(item)
            //      }
            // })

            //TODO versió ultra reduïda
            joinedList.map{Text(it)}

        }
    }
}

//Programa principal
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app6()
    }
}


