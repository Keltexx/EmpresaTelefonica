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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import interfazusuario.controlador.Controlador;
import interfazusuario.modelo.Modelo;

public class ImplementacionVista implements Vista {
	private Controlador controlador;
	private Modelo modelo;
	private JFrame ventana = null;
	Container contenedor = null;

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
		//panelArriba.setLayout(new BoxLayout(panelArriba, BoxLayout.LINE_AXIS));
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
	
		ventana.setSize(1280, 720);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);

	}

	private void GUICliente() {
		JPanel panelCentral = new JPanel();
		contenedor = ventana.getContentPane();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.LINE_AXIS));
		JButton boton = new JButton("Nuevo cliente");
		panelCentral.add(boton);
		boton = new JButton("Borrar cliente");
		panelCentral.add(boton);
		boton = new JButton("Cambiar tarifa");
		panelCentral.add(boton);
		boton = new JButton("Recuperar datos cliente");
		panelCentral.add(boton);
		boton = new JButton("Recuperar listado clientes");
		panelCentral.add(boton);
		boton = new JButton("Recuperar listado clientes entre fechas");
		panelCentral.add(boton);
		contenedor.add(panelCentral, BorderLayout.WEST);
	}



	public void creaGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI();
			}
		});
	}

	class EscuchadorPrincipal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();
			String texto = boton.getText();
			if (texto.equals("Gestión clientes")) {
				GUICliente();
			}
			if (texto.equals("Gestión llamadas")) {
				// GUILlamadas();
			}
			if (texto.equals("Gestión facturas")) {
				// GUIFacturas();
			}
		}
	}

}
