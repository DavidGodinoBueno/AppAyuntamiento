package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Comentarios;

public class DaoComentarios {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void escribirComentarios(Comentarios comentario) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String comentar = "INSERT INTO COMENTARIOS(DESCRIPCION, NOMBREUSUARIO)"
			               +" VALUES(?, ?)";
			ps = con.prepareStatement(comentar);
			ps.setString(1, comentario.getDescripcion());
			ps.setString(2, comentario.getNombreusuario());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		} 
	}
	
/**************************************************************************************/	
	
	public ArrayList<Comentarios> listarComentarios() throws SQLException, Exception {
		ArrayList<Comentarios> comentarios = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM COMENTARIOS ORDER BY ID_COMENTARIO";
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				Comentarios comentario = new Comentarios();
				comentario.setIdcomentario(rs.getInt(1));
				comentario.setDescripcion(rs.getString(2));
				comentario.setNombreusuario(rs.getString(3));
				comentario.setFechacomentario(rs.getTimestamp(4));
				comentarios.add(comentario);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return comentarios;
	}
	
/***********************************************************************/
	
	public void deleteComentario(int idcomentario) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String eliminar = "DELETE FROM COMENTARIOS WHERE ID_COMENTARIO = ?";
			ps = con.prepareStatement(eliminar);
			ps.setInt(1, idcomentario);
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		} 
	}

/**************************************************************************/
	
	public ArrayList<Comentarios> ordenFechaComentarios(String orden) throws SQLException, Exception {
		ArrayList<Comentarios> comentarios = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM COMENTARIOS ORDER BY FECHACOMENTARIO "+orden;
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				Comentarios comentario = new Comentarios();
				comentario.setIdcomentario(rs.getInt(1));
				comentario.setDescripcion(rs.getString(2));
				comentario.setNombreusuario(rs.getString(3));
				comentario.setFechacomentario(rs.getTimestamp(4));
				comentarios.add(comentario);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return comentarios;
	}

	
}
