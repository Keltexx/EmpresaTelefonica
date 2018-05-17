package interfazusuario.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import interfazusuario.controlador.Controlador;
import interfazusuario.modelo.Modelo;

public class ImplementacionVista implements Vista {
	private Controlador controlador;
	private Modelo modelo;
	private JFrame ventana = null;
	Container contenedor = null;
	JPanel panelCentral = null;
	JPanel panelAbajo = null;
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public ImplementacionVista() {
	}

	private void GUI() {
		ventana = new JFrame("Empresa Telefónica");
		contenedor = ventana.getContentPane();
		EscuchadorPrincipal escuchador = new EscuchadorPrincipal();
		JPanel panelArriba = new JPanel();
		JButton boton = new JButton("Gestión clientes");
		boton.addActionListener(escuchador);
		panelArriba.add(boton);
		boton = new JButton("Gestión llamadas");
		boton.addActionListener(escuchador);
		panelArriba.add(boton);
		boton = new JButton("Gestión facturas");
		boton.addActionListener(escuchador);
		panelArriba.add(boton);
		contenedor.add(panelArriba, BorderLayout.NORTH);
		panelCentral = new JPanel();
		contenedor.add(panelCentral, BorderLayout.CENTER);
		ventana.setSize(1280, 720);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);

	}

	private void GUICliente() {
		JPanel menu = new JPanel();
		panelCentral.removeAll();
		EscuchadorCliente escuchador = new EscuchadorCliente();
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		JButton boton = new JButton("Nuevo cliente");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Borrar cliente");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Cambiar tarifa");
		menu.add(boton);
		boton = new JButton("Recuperar datos cliente");
		menu.add(boton);
		boton = new JButton("Recuperar listado clientes");
		menu.add(boton);
		boton = new JButton("Recuperar listado clientes entre fechas");
		menu.add(boton);
		panelCentral.add(menu, BorderLayout.NORTH);
		panelAbajo = new JPanel();
		panelAbajo.setLayout(new BoxLayout(panelAbajo, BoxLayout.PAGE_AXIS));
		panelCentral.add(panelAbajo, BorderLayout.CENTER);
		panelCentral.updateUI();
	}

	private void GUILlamada() {
		JPanel menu = new JPanel();
		panelCentral.removeAll();
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		JButton boton = new JButton("Dar alta llamada");
		menu.add(boton);
		boton = new JButton("Listar llamadas cliente");
		menu.add(boton);
		boton = new JButton("Listar llamadas cliente entre fechas");
		menu.add(boton);
		panelCentral.add(menu, BorderLayout.NORTH);
		panelAbajo = new JPanel();
		panelCentral.add(panelAbajo, BorderLayout.CENTER);
		panelCentral.updateUI();
	}
	
	private void GUIFactura() {
		JPanel menu = new JPanel();
		panelCentral.removeAll();
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		JButton boton = new JButton("Emitir factura");
		menu.add(boton);
		boton = new JButton("Recuperar datos factura");
		menu.add(boton);
		boton = new JButton("Recuperar todas las facturas");
		menu.add(boton);
		boton = new JButton("Recuperar listado facturas entre fechas");
		menu.add(boton);
		panelCentral.add(menu, BorderLayout.NORTH);
		panelAbajo = new JPanel();
		panelCentral.add(panelAbajo, BorderLayout.CENTER);
		panelCentral.updateUI();
	}

	
	private void GUIDarAlta(){
		panelAbajo.removeAll();
		
		
		JPanel panelEmpresa = new JPanel();
		JRadioButton si = new JRadioButton("si");
		JRadioButton no = new JRadioButton("no");
		panelEmpresa.add(new JLabel("¿Eres una empresa?"));
		panelEmpresa.add(si);
		panelEmpresa.add(no);
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(si);
		grupo.add(no);
		
		panelAbajo.add(panelEmpresa);
		
		JPanel panelNombre = new JPanel();
		JTextField nombre = new JTextField(20);
		JLabel nombreLabel = new JLabel("Nombre: ");
		panelNombre.add(nombreLabel);
		panelNombre.add(nombre);
		
		panelAbajo.add(panelNombre);
		
		JPanel panelApellido = new JPanel();
		JTextField apellido = new JTextField(20);
		JLabel apellidoLabel = new JLabel("Apellido (Sólo si eres un particular): ");
		panelApellido.add(apellidoLabel);
		panelApellido.add(apellido);
		
		panelAbajo.add(panelApellido);
		
		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);
		
		panelAbajo.add(panelNif);
		
		JPanel panelDireccion = new JPanel();
		JTextField codPos = new JTextField(5);
		JTextField prov = new JTextField(20);
		JTextField pob = new JTextField(30);
		JLabel direccionLabel = new JLabel("Dirección: ");
		JLabel codPosLabel = new JLabel("Código Postal: ");
		JLabel provLabel = new JLabel("Provincia: ");
		JLabel pobLabel = new JLabel("Población: ");
		
		panelDireccion.add(direccionLabel);
		panelDireccion.add(codPosLabel);
		panelDireccion.add(codPos);
		panelDireccion.add(provLabel);
		panelDireccion.add(prov);
		panelDireccion.add(pobLabel);
		panelDireccion.add(pob);
		
		panelAbajo.add(panelDireccion);
		
		JPanel panelCorreo = new JPanel();
		JTextField correo = new JTextField(30);
		JLabel correoLabel = new JLabel("Correo: ");
		panelCorreo.add(correoLabel);
		panelCorreo.add(correo);
		
		panelAbajo.add(panelCorreo);
		
		JPanel panelFecha = new JPanel();
		JTextField año = new JTextField(4);
		JTextField mes = new JTextField(2);
		JTextField dia = new JTextField(2);
		JLabel fechaLabel = new JLabel("Fecha de alta: ");
		JLabel añoLabel = new JLabel("Año: ");
		JLabel mesLabel = new JLabel("Mes(numérico): ");
		JLabel diaLabel = new JLabel("Día: ");
		panelFecha.add(fechaLabel);
		panelFecha.add(añoLabel);
		panelFecha.add(año);
		panelFecha.add(mesLabel);
		panelFecha.add(mes);
		panelFecha.add(diaLabel);
		panelFecha.add(dia);
		
		panelAbajo.add(panelFecha);
		
		JPanel panelTarifa = new JPanel();
		JTextField tarifa = new JTextField(5);
		JLabel tarifaLabel = new JLabel("Tarifa: ");
		panelTarifa.add(tarifaLabel);
		panelTarifa.add(tarifa);
		
		panelAbajo.add(panelTarifa);
		
		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);
		
		panelAbajo.add(submit);
		
		panelSubmit.updateUI();
		panelTarifa.updateUI();
		panelFecha.updateUI();
		panelCorreo.updateUI();
		panelDireccion.updateUI();
		panelApellido.updateUI();
		panelNombre.updateUI();
		panelNif.updateUI();
		panelEmpresa.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIBorrarCliente() {
		panelAbajo.removeAll();

		

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);
		
		panelAbajo.add(panelNif);
		

		
		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);
		
		panelAbajo.add(submit);
		
		panelSubmit.updateUI();
		
		panelNif.updateUI();
		panelAbajo.updateUI();
	}
	
	public void creaGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI();
			}
		});
	}

	class EscuchadorCliente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();
			String texto = boton.getText();
			if (texto.equals("Nuevo cliente")) {
				GUIDarAlta();
			}
			if (texto.equals("Borrar cliente")) {
				GUIBorrarCliente();
			}
//			if (texto.equals("Gestión facturas")) {
//				GUIFactura();
//			}
		}
	}
	
	class EscuchadorPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();
			String texto = boton.getText();
			if (texto.equals("Gestión clientes")) {
				GUICliente();
			}
			if (texto.equals("Gestión llamadas")) {
				GUILlamada();
			}
			if (texto.equals("Gestión facturas")) {
				GUIFactura();
			}
		}
	}

}
