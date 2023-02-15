package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DaoCategoriasServicios;
import dao.DaoFiestas;
import dao.DaoInterfaz;
import dao.DaoNoticias;
import dao.DaoServiciosPublicos;
import dao.DaoUsuarios;
import entidades.CategoriasServicios;
import entidades.Fiestas;
import entidades.Interfaz;
import entidades.Noticias;
import entidades.ServiciosPublicos;
import entidades.Usuarios;
import excepciones.CategoriaException;
import excepciones.FiestaException;
import excepciones.NoticiaException;
import excepciones.ServicioException;

/**
 * Servlet implementation class ControllerAdmin
 */
@MultipartConfig
@WebServlet("/ControllerAdmin")
public class ControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void procesarErrorNoticia(HttpServletRequest request, HttpServletResponse response, NoticiaException ne, String url)
    		throws ServletException, IOException{
    	    String errorNoticia = ne.getMessage();
    	    request.setAttribute("errorNoticia", errorNoticia);
    	    request.getRequestDispatcher(url).forward(request, response);
    }
    
    protected void procesarErrorServicio(HttpServletRequest request, HttpServletResponse response, ServicioException se, String url)
    		throws ServletException, IOException{
    	    String errorServicio = se.getMessage();
    	    request.setAttribute("errorServicio", errorServicio);
    	    request.getRequestDispatcher(url).forward(request, response);
    }
    
    protected void procesarErrorFiesta(HttpServletRequest request, HttpServletResponse response, FiestaException fe, String url)
    		throws ServletException, IOException{
    	    String errorFiesta = fe.getMessage();
    	    request.setAttribute("errorFiesta", errorFiesta);
    	    request.getRequestDispatcher(url).forward(request, response);
    }
    
    protected void procesarErrorCategoria(HttpServletRequest request, HttpServletResponse response, CategoriaException ce, String url)
    		throws ServletException, IOException{
    	    String errorCategoria = ce.getMessage();
    	    request.setAttribute("errorCategoria", errorCategoria);
    	    request.getRequestDispatcher(url).forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String keyword = request.getParameter("keyword");
		HttpSession sesion = request.getSession(true);
		
		DaoUsuarios daousuario = new DaoUsuarios();
		DaoNoticias daonoticia = new DaoNoticias();
		DaoServiciosPublicos daoservicio = new DaoServiciosPublicos();
		DaoCategoriasServicios daocategoria = new DaoCategoriasServicios();
		DaoFiestas daofiesta = new DaoFiestas();
		DaoInterfaz daointerfaz = new DaoInterfaz();
		try {
			Interfaz lainterfaz = daointerfaz.coloresInterfaz();
			request.setAttribute("lainterfaz", lainterfaz);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int firstRow = 0;
		int rowsByPage = 4;
		int lastRow = firstRow + rowsByPage;		
		switch(keyword) {
		   case "IrAListaUsuarios":
			   try {
				   String thenameuser = (String) sesion.getAttribute("nombreusuario");
				   ArrayList<Usuarios> listausuarios = daousuario.listarUsuarios(thenameuser, rowsByPage, firstRow);
				   int contadorUsuarios = daousuario.totalUsuarios(thenameuser);
				   request.setAttribute("rowsByPage", rowsByPage);
				   request.setAttribute("firstRow", firstRow);
				   request.setAttribute("lastRow", lastRow);
				   request.setAttribute("totalusers", contadorUsuarios);
				   request.setAttribute("listausuarios", listausuarios);
				   request.getRequestDispatcher("administrador/listausuarios.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "PaginaAnteriorUsers":
			   int firstRowPrev = Integer.parseInt(request.getParameter("thefirstRow"));
			   if(firstRowPrev < 0) {
				   firstRowPrev = 0;
			   }
			   lastRow = firstRowPrev + rowsByPage;
			   try {
				   String thenameuser = (String) sesion.getAttribute("nombreusuario");
				   ArrayList<Usuarios> listausuarios = daousuario.listarUsuarios(thenameuser, rowsByPage, firstRowPrev);
				   int contadorUsuarios = daousuario.totalUsuarios(thenameuser);
				   request.setAttribute("rowsByPage", rowsByPage);
				   request.setAttribute("firstRow", firstRowPrev);
				   request.setAttribute("lastRow", lastRow);
				   request.setAttribute("totalusers", contadorUsuarios);
				   request.setAttribute("listausuarios", listausuarios);
				   request.getRequestDispatcher("administrador/listausuarios.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "PaginaSiguienteUsers":
			   int firstRowNext = Integer.parseInt(request.getParameter("firstRow"));
			   lastRow = firstRowNext + rowsByPage;
			   try {
				   String thenameuser = (String) sesion.getAttribute("nombreusuario");
				   int contadorUsuarios = daousuario.totalUsuarios(thenameuser);
				   if(firstRowNext >= contadorUsuarios) {
					   firstRowNext = firstRowNext - rowsByPage;
				   }
				   if(lastRow > contadorUsuarios) {
					   lastRow = contadorUsuarios;
				   }
				   ArrayList<Usuarios> listausuarios = daousuario.listarUsuarios(thenameuser, rowsByPage, firstRowNext);
				   request.setAttribute("rowsByPage", rowsByPage);
				   request.setAttribute("firstRow", firstRowNext);
				   request.setAttribute("lastRow", lastRow);
				   request.setAttribute("totalusers", contadorUsuarios);
				   request.setAttribute("listausuarios", listausuarios);
				   request.getRequestDispatcher("administrador/listausuarios.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "EditarUsuario":
			   int eliduser = Integer.parseInt(request.getParameter("eliduser"));
			   try {
				   Usuarios usuarioeditar = daousuario.findUsuarioById(eliduser);
				   ArrayList<Usuarios> rolesusuario = daousuario.listarRolesUsuarios();
				   request.setAttribute("rolesusuario", rolesusuario);
				   request.setAttribute("usuarioeditar", usuarioeditar);
				   request.getRequestDispatcher("administrador/modificarUsuario.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "ModificarUsuario":
			   Usuarios usuariomodificado = new Usuarios();
			   int iduser = Integer.parseInt(request.getParameter("iduser"));
			   String dniuser = request.getParameter("dniuser");
			   String nombreuser = request.getParameter("nombreuser");
			   String roluser = request.getParameter("roluser");
			   Part fotoperfil = request.getPart("fotoperfil");
			   InputStream fotoperfilusuario = fotoperfil.getInputStream();
			   String correouser = request.getParameter("correouser");
			   String claveuser = request.getParameter("claveuser");
			   String direccionuser = request.getParameter("direccionuser");
			   try {
				   usuariomodificado.setIdusuario(iduser);
				   usuariomodificado.setDni(dniuser);
				   usuariomodificado.setNombre(nombreuser);
				   usuariomodificado.setCorreo(correouser);
				   usuariomodificado.setClave(claveuser);
				   usuariomodificado.setDireccion(direccionuser);
				   usuariomodificado.setFotoperfil(fotoperfilusuario);
				   usuariomodificado.setRol(roluser);
				   daousuario.modificarUsuario(usuariomodificado);
				   ArrayList<Usuarios> rolesusuario = daousuario.listarRolesUsuarios();
				   request.setAttribute("usuarioeditar", usuariomodificado);				  
				   request.setAttribute("rolesusuario", rolesusuario);
				   request.setAttribute("confirmaredicion", "Usuario modificado.");
				   request.getRequestDispatcher("administrador/modificarUsuario.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "EliminarUsuario":
			   String thenamesuser = (String) sesion.getAttribute("nombreusuario");
			   String delthisuser = request.getParameter("delthisuser");
			   int pageBeforeDelete = Integer.parseInt(request.getParameter("pageBeforeDelete"));		   
			   try {
				   daousuario.eliminarUsuario(delthisuser);
				   lastRow = pageBeforeDelete + rowsByPage;
				   int contadorUsuarios = daousuario.totalUsuarios(thenamesuser);
				   if(lastRow >= contadorUsuarios) {
					   lastRow = contadorUsuarios;
				   }
				   ArrayList<Usuarios> listausuarios = daousuario.listarUsuarios(thenamesuser, rowsByPage, pageBeforeDelete);
				   request.setAttribute("rowsByPage", rowsByPage);
				   request.setAttribute("firstRow", pageBeforeDelete);
				   request.setAttribute("lastRow", lastRow);
				   request.setAttribute("totalusers", contadorUsuarios);
				   request.setAttribute("listausuarios", listausuarios);
				   request.setAttribute("confirmardeluser", "Usuario eliminado.");
				   request.getRequestDispatcher("administrador/listausuarios.jsp").forward(request, response);
			   }
			   catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "IrAGestionarNoticias":
				  request.getRequestDispatcher("administrador/buscarNoticiasModificarEliminar.jsp").forward(request, response);
		   break;
		   case "IrAltaNoticia":
			   request.getRequestDispatcher("administrador/altanoticia.jsp").forward(request, response);
		   break;
		   case "AgregarNoticia":
			   Noticias nuevanoticia = new Noticias();
			   String titulonoticia = request.getParameter("titulonoticia");
			   String fechastr = request.getParameter("fechanoticia");
			   String descripcionNoticia = request.getParameter("descripcionNoticia");
			   Part fotonoticia = request.getPart("fotonoticia");
			   InputStream lafotonoticia = fotonoticia.getInputStream();
			   String autornoticia = request.getParameter("autornoticia");
			   try {
				  DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	  LocalDate fechalocal = LocalDate.parse(fechastr, formato);
		    	  java.sql.Date fechanoticiaSQL = java.sql.Date.valueOf(fechalocal);
				  nuevanoticia.setTitulo(titulonoticia);
				  nuevanoticia.setDescripcion(descripcionNoticia);
				  nuevanoticia.setFotonoticia(lafotonoticia);
				  nuevanoticia.setFechanoticia(fechanoticiaSQL);
				  nuevanoticia.setAutor(autornoticia);
				  daonoticia.altaNoticia(nuevanoticia);
				  request.setAttribute("confirmaltanoticia", "Noticia insertada con exito.");
				  request.getRequestDispatcher("administrador/altanoticia.jsp").forward(request, response);
			   } catch(NoticiaException n) {
				   request.setAttribute("titnoticia", titulonoticia);
				   request.setAttribute("desnoticia", descripcionNoticia);
				   request.setAttribute("fecnoticia", fechastr);
				   procesarErrorNoticia(request, response, n, "administrador/altanoticia.jsp");
			   }
			   catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "BuscarNoticiasEditDelete":
			   String titnoticias = request.getParameter("titnoticias");
			   try {
				   ArrayList<Noticias> listanoticias;
				   if(titnoticias == null) {
					   listanoticias = daonoticia.buscarNoticias("%");
				   } else {
					   listanoticias = daonoticia.buscarNoticias(titnoticias);
				   }
				   request.setAttribute("partetitulo", titnoticias);
				   request.setAttribute("listanoticias", listanoticias);
				   request.getRequestDispatcher("administrador/buscarNoticiasModificarEliminar.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "EliminarNoticias":
			   String trozotitulo = request.getParameter("trozotitulo");
			   String [] noticiaseliminar = request.getParameterValues("noticiaseliminar");
			   try {
				   daonoticia.eliminarNoticias(noticiaseliminar);
				   ArrayList<Noticias> listanoticias;
				   if(trozotitulo == null) {
					   listanoticias = daonoticia.buscarNoticias("%");
				   } else {
					   listanoticias = daonoticia.buscarNoticias(trozotitulo);
				   }
				   request.setAttribute("partetitulo", trozotitulo);
				   request.setAttribute("listanoticias", listanoticias);
				   request.setAttribute("confdelnoticias", "Noticias eliminadas.");
				   request.getRequestDispatcher("administrador/buscarNoticiasModificarEliminar.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "NoticiaAEditar":
			   int idnoticiaedit = Integer.parseInt(request.getParameter("idnoticiaedit"));
			   try {
				   Noticias noticiaedit = daonoticia.findNoticiasById(idnoticiaedit);
				   request.setAttribute("noticiaedit", noticiaedit);
				   request.getRequestDispatcher("administrador/modificarNoticia.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "ModificarNoticia":
			   int elidnoticia = Integer.parseInt(request.getParameter("elidnoticia"));
			   String eltitulo = request.getParameter("eltitulo");
			   String ladescripcion = request.getParameter("ladescripcion");
			   Part photonoticia = request.getPart("photonoticia");
			   InputStream thephotonoticia = photonoticia.getInputStream();
			   String fechanoticiastr = request.getParameter("lafechanoticia");
			   try {
				   DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				   LocalDate fechalocal = LocalDate.parse(fechanoticiastr, formato);
				   java.sql.Date fechanoticiasql = java.sql.Date.valueOf(fechalocal);
				   Noticias noticiamodificada = daonoticia.findNoticiasById(elidnoticia);
				   noticiamodificada.setTitulo(eltitulo);
				   noticiamodificada.setDescripcion(ladescripcion);
				   noticiamodificada.setFotonoticia(thephotonoticia);
				   noticiamodificada.setFechanoticia(fechanoticiasql);
				   daonoticia.modificarNoticia(noticiamodificada);
				   request.setAttribute("confnoticiaedit", "Noticia modificada.");
				   request.setAttribute("noticiaedit", noticiamodificada);
				   request.getRequestDispatcher("administrador/modificarNoticia.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "IrAGestionServicios":
				  request.getRequestDispatcher("administrador/gestionservicio.jsp").forward(request, response);
		   break;
		   case "IrANuevoServicio":
			   try {
				   ArrayList<CategoriasServicios> listadocategorias = daocategoria.listaCategoriasServicios();
				   request.setAttribute("listadocategorias", listadocategorias);
				   request.getRequestDispatcher("administrador/altaservicio.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "AgregarServicio":
			     ServiciosPublicos nuevoservicio = new ServiciosPublicos();
			     String nombreservicio = request.getParameter("nombreservicio");
			     int categoriaservicio = Integer.parseInt(request.getParameter("categoriaservicio"));
			     String infoservicio = request.getParameter("infoservicio");
			     Part fotoservicio = request.getPart("fotoservicio");
			     InputStream lafotoservicio = fotoservicio.getInputStream();
			   try {
				   nuevoservicio.setNombre(nombreservicio);
				   nuevoservicio.setIdcategoria(categoriaservicio);
				   if(infoservicio != null) {
					   nuevoservicio.setInformacion(infoservicio);
				   } else {
					   nuevoservicio.setInformacion(null);
				   }
				   nuevoservicio.setFotoservicio(lafotoservicio);
				   daoservicio.altaServicio(nuevoservicio);
				   ArrayList<CategoriasServicios> listadocategorias = daocategoria.listaCategoriasServicios();
				   request.setAttribute("listadocategorias", listadocategorias);
				   request.setAttribute("confaltaservicio", "Servicio agregado");
				   request.getRequestDispatcher("administrador/altaservicio.jsp").forward(request, response);
			   } catch(ServicioException se) {
				   ArrayList<CategoriasServicios> listadocategorias;
				try {
					listadocategorias = daocategoria.listaCategoriasServicios();
					request.setAttribute("listadocategorias", listadocategorias);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   request.setAttribute("nameservicio", nombreservicio);
				   request.setAttribute("catservicio", categoriaservicio);
				   request.setAttribute("inservicio", infoservicio);
				   procesarErrorServicio(request, response, se, "administrador/altaservicio.jsp");
			   }  
			   catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "IrAEditDeleteServicios":
			  request.getRequestDispatcher("administrador/buscarServiciosEditarEliminar.jsp").forward(request, response);  
		   break;
		   case "BuscarCategoriasServicios":
			   String trozodescat = request.getParameter("trozodescat");
			   try {
				  ArrayList<CategoriasServicios> lascategorias = daocategoria.buscarCategorias(trozodescat); 
				  request.setAttribute("patroncat", trozodescat);
				  request.setAttribute("lascategorias", lascategorias);
				  request.getRequestDispatcher("administrador/buscarServiciosEditarEliminar.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "listarServiciosEditDelete":
			   int theidcat = Integer.parseInt(request.getParameter("theidcat"));
			   String trozocat = request.getParameter("trozocat");
			   try {
				   ArrayList<CategoriasServicios> lascategorias = daocategoria.buscarCategorias(trozocat); 
				   ArrayList<ServiciosPublicos> serviciosmanager = daoservicio.listarServiciosByCategoria(theidcat);
				   CategoriasServicios estacategoria = daocategoria.findCategoriaById(theidcat);
				   request.setAttribute("thiscategory", estacategoria);
				   request.setAttribute("idcat", theidcat);
				   request.setAttribute("patroncat", trozocat);
				   request.setAttribute("lascategorias", lascategorias);
				   request.setAttribute("serviciosmanager", serviciosmanager);
				   request.getRequestDispatcher("administrador/buscarServiciosEditarEliminar.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "EliminarServicios":
			   String inicialescat = request.getParameter("inicialescat");
			   int esteidcat = Integer.parseInt(request.getParameter("esteidcat"));
			   String [] servicesdelete = request.getParameterValues("servicesdelete");
			   try {
				   daoservicio.eliminarServicios(servicesdelete);
				   ArrayList<CategoriasServicios> lascategorias = daocategoria.buscarCategorias(inicialescat); 
				   ArrayList<ServiciosPublicos> serviciosmanager = daoservicio.listarServiciosByCategoria(esteidcat);
				   CategoriasServicios estacategoria = daocategoria.findCategoriaById(esteidcat);
				   request.setAttribute("patroncat", inicialescat);
				   request.setAttribute("lascategorias", lascategorias);
				   request.setAttribute("thiscategory", estacategoria);
				   request.setAttribute("serviciosmanager", serviciosmanager);
				   request.setAttribute("confdelservicios", "Servicios públicos eliminados.");
				   request.getRequestDispatcher("administrador/buscarServiciosEditarEliminar.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "ServicioAEditar":
			   int theidservice = Integer.parseInt(request.getParameter("theidservice"));
			   try {
				   ServiciosPublicos servicioedit = daoservicio.findServicioById(theidservice);
				   ArrayList<CategoriasServicios> listacategorias = daocategoria.listaCategoriasServicios();
				   request.setAttribute("listadocategorias", listacategorias); 
				   request.setAttribute("servicioedit", servicioedit);
				   request.getRequestDispatcher("administrador/modificarServicio.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "ModificarServicio":
			     ServiciosPublicos serviciomodificado = new ServiciosPublicos();
			     int elidservicio = Integer.parseInt(request.getParameter("elidservicio"));
			     String nameservice = request.getParameter("nameservice");
			     String infoservice = request.getParameter("infoservice");
			     Part photoservice = request.getPart("photoservice");
			     InputStream thephotoservice = photoservice.getInputStream();
			     int categservice = Integer.parseInt(request.getParameter("categservice"));
			   try {
				   serviciomodificado.setIdservicio(elidservicio);
				   serviciomodificado.setNombre(nameservice);
				   serviciomodificado.setInformacion(infoservice);
				   serviciomodificado.setFotoservicio(thephotoservice);
				   serviciomodificado.setIdcategoria(categservice);
				   daoservicio.modificarServicio(serviciomodificado);
				   ArrayList<CategoriasServicios> listacategorias = daocategoria.listaCategoriasServicios();
				   request.setAttribute("listadocategorias", listacategorias);
				   request.setAttribute("servicioedit", serviciomodificado);
				   request.setAttribute("confmodservice", "Servicio público modificado.");
				   request.getRequestDispatcher("administrador/modificarServicio.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "IrAGestionarFiestas":
			   request.getRequestDispatcher("administrador/gestionarfiestas.jsp").forward(request, response);
		   break;
		   case "IrAltaFiesta":
			   request.getRequestDispatcher("administrador/altafiesta.jsp").forward(request, response);
		   break;
		   case "AgregarFiesta":
			     Fiestas nuevafiesta = new Fiestas();
			     String namefiesta = request.getParameter("namefiesta");
			     String iniciofiesta = request.getParameter("iniciofiesta");
			     String finfiesta = request.getParameter("finfiesta");
			     String infofiesta = request.getParameter("infofiesta");
			     Part fotofiesta = request.getPart("fotofiesta");
			     InputStream lafotofiesta = fotofiesta.getInputStream();
			   try {
				   DateTimeFormatter formatouno = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				   LocalDate fechal = LocalDate.parse(iniciofiesta, formatouno);
				   java.sql.Date fechainicio = java.sql.Date.valueOf(fechal);
				   
				   DateTimeFormatter formatodos = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				   LocalDate fechalocal = LocalDate.parse(finfiesta, formatodos);
				   java.sql.Date fechafin = java.sql.Date.valueOf(fechalocal);
				   nuevafiesta.setNombre(namefiesta);
				   nuevafiesta.setInformacion(infofiesta);
				   nuevafiesta.setFotofiesta(lafotofiesta);
				   nuevafiesta.setFechainicio(fechainicio);
				   nuevafiesta.setFechafin(fechafin);
				   daofiesta.altaFiesta(nuevafiesta);
				   request.setAttribute("confaltafiesta", "Fiesta agregada.");
				   request.getRequestDispatcher("administrador/altafiesta.jsp").forward(request, response);
			   } catch(FiestaException f) {
				   request.setAttribute("nombrefiesta", namefiesta);
				   request.setAttribute("informacionfiesta", infofiesta);
				   request.setAttribute("startfiesta", iniciofiesta);
				   request.setAttribute("endfiesta", finfiesta);
				   procesarErrorFiesta(request, response, f, "administrador/altafiesta.jsp");
			   }
			   catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "IrARecupUsuarios":
			   try {
				   ArrayList<Usuarios> usuariosalta = daousuario.listarUsuariosAlta();
				   ArrayList<Usuarios> usuariosbaja = daousuario.listarUsuariosBaja();
				   request.setAttribute("usuariosalta", usuariosalta);
				   request.setAttribute("usuariosbaja", usuariosbaja);
				   request.getRequestDispatcher("administrador/recuperarUsuarios.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "RecupUsuarios":
			  int userRecup = Integer.parseInt(request.getParameter("userRecup"));
			   try {
				   daousuario.recuperarUsuariosEliminados(userRecup);
				   Usuarios userdel = daousuario.findUsuarioById(userRecup);
				   ArrayList<Usuarios> usuariosalta = daousuario.listarUsuariosAlta();
				   ArrayList<Usuarios> usuariosbaja = daousuario.listarUsuariosBaja();
				   request.setAttribute("usuariosalta", usuariosalta);
				   request.setAttribute("usuariosbaja", usuariosbaja);
				   request.setAttribute("confrecuser", "Has dado de alta: "+userdel.getNombre());
				   request.getRequestDispatcher("administrador/recuperarUsuarios.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "IrAGestionInterfaz":
			   request.getRequestDispatcher("administrador/gestioninterfaz.jsp").forward(request, response);
		   break;
		   case "IrChangeColourNav":
			   request.getRequestDispatcher("administrador/colorNav.jsp").forward(request, response);
		   break;
		   case "changecolournav":
			   Interfaz editnav = new Interfaz();
			   String colournav = request.getParameter("colournav");
			   try {
				   editnav.setColornavegacion(colournav);
				   daointerfaz.cambioColorNav(editnav);
				   request.setAttribute("thecolour", colournav);
				   request.setAttribute("lainterfaz", editnav);
				   request.setAttribute("confcolinter", "Navegacion cambiada");
				   request.getRequestDispatcher("administrador/colorNav.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		   case "IrAltaCatService":
			   request.getRequestDispatcher("administrador/altaCategoriaServicio.jsp").forward(request, response);
		   break;
		   case "AltaCategoria":
			   String descategoria = request.getParameter("descategoria");
			   Part fotocategoria = request.getPart("fotocategoria");
			   InputStream lafotocategory = fotocategoria.getInputStream();
			   CategoriasServicios nuevacategoria = new CategoriasServicios();
			   try {
				   nuevacategoria.setDescripcion(descategoria);
				   nuevacategoria.setFotocategoria(lafotocategory);
				   daocategoria.altaCategoria(nuevacategoria);
				   CategoriasServicios lanuevacat = daocategoria.findCategoriaByDescription(descategoria);
				   request.setAttribute("confaltacat", "Nueva categoria: "+lanuevacat.getDescripcion());
				   request.getRequestDispatcher("administrador/altaCategoriaServicio.jsp").forward(request, response);
			   } catch(CategoriaException c) {
				   request.setAttribute("descat", descategoria);
				   procesarErrorCategoria(request, response, c, "administrador/altaCategoriaServicio.jsp");
			   }
			   catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
