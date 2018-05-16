package interfazusuario.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import interfazusuario.controlador.Controlador;
import interfazusuario.modelo.Modelo;

public class ImplementacionVista implements Vista {
	private Controlador controlador;
	private Modelo modelo;
	private JFrame ventana = null;
	Container contenedor = null;
	
	
	
	public void setModelo(Modelo modelo) {
		this.modelo=modelo;
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}
	
	public ImplementacionVista() {}
	
	private void GUI() {
		ventana = new JFrame("Empresa Telefónica");
		contenedor = ventana.getContentPane();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		GUIPrincipal();
	}
	
	private void GUIPrincipal(){
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
		EscuchadorPrincipal escuchador = new EscuchadorPrincipal();
		 JButton boton = new JButton("Gestión clientes");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Gestión llamadas");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Gestión facturas");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Salir");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 boton.addActionListener(escuchador);
		 contenedor.add(boton);
	     ventana.pack();
	     ventana.setVisible(true);
	}
	
	private void GUIGestionClientes() {
		 contenedor = ventana.getContentPane();
		 contenedor.removeAll();
		 JButton boton = new JButton("Dar de alta nuevo cliente");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Borrar un cliente");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Cambiar la tarifa de un cliente");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Recuperar los datos de un cliente a partir de su NIF");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Recuperar el listado de todos los clientes");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Mostrar un listado de clientes que fueron dados de alta entre dos fechas");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 boton = new JButton("Atrás");
		 boton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 contenedor.add(boton);
		 ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     ventana.pack();
	     ventana.setVisible(true);
	}
	
    public void creaGUI() {
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI();
			}
		});
    }
    

    
    class EscuchadorPrincipal implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		JButton boton = (JButton)e.getSource();
            String texto = boton.getText();
            if(texto.equals("Gestión Clientes")) {
            	//mostrar ventana gestion clientes
            }
            if(texto.equals("Gestión llamadas" )) {
            	//mostrar ventana gestion llamadas
            }
            if(texto.equals("Gestión facturas")) {
            	//mostrar ventana gestion facturas
            }
            if(texto.equals("Salir")) {
            	ventana.dispose();
            }
    	}
    }

}
