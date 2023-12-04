package br.com.ads.imobiliaria.ui.theme.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Texto(text : String){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .width(34.dp),
        textAlign = TextAlign.Center,
        text = text,
        color = MaterialTheme.colorScheme.onTertiary
    )
}