package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import java.util.ArrayList;
import java.util.HashMap;




public class RepositoryController extends Conector{
	
	private PreparedStatement ps;
	private ResultSet rs;
	
	public boolean encontrarUsuario(String username, String password) {
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("SELECT * FROM login WHERE nombre = ? and clave = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				return true;
			}else {
				return false;
			}	
		} catch (SQLException e) {
			e.getErrorCode();
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean ingresarHuesped(String nombre, String apellido, Date fecha, String nacionalidad, String telefono){
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("INSERT INTO huespedes (nombre, apellido, fecha, nacionalidad, telefono) VALUES (?,?,?,?,?)");
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setDate(3, fecha);
			ps.setString(4, nacionalidad);
			ps.setString(5, telefono);
			ps.executeUpdate();
			con.close();
			return true;
		}catch(SQLException e) {
			e.getCause();
			e.getMessage();
			throw new RuntimeException();
		}
	}
	public boolean ingresarReserva(Reserva reserva) {
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("INSERT INTO reservas (fecha_entrada, fecha_salida, valor_total, metodo_pago, id_huesped) VALUES (?,?,?,?,?)");
			ps.setDate(1, reserva.getFecha_entrada());
			ps.setDate(2, reserva.getFecha_salida());
			ps.setDouble(3, reserva.getValor_total());
			ps.setString(4, reserva.getMetodo_pago());
			ps.setInt(5, reserva.getId_huesped());
			ps.executeUpdate();
			con.close();
			return true;
		}catch(SQLException e) {
			e.getCause();
			e.getMessage();
			throw new RuntimeException();
		}
	}
	public int encontrarHuesped(Date fechaNacimiento, String nombre, String apellido) {
		Connection con = conectarDB();	
		int id_huesped = -1;
		try {
			ps = con.prepareStatement("SELECT * FROM huespedes WHERE fecha=? and nombre=? and apellido=?");
			ps.setDate(1, fechaNacimiento);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			rs = ps.executeQuery();
			while(rs.next()){
				id_huesped = rs.getInt("id");
			}
			return id_huesped;
		} catch (SQLException e) {
			throw new RuntimeException();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Map<String, String>> listarReservas() {
		List<Map<String, String>>  resultado= new ArrayList<>();
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("SELECT * FROM reservas");
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String, String> fila = new HashMap<>();
				fila.put("id", String.valueOf(rs.getInt(1)));
				fila.put("fecha_inicio", String.valueOf(rs.getDate(2)));
				fila.put("fecha_salida", String.valueOf(rs.getDate(3)));
				fila.put("valor_total", String.valueOf(rs.getDouble(4)));
				fila.put("metodo_pago", rs.getString(5));
				fila.put("id_huesped", String.valueOf(rs.getInt(6)));
				resultado.add(fila);
			}
			con.close();
			return resultado;
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<Map<String, String>> listarHuespedes(){
		List<Map<String, String>>  resultado= new ArrayList<>();
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("SELECT * FROM huespedes");
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String, String> fila = new HashMap<>();
				fila.put("id", String.valueOf(rs.getInt(1)));
				fila.put("nombre", rs.getString(2));
				fila.put("apellido", rs.getString(3));
				fila.put("fecha", String.valueOf(rs.getDate(4)));
				fila.put("nacionalidad", rs.getString(5));
				fila.put("telefono", rs.getString(6));
				resultado.add(fila);
			}
			con.close();
			return resultado;
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Map<String, String>> filtrarHuespedes(int id){
		List<Map<String, String>>  resultado = new ArrayList<>();
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("SELECT * FROM huespedes WHERE id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String, String> fila = new HashMap<>();
				fila.put("id", String.valueOf(rs.getInt(1)));
				fila.put("nombre", rs.getString(2));
				fila.put("apellido", rs.getString(3));
				fila.put("fecha", String.valueOf(rs.getDate(4)));
				fila.put("nacionalidad", rs.getString(5));
				fila.put("telefono", rs.getString(6));
				resultado.add(fila);
			}
			con.close();
			return resultado;
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Map<String, String>> filtrarReservas(int id){
		List<Map<String, String>>  resultado = new ArrayList<>();
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("SELECT * FROM reservas WHERE id_huesped=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String, String> fila = new HashMap<>();
				fila.put("id", String.valueOf(rs.getInt(1)));
				fila.put("fecha_inicio", String.valueOf(rs.getDate(2)));
				fila.put("fecha_salida", String.valueOf(rs.getDate(3)));
				fila.put("valor_total", String.valueOf(rs.getDouble(4)));
				fila.put("metodo_pago", rs.getString(5));
				fila.put("id_huesped", String.valueOf(rs.getInt(6)));
				resultado.add(fila);
			}
			con.close();
			return resultado;
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			try {
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean eliminarReserva(int id){
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("DELETE FROM reservas WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.getErrorCode();
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean eliminarHuesped(int id){
		Connection con = conectarDB();
		try {
			ps = con.prepareStatement("DELETE FROM huespedes WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.getErrorCode();
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public boolean actualizarHuesped(String nombre, String apellido, Date fecha, String nacionalidad, String telefono, int id) {
		Connection con = conectarDB();
		try{
			ps = con.prepareStatement("UPDATE huespedes SET nombre = ?, apellido = ?, fecha = ?, nacionalidad = ?, telefono = ? WHERE id = ?");
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setDate(3, fecha);
			ps.setString(4, nacionalidad);
			ps.setString(5, telefono);
			ps.setInt(6, id);
			ps.executeUpdate();
			con.close();
			return true;
		}catch(SQLException e) {
			e.getMessage();
			return false;
		}
		
	}
	
	public boolean actualizarReserva(Date fechaEntrada, Date fechaSalida, Double valor_total, String metodo_pago, int id_huesped, int id){
		Connection con = conectarDB();
		try{
			ps = con.prepareStatement("UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor_total = ?, metodo_pago = ?, id_huesped = ? WHERE id = ?");
			ps.setDate(1, fechaEntrada);
			ps.setDate(2, fechaSalida);
			ps.setDouble(3, valor_total);
			ps.setString(4, metodo_pago);
			ps.setInt(5, id_huesped);
			ps.setInt(6, id);
			ps.executeUpdate();
			con.close();
			return true;
		}catch(SQLException e) {
			e.getMessage();
			return false;
		}
	}
}
