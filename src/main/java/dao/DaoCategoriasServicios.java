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
import entidades.CategoriasServicios;
import excepciones.CategoriaException;

public class DaoCategoriasServicios {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<CategoriasServicios> listaCategoriasServicios() throws SQLException, Exception {
		ArrayList<CategoriasServicios> categorias = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM CATEGORIAS_SERVICIOS"
			              +" ORDER BY IDCATEGORIA";
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoriasServicios categoria = new CategoriasServicios();
				categoria.setIdcategoria(rs.getInt(1));
				categoria.setDescripcion(rs.getString(2));
				categoria.setFotocategoria(rs.getBinaryStream(3));
				categorias.add(categoria);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return categorias;
	}
	
/**********************************************************************************/
	
	public void listarIMGcatservicios(int idcategoria, HttpServletResponse response) {
		String sql = "select * from categorias_servicios"+
	                " where idcategoria = "+idcategoria;
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
				inputstream = rs.getBinaryStream("FOTOCATEGORIA");
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
	
/**********************************************************************/
	
	public CategoriasServicios findCategoriaById(int idcategoria) throws SQLException, Exception {
		CategoriasServicios categoria = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM CATEGORIAS_SERVICIOS"
			              +" WHERE IDCATEGORIA = ?";
			ps = con.prepareStatement(buscar);
			ps.setInt(1, idcategoria);
			rs = ps.executeQuery();
			if(rs.next()) {
				categoria = new CategoriasServicios();
				categoria.setIdcategoria(rs.getInt(1));
				categoria.setDescripcion(rs.getString(2));
				categoria.setFotocategoria(rs.getBinaryStream(3));
			}
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
		return categoria;
	}	
	
/***************************************************************************/
	
	public ArrayList<CategoriasServicios> buscarCategorias(String patron) throws SQLException, Exception {
		ArrayList<CategoriasServicios> categorias = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT DISTINCT C.IDCATEGORIA, C.DESCRIPCION, C.FOTOCATEGORIA, S.BAJA"
			               +" FROM CATEGORIAS_SERVICIOS C JOIN SERVICIOSPUBLICOS S"
					       +" ON C.IDCATEGORIA = S.IDCATEGORIA"
			              +" WHERE DESCRIPCION LIKE ?"
					       +" AND S.BAJA != 'S'"
			              +" ORDER BY IDCATEGORIA";
			ps = con.prepareStatement(listar);
			ps.setString(1, "%"+patron+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoriasServicios categoria = new CategoriasServicios();
				categoria.setIdcategoria(rs.getInt("C.IDCATEGORIA"));
				categoria.setDescripcion(rs.getString("C.DESCRIPCION"));
				categoria.setFotocategoria(rs.getBinaryStream("C.FOTOCATEGORIA"));
				categorias.add(categoria);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(con!=null) con.close();
		}
		return categorias;
	}
	
/********************************************************************************/

/**********************************************************************/
	
	public CategoriasServicios findCategoriaByDescription(String descripcion) throws SQLException, Exception {
		CategoriasServicios categoria = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM CATEGORIAS_SERVICIOS"
			              +" WHERE DESCRIPCION = ?";
			ps = con.prepareStatement(buscar);
			ps.setString(1, descripcion);
			rs = ps.executeQuery();
			if(rs.next()) {
				categoria = new CategoriasServicios();
				categoria.setIdcategoria(rs.getInt(1));
				categoria.setDescripcion(rs.getString(2));
				categoria.setFotocategoria(rs.getBinaryStream(3));
			}
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
		return categoria;
	}	
	
/********************************************************************************/
	public void altaCategoria(CategoriasServicios c) throws CategoriaException, SQLException, Exception {
		Conexion conexion = new Conexion();
		CategoriasServicios buscarcategoria = findCategoriaByDescription(c.getDescripcion());
		if(buscarcategoria != null) {
			throw new CategoriaException("Ya existe la categoría: "+c.getDescripcion()+", registre otra.");
		}
		try {
			con = conexion.getConexion();
	        con.setAutoCommit(false);
			String insertar = "INSERT INTO CATEGORIAS_SERVICIOS(DESCRIPCION, FOTOCATEGORIA)"
	                        +" VALUES(?, ?)";
			ps = con.prepareStatement(insertar);
			ps.setString(1, c.getDescripcion());
			ps.setBlob(2, c.getFotocategoria());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
}
