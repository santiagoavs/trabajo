package santiago.avila.helpdesk

import Conexion.Conexion
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class Registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_Backbtn = findViewById<ImageView>(R.id.btn_Backbtn)
        val txt_Nombre = findViewById<EditText>(R.id.txt_Nombre)
        val txt_Correo = findViewById<EditText>(R.id.txt_Correo)
        val txt_Contrasena = findViewById<EditText>(R.id.txt_Contrasena)
        val btn_CrearCuenta = findViewById<Button>(R.id.btn_CrearCuenta)

        btn_Backbtn.setOnClickListener {
            val intent = Intent(this, Bienvenida::class.java)
            startActivity(intent)
        }

        btn_CrearCuenta.setOnClickListener {
            val nombre = txt_Nombre.text.toString()
            val correo = txt_Correo.text.toString()
            val contraseña = txt_Contrasena.text.toString()

            if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
                // mostrar error en caso de querer crear cuenta con campos vacios
                val toast = Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT)
                toast.show()
                Handler(Looper.getMainLooper()).postDelayed({ toast.cancel() }, 3000)
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val objConexion = Conexion().cadenaConexion()

                    // Insertar datos en la tabla
                    val addUsuario = objConexion?.prepareStatement("insert into UsuariosHD (uuid, nombre, correo, contraseña) values(?,?,?,?)")!!
                    addUsuario.setString(1, UUID.randomUUID().toString())
                    addUsuario.setString(2, nombre)
                    addUsuario.setString(3, correo)
                    addUsuario.setString(4, contraseña)
                    addUsuario.executeUpdate()

                    runOnUiThread {
                        // limpiar campos al hacer clic
                        txt_Nombre.setText("")
                        txt_Correo.setText("")
                        txt_Contrasena.setText("")
                    }
                }
            }

        }

    }
}