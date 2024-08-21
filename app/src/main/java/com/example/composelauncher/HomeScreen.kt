package com.example.composelauncher

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.Text

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        FeaturedSection()
        MovieCategorySection(category = "Action", movies = getMovieList())
        MovieCategorySection(category = "Action", movies = getMovieList())
    }
}

@Composable
fun FeaturedSection(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart

    ) {
        Button(onClick = {},
            modifier
                .width(200.dp)
                .height(60.dp),
            ){
            Text(text = "Test")
        }
        Image(
            painter = painterResource(id = R.drawable.featured_movie1),
            contentDescription = null,
            modifier
                .fillMaxWidth()
                .height(400.dp)
                // Workaround to enable alpha compositing
                .graphicsLayer { alpha = 0.95f }
                .drawWithContent {
                    val colors = listOf(
                        Color.Black,
                        Color.Transparent
                    )
                    drawContent()
                    drawRect(
                        brush = Brush.horizontalGradient(colors),
                        blendMode = BlendMode.DstOut
                    )
                },
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier
            .padding(48.dp)
        ) {
            Text(
                text = "House of Cards",
                color = Color.White, fontSize = 32.sp,
                fontWeight = FontWeight.Bold,

            )
            Text(
                text = "2013 · TV-MA · 1 Season",
                color = Color.White
            )
            Text(
                text = "Sharks gliding ominously beneath the surface...",
                color = Color.White.copy(alpha = 0.7f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun MovieCategorySection(
    modifier: Modifier = Modifier, category: String, movies : List<Movie>
) {
    val context = LocalContext.current
    Column(modifier
        .padding(16.dp)
    ) {
        Text(text = category, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow{
            items(movies) { movie ->
                MovieItem(movie, onMovieClick = {Toast.makeText(context, "Halo", Toast.LENGTH_SHORT).show()})
            }
        }
    }

}

@Composable
fun MovieItem(movie: Movie, onMovieClick: () -> Unit) {

    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .size(150.dp, 200.dp)
            .padding(end = 8.dp)
            .focusable()
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused // Track whether the item is focused
            }
            .onKeyEvent { event ->
                if (event.key == Key.Enter || event.key == Key.DirectionCenter) {
                    onMovieClick() // Navigasi ke halaman detail film saat Enter ditekan
                    true
                } else {
                    false
                }
            }
            .border(
                width = if (isFocused) 4.dp else 0.dp, // Add border when focused
                color = if (isFocused) Color.Red else Color.Transparent, // Red color for the border
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Image(
            painter = painterResource(id = movie.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
        )
        Text(text = movie.title, color = Color.White, fontSize = 12.sp)
    }
}


@Preview(
    widthDp = 962,
    heightDp = 720
)
@Composable
private fun TvPreview() {
    HomeScreen()
}