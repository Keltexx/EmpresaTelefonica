package interfazusuario.controlador;

import java.util.Calendar;
import java.util.HashMap;

import cliente.Cliente;
import cliente.Direccion;
import excepciones.ExcepcionClienteNoEncontrado;
import excepciones.ExcepcionClienteYaRegistrado;
import excepciones.ExcepcionListaClientesVacia;
import factura.Tarifa;

public interface Controlador {
	public boolean creaCliente(int tipo,String nombre,String apellidos,String nif,Direccion dir,String correo,Calendar fecha,Tarifa tarifa) throws ExcepcionClienteYaRegistrado;
	public boolean borraCliente(String nif) throws ExcepcionClienteNoEncontrado;
	public boolean cambiaTarifa(String nif, Tarifa tarifa) throws ExcepcionClienteNoEncontrado;
	public Cliente recuperarDatosCliente(String nif) throws ExcepcionClienteNoEncontrado;
	public HashMap<String, Cliente> recuperaListadoClientes() throws ExcepcionListaClientesVacia;
}
