package br.com.ads.imobiliaria.ui.theme.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import br.com.ads.imobiliaria.banco.dao.ImovelDAO
import br.com.ads.imobiliaria.banco.dao.ProprietarioDAO
import br.com.ads.imobiliaria.model.Imovel
import br.com.ads.imobiliaria.model.Proprietario

@Composable
fun PopupWindowProprietario(onProprietarioChange: (Proprietario) -> Unit) {

    val openDialog = remember { mutableStateOf(false) }
    val buttonTitle = remember { mutableStateOf("Inserir Proprietários") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(50.dp, 250.dp)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                openDialog.value = !openDialog.value
                if (!openDialog.value) {
                    buttonTitle.value = "Inserir Proprietários"
                }
            }
        ) {
            Text(text = buttonTitle.value, modifier = Modifier.padding(3.dp))
        }
        Box {
            if (openDialog.value) {
                buttonTitle.value = "Fechar"
                Popup(
                    onDismissRequest = { openDialog.value = false },
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties(focusable = true)
                ) {
                    Box(
                        Modifier
                            .heightIn(1.dp, 300.dp)
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                            .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(10.dp))
                            .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            var isInputEmpty by rememberSaveable { mutableStateOf(false) }
                            val cpf = editTextComponent("CPF", isInputEmpty)
                            val nome = editTextComponent("Nome", isInputEmpty)
                            val email = editTextComponent("Email", isInputEmpty)
                            val proprietario = Proprietario(cpf, nome, email, "")
                            onProprietarioChange(proprietario)
                            if (isInputEmpty) {
                                Text(
                                    text = "Campos necessários",
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
//                            Button(
//                                onClick = {
//                                    if(endereco.isNotEmpty() && matricula.isNotEmpty()){
//                                        val newImovel = Imovel(matricula, endereco, valorAluguel.toFloat())
//                                        proprietarioDAO.inserir(newImovel)
//                                    }else{
//                                        isInputEmpty = true
//                                    }
//                                },
//                                Modifier
//                                    .padding(
//                                        top = 8.dp,
//                                        start = 16.dp,
//                                        end = 16.dp,
//                                    )
//                                    .fillMaxWidth(),
//                            ) {
//                                Text(
//                                    text = "Inserir",
//                                    color = MaterialTheme.colorScheme.onPrimary
//                                )
//                            }
                        }
                    }
                }
            }
        }
    }
}