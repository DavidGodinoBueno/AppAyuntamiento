package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
     Connection con;
     String uri = "jdbc:mysql://localhost:3306/ayuntamiento";
     String user = "root";
     String password = "";
	 public Connection getConexion() {
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection(uri, user, password);
			 //System.out.print("Conexion establecida");
		 } catch(Exception e) {
			 System.err.print("Conexion fallida: "+e.getMessage());;
		 }
		return con;
	 }
}

