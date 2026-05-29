package com.example.anfibios.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.anfibios.model.Anfibios


@Composable
fun HomeScreen(
    anfibiosUiState: AnfibiosUiState,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
    ){
    when (anfibiosUiState){
        is AnfibiosUiState.Loading -> PantallaCarga(modifier = modifier.fillMaxSize())
        is AnfibiosUiState.Error -> PantallaError(modifier = modifier.fillMaxSize())
        is AnfibiosUiState.Success -> ListaAnfibios(
            anfibios = anfibiosUiState.infoAnfibios,
            contentPadding = contentPadding,
            modifier = modifier.fillMaxSize()
        )

    }

    }

@Composable
fun PantallaCarga(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cargando Anfibios...", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun PantallaError(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error al cargar los datos", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun ListaAnfibios(
    anfibios : List<Anfibios>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
    ){
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = anfibios, key = { anfibio -> anfibio.name }) { anfibio ->
            TarjetaAnfibio(anfibio = anfibio, modifier = Modifier.padding(horizontal = 16.dp))
        }
    }

}
@Composable
fun TarjetaAnfibio(anfibio: Anfibios, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = anfibio.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = anfibio.type,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = anfibio.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(anfibio.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = anfibio.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(240.dp)
            )
        }
    }
}