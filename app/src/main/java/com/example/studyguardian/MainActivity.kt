package com.example.studyguardian


import android.app.ActivityManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.studyguardian.androidlarge4.Registrarse
import com.example.studyguardian.androidlarge5.IniciarSesiN
import com.example.studyguardian.frame33.Frame33



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

fun getRunningApplications(context: Context): List<String> {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val packageManager = context.packageManager
    val runningApps = mutableListOf<String>()

    val runningProcesses = activityManager.runningAppProcesses
    for (processInfo in runningProcesses) {
        try {
            val packageName = processInfo.processName
            val applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            val appName = packageManager.getApplicationLabel(applicationInfo).toString()
            runningApps.add(appName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    return runningApps
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            Screen0(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("MyCode") {
            ViewMyCode(navController = navController)
        }
        composable(
            "child/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            ViewChild(
                navController = navController,
                name
            ) { navController.navigate("child/$name/config") }
        }
        composable(
            "child/{name}/config",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            ViewConfig(navController = navController, name = name)
        }
    }
}

@Composable
fun ViewMyCode(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Top(false) { navController.navigate("home") }
        Spacer(modifier = Modifier.height(100.dp))
        val context = LocalContext.current
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "TU CÓDIGO", fontSize = 55.sp, fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier
                    .clip(shape = MaterialTheme.shapes.medium)
                    .border(2.dp, Color.Black, shape = MaterialTheme.shapes.medium)
                    .clickable {
                        val clip = ClipData.newPlainText("text", "XYZKLSMDE")
                        clipboardManager.setPrimaryClip(clip)
                        showToast(context, "Código copiado")
                    }) {
                Text(
                    text = "XYZKLSMDE", fontSize = 30.sp, modifier = Modifier
                        .padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "*Con este código puedes añadir personas",
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 50.dp),
                textAlign = TextAlign.Center
            )

        }

    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun ViewConfig(navController: NavController, name: String?) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Top(false) { navController.navigate("child/$name") }
        Spacer(modifier = Modifier.height(8.dp))
        if (name != null) {
            Text(
                name,
                style = MaterialTheme.typography.displaySmall.copy(fontSize = 40.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Configuración",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 34.dp)
        )
        Divider(modifier = Modifier.padding(horizontal = 28.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 34.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tópicos",
                    modifier = Modifier.width(100.dp),
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(20.dp))
                val items = remember {
                    mutableStateListOf(
                        "Matemática",
                        "Lectura",
                        "Ciencias Naturales",
                        "Idiomas"
                    )
                }
                val checkedItems =
                    remember { mutableStateListOf<Boolean>(true, false, false, true) }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column() {
                        items.forEachIndexed { index, item ->
                            var isChecked by remember { mutableStateOf(false) }
                            checkedItems.getOrNull(index)?.let { isChecked = it }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { isChecked = it; checkedItems[index] = it }
                                )
                                Text(text = item)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(horizontal = 34.dp)) {
                Text(
                    text = "Dificultad",
                    modifier = Modifier.width(100.dp),
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(20.dp))
                var selectedOption by remember { mutableStateOf("Fácil") }
                var expanded by remember { mutableStateOf(false) }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.width(120.dp)) {
                        TextButton(
                            onClick = { expanded = true },
                            modifier = Modifier.fillMaxWidth(),
                            border = BorderStroke(1.dp, color = Color.Black)
                        ) {
                            Text(
                                text = selectedOption,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        DropdownMenu(
                            expanded = expanded, onDismissRequest = { expanded = false },
                            modifier = Modifier.width(120.dp)
                        ) {
                            DropdownMenuItem(onClick = {
                                selectedOption = "Fácil"
                                expanded = false
                            }) {
                                Text(text = "Fácil")
                            }
                            DropdownMenuItem(onClick = {
                                selectedOption = "Intermedio"
                                expanded = false
                            }) {
                                Text(text = "Intermedio")
                            }

                            DropdownMenuItem(onClick = {
                                selectedOption = "Difícil"
                                expanded = false
                            }) {
                                Text(text = "Difícil")
                            }

                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(horizontal = 34.dp)) {
                Text(
                    text = "Número de intentos",
                    modifier = Modifier.width(100.dp),
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(20.dp))
                val numberState = remember { mutableStateOf(3) }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = { if (numberState.value != 1) numberState.value-- },
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            contentPadding = PaddingValues(horizontal = 0.dp)
                        ) {
                            Text(text = "-")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            numberState.value.toString(),
                            modifier = Modifier.width(20.dp),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        TextButton(
                            onClick = { if (numberState.value != 8) numberState.value++ },
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            contentPadding = PaddingValues(horizontal = 0.dp)
                        ) {
                            Text(text = "+")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(horizontal = 34.dp)) {
                Text(
                    text = "Aplicaciones",
                    modifier = Modifier.width(100.dp),
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(20.dp))
                val availableApps = getInstalledAppNames(LocalContext.current)
                val selectedApps = remember { mutableStateListOf<String>() }
                LazyColumn(userScrollEnabled = true) {
                    items(availableApps) { app->
                        val isChecked = remember { mutableStateOf(selectedApps.contains(app)) }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    isChecked.value = !isChecked.value
                                    if (isChecked.value) {
                                        selectedApps.add(app)
                                    } else {
                                        selectedApps.remove(app)
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isChecked.value,
                                onClick = { isChecked.value = !isChecked.value },
                                modifier = Modifier.padding(end = 16.dp)
                            )
                            Text(app)
                        }
                    }
                }
            }
        }
    }
}

fun getInstalledAppNames(context: Context): List<String> {
    val packageManager: PackageManager = context.packageManager
    val installedApps: List<ApplicationInfo> =
        packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

    val appNames: MutableList<String> = mutableListOf()

    for (appInfo in installedApps) {
        if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
            // Aplicación instalada que no es del sistema
            val appName: String = appInfo.loadLabel(packageManager).toString()

            // Agregar el nombre de la aplicación a la lista
            appNames.add(appName)
        }
    }

    return appNames
}

@Composable
fun ViewChild(navController: NavController, name: String?, config: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Top(false) { navController.navigate("home") }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (name != null) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        name,
                        style = MaterialTheme.typography.displaySmall.copy(fontSize = 40.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = config, modifier = Modifier
                            .size(48.dp)
                            .padding(end = 16.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = "settings")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.t2),
                contentDescription = "chart"
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            "Actividad",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(horizontal = 34.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))

        val logs = """Tiktok: 12:32 pm (Duración: 0:34 minutos)
        |Facebook: 12:21 pm (Duración: 0:11 minutos)
        |YouTube: 10:32 am (Duración: 0:52 min)
        |Tiktok: 10:21 am (Duración: 0:12 min)
        |Geometry Dash: 9:58 am (Duración: 0:20 min)""".trimMargin()
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(horizontal = 34.dp)
                .background(color = Color(247, 247, 247))
        ) {
            Text(text = logs)
        }

        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            OutlinedButton(onClick = {}) {
                Text(text = "Generar Informe")
            }
        }
    }
}


@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Top(true, {})
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Conectados",
                style = MaterialTheme.typography.displaySmall.copy(fontSize = 40.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            val names: List<Pair<String, Boolean>> =
                listOf(Pair("Diego", true), Pair("Camila", false))

            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(names) { item ->
                    Child(item.first, item.second) { navController.navigate("child/${item.first}") }
                }
            }
        }

        ElevatedButton(
            onClick = { navController.navigate("MyCode") }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(30.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "add")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Añadir", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Child(name: String = "Diego", status: Boolean = true, view: () -> Unit = {}) {
    Card(
        modifier = Modifier.height(200.dp),
        colors = CardDefaults.cardColors(containerColor = Color(242, 242, 242)),
        onClick = view
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                color = Color.Black,
                style = MaterialTheme.typography.displaySmall
            )
            var color = Color.Green
            if (!status) color = Color.Gray
            Box(
                modifier = Modifier
                    .size(20.dp, 20.dp)
                    .align(Alignment.BottomEnd)
                    .background(color = color)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top(in_home: Boolean, back: () -> Unit) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(74, 172, 49)),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Study Guardian",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp)
                )
            }
        },
        navigationIcon = {
            if (in_home) {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
            } else {
                IconButton(onClick = back) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }

        }
    )
}

@Composable
fun Screen0(navController: NavController) {
    val context = LocalContext.current
    var flowMoment by remember { mutableStateOf(1) }
    var numBox by remember { mutableStateOf(1) }
    Box {
        IconButton(onClick = {
            if (flowMoment == 1) numBox = 1
            else {
                flowMoment = 1
                numBox = 3
            }
        }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Retroceder")
        }

        Crossfade(targetState = flowMoment) { targetValue ->

            if (targetValue == 1) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Frame33(modifier = Modifier.size(width = 228.dp, height = 346.dp))
                    Spacer(modifier = Modifier.height(16.dp))

                    Crossfade(
                        targetState = numBox,
                        animationSpec = tween(durationMillis = 300)
                    ) { targetValue ->

                        if (targetValue == 1) {
                            ColumnView0(estudiante = { numBox = 2 }, tutor = { numBox = 3 })
                        } else if (targetValue == 2) {
                            ColumnView1(entrar = {
                                val homeIntent = Intent(Intent.ACTION_MAIN).apply {
                                    addCategory(Intent.CATEGORY_HOME)
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                                context.startActivity(homeIntent)

                                // Inicia el servicio en segundo plano
                                val serviceIntent = Intent(context, MyForegroundService::class.java)
                                context.startService(serviceIntent)
                            })
                        } else if (targetValue == 3) {
                            ColumnView2(
                                Login = { flowMoment = 2 },
                                Register = { flowMoment = 3 })
                        }

                    }

                }
            } else if (targetValue == 2) {
                Login(
                    login = { navController.navigate("home") },
                    Registrate = { a -> flowMoment = 3 })
            } else if (targetValue == 3) {
                Register(register = { flowMoment = 2 })
            }
        }


    }
}


@Composable
fun ColumnView0(estudiante: () -> Unit, tutor: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(150.dp)
    ) {
        OutlinedButton(onClick = estudiante, modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Estudiante")
        }
        OutlinedButton(onClick = tutor, modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Tutor")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnView1(entrar: () -> Unit) {
    val code = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(150.dp)
    ) {
        OutlinedTextField(
            value = code.value,
            onValueChange = { code.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 100.dp),
            label = { Text(text = "Código") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        OutlinedButton(onClick = entrar, modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Entrar")
        }
    }
}

@Composable
fun ColumnView2(Login: () -> Unit, Register: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(150.dp)
    ) {
        OutlinedButton(onClick = Login, modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Iniciar Sesión")
        }
        OutlinedButton(onClick = Register, modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Registrarse")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(login: () -> Unit, Registrate: (Int) -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IniciarSesiN()
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 100.dp),
            label = { Text(text = "Correo electronico") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 100.dp),
            label = { Text(text = "Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )

        OutlinedButton(onClick = login, modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Aun no tiene cuenta")
        ClickableText(
            text = AnnotatedString(
                "Registrate",
                spanStyle = SpanStyle(color = Color.Blue)
            ), onClick = Registrate
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(register: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val names = remember { mutableStateOf("") }
    val user = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Registrarse()
        OutlinedTextField(
            value = names.value,
            onValueChange = { names.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 100.dp),
            label = { Text(text = "Nombres") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 100.dp),
            label = { Text(text = "Correo electronico") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )

        OutlinedTextField(
            value = user.value,
            onValueChange = { user.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 100.dp),
            label = { Text(text = "Usuario") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 100.dp),
            label = { Text(text = "Contraseña") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )

        OutlinedButton(onClick = register, modifier = Modifier.padding(vertical = 8.dp)) {
            Text("Registrar")
        }
    }

}


