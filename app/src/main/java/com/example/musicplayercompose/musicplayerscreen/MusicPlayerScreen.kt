package com.example.musicplayercompose.musicplayerscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayercompose.R
import com.example.musicplayercompose.ui.theme.MusicPlayerComposeTheme

@Composable
fun MusicPlayerScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Now Playing...", textAlign = TextAlign.Center)
        Spacer(Modifier.size(16.dp))
        Image(
            painterResource(id = R.drawable.ic_launcher_background),
            null,
            Modifier.size(280.dp)
            )
        PlayerSlider()
        MediaControls()
    }
}

@Composable
fun MediaControls() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = null)
        }
        Spacer(Modifier.size(48.dp))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(64.dp)
                .heightIn(64.dp)
                .widthIn(64.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }
        Spacer(Modifier.size(48.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
fun PlayerSlider() {
    var sliderPosition by remember { mutableStateOf(0f) }

    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        valueRange = 0f..100f,
        onValueChangeFinished = {

        },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.secondary,
            activeTrackColor = MaterialTheme.colors.secondary
        ),
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PlayerSliderPreview() {
    MusicPlayerComposeTheme {
        PlayerSlider()
    }
}

@Preview(showBackground = true)
@Composable
fun MusicPlayerScreenPreview() {
    MusicPlayerComposeTheme {
        MusicPlayerScreen()
    }
}