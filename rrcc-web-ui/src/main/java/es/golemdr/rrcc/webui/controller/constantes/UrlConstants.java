package es.golemdr.rrcc.webui.controller.constantes;

public class UrlConstants {
	
	public static final String INICIO = "/inicio";
	
	// Comunidades
	public static final String LISTADO_COMUNIDADES = "/listadoComunidades";
	public static final String VER_ALTA_COMUNIDAD = "/verAltaComunidad";
	public static final String INSERTAR_COMUNIDAD = "/insertarComunidad";
	public static final String EDITAR_COMUNIDAD = "/editarComunidad";
	public static final String ACTUALIZAR_COMUNIDAD = "/actualizarComunidad";	
	public static final String BORRAR_COMUNIDAD = "/borrarComunidad";
	
	// Usuarios
	public static final String LISTADO_USUARIOS_COMUNIDAD = "/listadoUsuariosComunidad";
	public static final String LISTADO_USUARIOS_COMUNIDAD_GET = "/listadoUsuariosComunidad{idComunidad}";
	public static final String VER_ALTA_USUARIO = "/verAltaUsuario{idComunidad}";
	public static final String INSERTAR_USUARIO = "/insertarUsuario";
	public static final String EDITAR_USUARIO = "/editarUsuario{idComunidad}";
	public static final String ACTUALIZAR_USUARIO = "/actualizarUsuario";	
	public static final String BORRAR_USUARIO = "/borrarUsuario";
	
	// Recursos
	public static final String LISTADO_RECURSOS_COMUNIDAD = "/listadoRecursosComunidad";
	public static final String LISTADO_RECURSOS_COMUNIDAD_GET = "/listadoRecursosComunidad{idComunidad}";
	public static final String VER_ALTA_RECURSO = "/verAltaRecurso{idComunidad}";
	public static final String INSERTAR_RECURSO = "/insertarRecurso";
	public static final String EDITAR_RECURSO = "/editarRecurso";
	public static final String ACTUALIZAR_RECURSO = "/actualizarRecurso";	
	public static final String BORRAR_RECURSO = "/borrarRecurso";
	
	// Reservas
	public static final String LISTADO_RESERVAS_USUARIO = "/listadoReservasUsuario";
	public static final String VER_NUEVA_RESERVA = "/verNuevaReserva{idUsuario}";
	public static final String INSERTAR_RESERVA = "/insertarReserva";
	public static final String ANULAR_RESERVA = "/anularReserva";

}
