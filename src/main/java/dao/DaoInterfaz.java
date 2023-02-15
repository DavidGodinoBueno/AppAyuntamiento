package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexiones.Conexion;
import entidades.Interfaz;

public class DaoInterfaz {
  
	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Interfaz coloresInterfaz() throws SQLException, Exception {
		Interfaz interfaz = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM INTERFAZ"
			              +" WHERE IDINTERFAZ = 1";
			ps = con.prepareStatement(buscar);
			rs = ps.executeQuery();
			if(rs.next()) {
				interfaz = new Interfaz();
				interfaz.setIdinterfaz(rs.getInt(1));
				interfaz.setColorfondo(rs.getString(2));
				interfaz.setColornavegacion(rs.getString(3));
				interfaz.setColorforms(rs.getString(4));
				interfaz.setColortablas(rs.getString(5));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return interfaz;
	}
	
/**********************************************************************/
	
	public void cambioColorNav(Interfaz i) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String modificar = "UPDATE INTERFAZ SET COLORNAVEGACION = ?"
			                 +" WHERE IDINTERFAZ = 1";
			ps = con.prepareStatement(modificar);
			ps.setString(1, i.getColornavegacion());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
}
