package br.com.ads.imobiliaria.ui.theme.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.ads.imobiliaria.banco.dao.comandoSalvar

@Composable
fun BotaoSalvar() {
    Button(onClick = {
        comandoSalvar("sqlite3 my_database.db \".output my_data.txt\"; \".dump\"")
    },
    Modifier
        .padding(
            top = 8.dp,
            start = 16.dp,
            end = 16.dp,
        )
        .fillMaxWidth()) {
        Text("Save SQLite to TXT")
    }
}
