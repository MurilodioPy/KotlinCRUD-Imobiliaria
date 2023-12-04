package br.com.ads.imobiliaria.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.ads.imobiliaria.banco.dao.ImovelDAO
import br.com.ads.imobiliaria.banco.dao.InquilinoDAO
import br.com.ads.imobiliaria.banco.dao.ProprietarioDAO
import br.com.ads.imobiliaria.model.Imovel
import br.com.ads.imobiliaria.model.Inquilino
import br.com.ads.imobiliaria.model.Proprietario
import br.com.ads.imobiliaria.ui.theme.componentes.TopBarComponent
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

@Composable
fun SalvarScreen(
    proprietarioDAO: ProprietarioDAO,
    imovelDAO: ImovelDAO,
    inquilinoDAO: InquilinoDAO,
    navController: NavHostController
) {

    val context = LocalContext.current

    fun criarArquivoTexto(nomeArquivo: String, conteudo: String) {
        try {
            val file = File(context.filesDir, nomeArquivo)
            val fos = FileOutputStream(file)
            fos.write(conteudo.toByteArray())
            fos.close()
            println("Arquivo $nomeArquivo criado com sucesso!")
        } catch (e: Exception) {
            e.printStackTrace()
            // Trate a exceção conforme necessário
        }
    }

    fun lerArquivoTexto(nomeArquivo: String): String {
        val file = File(context.filesDir, nomeArquivo)
        val stringBuilder = StringBuilder()

        try {
            val reader = BufferedReader(InputStreamReader(file.inputStream()))
            var line: String? = reader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = reader.readLine()
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
            // Tratar exceção conforme necessário
        }

        return stringBuilder.toString()
    }

    Scaffold(
        topBar = {
            TopBarComponent("Salvar Arquivos", navController)
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.secondary),
//                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
         /*   var proprietarios by remember { mutableStateOf(listOf<Proprietario>()) }
            proprietarios = proprietarioDAO.obterTodos()
            var inquilinos by remember { mutableStateOf(listOf<Inquilino>()) }
            inquilinos = inquilinoDAO.obterTodos()
            var imoveis by remember { mutableStateOf(listOf<Imovel>()) }
            imoveis = imovelDAO.obterTodos()*/
            Button(
                onClick = {
                    var texto1 = inquilinoDAO.salvarArquivosInquilinos()
                    var texto2 = proprietarioDAO.salvarArquivosProprietarios()
                    var texto3 =imovelDAO.salvarArquivosImoveis()
                    val nomeArquivo = "meu_arquivo.txt"

                    var text = texto1 + texto2 + texto3

                    criarArquivoTexto(nomeArquivo, text)

                    val textoRecuperado = lerArquivoTexto(nomeArquivo)

                    println(textoRecuperado)
                },
                Modifier
                    .padding(
                        top = 8.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Salvar Arquivos",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
    }
}
