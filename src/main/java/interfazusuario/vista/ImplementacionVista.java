package interfazusuario.vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Collection;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import cliente.Cliente;
import cliente.Direccion;
import excepciones.ExcepcionClienteNoEncontrado;
import excepciones.ExcepcionClienteYaRegistrado;
import excepciones.ExcepcionFacturaNoEncontrada;
import excepciones.ExcepcionFechas;
import excepciones.ExcepcionListaClientesVacia;
import excepciones.ExcepcionListaFacturasVacia;
import excepciones.ExcepcionListaLlamadasVacia;
import factura.Factura;
import factura.Llamada;
import factura.Tarifa;
import factura.TarifaFactory;
import interfazusuario.controlador.Controlador;
import interfazusuario.modelo.Modelo;

public class ImplementacionVista implements Vista {
	private Controlador controlador;
	private Modelo modelo;
	private JFrame ventana = null;
	Container contenedor = null;
	JPanel panelCentral = null;
	JPanel panelAbajo = null;
	JPanel panelFinal = new JPanel();
	JTextField nif = null;
	JTextField nombre = null;
	JTextField apellido = null;
	JTextField codPos = null;
	JTextField pob = null;
	JTextField prov = null;
	JTextField correo = null;
	JTextField dia = null;
	JTextField mes = null;
	JTextField año = null;
	JTextField tarifa = null;
	JTextField dia2 = null;
	JTextField mes2 = null;
	JTextField año2 = null;
	JTextField telf = null;
	JTextField codFac = null;
	JTextField hora = null;
	JTextField minuto = null;
	JTextField dur = null;
	JButton submit = null;
	int tipo;
	JTextField tipoTar;

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
		panelArriba.setPreferredSize(new Dimension(1080, 40));
		contenedor.add(panelCentral, BorderLayout.CENTER);
		ventana.setSize(1280, 400);
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
		menu.setPreferredSize(new Dimension(1200, 40));
		panelCentral.add(menu, BorderLayout.NORTH);
		panelAbajo = new JPanel();
		panelAbajo.setPreferredSize(new Dimension(1200, 600));
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
		panelAbajo.setPreferredSize(new Dimension(1200, 600));
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
		panelAbajo.setPreferredSize(new Dimension(1200, 600));
		panelCentral.add(panelAbajo, BorderLayout.CENTER);
		panelCentral.updateUI();
	}

	// GUI CLIENTE

	private void GUIDarAlta() {
		panelAbajo.removeAll();

		JPanel panelEmpresa = new JPanel();
		tipo = 1;
		JRadioButton si = new JRadioButton("si");
		JRadioButton no = new JRadioButton("no");
		si.setActionCommand("si");
		si.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				switch (e.getStateChange()) {
				case ItemEvent.SELECTED:
					tipo = 0;
					break;
				case ItemEvent.DESELECTED:
					tipo = 1;
					break;
				}
			}
		});
		panelEmpresa.add(new JLabel("¿Eres una empresa?"));
		panelEmpresa.add(si);
		panelEmpresa.add(no);
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(si);
		grupo.add(no);

		panelAbajo.add(panelEmpresa);

		JPanel panelNombre = new JPanel();
		nombre = new JTextField(20);
		JLabel nombreLabel = new JLabel("Nombre: ");
		panelNombre.add(nombreLabel);
		panelNombre.add(nombre);

		panelAbajo.add(panelNombre);

		JPanel panelApellido = new JPanel();
		apellido = new JTextField(20);
		JLabel apellidoLabel = new JLabel("Apellido (Sólo si eres un particular): ");
		panelApellido.add(apellidoLabel);
		panelApellido.add(apellido);

		panelAbajo.add(panelApellido);

		JPanel panelNif = new JPanel();
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelDireccion = new JPanel();
		codPos = new JTextField(5);
		prov = new JTextField(20);
		pob = new JTextField(30);
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
		correo = new JTextField(30);
		JLabel correoLabel = new JLabel("Correo: ");
		panelCorreo.add(correoLabel);
		panelCorreo.add(correo);

		panelAbajo.add(panelCorreo);

		JPanel panelFecha = new JPanel();
		año = new JTextField(4);
		mes = new JTextField(2);
		dia = new JTextField(2);
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
		tarifa = new JTextField(5);
		JLabel tarifaLabel = new JLabel("Importe tarifa: ");
		panelTarifa.add(tarifaLabel);
		panelTarifa.add(tarifa);

		panelAbajo.add(panelTarifa);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		EscuchadorDarAlta escuchadorDarAlta = new EscuchadorDarAlta();
		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		submit.addActionListener(escuchadorDarAlta);
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
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorBorrarCliente escuchadorBorrarCliente = new EscuchadorBorrarCliente();
		submit.addActionListener(escuchadorBorrarCliente);
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
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelTipoTar = new JPanel();
		JLabel tipoTarLabel = new JLabel("Tarifa	(0 Básica) (1 Domingo) (2 Tardes): ");
		tipoTar = new JTextField(5);
		panelTipoTar.add(tipoTarLabel);
		panelTipoTar.add(tipoTar);

		panelAbajo.add(panelTipoTar);

		JPanel panelTarifa = new JPanel();
		JLabel tarifaLabel = new JLabel("Importe: ");
		tarifa = new JTextField(5);
		panelTarifa.add(tarifaLabel);
		panelTarifa.add(tarifa);

		panelAbajo.add(panelTarifa);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorCambiarTarifa escuchadorCambiarTarifa = new EscuchadorCambiarTarifa();
		submit.addActionListener(escuchadorCambiarTarifa);
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelTipoTar.updateUI();
		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelTarifa.updateUI();
		panelNif.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIRecuperarCliente() {
		panelAbajo.removeAll();

		JPanel panelNif = new JPanel();
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorRecuperarCliente escuchadorRecuperarCliente = new EscuchadorRecuperarCliente();
		submit.addActionListener(escuchadorRecuperarCliente);
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
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Recuperar");
		EscuchadorRecuperarTodos escuchadorTodos = new EscuchadorRecuperarTodos();
		submit.addActionListener(escuchadorTodos);
		panelSubmit.add(submit);

		panelAbajo.add(submit);

		panelEspacio.updateUI();
		panelSubmit.updateUI();
		panelAbajo.updateUI();
	}

	private void GUIRecuperarListadoEntreFechas() {
		panelAbajo.removeAll();

		JPanel panelFecha = new JPanel();
		año = new JTextField(4);
		mes = new JTextField(2);
		dia = new JTextField(2);
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
		año2 = new JTextField(4);
		mes2 = new JTextField(2);
		dia2 = new JTextField(2);
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
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorRecuperarTodosEntreFechas escuchadorFechas = new EscuchadorRecuperarTodosEntreFechas();
		submit.addActionListener(escuchadorFechas);
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
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelTelf = new JPanel();
		telf = new JTextField(12);
		JLabel telfLabel = new JLabel("Teléfono: ");
		panelTelf.add(telfLabel);
		panelTelf.add(telf);

		panelAbajo.add(panelTelf);

		JPanel panelFecha = new JPanel();
		año = new JTextField(4);
		mes = new JTextField(2);
		dia = new JTextField(2);
		hora = new JTextField(6);
		minuto = new JTextField(6);
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
		dur = new JTextField(5);
		JLabel durLabel = new JLabel("Duración: ");
		panelDur.add(durLabel);
		panelDur.add(dur);

		panelAbajo.add(panelDur);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorDarAltaLlamada escuchadorAltaLlamada = new EscuchadorDarAltaLlamada();
		submit.addActionListener(escuchadorAltaLlamada);
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
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorRecuperarTodasLlamadas escuchadorRecuperarTodasLlamadas = new EscuchadorRecuperarTodasLlamadas();
		submit.addActionListener(escuchadorRecuperarTodasLlamadas);
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
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelFecha = new JPanel();
		año = new JTextField(4);
		mes = new JTextField(2);
		dia = new JTextField(2);
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
		año2 = new JTextField(4);
		mes2 = new JTextField(2);
		dia2 = new JTextField(2);
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
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorRecuperarTodasLlamadasEntreFechas escRTL = new EscuchadorRecuperarTodasLlamadasEntreFechas();
		submit.addActionListener(escRTL);
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
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelFecha = new JPanel();
		año = new JTextField(4);
		mes = new JTextField(2);
		dia = new JTextField(2);
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
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorEmitirFactura escEF = new EscuchadorEmitirFactura();
		submit.addActionListener(escEF);
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
		codFac = new JTextField(20);
		JLabel codFacLabel = new JLabel("Código de factura: ");
		panelcodFac.add(codFacLabel);
		panelcodFac.add(codFac);

		panelAbajo.add(panelcodFac);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorRecuperarFacturas escRF = new EscuchadorRecuperarFacturas();
		submit.addActionListener(escRF);
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
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelEspacio = new JPanel();
		JLabel espacioLabel = new JLabel(
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorRecuperarTodasFacturas escRTF = new EscuchadorRecuperarTodasFacturas();
		submit.addActionListener(escRTF);
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
		nif = new JTextField(8);
		JLabel nifLabel = new JLabel("NIF: ");
		panelNif.add(nifLabel);
		panelNif.add(nif);

		panelAbajo.add(panelNif);

		JPanel panelFecha = new JPanel();
		año = new JTextField(4);
		mes = new JTextField(2);
		dia = new JTextField(2);
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
		año2 = new JTextField(4);
		mes2 = new JTextField(2);
		dia2 = new JTextField(2);
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
				"                                                                                                                                                                                                                "
						+ "                                                                                                                                                                                                                                                                                 ");
		panelEspacio.add(espacioLabel);

		panelAbajo.add(panelEspacio);

		JPanel panelSubmit = new JPanel();
		submit = new JButton("Enviar");
		EscuchadorRecuperarTodasFacturasEntreFechas escTFEF = new EscuchadorRecuperarTodasFacturasEntreFechas();
		submit.addActionListener(escTFEF);
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

	// ESCUCHADORES CLIENTE

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

	class EscuchadorDarAlta implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				Calendar fecha = Calendar.getInstance();
				int añoLocal = Integer.parseInt(año.getText().trim());
				int mesLocal = Integer.parseInt(mes.getText().trim());
				int diaLocal = Integer.parseInt(dia.getText().trim());
				fecha.set(añoLocal, mesLocal, diaLocal);
				int codPosLocal = Integer.parseInt(codPos.getText().trim());
				Direccion dir = new Direccion(codPosLocal, prov.getText(), pob.getText());
				Tarifa tarifaLocal = null;
				tarifaLocal = TarifaFactory.crearTarifa(0, tarifaLocal, Double.parseDouble(tarifa.getText().trim()));
				try {
					controlador.creaCliente(tipo, nombre.getText(), apellido.getText(), nif.getText(), dir,
							correo.getText(), fecha, tarifaLocal);
					modelo.guardarDatos();

					JLabel clienteRegistrado = new JLabel("Cliente registrado con éxito");
					panelFinal.add(clienteRegistrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteYaRegistrado e1) {
					JLabel clienteNoRegistrado = new JLabel("Cliente ya existente");
					panelFinal.add(clienteNoRegistrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorBorrarCliente implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					controlador.borraCliente(nif.getText());
					modelo.guardarDatos();
					JLabel clienteBorrado = new JLabel("Cliente borrado con éxito");
					panelFinal.add(clienteBorrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorCambiarTarifa implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			Tarifa tarifaLocal = null;
			tarifaLocal = TarifaFactory.crearTarifa(Integer.parseInt(tipoTar.getText()), tarifaLocal,
					Double.parseDouble(tarifa.getText().trim()));
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					controlador.cambiaTarifa(nif.getText(), tarifaLocal);
					modelo.guardarDatos();
					panelFinal = new JPanel();
					JLabel tarifaCambiada = new JLabel("Tarifa cambiada con éxito");
					panelFinal.add(tarifaCambiada);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorRecuperarCliente implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					JLabel cliente = new JLabel(controlador.recuperarDatosCliente(nif.getText()).toString());
					JScrollPane scroll = new JScrollPane(cliente);
					scroll.setPreferredSize(new Dimension(1080, 100));
					panelFinal.add(scroll);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorRecuperarTodosEntreFechas implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			Calendar fechaInicio = Calendar.getInstance();
			int añoLocal = Integer.parseInt(año.getText().trim());
			int mesLocal = Integer.parseInt(mes.getText().trim());
			int diaLocal = Integer.parseInt(dia.getText().trim());
			fechaInicio.set(añoLocal, mesLocal, diaLocal);
			Calendar fechaFin = Calendar.getInstance();
			int añoLocal2 = Integer.parseInt(año2.getText().trim());
			int mesLocal2 = Integer.parseInt(mes2.getText().trim());
			int diaLocal2 = Integer.parseInt(dia2.getText().trim());
			fechaFin.set(añoLocal2, mesLocal2, diaLocal2);
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					Collection<Cliente> col = controlador.recuperaListadoClientesEntreFechas(fechaInicio, fechaFin);
					DefaultListModel<String> datos = new DefaultListModel<>();
					for (Cliente cliente : col) {
						datos.addElement(cliente.toString());
					}
					JList<String> clientes = new JList<String>(datos);
					JScrollPane scroll = new JScrollPane(clientes);
					scroll.setPreferredSize(new Dimension(1080, 400));
					clientes.setVisibleRowCount(30);
					panelFinal.add(scroll);
					panelAbajo.add(panelFinal);
					scroll.updateUI();
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionListaClientesVacia e1) {
					JLabel clientesNoEncontrados = new JLabel("Clientes no encontrados");
					panelFinal.add(clientesNoEncontrados);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionFechas e1) {
					JLabel clientesNoEncontrados = new JLabel("Fecha no válida");
					panelFinal.add(clientesNoEncontrados);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorRecuperarTodos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Recuperar")) {
				panelFinal.removeAll();
				try {
					Collection<Cliente> col = controlador.recuperaListadoClientes().values();
					DefaultListModel<String> datos = new DefaultListModel<>();
					for (Cliente cliente : col) {
						datos.addElement(cliente.toString());
					}
					JList<String> clientes = new JList<String>(datos);
					JScrollPane scroll = new JScrollPane(clientes);
					scroll.setPreferredSize(new Dimension(1080, 400));
					clientes.setVisibleRowCount(30);
					panelFinal.add(scroll);
					panelAbajo.add(panelFinal);
					scroll.updateUI();
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionListaClientesVacia e1) {
					JLabel clientesNoEncontrados = new JLabel("Clientes no encontrados");
					panelFinal.add(clientesNoEncontrados);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorDarAltaLlamada implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				Calendar fecha = Calendar.getInstance();
				int añoLocal = Integer.parseInt(año.getText().trim());
				int mesLocal = Integer.parseInt(mes.getText().trim());
				int diaLocal = Integer.parseInt(dia.getText().trim());
				int horaLocal = Integer.parseInt(hora.getText().trim());
				int minLocal = Integer.parseInt(minuto.getText().trim());
				fecha.set(añoLocal, mesLocal, diaLocal, horaLocal, minLocal);
				int telefono = Integer.parseInt(telf.getText().trim());
				int dura = Integer.parseInt(dur.getText().trim());

				Llamada llamada = new Llamada(telefono, fecha, dura);
				try {
					controlador.darDeAltaLlamada(nif.getText(), llamada);
					modelo.guardarDatos();

					JLabel llamadaRegistrada = new JLabel("Llamada registrada con éxito");
					panelFinal.add(llamadaRegistrada);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorRecuperarTodasLlamadas implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					Collection<Llamada> col = controlador.listarLlamadasCliente(nif.getText());
					DefaultListModel<String> datos = new DefaultListModel<>();
					for (Llamada llamada : col) {
						datos.addElement(llamada.toString());
					}
					JList<String> llamadas = new JList<String>(datos);
					JScrollPane scroll = new JScrollPane(llamadas);
					scroll.setPreferredSize(new Dimension(1080, 400));
					llamadas.setVisibleRowCount(30);
					panelFinal.add(scroll);
					panelAbajo.add(panelFinal);
					scroll.updateUI();
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorRecuperarTodasLlamadasEntreFechas implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			Calendar fechaInicio = Calendar.getInstance();
			int añoLocal = Integer.parseInt(año.getText().trim());
			int mesLocal = Integer.parseInt(mes.getText().trim());
			int diaLocal = Integer.parseInt(dia.getText().trim());
			fechaInicio.set(añoLocal, mesLocal, diaLocal);
			Calendar fechaFin = Calendar.getInstance();
			int añoLocal2 = Integer.parseInt(año2.getText().trim());
			int mesLocal2 = Integer.parseInt(mes2.getText().trim());
			int diaLocal2 = Integer.parseInt(dia2.getText().trim());
			fechaFin.set(añoLocal2, mesLocal2, diaLocal2);
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					Collection<Llamada> col = controlador.mostrarListadoLlamadasFechas(nif.getText(), fechaInicio, fechaFin);
					DefaultListModel<String> datos = new DefaultListModel<>();
					for (Llamada cliente : col) {
						datos.addElement(cliente.toString());
					}
					JList<String> clientes = new JList<String>(datos);
					JScrollPane scroll = new JScrollPane(clientes);
					scroll.setPreferredSize(new Dimension(1080, 400));
					clientes.setVisibleRowCount(30);
					panelFinal.add(scroll);
					panelAbajo.add(panelFinal);
					scroll.updateUI();
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionFechas e1) {
					JLabel fechaNoValida = new JLabel("Fecha no válida");
					panelFinal.add(fechaNoValida);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionListaLlamadasVacia e1) {
					JLabel listaVacia = new JLabel("Lista vacía");
					panelFinal.add(listaVacia);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorEmitirFactura implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				Calendar fecha = Calendar.getInstance();
				int añoLocal = Integer.parseInt(año.getText().trim());
				int mesLocal = Integer.parseInt(mes.getText().trim());
				int diaLocal = Integer.parseInt(dia.getText().trim());
				fecha.set(añoLocal, mesLocal, diaLocal);

				try {
					controlador.emitirFactura(nif.getText(), fecha);
					modelo.guardarDatos();

					JLabel facturaRegistrada = new JLabel("Factura registrada con éxito");
					panelFinal.add(facturaRegistrada);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorRecuperarTodasFacturas implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					Collection<Factura> col = controlador.recuperarFacturas(nif.getText());
					DefaultListModel<String> datos = new DefaultListModel<>();
					for (Factura factura : col) {
						datos.addElement(factura.toString());
					}
					JList<String> facturas = new JList<String>(datos);
					JScrollPane scroll = new JScrollPane(facturas);
					scroll.setPreferredSize(new Dimension(1080, 400));
					facturas.setVisibleRowCount(30);
					panelFinal.add(scroll);
					panelAbajo.add(panelFinal);
					scroll.updateUI();
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionListaFacturasVacia e1) {
					JLabel listaVacia = new JLabel("Lista vacía");
					panelFinal.add(listaVacia);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorRecuperarFacturas implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					int codFactura = Integer.parseInt(codFac.getText().trim());
					JLabel fac = new JLabel(controlador.recuperarDatosFacturaCodigo(codFactura).toString());
					JScrollPane scroll = new JScrollPane(fac);
					scroll.setPreferredSize(new Dimension(1080, 100));
					panelFinal.add(scroll);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionFacturaNoEncontrada e1) {
					JLabel facturaNoEncontrada = new JLabel("Factura no encontrada");
					panelFinal.add(facturaNoEncontrada);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
			}
		}
	}

	class EscuchadorRecuperarTodasFacturasEntreFechas implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			submit = (JButton) e.getSource();
			String texto = submit.getText();
			Calendar fechaInicio = Calendar.getInstance();
			int añoLocal = Integer.parseInt(año.getText().trim());
			int mesLocal = Integer.parseInt(mes.getText().trim());
			int diaLocal = Integer.parseInt(dia.getText().trim());
			fechaInicio.set(añoLocal, mesLocal, diaLocal);
			Calendar fechaFin = Calendar.getInstance();
			int añoLocal2 = Integer.parseInt(año2.getText().trim());
			int mesLocal2 = Integer.parseInt(mes2.getText().trim());
			int diaLocal2 = Integer.parseInt(dia2.getText().trim());
			fechaFin.set(añoLocal2, mesLocal2, diaLocal2);
			if (texto.equals("Enviar")) {
				panelFinal.removeAll();
				try {
					Collection<Factura> col = controlador.mostrarListadoFacturasFechas(nif.getText(), fechaInicio, fechaFin);
					DefaultListModel<String> datos = new DefaultListModel<>();
					for (Factura fac : col) {
						datos.addElement(fac.toString());
					}
					JList<String> facturas = new JList<String>(datos);
					JScrollPane scroll = new JScrollPane(facturas);
					scroll.setPreferredSize(new Dimension(1080, 400));
					facturas.setVisibleRowCount(30);
					panelFinal.add(scroll);
					panelAbajo.add(panelFinal);
					scroll.updateUI();
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionFechas e1) {
					JLabel fechaNoValida = new JLabel("Fecha no válida");
					panelFinal.add(fechaNoValida);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionClienteNoEncontrado e1) {
					JLabel clienteNoEncontrado = new JLabel("Cliente no encontrado");
					panelFinal.add(clienteNoEncontrado);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				} catch (ExcepcionListaFacturasVacia e1) {
					JLabel listaVacia = new JLabel("Lista vacía");
					panelFinal.add(listaVacia);
					panelAbajo.add(panelFinal);
					panelFinal.updateUI();
					panelAbajo.updateUI();
				}
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

}
