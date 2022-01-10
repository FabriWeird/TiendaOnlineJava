package coneccionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Coneccion {
    private int cantidadConecciones;
    private static Connection conn;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "nk31";
    private static final String url = "jdbc:mysql://localhost:3306/ventas_online";

    // este method nos permite la conección
    public Coneccion() {
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                cantidadConecciones = 0;
                //System.out.print("conección establecida\n");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print("Error al conectar " + e);
        }
    }

    // creamos un method para que nos retorne la conección
    public Connection getConnection() {
        return conn;
    }
    // Con este método nos desconectamos de la BD
    public void desconectar(){
        conn = null;
        if(conn == null){
            System.out.println("CONECCIÓN TERMINADA");
        }
    }
}
