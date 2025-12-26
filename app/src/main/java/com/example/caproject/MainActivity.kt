package com.example.caproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExplicitGroupsComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(30.dp)
            ) {
//                WorkShop()
//                baiscCheckBox()
//                baiscSlider()
//                SliderAdvancedExample()
//                SimpleCheckBox()
//                MultipleCheckboxList()
//                basicRadioButton()
//                  twoRadioButton()
//                radioGroupButton()
                ImplicitIntentScreen2()



//                ImplicitIntentScreen()
//                  ExplicitIntentScreen()
            }

        }
    }
}



//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun ExplicitIntentScreen(){

    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("Enter Name")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Enter email")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(context,SendDataScreen::class.java ).apply {
                putExtra("name_key",name)
                putExtra("email_key",email)
            }

            context.startActivity(intent)

        }) {
            Text("Send Data to next Page") 
        }


    }
}
@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun ImplicitIntentScreen2() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Opens a URL in a web browser
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            context.startActivity(intent)
        }) {
            Text("Open Google")
        }

        // Dials a phone number
        Button(onClick = {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:8409469490"))
            context.startActivity(intent)
        }) {
            Text("Dial Number")
        }

        // Sends an SMS with pre-filled number and message
        Button(
            onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:8409469490")
                putExtra("sms_body", "Hello! This is a test message")
            }
            context.startActivity(intent)
        }) {
            Text("Send message (SMS)")
        }

        // Opens the email app with pre-filled recipient, subject, and body
        Button(onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:codewithmyself2@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Test Email Subject")
                putExtra(Intent.EXTRA_TEXT, "This is a test email body.")
            }
            context.startActivity(intent)
        }) {
            Text("Send Email")
        }

        // Opens the Android share sheet to share plain text
        Button(onClick = {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Sharing text using Implicit Intent!")
            }
            context.startActivity(Intent.createChooser(intent, "Share Via"))
        }) {
            Text("Share Text")
        }

        // Opens the camera to capture an image
        Button(onClick = {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            context.startActivity(intent)
        }) {
            Text("Open Camera")
        }

        // Opens a file picker to select a document
        Button(onClick = {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "*/*" // Or be more specific e.g., "document/pdf"
            }
            context.startActivity(intent)
        }) {
            Text("Open Document")
        }

        // Opens Google Maps at a specific location
        Button(onClick = {
            // geo:latitude,longitude?q=query
            val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q=Golden Gate Bridge")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps") // Ensures it opens in Google Maps
            context.startActivity(mapIntent)
        }) {
            Text("Open Map Location")
        }

        // Opens the calendar to add a new event
        Button(onClick = {
            val intent = Intent(Intent.ACTION_INSERT).apply {
                data = CalendarContract.Events.CONTENT_URI
                putExtra(CalendarContract.Events.TITLE, "Android Dev Meeting")
                putExtra(CalendarContract.Events.EVENT_LOCATION, "Virtual")
                putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
                putExtra(
                    CalendarContract.EXTRA_EVENT_END_TIME,
                    System.currentTimeMillis() + 60 * 60 * 1000
                )
            }
            context.startActivity(intent)
        }) {
            Text("Add Calendar Event")
        }

        // Opens the clock to set an alarm
        Button(onClick = {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Time for a break!")
                putExtra(AlarmClock.EXTRA_HOUR, 15)
                putExtra(AlarmClock.EXTRA_MINUTES, 30)
            }
            context.startActivity(intent)
        }) {
            Text("Set an Alarm")
        }
    }
}



//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun ImplicitIntentScreen(){
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Button(
                 onClick = {
                     val intent = Intent(Intent.ACTION_VIEW).apply {
                          data  = Uri.parse("https://www.google.com") }

                         context.startActivity(intent)
                 }) {

            Text("Open Google")
        }

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("smsto:8409469490")
                    putExtra("sms_body","Hello! This is a test message")
                }
                context.startActivity(intent)


                }
        ) {
            Text("Send message (SMS)")
        }


        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("tel:8409469490" )
                 }
                context.startActivity(intent)


            }
        ) {
            Text("Dial Number")
        }

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    type="image/*"
                 }
                context.startActivity(intent)
            }
        ) {
            Text("Send Img")
        }

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:codewithmyself2@gmail.com")
                    putExtra(Intent.EXTRA_SUBJECT,"TEST EMAIL")
                    putExtra(Intent.EXTRA_TEXT,"THIS IS A TEST EMAIL")
                }
                context.startActivity(intent)
            }
        ) {
            Text("Send mail")
        }

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type="text/plain"
                     putExtra(Intent.EXTRA_TEXT,"Sharing text" + " Using Implicit Intent!")
                }
                context.startActivity(Intent.createChooser(intent,"share Via"))
            }
        ) {
            Text("Share Text")
        }

        Button(
            onClick = {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                context.startActivity(intent)
            }

        ) {
            Text("Open Camera")
        }

        Button(onClick = {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type  = "document/pdf"
            }
            context.startActivity(intent)
        }) {
            Text("Open Document")
        }

    }
}


//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun radioGroupButton(){
    val option  = listOf("Python","C","C++","JAVA","Kotlin")
    var selectoption by remember { mutableStateOf("") }
    var submittedOption by remember { mutableStateOf("") } // State to hold the submitted value
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)

    ) {

        Text("Select Option ")

        option.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                RadioButton(
                    selected = selectoption == option,
                    onClick = {selectoption = option}
                )

                Text(option)
            }
        }

        Button(onClick = {
            submittedOption = selectoption // Update the submittedOption state on click
        }) {
            Text("Submit")
        }

        // Display the result below the button
        if (submittedOption.isNotEmpty()) {
            Text("$submittedOption is selected")
        }
    }

}



@Composable
fun twoRadioButton(){
    var rBtnValue by remember {mutableStateOf((""))}
    Column(verticalArrangement = Arrangement.Center) {
        Row( verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = rBtnValue == "Male",
                onClick = {rBtnValue = "Male"}
            )
            Text("Male")
        }

        Row( verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = rBtnValue == "Female",
                onClick = {rBtnValue = "Female"}
            )
            Text("Female")
        }
    }
}





@Composable
fun basicRadioButton(){
    var rBtnValue by remember { mutableStateOf(false) }
    Row( verticalAlignment = Alignment.CenterVertically) {
    RadioButton(
        selected = rBtnValue == true,
        onClick = {rBtnValue = true}
    )
    Text("Checked or Unchecked")
    }
}

















//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun baiscCheckBox(){
    var ischeched by remember { mutableStateOf(false) }

    Text(text = if (ischeched) "ON" else "OFF")
    Spacer(modifier = Modifier.width(20.dp))
//    Switch(
//        checked = ischeched,
//        onCheckedChange = {ischeched = it}
//    )

        Switch(
        checked = ischeched,
        onCheckedChange = {ischeched = it},
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Red,
                checkedTrackColor = Color.Gray,
                uncheckedThumbColor = Color.Black,
                uncheckedTrackColor = Color.DarkGray
            )
    )
}
//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun SimpleCheckBox(){
    var java by remember { mutableStateOf(false) }
    var kotlin by remember { mutableStateOf(false) }
    var python by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(20.dp)) {
        checkboxreusable("DSA",java)
        checkboxreusable("CPP",kotlin)
        checkboxreusable("Python",python)
    }
}


@Composable
fun checkboxreusable(tText : String, Cchecked : Boolean){
    var Vchecked by remember { mutableStateOf(Cchecked) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = Vchecked,
            onCheckedChange = {Vchecked = it}

        )

        Text(text=tText, modifier = Modifier.padding(10.dp))
    }
}

//CheckBox Using forEach Loop
//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun MultipleCheckboxList(){
    val option = listOf("JAVA","DSA","Python")
    val checkStates = remember { mutableStateMapOf<String, Boolean>() }

//    Initial Default value
    option.forEach { option ->
        if(!checkStates.containsKey(option))
            checkStates[option] = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        option.forEach {
            option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                
                Checkbox(
                    checked = checkStates[option]  == true,
                    onCheckedChange = {checkStates[option] = it}
                )
                Text(text = option,
                    modifier = Modifier.padding(16.dp))
            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun baiscSlider(){
    var sliderValue by remember { mutableStateOf(0f) }

    Text(text = "Value: ${sliderValue.toInt()}")
    Spacer(modifier = Modifier.width(20.dp))
//    Switch(
//        checked = ischeched,
//        onCheckedChange = {ischeched = it}
//    )

    Slider(
        value = sliderValue,
        onValueChange = {sliderValue = it},
        valueRange = 0f .. 100f,
        modifier = Modifier.padding(10.dp)
    )
}

//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun SliderAdvancedExample() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }



    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 3,
            valueRange = 0f..50f
        )
        Text(text = sliderPosition.toString())
    }
}
//@Preview(showBackground = true, widthDp = 400, heightDp =700)
@Composable
fun ClassActivity(){
Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize().padding(20.dp)
) {

    HeadingText()
    Profile()
    InputField("Name", "Enter Your Name")
    InputField("Reg No", "Enter Your Reg No")
    InputField("Mob No", "Enter Your Mobile No")
    ButtonFun()


}
}

@Composable
fun Profile(){
    Image(
        painter = painterResource(R.drawable.myimg),
        contentDescription = " ",
        modifier = Modifier.size(100.dp).clip(CircleShape),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}

@Composable
fun HeadingText(){
    Text(
        text = "Login  form",
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun InputField(tText: String, pText: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // ---- 40% WIDTH TEXT ----
        Text(
            text = tText,
            modifier = Modifier
                .weight(0.25f),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Left
        )

        Spacer(Modifier.padding(20.dp))

        // ---- 60% WIDTH INPUT FIELD ----
        val workshopLevel = remember { mutableStateOf("") }

        OutlinedTextField(
            value = workshopLevel.value,
            onValueChange = { workshopLevel.value = it },
            placeholder = { Text(pText) },
            modifier = Modifier
                .weight(0.75f)
                .padding(start = 10.dp),
            shape = RoundedCornerShape(10.dp),
            singleLine = true
        )
    }
}



@Composable
fun ButtonFun(){

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth().padding(30.dp)
    ){
        Button(
            onClick = {},
            modifier = Modifier.width(150.dp).padding(10.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Enroll Now")
        }
        Button(
            onClick = {},
            modifier = Modifier.width(150.dp).padding(10.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Enroll Now")
        }

    }
}




//@Preview(showBackground = true, widthDp = 400, heightDp =700)
// CA Code
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
        val name = remember { mutableStateOf("") }

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
        val regNo = remember { mutableStateOf("") }

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
        val workshopLevel = remember { mutableStateOf("") }

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