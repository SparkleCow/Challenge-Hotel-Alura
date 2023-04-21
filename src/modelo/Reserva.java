package modelo;

import java.sql.Date;

public class Reserva {
	
	private Date fecha_entrada;
	private Date fecha_salida;
	private double valor_total;
	private String metodo_pago;
	private int id_huesped;
	

	public Reserva(Date fecha_entrada, Date fecha_salida, String metodo_pago, double valor_total) {
		super();
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor_total = valor_total;
		this.metodo_pago = metodo_pago;
	}

	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public int getId_huesped() {
		return id_huesped;
	}

	public void setId_huesped(int id_huesped) {
		this.id_huesped = id_huesped;
	}
	
	@Override
	public String toString() {
		return "Reserva [fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", valor_total="
				+ valor_total + ", metodo_pago=" + metodo_pago + "]";
	}


	
	
	
	
	
	
}
