package com.example.studyguardian

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyguardian.ui.theme.StudyGuardianTheme


import com.example.studyguardian.androidlarge1.AndroidLarge1
import com.example.studyguardian.androidlarge2.AndroidLarge2
import com.example.studyguardian.androidlarge3.AndroidLarge3
import com.example.studyguardian.androidlarge4.AndroidLarge4
import com.example.studyguardian.androidlarge5.AndroidLarge5
import com.example.studyguardian.androidlarge6.AndroidLarge6
import com.example.studyguardian.androidlarge7.AndroidLarge7

class MainActivity : ComponentActivity() {
    private var currentScreen: @Composable () -> Unit = { Screen0(::changeScreen) };
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            currentScreen()
        }
    }

    fun changeScreen(newScreen: @Composable () -> Unit) {
        currentScreen = newScreen
        setContent {
            currentScreen()
        }
    }

}

@Composable
fun Screen0(changeScreen: (newScreen: @Composable () -> Unit) -> Unit) {
    AndroidLarge1(
        onButtonEstudiante = { changeScreen { StudentCode(changeScreen = changeScreen) } },
        onButtonTutor = {
            changeScreen {
                Tutor(
                    changeScreen = changeScreen
                )
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentCode(changeScreen: (newScreen: @Composable () -> Unit) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        AndroidLarge2(back = { changeScreen { Screen0(changeScreen = changeScreen) } })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp, vertical = 40.dp)
                .offset(y = 460.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción al cambiar el valor del campo de texto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text(text = "Código") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
    }
}

@Composable
fun Tutor(changeScreen: (newScreen: @Composable () -> Unit) -> Unit) {
    AndroidLarge3(
        back = { changeScreen { Screen0(changeScreen = changeScreen) } },
        login = { changeScreen { Login(changeScreen = changeScreen) } },
        register = {
            changeScreen {
                Register(
                    changeScreen = changeScreen
                )
            }
        })
}

@Composable
fun main_page_tutor(changeScreen: (newScreen: @Composable () -> Unit) -> Unit) {
    AndroidLarge6(childInfo = {changeScreen{ page_ingo_child(changeScreen = changeScreen) }})
}

@Composable
fun page_ingo_child(changeScreen: (newScreen: @Composable () -> Unit) -> Unit){
    AndroidLarge7(back = {changeScreen{ main_page_tutor(changeScreen = changeScreen)}})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(changeScreen: (newScreen: @Composable () -> Unit) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        AndroidLarge5(back = { changeScreen { Tutor(changeScreen = changeScreen) } }, login = {
            changeScreen {
                main_page_tutor(
                    changeScreen = changeScreen
                )
            }
        })

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp, vertical = 40.dp)
                .offset(y = 230.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción al cambiar el valor del campo de texto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text(text = "Correo electronico") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción al cambiar el valor del campo de texto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text(text = "Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(changeScreen: (newScreen: @Composable () -> Unit) -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        AndroidLarge4(back = { changeScreen { Tutor(changeScreen = changeScreen) } }, register = {
            changeScreen {
                Login(
                    changeScreen = changeScreen
                )
            }
        })

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp, vertical = 40.dp)
                .offset(y = 210.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción al cambiar el valor del campo de texto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text(text = "Nombres") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción al cambiar el valor del campo de texto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text(text = "Correo electronico") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción al cambiar el valor del campo de texto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text(text = "Usuario") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción al cambiar el valor del campo de texto */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text(text = "Contraseña") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            )
        }

    }
}

