package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                LemonadeAppPreview()
            }
        }
    }
}


@Composable
fun CreateHeader(modifier: Modifier = Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
    }
}

@Composable
fun LemonadeTree(modifier: Modifier = Modifier) {
    var tapCount by remember {
        mutableStateOf(0)
    }

    var randomSqueeze by remember {
        mutableStateOf((2..4).random())
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (tapCount >= randomSqueeze+2) {
                    tapCount = 0
                    randomSqueeze = (2..4).random()
                }else
                    tapCount++
            },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.teal_700))
        ) {
            Image(
                if (tapCount < 1) {
                    painterResource(id = R.drawable.lemon_tree)
                } else if (tapCount in 1..randomSqueeze) {
                    painterResource(id = R.drawable.lemon_squeeze)
                }else if (tapCount<=randomSqueeze+1){
                    painterResource(id = R.drawable.lemon_drink)
                } else {
                    painterResource(id = R.drawable.lemon_restart)
                },
                contentDescription = "lemon tree"
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            if (tapCount < 1) {
                stringResource(id = R.string.first_tap_text)
            } else if (tapCount in 1..randomSqueeze) {
                stringResource(id = R.string.lemon_tap_text)
            }else if (tapCount<=randomSqueeze+1){
                stringResource(id = R.string.lemonade_tap_text)
            } else {
                stringResource(id = R.string.lemonade_glass_text)
            },
            fontSize = 18.sp

        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeAppTheme {
        CreateHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(colorResource(id = R.color.yellow))
        )
        LemonadeTree(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}