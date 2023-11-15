package ru.stan.a65.presentation.ui.fragmentCharacterList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import ru.stan.a65.R
import ru.stan.a65.domain.model.CharacterItem
import ru.stan.a65.domain.model.CharacterPagingItem
import java.util.Random

@Composable
fun CharacterItemCompose(
    characterItem: CharacterItem? = null,
    characterPagingItem: CharacterPagingItem? = null
) {
    if (characterItem != null) {
        CharacterCard(characterItem)
    } else {
        val characterMapper = CharacterItem(
            id= Random().nextInt(),
            name = characterPagingItem?.name ?: "N/D",
            hogwartsHouse = characterPagingItem?.hogwartsHouse ?: "N/D",
            imageUrl = characterPagingItem?.imageUrl ?: "N/D"
        )
        CharacterCard(characterMapper)
    }


}

@Composable
private fun CharacterCard(characterItem: CharacterItem?) {
    Card(
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.background_card)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(4.dp))
            SubcomposeAsyncImage(
                model = if (characterItem!!.imageUrl != "N/D") characterItem.imageUrl else R.drawable.empty_face,
                contentDescription = "character image",
                loading = { CircularProgressIndicator() },
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(100)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(32.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(characterItem.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(characterItem.hogwartsHouse, fontSize = 15.sp)
            }
        }
    }
}
