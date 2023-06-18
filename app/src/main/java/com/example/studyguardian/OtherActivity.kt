package com.example.studyguardian

import android.app.Activity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studyguardian.Top
import kotlin.system.exitProcess

class OtherActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App1()
        }
    }
}

@Composable
fun App1() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "page1") {
        composable("page1") {
            Page1(navController = navController) { navController.navigate("page2") }
        }
        composable("page2") {
            Page2(navController = navController, { navController.navigate("finish") })
        }
        composable("finish"){
            Finish(navController = navController, {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Finish(navController: NavController, next: ()->Unit){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Top(in_home = true, {})
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(R.drawable.frame35), contentDescription = "textt")
            }
            ElevatedButton(onClick = { exitProcess(0)}) {
                Text(text = "Finalizar")
            }
        }
        
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page2(navController: NavController, next: ()->Unit){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Top(in_home = true, {})
        Spacer(modifier = Modifier.height(8.dp))

        val res = remember { mutableStateOf("") }
        val res2 = remember { mutableStateOf("") }
        val res3 = remember { mutableStateOf("") }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp, vertical = 50.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Vea el siguiente video", textAlign = TextAlign.Center, fontSize = 20.sp)
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(R.drawable.frame34), contentDescription = "textt")
            }
            Column( ){
                Box(modifier = Modifier){
                    Text(text = "1. ¿De que trata el video?")
                }
                OutlinedTextField(value = res.value, onValueChange = { res.value = it })
            }

            Column(){
                Box(modifier = Modifier){
                    Text(text = "2. ¿Como se llama el personaje principal?")
                }
                OutlinedTextField(value = res2.value, onValueChange = { res2.value = it })
            }
            Column(){
                Box(modifier = Modifier){
                    Text(text = "3. ¿Qué hizo el personaje principal?")
                }
                OutlinedTextField(value = res3.value, onValueChange = { res3.value = it })
            }
            val progress = remember { mutableStateOf(0.5f) }

            // Composable de la barra de progreso
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = progress.value,
                )
                IconButton(onClick = next, modifier = Modifier.background(color = Color(247,247,247))) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Next")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page1(navController: NavController, next: ()->Unit){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Top(in_home = true, {})
        Spacer(modifier = Modifier.height(8.dp))

        val res = remember { mutableStateOf("") }
        val res2 = remember { mutableStateOf("") }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp, vertical = 50.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Resuelva las siguiente preguntas del topico de Matemática", textAlign = TextAlign.Center, fontSize = 20.sp)
            Column( ){
                Box(modifier = Modifier){
                    Text(text = "1. ¿Qué número sigue en la secuencia?\n" +
                            "   24, 30, 36, 42, . . .")
                }
                OutlinedTextField(value = res.value, onValueChange = { res.value = it },keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            }

            Column(){
                Box(modifier = Modifier){
                    Text(text = "2. ¿Cuánto vale X?                                   \n" +
                            "                      4X + 3 = X + 18")
                }
                OutlinedTextField(value = res2.value, onValueChange = { res2.value = it },keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            }
            val progress = remember { mutableStateOf(0.0f) }

            // Composable de la barra de progreso
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = progress.value,
                )
                IconButton(onClick = next, modifier = Modifier.background(color = Color(247,247,247))) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Next")
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }


    }
}
