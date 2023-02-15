package dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import conexiones.Conexion;

import entidades.Noticias;
import excepciones.NoticiaException;

public class DaoNoticias {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<Noticias> listarNoticias() throws SQLException, Exception {
		ArrayList<Noticias> noticias = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM NOTICIAS"
					      +" WHERE BAJA != 'S'"
			              +" ORDER BY ID_NOTICIA";
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				Noticias noticia = new Noticias();
				noticia.setIdnoticia(rs.getInt(1));
				noticia.setTitulo(rs.getString(2));
				noticia.setDescripcion(rs.getString(3));
				noticia.setFotonoticia(rs.getBinaryStream(4));
				noticia.setFechanoticia(rs.getDate(5));
				noticia.setAutor(rs.getString(6));
				noticias.add(noticia);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return noticias;
	}
	
/*****************************************************************************************************/	
	
	public ArrayList<Noticias> findNewsByDateAndTitle(String iniciales, String criterioFecha) throws SQLException, Exception {
		ArrayList<Noticias> noticias = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM NOTICIAS "
			         +"WHERE UPPER(TITULO) LIKE UPPER(?)"
					+" AND BAJA != 'S'"
					+" ORDER BY FECHANOTICIA "+criterioFecha;
			ps = con.prepareStatement(listar);
			ps.setString(1, "%"+iniciales+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				Noticias noticia = new Noticias();
				noticia.setIdnoticia(rs.getInt(1));
				noticia.setTitulo(rs.getString(2));
				noticia.setDescripcion(rs.getString(3));
				noticia.setFotonoticia(rs.getBinaryStream(4));
				noticia.setFechanoticia(rs.getDate(5));
				noticia.setAutor(rs.getString(6));
				noticias.add(noticia);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return noticias;
	}
	
/******************************************************************************************************/	
	
	public ArrayList<Noticias> buscarNoticias(String iniciales) throws SQLException, Exception {
		ArrayList<Noticias> noticias = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM NOTICIAS "
			         +"WHERE UPPER(TITULO) LIKE UPPER(?)"
					+" AND BAJA != 'S'"
					+" ORDER BY ID_NOTICIA";
			ps = con.prepareStatement(listar);
			ps.setString(1, "%"+iniciales+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				Noticias noticia = new Noticias();
				noticia.setIdnoticia(rs.getInt(1));
				noticia.setTitulo(rs.getString(2));
				noticia.setDescripcion(rs.getString(3));
				noticia.setFotonoticia(rs.getBinaryStream(4));
				noticia.setFechanoticia(rs.getDate(5));
				noticia.setAutor(rs.getString(6));
				noticias.add(noticia);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return noticias;
	}
	
/*************************************************************************/	
	
	public Noticias findNoticiasByTitle(String titulo) throws SQLException, Exception {
		Noticias noticia = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM NOTICIAS"
			              +" WHERE TITULO = ?";
			ps = con.prepareStatement(buscar);
			ps.setString(1, titulo);
			rs = ps.executeQuery();
			if(rs.next()) {
				noticia = new Noticias();
				noticia.setIdnoticia(rs.getInt(1));
				noticia.setTitulo(rs.getString(2));
				noticia.setDescripcion(rs.getString(3));
				noticia.setFotonoticia(rs.getBinaryStream(4));
				noticia.setFechanoticia(rs.getDate(5));
				noticia.setAutor(rs.getString(6));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return noticia;
	}
	
/***********************************************************************/
	
	public void altaNoticia(Noticias n) throws NoticiaException, SQLException, Exception {
		Noticias buscarnoticia = findNoticiasByTitle(n.getTitulo());
		if(buscarnoticia != null) {
			throw new NoticiaException("Ya existe la noticia con título "+buscarnoticia.getTitulo()+", ingrese otra distinta.");
		}
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String insertar = "INSERT INTO NOTICIAS (TITULO, DESCRIPCION, "
			             +"FOTONOTICIA, FECHANOTICIA, AUTOR, BAJA) VALUES (?, ?, ?, ?, ?, 'N')";
			ps = con.prepareStatement(insertar);
			ps.setString(1, n.getTitulo());
			ps.setString(2, n.getDescripcion());
			ps.setBlob(3, n.getFotonoticia());
			ps.setDate(4, n.getFechanoticia());
			ps.setString(5, n.getAutor());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
/************************************************************************/
	
	public void listarIMGnoticias(int idnoticia, HttpServletResponse response) {
		String sql = "select * from noticias"+
	                " where id_noticia = "+idnoticia;
	    InputStream inputstream = null;
		OutputStream outputstream = null;
		BufferedInputStream bufferedinputstream = null;
		BufferedOutputStream bufferedoutputstream = null;
		response.setContentType("image/*");
		try {
			outputstream = response.getOutputStream();
			con = c.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				inputstream = rs.getBinaryStream("FOTONOTICIA");
			}
			bufferedinputstream = new BufferedInputStream(inputstream);
			bufferedoutputstream = new BufferedOutputStream(outputstream);
			int i = 0;
			while((i=bufferedinputstream.read()) != -1) {
				bufferedoutputstream.write(i);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/**************************************************************************/

	public Noticias findNoticiasById(int idnoticia) throws SQLException, Exception {
		Noticias noticia = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM NOTICIAS"
			              +" WHERE ID_NOTICIA = ?";
			ps = con.prepareStatement(buscar);
			ps.setInt(1, idnoticia);
			rs = ps.executeQuery();
			if(rs.next()) {
				noticia = new Noticias();
				noticia.setIdnoticia(rs.getInt(1));
				noticia.setTitulo(rs.getString(2));
				noticia.setDescripcion(rs.getString(3));
				noticia.setFotonoticia(rs.getBinaryStream(4));
				noticia.setFechanoticia(rs.getDate(5));
				noticia.setAutor(rs.getString(6));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return noticia;
	}
	
/***************************************************************************/
	public void modificarNoticia(Noticias n) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String modificar = "UPDATE NOTICIAS SET TITULO = ?, DESCRIPCION = ?,"
			                 +" FOTONOTICIA = ?, FECHANOTICIA = ?"
					         +" WHERE ID_NOTICIA = ?";
			ps = con.prepareStatement(modificar);
			ps.setString(1, n.getTitulo());
			ps.setString(2, n.getDescripcion());
			ps.setBlob(3, n.getFotonoticia());
			ps.setDate(4, n.getFechanoticia());
			ps.setInt(5, n.getIdnoticia());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
/**********************************************************************/
	
	public void eliminarNoticias(String[] noticiaseliminar) throws SQLException, Exception {
		StringBuilder builder = new StringBuilder();
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			for(int i=0;i<noticiaseliminar.length;i++) {
				builder.append("?,");
			}
			String eliminar = "UPDATE NOTICIAS SET BAJA = 'S' WHERE ID_NOTICIA IN ("
					    + builder.deleteCharAt( builder.length() -1 ).toString() +")";
			ps = con.prepareStatement(eliminar);
			int index = 1;
			for(String i:noticiaseliminar) {
				 ps.setString(index++, i);
			}
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	} 

}
