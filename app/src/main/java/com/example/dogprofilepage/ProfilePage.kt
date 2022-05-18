package com.example.dogprofilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfilePage(){
    Column {
        Image(painter = painterResource(id = R.drawable.husky),
            "husky" )
        
        Text(text = "Siberian Husky")
        Text(text = "Rene")
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview(){
    ProfilePage() 
}