package com.example.dogprofilepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.dogprofilepage.ui.theme.DogProfilePageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogProfilePageTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    ProfilePage()
                }
            }
        }
    }

    @Composable
    fun ProfilePage() {
        Card(
            elevation = 6.dp, modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)

        ) {
            ConstraintLayout {

                /**Creating the references to the composables that are gonna be used in
                 *  the constraintLayout*/
                val (
                    image,
                    textName,
                    nikNameText,
                    rowStats,
                    btnFollow,
                    btnDirectMessage,
                ) = createRefs()

                val guideLine = createGuidelineFromTop(0.2F)

                Image(
                    painter = painterResource(id = R.drawable.husky),
                    "husky",
                    Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                        .constrainAs(image) {
                            top.linkTo(guideLine)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Siberian Husky",
                    modifier = Modifier.constrainAs(textName) {
                        top.linkTo(image.bottom)
                        start.linkTo(image.start)
                        end.linkTo(image.end)
                    })
                Text(
                    text = "Rene",
                    modifier = Modifier.constrainAs(nikNameText) {
                        top.linkTo(textName.bottom)
                        start.linkTo(textName.start)
                        end.linkTo(textName.end)
                    })

                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(rowStats) {
                            top.linkTo(nikNameText.bottom)
                        }
                ) {
                    ProfileStats(count = "150", title = "Followers")
                    ProfileStats(count = "100", title = "Following")
                    ProfileStats(count = "30", title = "Posts")
                }

                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnFollow){
                        top.linkTo(rowStats.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(btnDirectMessage.start)
                        width = Dimension.wrapContent
                    }
                ) {
                    Text(text = "Follow User")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnDirectMessage){
                        top.linkTo(rowStats.bottom)
                        start.linkTo(btnFollow.end)
                        end.linkTo(parent.end)
                        width = Dimension.wrapContent

                    }
                )
                {
                    Text(text = "Direct Message")
                }


            }

        }
    }

    @Composable
    fun ProfileStats(count: String, title: String) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = count, fontWeight = FontWeight.Bold)
            Text(text = title)

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ProfilePagePreview() {
        ProfilePage()
    }
}

