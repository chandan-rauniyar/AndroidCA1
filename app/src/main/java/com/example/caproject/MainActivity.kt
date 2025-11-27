package com.example.caproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caproject.ui.theme.CAProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WorkShop()
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun WorkShop(){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {

        Text(
            text = "Android Workshop",
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(R.drawable.myimg),
            contentDescription = " ",
            modifier = Modifier.size(100.dp).clip(CircleShape),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Text(
            text = "Your Name",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Left
        )
        val name = remember { mutableStateOf("Chandan Kumar Gupta") }

//        TextField( // we can also used TextField
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier
                .fillMaxWidth().padding(5.dp),
            shape =  RoundedCornerShape(12.dp),

                       placeholder = {Text(
                text = "Enter Your Name" )}
        )

        Text(
            text = "Registration Number",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Left
        )
        val regNo = remember { mutableStateOf("12314959") }

//        TextField( // we can also used TextField
        OutlinedTextField(
            value = regNo.value,
            onValueChange = { regNo.value = it },
            modifier = Modifier
                .fillMaxWidth().padding(5.dp),
            shape =  RoundedCornerShape(12.dp),

            placeholder = {Text(
                text = "Enter Your Registration Number")}
        )
        Text(
            text = "Enter Workshop Level",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Left
        )
        val workshopLevel = remember { mutableStateOf("Beginner/Intermediate/Advanced") }

//        TextField( // we can also used TextField
        OutlinedTextField(
            value = workshopLevel.value,
            onValueChange = { workshopLevel.value = it },
            modifier = Modifier
                .fillMaxWidth().padding(5.dp),
            shape =  RoundedCornerShape(12.dp),

            placeholder = {Text(
                text = "Beginner/Intermediate/Advanced")}
        )


        Text(
            text = "Enter Workshop Date",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Left
        )

        val workshopDate = remember { mutableStateOf(" ") }

//        TextField( // we can also used TextField
        OutlinedTextField(
            value = workshopDate.value,
            onValueChange = { workshopDate.value = it },
            modifier = Modifier
                .fillMaxWidth().padding(5.dp),
            shape =  RoundedCornerShape(12.dp),


        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Enroll Now")
        }

    }

}