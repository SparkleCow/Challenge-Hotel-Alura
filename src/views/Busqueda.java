package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.RepositoryController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.jgoodies.forms.factories.DefaultComponentFactory;

@SuppressWarnings("serial")
public class Busqueda extends JFrame implements KeyListener {

	private int xMouse, yMouse;
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private RepositoryController repository = new RepositoryController();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Busqueda() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(510, 127, 219, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBuscar.addKeyListener(this);
		contentPane.add(txtBuscar);		
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(255, 127, 80));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 340, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(255, 127, 80));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		modelo.addColumn("Id de huesped");
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		modelo = llenarTablaReservas(modelo);	

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		/*
		 *With this list interface we will put rows in the model table 
		 */
		modeloHuesped = llenarTablaHuespedes(modeloHuesped);
	
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		
		JPanel btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { 
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { 
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(510, 159, 222, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(255, 127, 80));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		contentPane.add(btnbuscar);
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(!txtBuscar.getText().isEmpty()) {
						limpiarTabla();
						modeloHuesped = filtrarTablaHuespedes(modeloHuesped, Integer.valueOf(txtBuscar.getText()));
						modelo = filtrarTablaReservas(modelo, Integer.valueOf(txtBuscar.getText()));	
					}else{
						limpiarTabla();
						modelo = llenarTablaReservas(modelo);
						modeloHuesped = llenarTablaHuespedes(modeloHuesped);
					}
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ingrese un ID valido");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) { 
				btnbuscar.setBackground(new Color(235, 127, 80));
			}			
			@Override
			public void mouseExited(MouseEvent e) { 
				 btnbuscar.setBackground(new Color(255, 127, 80));
			}
		});

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(255, 127, 80));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		contentPane.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int filaSeleccionadaHuesped = tbHuespedes.getSelectedRow();
				int filaSeleccionadaReserva = tbReservas.getSelectedRow();
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				boolean ejecutado;
				
				if((filaSeleccionadaHuesped==-1) && (filaSeleccionadaReserva==-1)) {
					JOptionPane.showMessageDialog(null, "Elige una reserva/huesped a editar");	
				}if(filaSeleccionadaHuesped!=-1 && filaSeleccionadaReserva!=-1) {
					JOptionPane.showMessageDialog(null, "Elige un un solo dato de una unica tabla");		
				}else if(filaSeleccionadaHuesped!=-1) {
					String fechaString = tbHuespedes.getValueAt(filaSeleccionadaHuesped, 3).toString();
					Date fechaConvertida;
					try {	
						fechaConvertida = formato.parse(fechaString);
						long fechaLong = fechaConvertida.getTime();
						java.sql.Date fechaSQL = new java.sql.Date(fechaLong);
						
						String nombre = tbHuespedes.getValueAt(filaSeleccionadaHuesped, 1).toString();
						String apellido = tbHuespedes.getValueAt(filaSeleccionadaHuesped, 2).toString();
						String nacionalidad = tbHuespedes.getValueAt(filaSeleccionadaHuesped, 4).toString();
						String telefono = tbHuespedes.getValueAt(filaSeleccionadaHuesped, 5).toString();
						int id_huesped = Integer.parseInt(tbHuespedes.getValueAt(filaSeleccionadaHuesped, 0).toString());
						
						ejecutado = repository.actualizarHuesped(nombre, apellido, fechaSQL, nacionalidad, telefono, id_huesped);
						limpiarTabla();
						modeloHuesped = llenarTablaHuespedes(modeloHuesped);
						modelo = llenarTablaReservas(modelo);
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
				}else if(filaSeleccionadaReserva!=-1) {
					String fechaEntradaString = tbReservas.getValueAt(filaSeleccionadaReserva, 1).toString();
					String fechaSalidaString = tbReservas.getValueAt(filaSeleccionadaReserva, 2).toString();
					Date fechaEntradaConvertida;
					Date fechaSalidaConvertida;
					try {
						fechaEntradaConvertida = formato.parse(fechaEntradaString);
						long fechaLongEntrada = fechaEntradaConvertida.getTime();
						java.sql.Date fechaEntradaSQL = new java.sql.Date(fechaLongEntrada);
						
						fechaSalidaConvertida = formato.parse(fechaSalidaString);
						long fechaLongSalida = fechaSalidaConvertida.getTime();
						java.sql.Date fechaSalidaSQL = new java.sql.Date(fechaLongSalida);
						
						Double valor = Double.parseDouble(tbReservas.getValueAt(filaSeleccionadaReserva, 3).toString());
						String metodo = tbReservas.getValueAt(filaSeleccionadaReserva, 4).toString();
						int id_huesped = Integer.parseInt(tbReservas.getValueAt(filaSeleccionadaReserva, 5).toString());
						int id = Integer.parseInt(tbReservas.getValueAt(filaSeleccionadaReserva, 0).toString());
						
						ejecutado = repository.actualizarReserva(fechaEntradaSQL, fechaSalidaSQL, valor, metodo, id_huesped, id);
						limpiarTabla();
						modeloHuesped = llenarTablaHuespedes(modeloHuesped);
						modelo = llenarTablaReservas(modelo);
					}catch(ParseException ex) {
						ex.printStackTrace();
					}
				}
			}
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnEditar.setBackground(new Color(235, 127, 80));
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnEditar.setBackground(new Color(255, 127, 80));
			}
		});
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(255, 127, 80));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		contentPane.add(btnEliminar);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Buscar por ID de huesped");
		lblNewJgoodiesLabel.setBounds(331, 135, 169, 14);
		contentPane.add(lblNewJgoodiesLabel);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int filaSeleccionadaHuesped = tbHuespedes.getSelectedRow();
				int filaSeleccionadaReserva = tbReservas.getSelectedRow();
				int idHuesped;
				int idReserva;
				
				if((filaSeleccionadaHuesped==-1) && (filaSeleccionadaReserva==-1)) {
					JOptionPane.showMessageDialog(null, "Elige una reserva/huesped a eliminar");
				}if(filaSeleccionadaHuesped!=-1 && filaSeleccionadaReserva!=-1) {
					JOptionPane.showMessageDialog(null, "Elige un un solo dato de una unica tabla");
				}if(filaSeleccionadaHuesped!=-1) {
					String id_huesped = tbHuespedes.getValueAt(filaSeleccionadaHuesped, 0).toString();
					idHuesped = Integer.parseInt(id_huesped);
					repository.eliminarHuesped(idHuesped);				
					limpiarTabla();
					modeloHuesped = llenarTablaHuespedes(modeloHuesped);
					modelo = llenarTablaReservas(modelo);
				}if(filaSeleccionadaReserva!=-1) {
					String id_reserva = tbReservas.getValueAt(filaSeleccionadaReserva, 0).toString();
					idReserva = Integer.parseInt(id_reserva);				
					repository.eliminarReserva(idReserva);
					limpiarTabla();
					modelo = llenarTablaReservas(modelo);
					modeloHuesped = llenarTablaHuespedes(modeloHuesped);	
				}
			}
			public void mouseEntered(MouseEvent e) { 
				btnEliminar.setBackground(new Color(235, 127, 80));
			}			
			@Override
			public void mouseExited(MouseEvent e) { 
				 btnEliminar.setBackground(new Color(255, 127, 80));
			}
		});
		
		
		setResizable(false);
	}
	
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	}
	    
	public void limpiarTabla(){
		modelo.setRowCount(0);
		modeloHuesped.setRowCount(0);
	}
	
	/*
	 *With this list interface we will put rows in the model table 
	 */ 
	
	public DefaultTableModel llenarTablaReservas(DefaultTableModel modelo) {
		List<Map<String, String>> listaReserva =  repository.listarReservas();
		listaReserva.forEach(reserva -> modelo.addRow(new Object[] {
				reserva.get("id"), reserva.get("fecha_inicio"), reserva.get("fecha_salida"), reserva.get("valor_total"),
				reserva.get("metodo_pago"), reserva.get("id_huesped")
		}));
		return modelo;
	}
	public DefaultTableModel llenarTablaHuespedes(DefaultTableModel modeloHuesped) {
		List<Map<String, String>> listaHuesped = repository.listarHuespedes();
		listaHuesped.forEach(reserva -> modeloHuesped.addRow(new Object[] {
				reserva.get("id"), reserva.get("nombre"), reserva.get("apellido"), reserva.get("fecha"),
				reserva.get("nacionalidad"),reserva.get("telefono")
		}));
		return modeloHuesped;
	}
	
	public DefaultTableModel filtrarTablaReservas(DefaultTableModel modelo, int id) {
		List<Map<String, String>> listaReserva =  repository.filtrarReservas(id);
		listaReserva.forEach(reserva -> modelo.addRow(new Object[] {
				reserva.get("id"), reserva.get("fecha_inicio"), reserva.get("fecha_salida"), reserva.get("valor_total"),
				reserva.get("metodo_pago"), reserva.get("id_huesped")
		}));
		return modelo;
	}
	public DefaultTableModel filtrarTablaHuespedes(DefaultTableModel modeloHuesped, int id) {
		List<Map<String, String>> listaHuesped = repository.filtrarHuespedes(id);
		listaHuesped.forEach(reserva -> modeloHuesped.addRow(new Object[] {
				reserva.get("id"), reserva.get("nombre"), reserva.get("apellido"), reserva.get("fecha"),
				reserva.get("nacionalidad"),reserva.get("telefono")
		}));
		return modeloHuesped;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource()==txtBuscar) {
			char c = e.getKeyChar();
			if(c<'0' || c>'9') {
				e.consume();
			}
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}	
}
