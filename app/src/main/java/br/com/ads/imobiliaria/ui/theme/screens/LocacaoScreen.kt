package br.com.ads.imobiliaria.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.ads.imobiliaria.banco.dao.ImovelDAO
import br.com.ads.imobiliaria.banco.dao.InquilinoDAO
import br.com.ads.imobiliaria.banco.dao.ProprietarioDAO
import br.com.ads.imobiliaria.model.Imovel
import br.com.ads.imobiliaria.model.Inquilino
import br.com.ads.imobiliaria.model.Proprietario
import br.com.ads.imobiliaria.navigation.AppDestination
import br.com.ads.imobiliaria.ui.theme.componentes.ImagemCardComponent
import br.com.ads.imobiliaria.ui.theme.componentes.PopupWindowImovel
import br.com.ads.imobiliaria.ui.theme.componentes.PopupWindowInquilino
import br.com.ads.imobiliaria.ui.theme.componentes.PopupWindowProprietario
import br.com.ads.imobiliaria.ui.theme.componentes.TextoBoldComponent
import br.com.ads.imobiliaria.ui.theme.componentes.TopBarComponent

@Composable
fun LocacaoScreen(
    proprietarioDAO: ProprietarioDAO,
    imovelDAO: ImovelDAO,
    inquilinoDAO: InquilinoDAO,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopBarComponent("Locação", navController)
        },
        ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.secondary),
//                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var imovel = Imovel("", "", "".toFloat())
            PopupWindowImovel(){newImovel  ->
                imovel = Imovel(newImovel.matricula, newImovel.endereco, newImovel.valoraluguel)
            }
            PopupWindowInquilino(){newInquilino ->
                var inquilino = Inquilino(newInquilino.cpf, newInquilino.nome, newInquilino.valorCaucaoDepositado, newInquilino.imovel)
            }
            PopupWindowProprietario(){newProprietario ->
                var proprietario = Proprietario(newProprietario.cpf, newProprietario.nome, newProprietario.email, newProprietario.imovel)
            }
            imovelDAO.inserir(imovel)


        }
    }
}

@Composable
fun BlocoLocacao(imovel: Imovel, selectedCpf: String, onSelectedCpfChange: (String) -> Unit){
    val matriculaImovel = imovel.matricula
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .selectable(
                selected = matriculaImovel == selectedCpf,
                onClick = {
                    if (selectedCpf != matriculaImovel) {
                        onSelectedCpfChange(matriculaImovel)
                    } else {
                        onSelectedCpfChange("")
                    }
                }
            ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(2.dp, color = Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = if(matriculaImovel == selectedCpf) {
                MaterialTheme.colorScheme.tertiary
            }else{
                MaterialTheme.colorScheme.primary
            }
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImagemCardComponent()
            Column {
                TextoBoldComponent("Matricula Imovel: " + matriculaImovel)
                TextoBoldComponent("Endereço: " + imovel.endereco)
                TextoBoldComponent("Valor Aluguel: " + imovel.valoraluguel.toString())
            }
        }
    }

}
