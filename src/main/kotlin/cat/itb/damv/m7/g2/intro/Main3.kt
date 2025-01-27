package cat.itb.damv.m7.g2.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

/*
Resum:
Hi ha tres parts que intervenen quan es fa servir una lambda TODO 1, TODO 2, i TODO 3):
1. Declaració de l funció que la fa servir.  Aquí és: operarItem: ( Int, Int) -> String dintre de
    fun operarLists(operarItem: ( Int, Int) -> String): List<String> {
2. Implementar la funció tal i com demana el paràmetre operarItm de operarLists.
    Aquí tenim això a: fun sumar (a: Int, b:Int) :String{
3. Fe servir la funció.  Això es fa a:
    val joinedList = listJoiner.operarLists(::sumar)
 */

/*
 * Això el podem necessitar, si per exemple, ens demanen que ListJoiner, amb cada parell de números agafat de
 * la mateixa posició de les dues llistes, també pugui sumar, restar, dividir, fer potències, fer logaritmes...
 * Llavors, fem com l'acudit del qui donava el menjar als seus porcs.
 * Implementem la classe de manera que només amb una funció podem fer totes les operacions.
 * La clau és que el codi del que s'ha de fer amb cada parell de valors, es passa quan es crida la funció...
 * ... enforma de funció
 */

@Composable
fun app3() {

    //La classe
    class ListJoiner(private val list1: List<Int>, private val list2: List<Int>) {

        /*
         * Desapareixen de la classe els mètodes de sumar i multiplicar
         * Ara el mètode es únic: operarList
         * Però rep una funció, que indica qué el que s'ha de fer abans d'afegir un element a la nova llista
         */
        //TODO 1
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
    //Cridar a l'operació de ListJoiner per obtenir la tercera llista
    //Hem de passar-li una funció definida com diu a la signatura de la funció operarLists:  (Int, Int) -> String
    //La funció sumar que tenim definida més abaix, pot servir
    //TODO 3
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

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app3()
    }
}

// A qualsevol lloc podem tenir una funció que està implementada
// tal i com demana la funció operarList que se li ha de passar una funció:  (Int, Int) -> String
// Per exemple, aquesta:
//TODO 2
fun sumar (a: Int, b:Int) :String{
    return (a+b).toString()
}

//.. O aquesta que no fem servir
fun restar (a: Int, b:Int) :String{
    return (a+b).toString()
}

//.. O aquesta altra que tampoc fem servir
fun concatenarIPosarAsteriscs (a: Int, b:Int) :String{
    return "****$a****$b****"
}



