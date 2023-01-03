package com.example.musicbutton

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.musicbutton.ui.theme.MusicButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicButtonTheme(darkTheme = false) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MusicButton()
                }
            }
        }
    }
}

private const val TAG = "FULIX"
private const val pausedIcon = 1
private const val playingIcon = 2

@Composable
fun MusicButton() {
    var buttonIcon by remember { mutableStateOf(pausedIcon) }

    val mContext = LocalContext.current
    val mediaPlayer = MediaPlayer.create(mContext, R.raw.audio)

    FloatingActionButton(
        modifier = Modifier.size(80.dp),
        shape = CircleShape,
        backgroundColor = Color.Red,
        onClick = {
            if (buttonIcon == pausedIcon) {
                Log.i(TAG, "start")
                mediaPlayer.start()
                buttonIcon = playingIcon
            } else if (buttonIcon == playingIcon) {
                Log.i(TAG, "stop")
                mediaPlayer.pause()
                buttonIcon = pausedIcon
            }
        }
    ) {
        when (buttonIcon) {
            pausedIcon -> {
                Icon(
                    Icons.Filled.PlayArrow,
                    modifier = Modifier.size(40.dp),
                    contentDescription = "Play Song",
                    tint = Color.White
                )
            }
            playingIcon -> {
                Icon(
                    Icons.Filled.Pause,
                    modifier = Modifier.size(40.dp),
                    contentDescription = "Pause Song",
                    tint = Color.White)
            }
        }
    }
}
