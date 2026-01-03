package com.example.caproject

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.caproject.ui.theme.CAProjectTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class ThemesEx : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            ThemesSwitcherEx()
            InternetPermissionEx()
        }
    }
}

@Composable
fun InternetPermissionEx(){
    var result by remember { mutableStateOf("loading IP...") }
    LaunchedEffect(Unit) {
        result  = withContext(Dispatchers.IO){
            URL("https://api.ipify.org").readText()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(("your IP Address...\n: $result"))
    }
}

@Composable
fun ThemesSwitcherEx(){
    val isdarkThemes = isSystemInDarkTheme()
    CAProjectTheme(isdarkThemes) {
        Scaffold() { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Card(
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.padding(16.dp)


                ){
                    Text(
                        text = if(isdarkThemes) "Dark Mode Background" else "Light Mode Background",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}



@Composable
fun AppNavS(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
             HomeScreenS(navController)
        }

        composable(route = "detailsScreen/{courseName}/{courseCode}/{courseDuration}"){

            backStackEntry ->
                val courseName = backStackEntry.arguments?.getString("courseName")
                val courseCode = backStackEntry.arguments?.getString("courseCode")
                val courseDuration = backStackEntry.arguments?.getString("courseDuration")
            detailsScreen(courseName,courseCode,courseDuration,navController)
        }
    }
}

@Composable
fun HomeScreenS(navHostController: NavHostController){
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        var courseName by remember { mutableStateOf("") }
        var courseCode by remember { mutableStateOf("") }
        var courseDuration by remember { mutableStateOf("") }
        var ischecked by remember { mutableStateOf(false) }

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ){
            innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = courseName,
                    onValueChange = {courseName = it},
                    label = { Text("Course Name") },
                    placeholder = {Text("Enter Your Course Name")}
                )

                OutlinedTextField(
                    value = courseCode,
                    onValueChange = {courseCode = it},
                    label = { Text("Course Code") },
                    placeholder = {Text("Enter Your Course Code")}
                )

                OutlinedTextField(
                    value = courseDuration,
                    onValueChange = {
                        newValue ->
                        // Filter the input to only allow digits (0-9)
                        val filteredValue = newValue.filter { it.isDigit() }
                        courseDuration = filteredValue
                                    },
                    label = { Text("Course Duration") },
                    placeholder = {Text("Enter Your Course Duration in weeks")
                    }
                )

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(10.dp)
                ){
                    Checkbox(
                        checked = ischecked,
                        onCheckedChange = {ischecked = it}
                    )
                    Text("Accept the terms and conditions of course guidlines")

                }

                Button(onClick = {
                    var courseDurationInt = courseDuration.toIntOrNull() ?: 0
                    if(courseName.isEmpty() || courseCode.isEmpty() || courseDuration.isEmpty()  || !ischecked  ){
                        if(courseDurationInt <= 0){
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Course Duration (in week should more than 0)"
                            )
                        }}else{
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Please fill all the details"
                                )}
                        }

                    } else{

                        navHostController.navigate("detailsScreen/$courseName/$courseCode/$courseDuration")


                        }

                }) {
                    Text("Enroll")
                }
            }



        }
}

@Composable
fun detailsScreen(courseName: String?, courseCode: String?, courseDuration: String?, navHostController: NavHostController){

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Course Details:\n" +
                "Course Name: $courseName\n" +
                "Course Code: $courseCode\n" +
                "Course Duration: $courseDuration")
        Button(onClick = {
            navHostController.popBackStack()
        }) {
            Text("Go to Home")
        }
    }
}