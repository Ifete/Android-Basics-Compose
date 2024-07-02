package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var imageShown by remember { mutableStateOf(R.drawable.photo_1) }
        var description by remember { mutableStateOf(R.string.photo_1_description) }
        var author by remember { mutableStateOf(R.string.photo_1_author) }
        var yearImage by remember { mutableStateOf(R.string.photo_1_year) }

        ImageLayout(photo = imageShown, modifier = modifier)
        DescrptionLayout(description = description, author = author, yearImage = yearImage)
        ButtonLayout(
            onClickedPrevChanged = {
                imageShown = PreviousImage(imageShown)
                description = PreviousDescription(description)
                author = PreviousAuthor(author)
                yearImage = PreviousYear(yearImage)
            },
            onClickedNextChanged = {
                imageShown = NextImage(imageShown)
                description = NextDescription(description)
                author = NextAuthor(author)
                yearImage = NextYear(yearImage)
            }
        )
    }
}


@Composable
fun ImageLayout(
    @DrawableRes photo: Int,
    modifier: Modifier = Modifier
) {
    val borderWidth = 14.dp
    Image(
        painter = painterResource(id = photo),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(300.dp)
            .height(450.dp)
            .border(
                width = borderWidth,
                color = colorResource(id = R.color.white_border)
            )
            .shadow(elevation = 30.dp)
    )
}

@Composable
fun DescrptionLayout(
    @StringRes description: Int,
    @StringRes author: Int,
    @StringRes yearImage: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .padding(top = 50.dp)
            .size(250.dp, 100.dp)
            .background(color = colorResource(id = R.color.purple_bk))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = description),
                fontSize = 20.sp,
            )
            Row {
                Text(
                    text = stringResource(id = author),
                    fontWeight = FontWeight.Bold,
                )
                Text(text = " " + stringResource(id = yearImage))
            }
        }
    }
}

@Composable
fun ButtonLayout(
    onClickedPrevChanged: () -> Unit,
    onClickedNextChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier.padding(top = 25.dp)) {
        Button(
            onClick = onClickedPrevChanged,
            modifier = Modifier
                .padding(end = 10.dp)
                .weight(1f)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick =
            onClickedNextChanged,
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        ) {
            Text(text = "Next")
        }

    }
}

fun NextImage(
    @DrawableRes currentImage: Int,
): Int {
    var newImage: Int = 0
    if (R.drawable.photo_1 == currentImage) newImage = R.drawable.photo_2
    else if (R.drawable.photo_2 == currentImage) newImage = R.drawable.photo_3
    else if (R.drawable.photo_3 == currentImage) newImage = R.drawable.photo_1
    else throw IllegalArgumentException("Unknown image")
    return newImage
}

fun PreviousImage(
    @DrawableRes currentImage: Int,
): Int {
    var newImage: Int = 0
    if (R.drawable.photo_1 == currentImage) newImage = R.drawable.photo_3
    else if (R.drawable.photo_2 == currentImage) newImage = R.drawable.photo_1
    else if (R.drawable.photo_3 == currentImage) newImage = R.drawable.photo_2
    else throw IllegalArgumentException("Unknown image")
    return newImage
}

fun NextDescription(
    @StringRes currentDesc: Int,
): Int {
    var newDesc: Int = 0
    when (currentDesc) {
        R.string.photo_1_description -> newDesc = R.string.photo_2_description
        R.string.photo_2_description -> newDesc = R.string.photo_3_description
        R.string.photo_3_description -> newDesc = R.string.photo_1_description
    }
    return newDesc
}

fun PreviousDescription(
    @StringRes currentDesc: Int,
): Int {
    var newDesc: Int = 0
    when (currentDesc) {
        R.string.photo_1_description -> newDesc = R.string.photo_3_description
        R.string.photo_2_description -> newDesc = R.string.photo_1_description
        R.string.photo_3_description -> newDesc = R.string.photo_2_description
    }
    return newDesc
}

fun NextAuthor(
    @StringRes currentAuthor: Int,
): Int {
    var newAuthor: Int = 0
    when (currentAuthor) {
        R.string.photo_1_author -> newAuthor = R.string.photo_2_author
        R.string.photo_2_author -> newAuthor = R.string.photo_3_author
        R.string.photo_3_author -> newAuthor = R.string.photo_1_author
    }
    return newAuthor
}

fun PreviousAuthor(
    @StringRes currentAuthor: Int,
): Int {
    var newAuthor: Int = 0
    when (currentAuthor) {
        R.string.photo_1_author -> newAuthor = R.string.photo_3_author
        R.string.photo_2_author -> newAuthor = R.string.photo_1_author
        R.string.photo_3_author -> newAuthor = R.string.photo_2_author
    }
    return newAuthor
}

fun NextYear(
    @StringRes currentYear: Int,
): Int {
    var newYear: Int = 0
    when (currentYear) {
        R.string.photo_1_year -> newYear = R.string.photo_2_year
        R.string.photo_2_year -> newYear = R.string.photo_3_year
        R.string.photo_3_year -> newYear = R.string.photo_1_year
    }
    return newYear
}

fun PreviousYear(
    @StringRes currentYear: Int,
): Int {
    var newYear: Int = 0
    when (currentYear) {
        R.string.photo_1_year -> newYear = R.string.photo_3_year
        R.string.photo_2_year -> newYear = R.string.photo_1_year
        R.string.photo_3_year -> newYear = R.string.photo_2_year
    }
    return newYear
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}