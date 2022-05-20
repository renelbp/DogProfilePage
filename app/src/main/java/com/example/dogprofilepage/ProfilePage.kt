package com.example.dogprofilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Composable
fun ProfilePage() {
    Card(elevation = 6.dp, modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
        .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))) {
        BoxWithConstraints() {
            val constraints = if (minWidth < 600.dp) {
                portraitConstraints(margin = 16.dp)
            } else {

                landscapeConstraints(margin = 16.dp)
            }
            ConstraintLayout(constraints) {

                /**Creating the references to the composables that are gonna be used in
                 *  the constraintLayout*/

                Image(
                    painter = painterResource(id = R.drawable.husky), "husky",
                    Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                        .layoutId("image"),
                    contentScale = ContentScale.Crop)

                Text(text = "Siberian Husky", fontWeight = FontWeight.Bold,
                    modifier = Modifier.layoutId("textName"))

                Text(text = "Rene", modifier = Modifier.layoutId("nickNameText"))

                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .layoutId("rowStats")
                ) {
                    ProfileStats(count = "150", title = "Followers")
                    ProfileStats(count = "100", title = "Following")
                    ProfileStats(count = "30", title = "Posts")
                }

                Button(onClick = { /*TODO*/ }, Modifier.layoutId("btnFollow")) {
                    Text(text = "Follow User")
                }
                Button(onClick = { /*TODO*/ }, Modifier.layoutId("btnMessage")) {
                    Text(text = "Direct Message")
                }
            }
        }

    }
}

private fun portraitConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val textName = createRefFor("textName")
        val nickNameText = createRefFor("nickNameText")
        val rowStats = createRefFor("rowStats")
        val btnFollow = createRefFor("btnFollow")
        val btnMessage = createRefFor("btnMessage")
        val guideLine = createGuidelineFromTop(0.3f)

        constrain(image) {
            top.linkTo(guideLine)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(textName) {
            top.linkTo(image.bottom)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }
        constrain(nickNameText) {
            top.linkTo(textName.bottom)
            start.linkTo(textName.start)
            end.linkTo(textName.end)
        }
        constrain(rowStats) {
            top.linkTo(nickNameText.bottom)
        }
        constrain(btnFollow) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(btnMessage.start)
            width = Dimension.wrapContent
        }
        constrain(btnMessage) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(btnFollow.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
    }
}

private fun landscapeConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val textName = createRefFor("textName")
        val nickNameText = createRefFor("nickNameText")
        val rowStats = createRefFor("rowStats")
        val btnFollow = createRefFor("btnFollow")
        val btnMessage = createRefFor("btnMessage")
        val guideLine = createGuidelineFromTop(0.3f)
        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(textName) {
            start.linkTo(image.start)
            top.linkTo(image.bottom)
        }
        constrain(nickNameText) {
            top.linkTo(textName.bottom)
            start.linkTo(textName.start)
            end.linkTo(textName.end)
        }
        constrain(rowStats) {
            top.linkTo(image.top)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end)
        }
        constrain(btnFollow) {
            top.linkTo(rowStats.bottom, margin = 16.dp)
            start.linkTo(rowStats.start)
            end.linkTo(btnMessage.start)
            bottom.linkTo(btnMessage.bottom)
            width = Dimension.wrapContent
        }
        constrain(btnMessage) {
            top.linkTo(rowStats.bottom, margin = 16.dp)
            start.linkTo(btnFollow.end)
            end.linkTo(parent.end)
            bottom.linkTo(btnFollow.bottom)
            width = Dimension.wrapContent
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