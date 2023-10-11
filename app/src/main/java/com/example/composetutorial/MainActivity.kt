package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class Message(
    var author: String,
    var message: String
) {}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        MessageList(messages = messageList)
                    }
                }
            }
        }
    }
}

var messageList: List<Message> = listOf(
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
    Message(author = "John Doe", message = "Lorem Ipsum"),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageList(messages: List<Message>) {
    ComposeTutorialTheme {
        Surface {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                stickyHeader {
                    Surface(
                        modifier = Modifier
                            .height(50.dp)
                            .shadow(elevation = 5.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Messages",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
                items(messages) { message ->
                    MessageCard(message = message)
                }
            }
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    ComposeTutorialTheme {
        Surface {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        // Set image size to 40 dp
                        .size(46.dp)
                        // Clip image to be shaped as a circle
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )

                // Add a horizontal space between the image and the column
                Spacer(modifier = Modifier.width(8.dp))
                var isExpanded by remember { mutableStateOf(false) }
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.clickable { isExpanded = !isExpanded }
                ) {
                    Text(text = message.author)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = message.message, maxLines = if (isExpanded) Int.MAX_VALUE else 1)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorialTheme {
        Greeting("Android")
    }
}