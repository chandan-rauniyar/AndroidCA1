package com.example.caproject

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.caproject.ui.theme.CAProjectTheme


class PermissionEx : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CAProjectTheme {
                carmraPerEx()
            }
        }
    }
}

@Composable
fun carmraPerEx(){
        val context = LocalContext.current
    val isLoactionGraunted = remember { mutableStateOf(
        ContextCompat.checkSelfPermission(context,
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        granted ->
        isLoactionGraunted.value = granted
        Toast.makeText(context,if(granted) "Permission Granted" else "Permission Denied",
            Toast.LENGTH_SHORT).show()

    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
            Text(
                text = if(isLoactionGraunted.value){
                    "Location Permission Granted"
                }else{
                    "Location Permission Not Granted"
                }
            )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {
            permissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }) {
            Text("Request Permission")
        }
    }
}

@Composable
fun LoactionPerEx(){

}