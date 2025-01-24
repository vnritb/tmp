import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.w3c.dom.Document
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

data class Pregunta(
    val enunciat: String,
    val respostes: List<Pair<String, Boolean>>
)

fun carregarPreguntes(path: String): List<Pregunta> {
    val file = File(path)
    val document: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)

    val nodeList = document.getElementsByTagName("pregunta")
    val preguntes = mutableListOf<Pregunta>()

    for (i in 0 until nodeList.length) {
        val node = nodeList.item(i)
        val enunciat = node.childNodes.item(1).textContent.trim()
        val respostes = mutableListOf<Pair<String, Boolean>>()

        for (j in 0 until node.childNodes.length) {
            val respostaNode = node.childNodes.item(j)
            if (respostaNode.nodeName == "respuesta") {
                val text = respostaNode.textContent.trim()
                val correcta = respostaNode.attributes.getNamedItem("correcta").nodeValue.toBoolean()
                respostes.add(text to correcta)
            }
        }

        preguntes.add(Pregunta(enunciat, respostes))
    }

    return preguntes
}

@Composable
@Preview
fun testApp(preguntes: List<Pregunta>) {
    var actualIndex by remember { mutableStateOf(0) }
    var encertades by remember { mutableStateOf(0) }
    var fallades by remember { mutableStateOf(0) }
    var seleccionada by remember { mutableStateOf<String?>(null) }
    var testFinalitzat by remember { mutableStateOf(false) }

    if (testFinalitzat) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Test finalitzat!", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Resultats:")
            Text("Encertades: $encertades")
            Text("Fallades: $fallades")
        }
    } else {
        val pregunta = preguntes[actualIndex]

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = pregunta.enunciat,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Opcions de resposta
                pregunta.respostes.forEach { resposta ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (resposta.first == seleccionada),
                                onClick = { seleccionada = resposta.first }
                            )
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            selected = (resposta.first == seleccionada),
                            onClick = { seleccionada = resposta.first }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(resposta.first)
                    }
                }
            }

            // Botons de navegació i estadístiques
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (actualIndex > 0) actualIndex--
                        },
                        enabled = actualIndex > 0
                    ) {
                        Text("Enrere")
                    }

                    Button(
                        onClick = {
                            if (seleccionada != null) {
                                val correcte = pregunta.respostes.find { it.first == seleccionada }?.second
                                if (correcte == true) {
                                    encertades++
                                } else {
                                    fallades++
                                }

                                seleccionada = null // Resetejar selecció

                                if (actualIndex < preguntes.size - 1) {
                                    actualIndex++ // Passar a la següent pregunta
                                } else {
                                    testFinalitzat = true // Finalitzar test
                                }
                            }
                        },
                        enabled = seleccionada != null
                    ) {
                        Text("Següent")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Estadístiques del test
                Text("Pregunta: ${actualIndex + 1}/${preguntes.size}")
                Text("Encertades: $encertades | Fallades: $fallades")
            }
        }
    }
}

fun main() = application {
    val preguntes = carregarPreguntes("src/main/resources/questions.xml")

    Window(onCloseRequest = ::exitApplication, title = "Test sobre Lambdas Kotlin") {
        MaterialTheme {
            testApp(preguntes)
        }
    }
}