package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DaoCategoriasServicios;
import dao.DaoComentarios;
import dao.DaoFiestas;
import dao.DaoImagenesayuntamiento;
import dao.DaoInterfaz;
import dao.DaoNoticias;
import dao.DaoServiciosPublicos;
import dao.DaoUsuarios;
import dao.DaoVisitas;
import entidades.CategoriasServicios;
import entidades.Comentarios;
import entidades.Fiestas;
import entidades.Imagenesayuntamiento;
import entidades.Interfaz;
import entidades.Noticias;
import entidades.ServiciosPublicos;
import entidades.Usuarios;
import entidades.Visitas;
import excepciones.LoginException;
import excepciones.RegistroException;

/**
 * Servlet implementation class ControllerUsuario
 */
@MultipartConfig
@WebServlet("/ControllerUsuario")
public class ControllerUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void procesarErrorLogin(HttpServletRequest request, HttpServletResponse response, LoginException le, String url) 
    		throws ServletException, IOException {
    	    String errorlogin = le.getMessage();
    	    request.setAttribute("errorlogin", errorlogin);
    	    request.getRequestDispatcher(url).forward(request, response);
    }
    
    protected void procesarErrorRegistro(HttpServletRequest request, HttpServletResponse response, RegistroException re, String url) 
    		throws ServletException, IOException {
    	    String errorRegistro = re.getMessage();
    	    request.setAttribute("errorRegistro", errorRegistro);
    	    request.getRequestDispatcher(url).forward(request, response);
    }

    DaoUsuarios daousuario = new DaoUsuarios();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String keyword = request.getParameter("keyword");
		String nombreusuario = request.getParameter("nombreusuario");
		String claveusuario = request.getParameter("claveusuario");
		HttpSession sesion = request.getSession(true);
		
		DaoComentarios daocomentario = new DaoComentarios();
		DaoImagenesayuntamiento daoimagen = new DaoImagenesayuntamiento();
		DaoNoticias daonoticia = new DaoNoticias();
		DaoCategoriasServicios daocatservicio = new DaoCategoriasServicios();
		DaoServiciosPublicos daoservicio = new DaoServiciosPublicos();
		DaoFiestas daofiesta = new DaoFiestas();
		DaoInterfaz daointerfaz = new DaoInterfaz();
		DaoVisitas daovisita = new DaoVisitas();
		try {
			Interfaz lainterfaz = daointerfaz.coloresInterfaz();
			request.setAttribute("lainterfaz", lainterfaz);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		switch(keyword) {
		  case "Acceder":
			  try {
				  Usuarios usuario = new Usuarios();
				  usuario.setNombre(nombreusuario);
				  usuario.setClave(claveusuario);
				  Usuarios rolusuario = daousuario.findUsuarioByName(nombreusuario);
				  daousuario.accesoUsuario(usuario);
				  Visitas nuevavisitausuario = new Visitas();
				  nuevavisitausuario.setUsuario(nombreusuario);
				  daovisita.aumentarVisitasUsuario(nuevavisitausuario);
		          Visitas visitausuario = daovisita.mostrarVisitasUsuario(nombreusuario);
				  ArrayList<Imagenesayuntamiento> listaimagenes = daoimagen.listarIdimagenes();
				  Date conectado=new Date(sesion.getLastAccessedTime());
				  request.setAttribute("listaimagenes", listaimagenes);
				  sesion.setAttribute("nombreusuario", nombreusuario);
				  sesion.setAttribute("visitausuario", visitausuario);
				  sesion.setAttribute("conectado", conectado);
				  sesion.setAttribute("rolusuario", rolusuario);
				  request.getRequestDispatcher("usuario/home.jsp").forward(request, response);
			  } catch(LoginException le) {
				  request.setAttribute("nameusuario", nombreusuario);
				  request.setAttribute("passusuario", claveusuario);
				  procesarErrorLogin(request, response, le, "index.jsp");
			  } catch(SQLException e) {
				  e.printStackTrace();
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
		  break;
		  case "Registrarse":
			  Usuarios nuevousuario = new Usuarios();
			  String dniuser = request.getParameter("dniuser");
			  String nombreuser = request.getParameter("nombreuser");
			  String mailuser = request.getParameter("mailuser");
			  String claveuser = request.getParameter("claveuser");
			  String direccionuser = request.getParameter("direccionuser");
			  Part fotouser = request.getPart("fotouser");
			  InputStream perfilusuario = fotouser.getInputStream();
			  try {
				  nuevousuario.setDni(dniuser);
				  nuevousuario.setNombre(nombreuser);
				  nuevousuario.setCorreo(mailuser);
				  nuevousuario.setClave(claveuser);
				  nuevousuario.setDireccion(direccionuser);
				  nuevousuario.setFotoperfil(perfilusuario);
				  daousuario.registrarUsuario(nuevousuario);
				  request.getRequestDispatcher("index.jsp").forward(request, response);
			  } catch(RegistroException re) {
				  procesarErrorRegistro(request, response, re, "index.jsp");
			  } catch(SQLException e) {
				  e.printStackTrace();
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
	      break;
		  case "IrAlHome":
			  request.getRequestDispatcher("usuario/home.jsp").forward(request, response);
		  break;
		  case "IrAFotosAyuntamiento":
			  try {
				  ArrayList<Imagenesayuntamiento> listaimagenes = daoimagen.listarIdimagenes();
				  request.setAttribute("listadoimagenes", listaimagenes); 
				  request.getRequestDispatcher("usuario/fotosayuntamiento.jsp").forward(request, response);
			  } catch(SQLException e) {
				  e.printStackTrace();
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
		  break;
		  case "mostrarfotoperfil":
			  String theuser = request.getParameter("theuser");
			  daousuario.listarIMGperfilUsuario(theuser, response);
		  break;
		  case "fotoayuntamiento":
			  int idfotoayuntamiento = Integer.parseInt(request.getParameter("idfotoayuntamiento"));
			  daoimagen.listarIMGayuntamiento(idfotoayuntamiento, response);
	      break;
		  case "IrAComentarios":
			  try {
				  ArrayList<Comentarios> listacomentarios = daocomentario.ordenFechaComentarios("ASC");
				  request.setAttribute("listacomentarios", listacomentarios);
				  request.getRequestDispatcher("usuario/comentarios.jsp").forward(request, response);
			  } catch(SQLException ex) {
				  ex.printStackTrace();
			  } catch(Exception e) {
				  e.printStackTrace();
			  }
	      break;
		  case "Comentar":
			  Comentarios nuevocomentario = new Comentarios();
			  String ladescripcion = request.getParameter("ladescripcion");
			  String elorden = request.getParameter("elorden");
			  try {
				  String elusuario = (String) sesion.getAttribute("nombreusuario");
				  nuevocomentario.setDescripcion(ladescripcion);
				  nuevocomentario.setNombreusuario(elusuario);
				  daocomentario.escribirComentarios(nuevocomentario);
				  ArrayList<Comentarios> listacomentarios;
				  if(elorden == null) {
					  listacomentarios = daocomentario.ordenFechaComentarios("ASC");
				  } else {
					  listacomentarios = daocomentario.ordenFechaComentarios(elorden);
				  }
				  request.setAttribute("criterioorden", elorden);
				  request.setAttribute("listacomentarios", listacomentarios);
				  request.getRequestDispatcher("usuario/comentarios.jsp").forward(request, response);
			  } catch(SQLException e) {
				  e.printStackTrace();
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
		  break;
		  case "EliminarComentario":
			  int elidcomentario = Integer.parseInt(request.getParameter("elidcomentario"));
			  String ordencomment = request.getParameter("ordencomment");
			  try {
				daocomentario.deleteComentario(elidcomentario);
				ArrayList<Comentarios> listacomentarios;
				if(ordencomment == null) {
					listacomentarios = daocomentario.ordenFechaComentarios("ASC");
				} else {
					listacomentarios = daocomentario.ordenFechaComentarios(ordencomment);
				}
				request.setAttribute("criterioorden", ordencomment);
				request.setAttribute("listacomentarios", listacomentarios);
				request.getRequestDispatcher("usuario/comentarios.jsp").forward(request, response);
			  } catch(SQLException e) {
				  e.printStackTrace();
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
		  break;
		  case "OrdenarComentarios":
			  String criterioComments = request.getParameter("criterioComments");
			  try {
				  ArrayList<Comentarios> commentsOrdenados = daocomentario.ordenFechaComentarios(criterioComments);
				  request.setAttribute("criterioorden", criterioComments);
				  request.setAttribute("listacomentarios", commentsOrdenados);
				  request.getRequestDispatcher("usuario/comentarios.jsp").forward(request, response);
			  } catch(SQLException e) {
				  e.printStackTrace();
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
	      break;
		  case "IrAVerNoticias":
			   try {
				  ArrayList<Noticias> lasnoticias = daonoticia.listarNoticias();
				  request.setAttribute("lasnoticias", lasnoticias);
				  request.getRequestDispatcher("usuario/vernoticias.jsp").forward(request, response);
			   } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		   break;
		  case "fotosnoticias":
			  int idfotonoticia = Integer.parseInt(request.getParameter("idfotonoticia"));
			  daonoticia.listarIMGnoticias(idfotonoticia, response);
		  break;
		  case "BuscarNoticias":
			  String ordenNoticias = request.getParameter("ordenNoticias");
			  String patrontitulo = request.getParameter("patrontitulo");
			  try {
				  ArrayList<Noticias> lasnoticias;
				  if(patrontitulo == null) {
					  lasnoticias = daonoticia.findNewsByDateAndTitle("%", "ASC");
				  } else {
					  lasnoticias = daonoticia.findNewsByDateAndTitle(patrontitulo, ordenNoticias);
				  }
				  request.setAttribute("ordenfecha", ordenNoticias);
				  request.setAttribute("lasiniciales", patrontitulo);
				  request.setAttribute("lasnoticias", lasnoticias);
				  request.getRequestDispatcher("usuario/vernoticias.jsp").forward(request, response);
			  } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
	      break;
		  case "LeerMasNoticia":
			  int idnoticiainfo = Integer.parseInt(request.getParameter("idnoticiainfo"));
			  try {
				  Noticias infoNoticia = daonoticia.findNoticiasById(idnoticiainfo);
				  request.setAttribute("infoNoticia", infoNoticia);
				  request.getRequestDispatcher("usuario/infoNoticias.jsp").forward(request, response);
			  } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		  break;
		  case "verfotoscatservicios":
			  int elidcat = Integer.parseInt(request.getParameter("elidcat"));
			  daocatservicio.listarIMGcatservicios(elidcat, response);
	      break;
		  case "verfotosServicios":
			  int elidservicio = Integer.parseInt(request.getParameter("elidservicio"));
			  daoservicio.listarIMGservicios(elidservicio, response);
		  break;
		  case "verfotosfiestas":
			  int elidfiesta = Integer.parseInt(request.getParameter("elidfiesta"));
			  daofiesta.listarIMGfiestas(elidfiesta, response);
		  break;
		  case "IrAVerServicios":
			  try {
				  ArrayList<CategoriasServicios> catservicios = daocatservicio.listaCategoriasServicios();
				  request.setAttribute("catservicios", catservicios);
				  request.getRequestDispatcher("usuario/listaservicios.jsp").forward(request, response);
			  } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
	      break;
		  case "BuscarServiciosByCategoria":
			  int category = Integer.parseInt(request.getParameter("category"));
			  try {
				  CategoriasServicios estacategoria = daocatservicio.findCategoriaById(category);
				  ArrayList<CategoriasServicios> catservicios = daocatservicio.listaCategoriasServicios();
				  request.setAttribute("thecategory", category);
				  request.setAttribute("estacategoria", estacategoria);
				  request.setAttribute("catservicios", catservicios);
				  ArrayList<ServiciosPublicos> listadoservicios = daoservicio.listarServiciosByCategoria(category);
				  request.setAttribute("listadoservicios", listadoservicios);
				  request.getRequestDispatcher("usuario/listaservicios.jsp").forward(request, response);
			  } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		  break;
		  case "IrAVerFiestas":
			  request.getRequestDispatcher("usuario/fiestas.jsp").forward(request, response);
		  break;
		  case "BuscarFiestas":
			  String mes = request.getParameter("mes");
			  try {
				  ArrayList<Fiestas> fiestasbymes = daofiesta.listarFiestasPorMes(mes);
				  request.setAttribute("estemes", mes);
				  request.setAttribute("fiestasbymes", fiestasbymes);
				  request.getRequestDispatcher("usuario/fiestas.jsp").forward(request, response);
			  } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		  break;
		  case "InfoFiesta":
			  int idfiesta = Integer.parseInt(request.getParameter("idfiesta"));
			  try {
				   Fiestas estafiesta = daofiesta.findFiestaById(idfiesta);
				   request.setAttribute("estafiesta", estafiesta);
				   request.getRequestDispatcher("usuario/inforFiesta.jsp").forward(request, response);
			  } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		  break;
		  case "DarLikeService":
			  int lacateg = Integer.parseInt(request.getParameter("lacateg"));
			  int ideservice = Integer.parseInt(request.getParameter("ideservice"));
			  try {
				  daoservicio.darLikeAlServicio(ideservice);
				  CategoriasServicios estacategoria = daocatservicio.findCategoriaById(lacateg);
				  ArrayList<CategoriasServicios> catservicios = daocatservicio.listaCategoriasServicios();
				  ArrayList<ServiciosPublicos> listadoservicios = daoservicio.listarServiciosByCategoria(lacateg);
				  request.setAttribute("thecategory", lacateg);
				  request.setAttribute("estacategoria", estacategoria);
				  request.setAttribute("catservicios", catservicios);
				  request.setAttribute("listadoservicios", listadoservicios);
				  request.getRequestDispatcher("usuario/listaservicios.jsp").forward(request, response);
			  } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
	      break;
		  case "DarDislikeService":
			  int thiscateg = Integer.parseInt(request.getParameter("thiscateg"));
			  int thisidserv = Integer.parseInt(request.getParameter("thisidserv"));
			  try {
				  daoservicio.darDislikeAlServicio(thisidserv);
				  CategoriasServicios estacategoria = daocatservicio.findCategoriaById(thiscateg);
				  ArrayList<CategoriasServicios> catservicios = daocatservicio.listaCategoriasServicios();
				  ArrayList<ServiciosPublicos> listadoservicios = daoservicio.listarServiciosByCategoria(thiscateg);
				  request.setAttribute("thecategory", thiscateg);
				  request.setAttribute("estacategoria", estacategoria);
				  request.setAttribute("catservicios", catservicios);
				  request.setAttribute("listadoservicios", listadoservicios);
				  request.getRequestDispatcher("usuario/listaservicios.jsp").forward(request, response);
			  } catch(SQLException e) {
				   e.printStackTrace();
			   } catch(Exception ex) {
				   ex.printStackTrace();
			   }
		  break;
		  case "CerrarSesion":
			 sesion.invalidate();
			 request.getRequestDispatcher("index.jsp").forward(request, response);
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
