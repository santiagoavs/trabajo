package santiago.avila.helpdesk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_BackLogin = findViewById<ImageView>(R.id.btn_BackLogin)
        val txt_CorreoL = findViewById<EditText>(R.id.txt_CorreoL)
        val txt_ContrasenaLogin = findViewById<EditText>(R.id.txt_ContrasenaLogin)
        val btn_InicioSesionL = findViewById<Button>(R.id.btn_InicioSesionL)

        btn_BackLogin.setOnClickListener {
            val intent = Intent(this, Bienvenida::class.java)
            startActivity(intent)
        }


    }
}