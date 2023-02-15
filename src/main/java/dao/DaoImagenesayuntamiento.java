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
import entidades.Comentarios;
import entidades.Imagenesayuntamiento;

public class DaoImagenesayuntamiento {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<Imagenesayuntamiento> listarIdimagenes() throws SQLException, Exception {
		ArrayList<Imagenesayuntamiento> imagenes = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM IMAGENESAYUNTAMIENTO ORDER BY ID_IMAGEN";
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				Imagenesayuntamiento imagen = new Imagenesayuntamiento();
				imagen.setIdimagen(rs.getInt(1));
				imagenes.add(imagen);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return imagenes;
	}
	
/*****************************************************************************************/	
	public void listarIMGayuntamiento(int idimagen, HttpServletResponse response) {
		String sql = "select * from imagenesayuntamiento"+
	                " where id_imagen = "+idimagen;
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
				inputstream = rs.getBinaryStream("FOTO");
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
}
