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
import entidades.ServiciosPublicos;
import excepciones.ServicioException;

public class DaoServiciosPublicos {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<ServiciosPublicos> listarServiciosByCategoria(int idcategoria) throws SQLException, Exception {
		ArrayList<ServiciosPublicos> servicios = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM SERVICIOSPUBLICOS"
			              +" WHERE IDCATEGORIA = ?"
					      +" AND BAJA != 'S'";
			ps = con.prepareStatement(listar);
			ps.setInt(1, idcategoria);
			rs = ps.executeQuery();
			while(rs.next()) {
				ServiciosPublicos servicio = new ServiciosPublicos();
				servicio.setIdservicio(rs.getInt(1));
				servicio.setNombre(rs.getString(2));
				servicio.setInformacion(rs.getString(3));
				servicio.setFotoservicio(rs.getBinaryStream(4));
				servicio.setIdcategoria(rs.getInt(5));
				servicio.setBaja(rs.getString(6));
				servicio.setLikes(rs.getLong(7));
				servicio.setDislikes(rs.getLong(8));
				servicios.add(servicio);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return servicios;
	}
	
/************************************************************************/
	
	public void listarIMGservicios(int idservicio, HttpServletResponse response) {
		String sql = "select * from serviciospublicos"+
	                " where idservicio = "+idservicio;
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
				inputstream = rs.getBinaryStream("FOTOSERVICIO");
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
	
/***************************************************************************/
	
	public ServiciosPublicos findServiciosByName(String nombre) throws SQLException, Exception {
		ServiciosPublicos servicio = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM SERVICIOSPUBLICOS"
			             +" WHERE NOMBRE = ? AND BAJA != 'S'";
			ps = con.prepareStatement(buscar);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			if(rs.next()) {
				servicio = new ServiciosPublicos();
				servicio.setIdservicio(rs.getInt(1));
				servicio.setNombre(rs.getString(2));
				servicio.setInformacion(rs.getString(3));
				servicio.setFotoservicio(rs.getBinaryStream(4));
				servicio.setIdcategoria(rs.getInt(5));
				servicio.setBaja(rs.getString(6));
				servicio.setLikes(rs.getLong(7));
				servicio.setDislikes(rs.getLong(8));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return servicio;
	}
	
/*********************************************************************************************/
	
	public ServiciosPublicos findServicioById(int idservicio) throws SQLException, Exception {
		ServiciosPublicos servicio = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM SERVICIOSPUBLICOS"
			             +" WHERE IDSERVICIO = ? AND BAJA != 'S'";
			ps = con.prepareStatement(buscar);
			ps.setInt(1, idservicio);
			rs = ps.executeQuery();
			if(rs.next()) {
				servicio = new ServiciosPublicos();
				servicio.setIdservicio(rs.getInt(1));
				servicio.setNombre(rs.getString(2));
				servicio.setInformacion(rs.getString(3));
				servicio.setFotoservicio(rs.getBinaryStream(4));
				servicio.setIdcategoria(rs.getInt(5));
				servicio.setBaja(rs.getString(6));
				servicio.setLikes(rs.getLong(7));
				servicio.setDislikes(rs.getLong(8));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return servicio;
	}
	
/********************************************************************************************/
	public void altaServicio(ServiciosPublicos s) throws ServicioException, SQLException, Exception {
		ServiciosPublicos buscarservicio = findServiciosByName(s.getNombre());
		if(buscarservicio != null) {
			throw new ServicioException("El servicio "+s.getNombre()+" ya existe, escriba otro.");
		}
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String insertar = "INSERT INTO SERVICIOSPUBLICOS (NOMBRE, INFORMACION, "
			                 +"FOTOSERVICIO, IDCATEGORIA, BAJA, LIKES, DISLIKES) "
					         +"VALUES (?, ?, ?, ?, 'N', 0, 0)";
			ps = con.prepareStatement(insertar);
			ps.setString(1, s.getNombre());
			ps.setString(2, s.getInformacion());
			ps.setBlob(3, s.getFotoservicio());
			ps.setInt(4, s.getIdcategoria());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
/**********************************************************************/
	
	public void modificarServicio(ServiciosPublicos s) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String modificar = "UPDATE SERVICIOSPUBLICOS SET NOMBRE = ?,"
			                  +" INFORMACION = ?, FOTOSERVICIO = ?, IDCATEGORIA = ?"
					         +" WHERE IDSERVICIO = ?";
		    ps = con.prepareStatement(modificar);
		    ps.setString(1, s.getNombre());
		    ps.setString(2, s.getInformacion());
		    ps.setBlob(3, s.getFotoservicio());
		    ps.setInt(4, s.getIdcategoria());
		    ps.setInt(5, s.getIdservicio());
		    ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
/************************************************************************/
	
	public void eliminarServicios(String[] servicioseliminar) throws SQLException, Exception {
		StringBuilder elbuilder = new StringBuilder();
		 try {
			 con = c.getConexion();
				con.setAutoCommit(false);
				for(int i=0;i<servicioseliminar.length;i++) {
					elbuilder.append("?,");
				}
				String eliminar = "UPDATE SERVICIOSPUBLICOS SET BAJA = 'S' WHERE IDSERVICIO IN ("
						    + elbuilder.deleteCharAt( elbuilder.length() -1 ).toString() +")";
				ps = con.prepareStatement(eliminar);
				int index = 1;
				for(String i:servicioseliminar) {
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
	
/************************************************************************/
	
	public void darLikeAlServicio(int idservicio) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String ellike = "UPDATE SERVICIOSPUBLICOS SET LIKES = LIKES +1"
			              +" WHERE IDSERVICIO = ?";
			ps = con.prepareStatement(ellike);
			ps.setInt(1, idservicio);
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
/************************************************************************/
	
	public void darDislikeAlServicio(int idservicio) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String ellike = "UPDATE SERVICIOSPUBLICOS SET DISLIKES = DISLIKES +1"
			              +" WHERE IDSERVICIO = ?";
			ps = con.prepareStatement(ellike);
			ps.setInt(1, idservicio);
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}	
	
}
