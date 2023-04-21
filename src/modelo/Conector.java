package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	
	public Connection conectarDB(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_alura?useTimeZone=true&serverTimeZone=UTC","root","Borman15");
		} catch (SQLException e) {
			throw new RuntimeException("No se logró conectar a la base de datos");
		}
	}
	
	

}
