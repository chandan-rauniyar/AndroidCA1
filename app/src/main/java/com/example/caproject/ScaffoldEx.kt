package com.example.caproject
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.caproject.ui.theme.CAProjectTheme


import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
 import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

class ScaffoldEx : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            MyScaffoldEx()
//            BottomNavScaffold()
//            Multilingual()
            ScaffoldWithComponentsExample()
        }
    }
}

//@Preview(showBackground = true,widthDp = 400, heightDp = 700)
//@Composable
//fun ModifierOrderExample1() {
//    Text(
//        text = "Click Me",
//        modifier = Modifier
//            .padding(16.dp)
//            .background(Color.Red)
//            .clickable {
//                println("Text clicked")
//            },
//        color = Color.White
//    )
//}
//
//@Preview(showBackground = true,widthDp = 400, heightDp = 700)
//@Composable
//fun ModifierOrderExample2() {
//    Text(
//        text = "Click Me",
//        modifier = Modifier
//            .background(Color.Red)
//            .padding(16.dp)
//            .clickable {
//                println("Text clicked")
//            },
//        color = Color.White
//    )
//}


@Preview(showBackground = true,widthDp = 400, heightDp = 700)
@Composable
fun Multilingual(){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.welcome))

        Button(onClick = { }) {
            Text(text = stringResource(R.string.login))
        }

        Text(text = stringResource(R.string.hello_user, "Android"))

        val count = 1
        Text(
            text = pluralStringResource(
                id = R.plurals.item_count,
                count = count,
                count
            )
        )
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithComponentsExample() {
    // 1. A CoroutineScope is needed to show the Snackbar asynchronously.
    val coroutineScope = rememberCoroutineScope()
    // 2. A SnackbarHostState controls the showing and hiding of Snackbars.
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    // Scaffold provides the basic Material Design layout structure.
    Scaffold(
        // 3. TopAppBar: Sits at the top of the screen.
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle search action */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        // 4. BottomAppBar: Sits at the bottom. Often used with a FAB.
        bottomBar = {
            BottomNavigationBarExample()
        },
        // 5. SnackbarHost: The container that will host and display Snackbars.
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        // 6. FloatingActionButton (FAB): A prominent button for a primary action.
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    // --- CHANGE IS HERE ---
                    // showSnackbar can return a result indicating if it was dismissed or an action was performed.
                    val result = snackbarHostState.showSnackbar(
                        message = "FAB was clicked!",
                        actionLabel = "Toast It" // This sets the text for the action button on the snackbar
                    )

                    // Check if the action button was clicked
                    if (result == SnackbarResult.ActionPerformed) {
                        // If so, show a Toast message
                        Toast.makeText(
                            context,
                            "Snackbar action clicked!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        // This is the main content area of your screen.
        // The `innerPadding` value provided by Scaffold contains the space
        // taken up by the TopAppBar and BottomAppBar.
        // You MUST apply this padding to your root content composable
        // to avoid it being drawn behind the bars.
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("This is the screen content")
        }
    }
}

/**
 * A separate example for a Bottom Navigation Bar.
 * Your previous code for this was already excellent, so I've included it here
 * for completeness as it's a common type of bottom bar.
 */
@Composable
fun BottomNavigationBarExample() {
    var selectedIndex by remember { mutableStateOf(0) }
    NavigationBar {
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 },
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") }
        )
        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = { selectedIndex = 2 },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}


/**
 * An example of a BottomAppBar, which is distinct from a Bottom Navigation Bar.
 * It's a container for navigation icons and an optional Floating Action Button (FAB).
 */
@Composable
fun BottomAppBarExample() {
    BottomAppBar(
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Default.Favorite, contentDescription = "Favorite")
            }
        }
    )
}


@Preview(showBackground = true,widthDp = 400, heightDp = 700)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavScaffold() {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Red,
                        selectedTextColor = Color.Red,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                    label = { Text("Search") }, // Corrected parameter name and text
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Red,
                        selectedTextColor = Color.Red,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Settings") }, // Changed Icon
                    label = { Text("Favorites") }, // Corrected parameter name and text
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Red,
                        selectedTextColor = Color.Red,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3 },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") }, // Changed Icon
                    label = { Text("Profile") }, // Corrected parameter name and text
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Red,
                        selectedTextColor = Color.Red,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    ) { innerPadding ->
        // Content of your screen for the selected item
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (selectedIndex) {
                0 -> Text("Home screen")
                1 -> Text("Search screen")
                2 -> Text("Settings screen")
                3 -> Text("Profile screen")
            }
         }
    }
}


@Preview(showBackground = true,widthDp = 400, heightDp = 700)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldEx(){
    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Page Title")

                        },
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Back Icon Clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Menu Icon Clicked", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        // Content of your screen goes here
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Screen Content"
        )
    }
}
