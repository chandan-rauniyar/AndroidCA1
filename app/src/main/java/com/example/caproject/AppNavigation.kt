package com.example.caproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

class AppNavigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CAProjectTheme {
                AppNavigationEx()
            }
        }
    }
}

//NavHost, NavController with data Pass
@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun AppNavigationEx(){
    val  navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable(
            route = "Second/{name}/{email}"
        )


        {
//            SecondScreen(navController)
            backStackEntry ->
            val inpName =  backStackEntry.arguments?.getString("name")
            val intEmail = backStackEntry.arguments?.getString("email")
            SecondScreen(inpName,intEmail,navController)
        }

    }

}



@Composable
fun HomeScreen(navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        Text("This is Home Screen")
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name ,
            onValueChange = {name = it},
            label = {Text("Enter Name")}
        )
        OutlinedTextField(
            value = email ,
            onValueChange = {email = it},
            label = {Text("Enter Email")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navHostController.navigate("Second/$name/$email")
            }
        ) {
            Text("Go to Second Screen")
        }
    }
}

@Composable
fun SecondScreen(name: String?, email: String?, navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Your Details:\n$name \n$email")

        Button(
            onClick = {
//                navHostController.navigate("home")
                navHostController.popBackStack()
            }
        ) {
            Text("Go to Home")
        }
    }
}



/*

//NavHost, NavController
@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun AppNavigationEx(){
    val  navController = rememberNavController()

    NavHost(
            navController = navController,
            startDestination = "home"
    ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("Second") {
                SecondScreen(navController)
            }
    }

}



@Composable
fun HomeScreen(navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
            ) {

        Text("This is Home Screen")

        Button(
            onClick = {
                navHostController.navigate("Second")
            }
        ) {
            Text("Go to Second Screen")
        }
    }
}

@Composable
fun SecondScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("This is Second Screen")

        Button(
            onClick = {
//                navHostController.navigate("home")
                navHostController.popBackStack()
            }
        ) {
            Text("Go to Home")
        }
    }
}

*/