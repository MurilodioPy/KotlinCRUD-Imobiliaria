package br.com.ads.imobiliaria.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.ads.imobiliaria.navigation.AppDestination
import br.com.ads.imobiliaria.ui.theme.ImobiliariaTheme
import br.com.ads.imobiliaria.ui.theme.componentes.BotaoSalvar
import br.com.ads.imobiliaria.ui.theme.componentes.CardComponent
import br.com.ads.imobiliaria.ui.theme.componentes.DivisorComponent
import br.com.ads.imobiliaria.ui.theme.componentes.TopBarComponent
import br.com.ads.imobiliaria.ui.theme.componentes.TopBarComponentNoBack

@Composable
fun PrincipalScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBarComponentNoBack("Imobiliária")
        }
    ) { innerPadding ->
        val telas = arrayListOf(
            AppDestination.Locacao.route,
            AppDestination.Imovel.route,
            AppDestination.Proprietario.route,
            AppDestination.Inqulino.route,
            AppDestination.Salvar.route)
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LazyColumn {
                items(telas) { tela ->
                    CardComponent(tela, navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrincipalPreview() {
    ImobiliariaTheme {
        val navController = rememberNavController()
        PrincipalScreen(navController = navController)
    }
}