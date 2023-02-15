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
import entidades.Usuarios;
import entidades.Visitas;
import excepciones.LoginException;
import excepciones.RegistroException;

public class DaoUsuarios {
	
	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	//DaoVisitas daovisita = new DaoVisitas();

	public void accesoUsuario(Usuarios u) throws LoginException, SQLException, Exception {
		Usuarios buscarusuario = findUsuarioByNameAndPassword(u.getNombre(), u.getClave());
		if(buscarusuario == null) {
			throw new LoginException("Credenciales no validas");
		}
	}
	
/**
 **********************************************************************************/	
	
	public Usuarios findUsuarioByNameAndPassword(String nombre, String clave) throws SQLException {
	    Usuarios usuario = null;
		con = c.getConexion();
		String buscar = "SELECT NOMBRE, CLAVE FROM USUARIOS"
		              +" WHERE NOMBRE = ? AND CLAVE = ?";
		ps = con.prepareStatement(buscar);
		ps.setString(1, nombre);
		ps.setString(2, clave);
		rs = ps.executeQuery();
		if(rs.next()) {
			usuario = new Usuarios();
			usuario.setNombre(rs.getString("NOMBRE"));
			usuario.setClave(rs.getString("CLAVE"));
		}
		return usuario;
	}
	
/**********************************************************************/

	public Usuarios findUsuarioByName(String nombre) throws SQLException {
		Usuarios usuario = null;
		con = c.getConexion();
		String buscar = "SELECT NOMBRE, CLAVE, ROL FROM USUARIOS"
		              +" WHERE NOMBRE = ?";
		ps = con.prepareStatement(buscar);
		ps.setString(1, nombre);
		rs = ps.executeQuery();
		if(rs.next()) {
			usuario = new Usuarios();
			usuario.setNombre(rs.getString("NOMBRE"));
			usuario.setRol(rs.getString("ROL"));
		}
		return usuario;
	}
	
/***********************************************************************/
	public void registrarUsuario(Usuarios u) throws RegistroException, SQLException, Exception {
		Usuarios buscarusuario = findUsuarioByName(u.getNombre());
		if(buscarusuario != null) {
			throw new RegistroException("El usuario "+u.getNombre()+" ya está registrado, ingrese otro distinto.");
		}
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String registrar = "INSERT INTO USUARIOS (DNI, NOMBRE, CLAVE, CORREO, DIRECCION, FOTOPERFIL, ROL, BAJA)"
			    +" VALUES (?, ?, ?, ?, ?, ?, 'USUARIO', 'N')";
			ps = con.prepareStatement(registrar);
			ps.setString(1, u.getDni());
			ps.setString(2, u.getNombre());
			ps.setString(3, u.getClave());
			ps.setString(4, u.getCorreo());
			ps.setString(5, u.getDireccion());
			ps.setBlob(6, u.getFotoperfil());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
/**********************************************************************************/
	
	public void listarIMGperfilUsuario(String nombre, HttpServletResponse response) {
		String sql = "select * from usuarios"+
	                " where nombre = '"+nombre+"'";
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
				inputstream = rs.getBinaryStream("FOTOPERFIL");
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
	
	public ArrayList<Usuarios> listarUsuarios(String useractual, int rowsByPage, int firstRow) throws SQLException, Exception {
		ArrayList<Usuarios> usuarios = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM USUARIOS"
			              +" WHERE NOMBRE != ?"
					      +" AND BAJA != 'S'"
			               +" ORDER BY ID_USUARIO"
					      + " LIMIT ? OFFSET ?";
			ps = con.prepareStatement(listar);
			ps.setString(1, useractual);
			ps.setInt(2, rowsByPage);
			ps.setInt(3, firstRow);
			rs = ps.executeQuery();
			while(rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setIdusuario(rs.getInt(1));
				usuario.setDni(rs.getString(2));
				usuario.setNombre(rs.getString(3));
				usuario.setClave(rs.getString(4));
				usuario.setCorreo(rs.getString(5));
				usuario.setDireccion(rs.getString(6));
				usuario.setFotoperfil(rs.getBinaryStream(7));
				usuario.setRol(rs.getString(8));
				usuario.setBaja(rs.getString(9));
				usuarios.add(usuario);
			}
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
		return usuarios;
	}
	
/***************************************************************************/
	
	public int totalUsuarios(String useractual) throws SQLException, Exception {
		int contador = 0;
		try {
			con = c.getConexion();
			String contar = "SELECT COUNT(*) AS 'TOTALUSERS'"
			              +" FROM USUARIOS"
			              +" WHERE NOMBRE != ?"
					      +" AND BAJA != 'S'";
			ps = con.prepareStatement(contar);
			ps.setString(1, useractual);
			rs = ps.executeQuery();
			if(rs.next()) {
				contador = rs.getInt("TOTALUSERS");
			}
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
		return contador;
	}	
	
/**********************************************************************/
	
	public Usuarios findUsuarioById(int idusuario) throws SQLException, Exception {
		Usuarios usuario = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT * FROM USUARIOS"
			              +" WHERE ID_USUARIO = ?";
			ps = con.prepareStatement(buscar);
			ps.setInt(1, idusuario);
			rs = ps.executeQuery();
			if(rs.next()) {
				usuario = new Usuarios();
				usuario.setIdusuario(rs.getInt(1));
				usuario.setDni(rs.getString(2));
				usuario.setNombre(rs.getString(3));
				usuario.setClave(rs.getString(4));
				usuario.setCorreo(rs.getString(5));
				usuario.setDireccion(rs.getString(6));
				usuario.setFotoperfil(rs.getBinaryStream(7));
				usuario.setRol(rs.getString(8));
				usuario.setBaja(rs.getString(9));
			}
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
		return usuario;
	}
	
/**********************************************************************/
	
	public ArrayList<Usuarios> listarRolesUsuarios() throws SQLException, Exception {
		ArrayList<Usuarios> usuarios = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT DISTINCT(ROL) FROM USUARIOS";
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setRol(rs.getString(1));
				usuarios.add(usuario);
			}
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
		return usuarios;
	}
	
/********************************************************************/
	
	public void modificarUsuario(Usuarios u) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String modificar = "UPDATE USUARIOS SET DNI = ?, NOMBRE = ?,"
			                 +" CLAVE = ?, CORREO = ?, DIRECCION = ?,"
					         +" FOTOPERFIL = ?, ROL = ? WHERE ID_USUARIO = ?";
			ps = con.prepareStatement(modificar);
			ps.setString(1, u.getDni());
			ps.setString(2, u.getNombre());
			ps.setString(3, u.getClave());
			ps.setString(4, u.getCorreo());
			ps.setString(5, u.getDireccion());
			ps.setBlob(6, u.getFotoperfil());
			ps.setString(7, u.getRol());
			ps.setInt(8, u.getIdusuario());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
/****************************************************************/
	
	public void eliminarUsuario(String nombre) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String eliminar = "UPDATE USUARIOS SET BAJA = 'S'"
			                +" WHERE NOMBRE = ?";
			ps = con.prepareStatement(eliminar);
			ps.setString(1, nombre);
			ps.executeUpdate();
			con.commit();
		}
		catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String dardebaja = "INSERT INTO usuariosbaja(ID_USUARIO, DNI, NOMBRE, CLAVE, CORREO, DIRECCION, FOTOPERFIL, ROL)"
					+ " SELECT ID_USUARIO, DNI, NOMBRE, CLAVE, CORREO, DIRECCION, FOTOPERFIL, ROL"
					+ " FROM usuarios "
					+ " WHERE NOMBRE = ?";
			ps = con.prepareStatement(dardebaja);
			ps.setString(1, nombre);
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
/*********************************************************************/

	public ArrayList<Usuarios> listarUsuariosAlta() throws SQLException, Exception {
		ArrayList<Usuarios> usuarios = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM USUARIOS"
					      +" WHERE BAJA != 'S'"
			               +" ORDER BY ID_USUARIO";
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setIdusuario(rs.getInt(1));
				usuario.setDni(rs.getString(2));
				usuario.setNombre(rs.getString(3));
				usuario.setClave(rs.getString(4));
				usuario.setCorreo(rs.getString(5));
				usuario.setDireccion(rs.getString(6));
				usuario.setFotoperfil(rs.getBinaryStream(7));
				usuario.setRol(rs.getString(8));
				usuario.setBaja(rs.getString(9));
				usuarios.add(usuario);
			}
		} catch(SQLException e) {
			throw e;
		}
		catch(Exception ex) {
			throw ex;
		}
		return usuarios;
	}
	
/**********************************************************************/
	public ArrayList<Usuarios> listarUsuariosBaja() throws SQLException, Exception {
		ArrayList<Usuarios> listausuarios = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM USUARIOSBAJA";
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				Usuarios usuarios = new Usuarios();
				usuarios.setIdusuario(rs.getInt(1));
				usuarios.setNombre(rs.getString(3));
				listausuarios.add(usuarios);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return listausuarios;
	}
	
/***************************************************************************/
	
	public void recuperarUsuariosEliminados(int idusuario) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String eliminarBaja = "DELETE FROM USUARIOSBAJA"
			                    +" WHERE ID_USUARIO = ?";
			ps = con.prepareStatement(eliminarBaja);
			ps.setInt(1, idusuario);
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		} try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String elalta = "UPDATE USUARIOS SET BAJA = 'N'"
			              +" WHERE ID_USUARIO = ?";
			ps = con.prepareStatement(elalta);
			ps.setInt(1, idusuario);
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}	
	
}
