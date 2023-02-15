package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import conexiones.Conexion;
import entidades.Visitas;

public class DaoVisitas {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Visitas mostrarVisitasUsuario(String usuario) throws SQLException, Exception {
		Visitas visita = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM VISITAS"
			               +" WHERE USUARIO = ?";
			ps = con.prepareStatement(buscar);
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if(rs.next()) {
				visita = new Visitas();
				visita.setIdvisita(rs.getInt(1));
				visita.setContador(rs.getLong(2));
				visita.setUsuario(rs.getString(3));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return visita;
	}
	
/**********************************************************************/
	
	public void aumentarVisitasUsuario(Visitas v) throws SQLException, Exception {
			Visitas buscarvisitante = mostrarVisitasUsuario(v.getUsuario());
			if(buscarvisitante != null) {
				try {
					 con = c.getConexion();
					 con.setAutoCommit(false);
					 String incrementar = "UPDATE VISITAS SET CONTADOR = CONTADOR + 1"
					                    +" WHERE USUARIO = ?";
					 ps = con.prepareStatement(incrementar);
					 ps.setString(1, v.getUsuario());
					 ps.executeUpdate();
					 con.commit();
				} catch(SQLException e) {
					throw e;
				} catch(Exception ex) {
					throw ex;
				}
			} else {
				try {
					con = c.getConexion();
					con.setAutoCommit(false);
					String visitar = "INSERT INTO VISITAS(CONTADOR, USUARIO)"
					                 +" VALUES(1, ?)";
					ps = con.prepareStatement(visitar);
					ps.setString(1, v.getUsuario());
					ps.executeUpdate();
					con.commit();
				} catch(SQLException e) {
					throw e;
				} catch(Exception ex) {
					throw ex;
				}
			}
	}
}
