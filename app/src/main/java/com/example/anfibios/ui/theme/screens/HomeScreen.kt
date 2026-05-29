package com.example.anfibios.ui.theme.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse), label = "alpha"
    )

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Explorando la selva...", modifier = Modifier.padding(16.dp))
        Surface(
            modifier = Modifier.size(60.dp).padding(8.dp),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.primary.copy(alpha = alpha)
        ) {}
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
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + expandVertically()
    ) {
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

}
@Composable
fun TarjetaAnfibio(anfibio: Anfibios, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.extraLarge,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            Text(
                text = anfibio.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(anfibio.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = anfibio.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f) // Relación de aspecto más panorámica
            )
            Text(
                text = anfibio.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}