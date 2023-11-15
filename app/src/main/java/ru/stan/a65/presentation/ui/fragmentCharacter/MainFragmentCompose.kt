package ru.stan.a65.presentation.ui.fragmentCharacter

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import ru.stan.a65.R
import ru.stan.a65.domain.model.CharacterItem

@Composable
fun MainFragmentCompose(viewModel: MainViewModel) {
    val character by viewModel.character.collectAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.hp_2),
            contentDescription = stringResource(R.string.background_image),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        MainColomn(character, viewModel)
    }

}

@Composable
private fun MainColomn(
    character: CharacterItem,
    viewModel: MainViewModel
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        SubcomposeAsyncImage(
            model = character.imageUrl,
            contentDescription = stringResource(R.string.Image_Of_character),
            loading = {
                CircularProgressIndicator(color = Color.Blue, strokeWidth = 8.dp)
            },
            modifier = Modifier
                .size(200.dp, 250.dp)
                .border(3.dp, color = Color.Blue),
            contentScale = ContentScale.FillBounds
        )

        Text(
            character.name,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(character.hogwartsHouse, color = Color.White, fontSize = 25.sp)
        OutlinedButton(onClick = { viewModel.randomCharacter() }) {
            Text(text = stringResource(R.string.button_text), color = Color.White, fontSize = 25.sp)
        }
    }
}