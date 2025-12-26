package com.example.caproject

import android.os.Bundle
import android.widget.photopicker.EmbeddedPhotoPickerFeatureInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.caproject.ui.theme.CAProjectTheme

class SendDataScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name_key") ?: "No name"
        val email  =intent.getStringExtra("email_key") ?: "No Email"
         setContent {
            CAProjectTheme {
                GetDataScreen(name,email)
            }
        }
    }
}

@Composable
fun GetDataScreen(name: String,email: String){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(16.dp)

    ) {
    Text("Your name is $name")
    Spacer(modifier = Modifier.height(16.dp))
    Text("Your Email is $email")
    }
}
