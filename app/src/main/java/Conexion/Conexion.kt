package Conexion

import java.sql.Connection
import java.sql.DriverManager

class Conexion {

    fun cadenaConexion(): Connection? {
        try {
            val url = "jdbc:oracle:thin:@10.10.1.166:1521:xe"
            val usuario = "system"
            val contraseña = "desarrollo"

            val connection = DriverManager.getConnection(url, usuario, contraseña)
            return connection
        }
        catch (e: Exception){
            println("ERROR : $e")
            return null
        }
    }
}