package com.example.tadpole.ui.theme.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tadpole.R
import com.example.tadpole.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(userViewModel: UserViewModel, onLogout: () -> Unit) {
    val user = userViewModel.currentUser.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hola, ${user?.username ?: "Usuario"}") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Empresa",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { /* TODO: navegar a perfil */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Perfil")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = { /* TODO: navegar a configuración */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Configuración")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}