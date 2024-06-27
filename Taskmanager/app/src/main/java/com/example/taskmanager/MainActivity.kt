package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                Congratulation(
                    taskText = stringResource(id = R.string.task_completed_text),
                    niceWorkText = stringResource(id = R.string.nice_work_text),
                )
            }
        }
    }
}

@Composable
fun Congratulation(taskText: String, niceWorkText: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.ic_task_completed)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = "Task completed",
            modifier = modifier.align(Alignment.CenterHorizontally),
        )
        Text(
            text = taskText,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(
                    top = 24.dp,
                    bottom = 8.dp
                )
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = niceWorkText,
            fontSize = 16.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TaskManagerTheme {
        Congratulation(
            taskText = stringResource(id = R.string.task_completed_text),
            niceWorkText = stringResource(id = R.string.nice_work_text),
        )
    }
}