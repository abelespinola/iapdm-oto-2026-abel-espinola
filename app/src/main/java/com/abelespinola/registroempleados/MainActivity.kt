package com.abelespinola.registroempleados

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abelespinola.registroempleados.ui.theme.NeuraTechTheme
import com.abelespinola.registroempleados.ui.theme.TealPrimary
import java.util.UUID



// 2. MODELO DE DATOS Y VIEWMODEL

data class Empleado(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String, val cargo: String, val departamento: String,
    val salario: String, val fecha: String
)

/**
 * El ViewModel mantiene el estado de la UI y sobrevive a los cambios
 * de configuración (como cambiar a Modo Oscuro).
 **/
class EmpleadoViewModel : ViewModel() {
    var nombre by mutableStateOf("")
    var cargo by mutableStateOf("")
    var departamento by mutableStateOf("")
    var salario by mutableStateOf("")
    var fecha by mutableStateOf("")

    // Función para que la UI se actualice al agregar/quitar elementos
    val listaEmpleados = mutableStateListOf<Empleado>()

    fun registrarEmpleado() {
        if (nombre.isNotBlank() && cargo.isNotBlank()) {
            val salarioFormateado = if (salario.isNotEmpty()) formatoSalarioTexto(salario) else ""
            val fechaFormateada = if (fecha.isNotEmpty()) formatoFechaTexto(fecha) else ""

            listaEmpleados.add(
                Empleado(
                    nombre = nombre, cargo = cargo, departamento = departamento,
                    salario = salarioFormateado, fecha = fechaFormateada
                )
            )
            // Limpiamos los campos luego de registrar
            nombre = ""; cargo = ""; departamento = ""; salario = ""; fecha = ""
        }
    }

    fun eliminarEmpleado(empleado: Empleado) {
        listaEmpleados.remove(empleado)
    }
}


// 3. ACTIVIDAD PRINCIPAL

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivityLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeuraTechTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    RegistroNeuraTechApp()
                }
            }
        }
    }

    override fun onStart() { super.onStart(); Log.i(TAG, "onStart ejecutado") }
    override fun onStop() { super.onStop(); Log.i(TAG, "onStop ejecutado") }
    override fun onDestroy() { super.onDestroy(); Log.i(TAG, "onDestroy ejecutado") }
}


// 4. INTERFAZ PRINCIPAL

@Composable
fun RegistroNeuraTechApp(viewModel: EmpleadoViewModel = viewModel()) {
    // Ya no usamos 'remember' localmente. Todo el estado viene del ViewModel.

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 24.dp)) {
            Icon(Icons.Default.Dashboard, contentDescription = "Logo", tint = TealPrimary, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("NeuraTech", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Registro de Empleados", fontSize = 14.sp, color = Color.Gray)
            }
        }

        // FORMULARIO

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                SoftTextField(
                    valor = viewModel.nombre, onValorCambiado = { viewModel.nombre = it },
                    icono = Icons.Default.Person, label = "Nombre completo", placeholder = "Ej: Juan Pérez"
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SoftTextField(
                        valor = viewModel.cargo, onValorCambiado = { viewModel.cargo = it },
                        icono = Icons.Default.WorkOutline, label = "Cargo", placeholder = "Ej: Técnico", modifier = Modifier.weight(1f)
                    )
                    SoftTextField(
                        valor = viewModel.departamento, onValorCambiado = { viewModel.departamento = it },
                        icono = Icons.Default.Business, label = "Dpto.", placeholder = "Ej: IT Infra", modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SoftTextField(
                        valor = viewModel.salario,
                        onValorCambiado = { input -> viewModel.salario = input.filter { it.isDigit() } },
                        icono = Icons.Default.AttachMoney, label = "Salario", placeholder = "0 gs.",
                        modifier = Modifier.weight(1f), keyboardType = KeyboardType.Number, visualTransformation = CurrencyVisualTransformation()
                    )

                    SoftTextField(
                        valor = viewModel.fecha,
                        onValorCambiado = { input ->
                            val digitos = input.filter { it.isDigit() }
                            if (digitos.length <= 8) viewModel.fecha = digitos
                        },
                        icono = Icons.Default.CalendarToday, label = "Fecha Contratación", placeholder = "DD/MM/AAAA",
                        modifier = Modifier.weight(1f), keyboardType = KeyboardType.Number, visualTransformation = DateVisualTransformation()
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.registrarEmpleado() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = TealPrimary)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Registrar Empleado", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Personal Activo (${viewModel.listaEmpleados.size})", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(12.dp))

        // LISTA DE EMPLEADOS
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(bottom = 20.dp)) {
            items(viewModel.listaEmpleados, key = { it.id }) { empleado ->
                ItemEmpleadoEstilizado(empleado = empleado, onEliminarClick = { viewModel.eliminarEmpleado(empleado) })
            }
        }
    }
}


// COMPONENTES AUXILIARES Y TRANSFORMACIONES VISUALES

@Composable
fun SoftTextField(
    valor: String, onValorCambiado: (String) -> Unit, icono: ImageVector,
    label: String, placeholder: String, modifier: Modifier = Modifier, keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = valor, onValueChange = onValorCambiado, label = { Text(label) },
        placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
        leadingIcon = { Icon(icono, contentDescription = null, tint = TealPrimary, modifier = Modifier.size(20.dp)) },
        shape = RoundedCornerShape(14.dp), singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType), visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TealPrimary, unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedLabelColor = TealPrimary, unfocusedLabelColor = Color.Gray
        ), modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ItemEmpleadoEstilizado(empleado: Empleado, onEliminarClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = empleado.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item { SoftTag(empleado.cargo, Icons.Default.WorkOutline) }
                item { SoftTag(empleado.departamento, Icons.Default.Business) }
                item { SoftTag(empleado.salario, Icons.Default.AttachMoney) }
                item { SoftTag(empleado.fecha, Icons.Default.Event) }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onEliminarClick, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", modifier = Modifier.size(18.dp), tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Eliminar", color = Color.White)
            }
        }
    }
}

@Composable
fun SoftTag(texto: String, icono: ImageVector) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icono, contentDescription = null, tint = TealPrimary, modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = texto, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

fun formatoFechaTexto(digitos: String): String {
    var out = ""
    for (i in digitos.indices) {
        out += digitos[i]
        if (i == 1 || i == 3) out += "/"
    }
    return out
}

fun formatoSalarioTexto(digitos: String): String {
    val rev = digitos.reversed()
    var out = ""
    for (i in rev.indices) {
        if (i > 0 && i % 3 == 0) out += "."
        out += rev[i]
    }
    return out.reversed() + " gs."
}

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text
        val formatted = formatoFechaTexto(digits)
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 3) return offset + 1
                if (offset <= 8) return offset + 2
                return formatted.length
            }
            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 5) return offset - 1
                if (offset <= 10) return offset - 2
                return digits.length
            }
        }
        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

class CurrencyVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        if (originalText.isEmpty()) return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        val formatted = formatoSalarioTexto(originalText)
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return 0
                return offset + ((offset - 1) / 3)
            }
            override fun transformedToOriginal(offset: Int): Int {
                var originalOffset = 0
                var currentTransformedOffset = 0
                for (char in formatted) {
                    if (currentTransformedOffset >= offset) break
                    if (char.isDigit()) originalOffset++
                    currentTransformedOffset++
                }
                return originalOffset
            }
        }
        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}