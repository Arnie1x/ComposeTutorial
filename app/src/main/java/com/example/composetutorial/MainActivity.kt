package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class Message(
    var id: Int?,
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
                        MessageList(messages = messageList, user_id = 1)
                    }
                }
            }
        }
    }
}

var messageList: List<Message> = listOf(
    Message(id = 1, author = "John Doe", message = "Lorem Ipsum"),
    Message(id = 1, author = "John Doe", message = "Lorem Ipsum Ipsum"),
    Message(id = 1, author = "John Doe", message = "Lorem Ipsum"),
    Message(
        id = 1,
        author = "John Doe",
        message = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"
    ),
    Message(id = 2, author = "Jane Doe", message = "Lorem Ipsum"),
    Message(id = 2, author = "Jane Doe", message = "Lorem Ipsum"),
    Message(
        id = 2,
        author = "Jane Doe",
        message = "Lorem Ipsum Ipsum Ipsum Ipsum Ipsum Ipsum Ipsum Ipsum"
    ),
    Message(
        id = 1,
        author = "John Doe",
        message = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"
    ),
    Message(id = 2, author = "Jane Doe", message = "Lorem Ipsum"),
    Message(id = 1, author = "John Doe", message = "Lorem Ipsum"),
    Message(id = 2, author = "Jane Doe", message = "Lorem Ipsum"),
    Message(
        id = 1,
        author = "John Doe",
        message = "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"
    ),
    Message(id = 2, author = "Jane Doe", message = "Lorem Ipsum"),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageList(messages: List<Message>, user_id: Int) {
    ComposeTutorialTheme {
        Surface {
//            Box(
//                Modifier
//                    .fillMaxWidth()
//                    .height(100.dp)
//            ) {
//                Text(
//                    text = "Messages",
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.primary,
//                    fontSize = 24.sp)
//            }
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                            )
                        }
                    }
                }
                items(messages) { message ->
                    MessageCard(message = message, user_id = user_id)
                }
            }
        }
    }
}

@Composable
fun MessageCard(message: Message, user_id: Int) {
    ComposeTutorialTheme {
        Surface() {
            Box(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = if (message.id != user_id) Arrangement.Start else Arrangement.End,
                ) {
                    // Add a horizontal space between the image and the column
                    var isExpanded by remember { mutableStateOf(false) }
                    val userTextBubbleColor by animateColorAsState(
                        if (!isExpanded) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                        label = "",
                    )
                    val textBubbleColor by animateColorAsState(
                        if (!isExpanded) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                        label = "",
                    )
                    if (message.id != user_id) {
                        Image(
                            painter = painterResource(R.drawable.pic1),
                            contentDescription = "Contact profile picture",
                            modifier = Modifier
                                // Set image size to 40 dp
                                .size(46.dp)
                                // Clip image to be shaped as a circle
                                .clip(CircleShape)
                                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Surface(
                        color = if (message.id == user_id) userTextBubbleColor else textBubbleColor,
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .widthIn(min = 50.dp, max = 300.dp)
                            .clickable { isExpanded = !isExpanded }
                    ) {
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = if (message.id != user_id) Alignment.Start else Alignment.End,
                            ) {
                                Text(
                                    text = message.author,
                                    fontWeight = FontWeight.Bold,
                                    color = if (message.id == user_id) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onTertiaryContainer,
                                    fontSize = 10.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = message.message,
                                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }

                    if (message.id == user_id) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(R.drawable.pic2),
                            contentDescription = "Contact profile picture",
                            modifier = Modifier
                                // Set image size to 40 dp
                                .size(46.dp)
                                // Clip image to be shaped as a circle
                                .clip(CircleShape)
                                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        )
                    }

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