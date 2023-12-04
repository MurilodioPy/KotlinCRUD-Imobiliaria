package br.com.ads.imobiliaria.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.ads.imobiliaria.banco.dao.ImovelDAO
import br.com.ads.imobiliaria.banco.dao.InquilinoDAO
import br.com.ads.imobiliaria.banco.dao.ProprietarioDAO
import br.com.ads.imobiliaria.model.Imovel
import br.com.ads.imobiliaria.model.Inquilino
import br.com.ads.imobiliaria.model.Proprietario
import br.com.ads.imobiliaria.ui.theme.componentes.TopBarComponent

@Composable
fun SalvarScreen(
    proprietarioDAO: ProprietarioDAO,
    imovelDAO: ImovelDAO,
    inquilinoDAO: InquilinoDAO,
    navController: NavHostController
) {
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
            var proprietarios by remember { mutableStateOf(listOf<Proprietario>()) }
            proprietarios = proprietarioDAO.obterTodos()
            var inquilinos by remember { mutableStateOf(listOf<Inquilino>()) }
            inquilinos = inquilinoDAO.obterTodos()
            var imoveis by remember { mutableStateOf(listOf<Imovel>()) }
            imoveis = imovelDAO.obterTodos()
            var selectedCpf by remember{ mutableStateOf("") }
            val listState = rememberLazyListState()

//            LazyColumn(state = listState,
//                modifier = Modifier.heightIn(min = 250.dp, max = 250.dp)
//            ) {
//                items(proprietarios) { proprietario ->
//                    blocoProprietario(proprietario, selectedCpf){ newSelectedCpf ->
//                        selectedCpf = newSelectedCpf
//                    }
//                }
//            }
//            DivisorComponent()
//
//            LazyColumn(state = listState,
//                modifier = Modifier.heightIn(min = 250.dp, max = 250.dp)
//            ) {
//                items(inquilinos) { inquilino ->
//                    blocoInquilino(inquilino, selectedCpf){ newSelectedCpf ->
//                        selectedCpf = newSelectedCpf
//                    }
//                }
//            }
//
//            DivisorComponent()
//
//            LazyColumn(state = listState,
//                modifier = Modifier.heightIn(min = 250.dp, max = 250.dp)
//            ) {
//                items(inquilinos) { inquilino ->
//                    blocoInquilino(inquilino, selectedCpf){ newSelectedCpf ->
//                        selectedCpf = newSelectedCpf
//                    }
//                }
//            }

            Button(
                onClick = {
                    inquilinoDAO.salvarArquivosInquilinos()
                    proprietarioDAO.salvarArquivosProprietarios()
                    imovelDAO.salvarArquivosImoveis()
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
