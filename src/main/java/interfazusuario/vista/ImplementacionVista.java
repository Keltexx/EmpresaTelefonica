package interfazusuario.vista;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import interfazusuario.controlador.Controlador;
import interfazusuario.modelo.Modelo;

public class ImplementacionVista implements Vista {
	private Controlador controlador;
	private Modelo modelo;

	public void setModelo(Modelo modelo) {
		this.modelo=modelo;
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}
	
	public ImplementacionVista() {}
	
	private void GUIPrincipal(){
		 JFrame ventana = new JFrame("Empresa Telefónica");
		 Container contenedor = ventana.getContentPane();
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
		 contenedor.add(boton);
		 ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     ventana.pack();
	     ventana.setVisible(true);
	}
	
    public void creaGUI() {
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUIPrincipal();
			}
		});
    }
    
    class EscuchadorNuevaVentana implements ActionListener{
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
            	//cerrar programa
            }
    	}
    }

}
