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
	JPanel panelFinal = null;

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public ImplementacionVista() {
	}

	// GUI PRINCIPAL

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

	// GUI MENU

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
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Recuperar datos cliente");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Recuperar listado clientes");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Mostrar listado clientes entre fechas");
		boton.addActionListener(escuchador);
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
		EscuchadorLlamada escuchador = new EscuchadorLlamada();
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		JButton boton = new JButton("Dar alta llamada");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Listar llamadas cliente");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Listar llamadas cliente entre fechas");
		boton.addActionListener(escuchador);
		menu.add(boton);
		panelCentral.add(menu, BorderLayout.NORTH);
		panelAbajo = new JPanel();
		panelAbajo.setLayout(new BoxLayout(panelAbajo, BoxLayout.PAGE_AXIS));
		panelCentral.add(panelAbajo, BorderLayout.CENTER);
		panelCentral.updateUI();
	}

	private void GUIFactura() {
		JPanel menu = new JPanel();
		panelCentral.removeAll();
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		EscuchadorFactura escuchador = new EscuchadorFactura();
		JButton boton = new JButton("Emitir factura");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Recuperar datos factura");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Recuperar todas las facturas");
		boton.addActionListener(escuchador);
		menu.add(boton);
		boton = new JButton("Recuperar listado facturas entre fechas");
		boton.addActionListener(escuchador);
		menu.add(boton);
		panelCentral.add(menu, BorderLayout.NORTH);
		panelAbajo = new JPanel();
		panelAbajo.setLayout(new BoxLayout(panelAbajo, BoxLayout.PAGE_AXIS));
		panelCentral.add(panelAbajo, BorderLayout.CENTER);
		panelCentral.updateUI();
	}

	// GUI CLIENTE

	private void GUIDarAlta() {
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

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
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

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelNif.updateUI();
		panelAbajo.updateUI();
	}

	private void GUICambiarTarifa() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelTarifa = new JPanel();
		JLabel tarifaLabel = new JLabel("Tarifa: ");
		JTextField tarifa = new JTextField(5);
		panelTarifa.add(tarifaLabel);
		panelTarifa.add(tarifa);

		panelAbajo.add(panelTarifa);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelTarifa.updateUI();
		panelNif.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIRecuperarCliente() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelSubmit.updateUI();
		panelEspacio.updateUI();
		panelNif.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIRecuperarListado() {
		panelAbajo.removeAll();

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Recuperar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIRecuperarListadoEntreFechas() {
		panelAbajo.removeAll();

		JPanel panelFecha = new JPanel();
		JTextField año = new JTextField(4);
		JTextField mes = new JTextField(2);
		JTextField dia = new JTextField(2);
		JLabel fechaLabel = new JLabel("Fecha de inicio: ");
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

		JPanel panelFecha2 = new JPanel();
		JTextField año2 = new JTextField(4);
		JTextField mes2 = new JTextField(2);
		JTextField dia2 = new JTextField(2);
		JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
		JLabel añoLabel2 = new JLabel("Año: ");
		JLabel mesLabel2 = new JLabel("Mes(numérico): ");
		JLabel diaLabel2 = new JLabel("Día: ");
		panelFecha2.add(fechaLabel2);
		panelFecha2.add(añoLabel2);
		panelFecha2.add(año2);
		panelFecha2.add(mesLabel2);
		panelFecha2.add(mes2);
		panelFecha2.add(diaLabel2);
		panelFecha2.add(dia2);

		panelAbajo.add(panelFecha2);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelFecha.updateUI();
		panelFecha2.updateUI();
		panelAbajo.updateUI();
	}

	// GUI LLAMADAS

	private void GUIDarAltaLlamada() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelTelf = new JPanel();
		JTextField telf = new JTextField(12);
		JLabel telfLabel = new JLabel("Teléfono: ");
		panelTelf.add(telfLabel);
		panelTelf.add(telf);

		panelAbajo.add(panelTelf);

		JPanel panelFecha = new JPanel();
		JTextField año = new JTextField(4);
		JTextField mes = new JTextField(2);
		JTextField dia = new JTextField(2);
		JTextField hora = new JTextField(6);
		JTextField minuto = new JTextField(6);
		JLabel fechaLabel = new JLabel("Fecha de alta: ");
		JLabel añoLabel = new JLabel("Año: ");
		JLabel mesLabel = new JLabel("Mes(numérico): ");
		JLabel diaLabel = new JLabel("Día: ");
		JLabel horaLabel = new JLabel("Hora: ");
		JLabel minutoLabel = new JLabel("Minuto: ");
		panelFecha.add(fechaLabel);
		panelFecha.add(añoLabel);
		panelFecha.add(año);
		panelFecha.add(mesLabel);
		panelFecha.add(mes);
		panelFecha.add(diaLabel);
		panelFecha.add(dia);
		panelFecha.add(horaLabel);
		panelFecha.add(hora);
		panelFecha.add(minutoLabel);
		panelFecha.add(minuto);

		panelAbajo.add(panelFecha);

		JPanel panelDur = new JPanel();
		JTextField dur = new JTextField(5);
		JLabel durLabel = new JLabel("Duración: ");
		panelDur.add(durLabel);
		panelDur.add(dur);

		panelAbajo.add(panelDur);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelDur.updateUI();
		panelFecha.updateUI();
		panelTelf.updateUI();
		panelNif.updateUI();
		panelAbajo.updateUI();

	}

	private void GUIListarLlamadas() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelNif.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIListarLlamadasEntreFechas() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelFecha = new JPanel();
		JTextField año = new JTextField(4);
		JTextField mes = new JTextField(2);
		JTextField dia = new JTextField(2);
		JLabel fechaLabel = new JLabel("Fecha de inicio: ");
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

		JPanel panelFecha2 = new JPanel();
		JTextField año2 = new JTextField(4);
		JTextField mes2 = new JTextField(2);
		JTextField dia2 = new JTextField(2);
		JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
		JLabel añoLabel2 = new JLabel("Año: ");
		JLabel mesLabel2 = new JLabel("Mes(numérico): ");
		JLabel diaLabel2 = new JLabel("Día: ");
		panelFecha2.add(fechaLabel2);
		panelFecha2.add(añoLabel2);
		panelFecha2.add(año2);
		panelFecha2.add(mesLabel2);
		panelFecha2.add(mes2);
		panelFecha2.add(diaLabel2);
		panelFecha2.add(dia2);

		panelAbajo.add(panelFecha2);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelNif.updateUI();
		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelFecha.updateUI();
		panelFecha2.updateUI();
		panelAbajo.updateUI();
	}

	// GUI FACTURAS

	private void GUIEmitirFactura() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelFecha = new JPanel();
		JTextField año = new JTextField(4);
		JTextField mes = new JTextField(2);
		JTextField dia = new JTextField(2);
		JLabel fechaLabel = new JLabel("Fecha: ");
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

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelFecha.updateUI();
		panelNif.updateUI();
		panelAbajo.updateUI();

	}

	private void GUIRecuperarDatosFactura() {
		panelAbajo.removeAll();

		JPanel panelcodFac = new JPanel();
		JTextField codFac = new JTextField(20);
		JLabel codFacLabel = new JLabel("Código de factura: ");
		panelcodFac.add(codFacLabel);
		panelcodFac.add(codFac);

		panelAbajo.add(panelcodFac);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelSubmit.updateUI();
		panelEspacio.updateUI();
		panelcodFac.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIRecuperarTodasFacturas() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelSubmit.updateUI();
		panelEspacio.updateUI();
		panelNif.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIRecuperarFacturasEntreFechas() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		JTextField nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelFecha = new JPanel();
		JTextField año = new JTextField(4);
		JTextField mes = new JTextField(2);
		JTextField dia = new JTextField(2);
		JLabel fechaLabel = new JLabel("Fecha de inicio: ");
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

		JPanel panelFecha2 = new JPanel();
		JTextField año2 = new JTextField(4);
		JTextField mes2 = new JTextField(2);
		JTextField dia2 = new JTextField(2);
		JLabel fechaLabel2 = new JLabel("Fecha de fin: ");
		JLabel añoLabel2 = new JLabel("Año: ");
		JLabel mesLabel2 = new JLabel("Mes(numérico): ");
		JLabel diaLabel2 = new JLabel("Día: ");
		panelFecha2.add(fechaLabel2);
		panelFecha2.add(añoLabel2);
		panelFecha2.add(año2);
		panelFecha2.add(mesLabel2);
		panelFecha2.add(mes2);
		panelFecha2.add(diaLabel2);
		panelFecha2.add(dia2);

		panelAbajo.add(panelFecha2);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                    "
						+ "                                                                                                                                                                                               ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		JButton submit = new JButton("Enviar");
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelNif.updateUI();
		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelFecha.updateUI();
		panelFecha2.updateUI();
		panelAbajo.updateUI();
	}

	// CREAR GUI

	public void creaGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI();
			}
		});
	}

	class EscuchadorFactura implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();
			String texto = boton.getText();
			if (texto.equals("Emitir factura")) {
				GUIEmitirFactura();
			}
			if (texto.equals("Recuperar datos factura")) {
				GUIRecuperarDatosFactura();
			}
			if (texto.equals("Recuperar todas las facturas")) {
				GUIRecuperarTodasFacturas();
			}
			if (texto.equals("Recuperar listado facturas entre fechas")) {
				GUIRecuperarFacturasEntreFechas();
			}
		}
	}

	class EscuchadorLlamada implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();
			String texto = boton.getText();
			if (texto.equals("Dar alta llamada")) {
				GUIDarAltaLlamada();
			}
			if (texto.equals("Listar llamadas cliente")) {
				GUIListarLlamadas();
			}
			if (texto.equals("Listar llamadas cliente entre fechas")) {
				GUIListarLlamadasEntreFechas();
			}
		}
	}

	class EscuchadorCliente implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();
			String texto = boton.getText();
			if (texto.equals("Nuevo cliente")) {
				GUIDarAlta();
			}
			if (texto.equals("Borrar cliente")) {
				GUIBorrarCliente();
			}
			if (texto.equals("Cambiar tarifa")) {
				GUICambiarTarifa();
			}
			if (texto.equals("Recuperar datos cliente")) {
				GUIRecuperarCliente();
			}
			if (texto.equals("Recuperar listado clientes")) {
				GUIRecuperarListado();
			}
			if (texto.equals("Mostrar listado clientes entre fechas")) {
				GUIRecuperarListadoEntreFechas();
			}
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
	
	class EscuchadorClienteControlador implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();
			String texto = boton.getText();
		}
	}

}
