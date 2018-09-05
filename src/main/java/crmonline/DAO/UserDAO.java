package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crmonline.DB.ConDB;
import crmonline.Entidade.Usuario;

public class UserDAO {
	private Connection con;
	private String buscaLogin;
	private String buscaEmail,buscaNIF;
	
	public UserDAO() {
		con = ConDB.getConnection();
		buscaLogin = "SELECT USUARIO.NOME FROM USUARIO"
				  + " WHERE USUARIO.NIF = ? AND USUARIO.SENHA = ?";
		
		buscaEmail = "SELECT USUARIO.NOME FROM USUARIO"
				+ " WHERE USUARIO.EMAIL = ?";
		buscaNIF = "SELECT USUARIO.NIF FROM USUARIO"
				+ "WHERE USUARIO.NIF = ?";
	}
	public Boolean inserirUser() {
		
		return true;
	}
	public Usuario buscarEmail(String email) {
		Usuario raiz = new Usuario();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(buscaEmail);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt("USUARIO.ID"));
				u.setNome(rs.getString("USUARIO.NOME"));
				u.setNif(rs.getString("USUARIO.NIF"));
				u.setEmail(rs.getString("USUARIO.EMAIL"));
				u.setPassword(rs.getString("USUARIO.SENHA"));
				u.setStatu(rs.getInt("USUARIO.STATU"));
				u.setTipo_user(rs.getInt("USUARIO.TIPO_USUARIO"));
				
				raiz = u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return raiz;
	}
	
	public Boolean verificaNifNoBanco(String nif) {
		try {
			PreparedStatement ps = con.prepareStatement(buscaNIF);
			ps.setString(1, nif);
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt("USUARIO.ID"));
				u.setNome(rs.getString("USUARIO.NOME"));
				u.setNif(rs.getString("USUARIO.NIF"));
				u.setSexo(rs.getString("USUARIO.SEXO"));
				u.setEmail(rs.getString("USUARIO.EMAIL"));
				u.setPassword(rs.getString("USUARIO.SENHA"));
				u.setStatu(rs.getInt("USUARIO.STATU"));
				u.setTipo_user(rs.getInt("USUARIO.TIPO_USUARIO"));
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Usuario buscaLogin(String usuario, String password) {
		 ArrayList<Usuario> usuarios = new ArrayList<>();
		 Usuario raiz = new Usuario();
		try {
			PreparedStatement ps = con.prepareStatement(buscaLogin);
			ps.setString(1, usuario);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt("USUARIO.ID"));
				u.setNome(rs.getString("USUARIO.NOME"));
				u.setNif(rs.getString("USUARIO.NIF"));
				u.setEmail(rs.getString("USUARIO.EMAIL"));
				u.setPassword(rs.getString("USUARIO.SENHA"));
				u.setStatu(rs.getInt("USUARIO.STATU"));
				u.setTipo_user(rs.getInt("USUARIO.TIPO_USUARIO"));
				
				raiz = u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return raiz;
	}
	
	

	
	public boolean cadastrar(Usuario usuario) {
		String sql = "INSERT INTO USUARIO VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getNif());
			ps.setString(3, usuario.getEmail());
			ps.setString(4, usuario.getPassword());
			ps.setInt(6, usuario.getStatu());
			ps.setInt(7, usuario.getTipo_user());
			
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	
}

