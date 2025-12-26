package com.example.caproject

import android.R.attr.fontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caproject.ui.theme.CAProjectTheme
import kotlinx.coroutines.launch

class CA2_Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            eventSighupForm()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun eventSighupForm(){
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = { Text("Name") },
            placeholder = { Text("Enter Your Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email") },
            placeholder = { Text("Enter Your Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        OutlinedTextField(
            value = phone,
            onValueChange = {phone = it},
            label = { Text("Phone") },
            placeholder = { Text("Enter Your Phone") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Checkbox(
                checked = isChecked,
                onCheckedChange = {isChecked=it}

            )

            Text("confirm that the signup details are correct. ",
                modifier = Modifier.padding(start = 10.dp,top = 16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        Button(onClick = {
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || !isChecked){
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message  = "Please fill all the details" )
                }
            }
            else{
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Signup Successful"
                    )
                }
            }
        },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ){
            Text("Signup")

        }
        Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) {innerPadding -> }



    }
}