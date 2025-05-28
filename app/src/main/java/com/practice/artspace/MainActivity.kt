package com.practice.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()

                ) {
                    ArtApp()
                }
            }
        }
    }
}

@Composable
fun ArtApp(modifier: Modifier = Modifier){

    var currentIndex by remember { mutableIntStateOf(1) }

    val currentImage = when(currentIndex){
        1 -> R.drawable.dree_y
        2-> R.drawable.cyril_mzn_2019
        else-> R.drawable.kedar_gadge_2022
    }
    val title = when (currentIndex) {
        1 -> R.string.african_renaissance
        2 -> R.string.eiffel_tower
        else -> R.string.space_needle
    }

    val artist = when (currentIndex) {
        1 -> R.string.dree_y_2023
        2 -> R.string.cyril_mzn_2019
        else -> R.string.kedar_gadge_2022
    }

   Column(
       verticalArrangement = Arrangement.SpaceEvenly,
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = modifier.padding(20.dp)
   ) {
       Column(
           verticalArrangement = Arrangement.Center,
           modifier = Modifier
       ){
           ArtImage(image = currentImage)
       }
       Column(
           horizontalAlignment = Alignment.CenterHorizontally,
       ){
           ArtDetail(title = title, artist = artist)
           Spacer(modifier = Modifier.height(20.dp))
           ArtButtons(
               currentIndex = currentIndex,
               onChangeIndex = { currentIndex = it }
           )
       }

   }
}

@Composable
fun ArtButtons(
    currentIndex: Int,
    onChangeIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()) {
        ArtButton(
            btnText = "Previous",
            onClick = {
                if(currentIndex > 1){
                   onChangeIndex(currentIndex - 1)
                }
            }
        )
        ArtButton(
            btnText = "Next",
            onClick = {
                if(currentIndex < 3){
                    onChangeIndex(currentIndex + 1)
                }
            }
        )
    }
}

@Composable
fun ArtImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
){
   Surface(
       shadowElevation = 30.dp,
       modifier = modifier
   ) {
       Box(modifier = Modifier
           .padding(30.dp)
           .size(350.dp)){
           Image(
               painter = painterResource(id = image),
               contentDescription = null,
               contentScale = ContentScale.Crop
           )
       }
   }
}
@Composable
fun ArtDetail(
    @StringRes title: Int,
    @StringRes artist: Int,
    modifier: Modifier = Modifier
){
      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = modifier
              .background(Color.LightGray)
              .padding(20.dp)

      ) {
          Text(
              text = stringResource(id = title),
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
          )
          Text(text = stringResource(id = artist))
      }
  }


@Composable
fun ArtButton(
    btnText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        shape = RectangleShape ,
        modifier = modifier.width(115.dp)
    ) {
        Text(text = btnText)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtApp(modifier = Modifier
            .fillMaxSize())
    }
}