package cat.itb.damv.m7.g2.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

/*
 * Fer una classe que gestiona un parell de llistes
 * Afegir a la classe un mètode per sumar els parells de números de cada posició
 * Per exemple: per a 1,2,3, i 4,5,6, el resultat serà 5,7,9
 */

@Composable
fun app1() {

    //La classe
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

    //Les tres llistes:
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(4, 5, 6)
    val listJoiner = ListJoiner(list1, list2)
    //Cridar al mètode de sumar de ListJoiner per obtenir la tercera llista
    val joinedList = listJoiner.sumLists()

    //Per cada string de la llista, crear un component Text, que anirà dintre d'un column
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
        app1()
    }
}



