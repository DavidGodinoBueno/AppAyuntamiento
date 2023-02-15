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

import entidades.Fiestas;
import excepciones.FiestaException;

public class DaoFiestas {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<Fiestas> listarFiestasPorMes(String elmes) throws SQLException, Exception {
		ArrayList<Fiestas> fiestas = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT TIMESTAMPDIFF(DAY, FECHAINICIO, FECHAFIN) +1 AS numerodias,"
			              +" IDFIESTA, NOMBRE, INFORMACION, FOTOFIESTA, FECHAINICIO, FECHAFIN"
			              +" FROM FIESTAS"
			              +" WHERE (SUBSTRING(FECHAINICIO, 6, 2) = ?)";
			ps = con.prepareStatement(listar);
			ps.setString(1, elmes);
			rs = ps.executeQuery();
			while(rs.next()) {
				Fiestas fiesta = new Fiestas();
				fiesta.setIdfiesta(rs.getInt("IDFIESTA"));
				fiesta.setNombre(rs.getString("NOMBRE"));
				fiesta.setInformacion(rs.getString("INFORMACION"));
				fiesta.setFotofiesta(rs.getBinaryStream("FOTOFIESTA"));
				fiesta.setFechainicio(rs.getDate("FECHAINICIO"));
				fiesta.setFechafin(rs.getDate("FECHAFIN"));
				fiesta.setRangodias(rs.getInt("numerodias"));
				fiestas.add(fiesta);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return fiestas;
	}
	
/************************************************************************/
	
	public void listarIMGfiestas(int idfiesta, HttpServletResponse response) {
		String sql = "select * from fiestas"+
	                " where idfiesta = "+idfiesta;
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
				inputstream = rs.getBinaryStream("FOTOFIESTA");
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
	
	public Fiestas findFiestaById(int idfiesta) throws SQLException, Exception {
		Fiestas fiesta = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT TIMESTAMPDIFF(DAY, FECHAINICIO, FECHAFIN) +1 AS numerodias,"
					     + " IDFIESTA, NOMBRE, INFORMACION, FOTOFIESTA, FECHAINICIO, FECHAFIN"
					     + " FROM FIESTAS"
					     + " WHERE IDFIESTA = ?";
			ps = con.prepareStatement(buscar);
			ps.setInt(1, idfiesta);
			rs = ps.executeQuery();
			if(rs.next()) {
				fiesta = new Fiestas();
				fiesta.setIdfiesta(rs.getInt("IDFIESTA"));
				fiesta.setNombre(rs.getString("NOMBRE"));
				fiesta.setInformacion(rs.getString("INFORMACION"));
				fiesta.setFotofiesta(rs.getBinaryStream("FOTOFIESTA"));
				fiesta.setFechainicio(rs.getDate("FECHAINICIO"));
				fiesta.setFechafin(rs.getDate("FECHAFIN"));
				fiesta.setRangodias(rs.getInt("numerodias"));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return fiesta;
	}
	
/******************************************************************************/

	public Fiestas findFiestaByName(String nombre) throws SQLException, Exception {
		Fiestas fiesta = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT TIMESTAMPDIFF(DAY, FECHAINICIO, FECHAFIN) +1 AS numerodias,"
					     + " IDFIESTA, NOMBRE, INFORMACION, FOTOFIESTA, FECHAINICIO, FECHAFIN"
					     + " FROM FIESTAS"
					     + " WHERE NOMBRE = ?";
			ps = con.prepareStatement(buscar);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			if(rs.next()) {
				fiesta = new Fiestas();
				fiesta.setIdfiesta(rs.getInt("IDFIESTA"));
				fiesta.setNombre(rs.getString("NOMBRE"));
				fiesta.setInformacion(rs.getString("INFORMACION"));
				fiesta.setFotofiesta(rs.getBinaryStream("FOTOFIESTA"));
				fiesta.setFechainicio(rs.getDate("FECHAINICIO"));
				fiesta.setFechafin(rs.getDate("FECHAFIN"));
				fiesta.setRangodias(rs.getInt("numerodias"));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return fiesta;
	}
	
/*******************************************************************************/
	public void altaFiesta(Fiestas f) throws FiestaException, SQLException, Exception {
		Fiestas buscarfiesta = findFiestaByName(f.getNombre());
		if(buscarfiesta != null) {
			 throw new FiestaException("Ya exite la fiesta "+f.getNombre()+", agrege otra distinta.");
		}
		if(f.getFechafin().compareTo(f.getFechainicio()) < 0) {
			throw new FiestaException("La fecha en la que termina la fiesta es anterior a la que empieza la fiesta");
		}
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String insertar = "INSERT INTO FIESTAS (NOMBRE, INFORMACION,"+
			                " FOTOFIESTA, FECHAINICIO, FECHAFIN) VALUES(?, ?, ?, ?, ?)";
			ps = con.prepareStatement(insertar);
			ps.setString(1, f.getNombre());
			ps.setString(2, f.getInformacion());
			ps.setBlob(3, f.getFotofiesta());
			ps.setDate(4, f.getFechainicio());
			ps.setDate(5, f.getFechafin());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
}
