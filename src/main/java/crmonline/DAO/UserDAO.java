package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.RecuperaUser;
import crmonline.Entidade.Usuario;

public class UserDAO {
	private Connection con;
	private String buscaLogin;
	private String buscaEmail,buscaNIF;
	
	public UserDAO() {
		con = ConDB.getConnection();
		buscaLogin = "SELECT * FROM USUARIO WHERE NIF = ? AND SENHA = ?";
		buscaEmail = "SELECT * FROM USUARIO WHERE EMAIL = ?";
		buscaNIF = " SELECT USUARIO.NIF FROM USUARIO WHERE USUARIO.NIF = ?";
	}
	
	public ArrayList<Usuario> listaClientes (){
			ArrayList<Usuario> clientes = new ArrayList<>();
			String SQL = "SELECT * FROM USUARIO";
			PreparedStatement PS;
			try {
				PS = con.prepareStatement(SQL);
				ResultSet rs = PS.executeQuery();
				
				while(rs.next()) {
					Usuario u = new Usuario();
					u.setCodigo(rs.getInt("ID"));
					u.setNome(rs.getString("NOME"));
					u.setNif(rs.getString("NIF"));
					u.setSexo(rs.getString("SEXO"));
					u.setPassword("*****");
					u.setEmail(rs.getString("EMAIL"));
					u.setStatu(rs.getInt("STATU"));
					u.setTipo_user(rs.getInt("TIPO_USUARIO"));
					
					clientes.add(u);	
				}
				return clientes;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	public Usuario buscarEmail(String email) {
		PreparedStatement ps;
		try {
			System.out.println(buscaEmail);
			ps = con.prepareStatement(buscaEmail);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt("ID"));
				u.setNome(rs.getString("NOME"));
				u.setNif(rs.getString("NIF"));
				u.setSexo(rs.getString("SEXO"));
				u.setEmail(rs.getString("EMAIL"));
				u.setPassword(rs.getString("SENHA"));
				u.setStatu(rs.getInt("STATU"));
				u.setTipo_user(rs.getInt("TIPO_USUARIO"));
				
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario verificaNifNoBanco(String nif) {
		Usuario raiz = null;
		try {
			PreparedStatement ps = con.prepareStatement(buscaNIF);
			ps.setString(1, nif.replaceAll(" ", ""));
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				raiz = new Usuario();
				raiz.setNif(rs.getString("NIF"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return raiz;
	}
	
	public Usuario buscaLogin(String usuario, String password) { 
		try {
			PreparedStatement ps = con.prepareStatement(buscaLogin);
			ps.setString(1, usuario);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt("ID"));
				u.setNome(rs.getString("NOME"));
				u.setNif(rs.getString("NIF"));
				u.setEmail(rs.getString("EMAIL"));
				u.setPassword(rs.getString("SENHA"));
				u.setStatu(rs.getInt("STATU"));
				u.setTipo_user(rs.getInt("TIPO_USUARIO"));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean deletaProtocologo(String ID) {
		String SQL = "DELETE FROM RECUPERA_USUARIO WHERE ID_USER = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, ID);
			
			if(ps.executeUpdate() > 0) {
				return  true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean novaSenha(String pass, String ID) {
		String SQL = "UPDATE USUARIO SET SENHA = ? WHERE ID = ? ";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, pass);
			ps.setString(2, ID);
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean cadastrar(Usuario usuario) {
		String sql = "INSERT INTO USUARIO VALUES(0, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getNif().replaceAll(" ", ""));
			ps.setString(3, usuario.getSexo());
			ps.setString(4, usuario.getEmail());
			ps.setString(5, usuario.getPassword());
			ps.setInt(6, usuario.getStatu());
			ps.setInt(7, usuario.getTipo_user());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	public RecuperaUser listaProtocolo(String cod){
		String sql = "SELECT * FROM RECUPERA_USUARIO WHERE CODIGO = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cod);
			ResultSet rs = ps.executeQuery();
			RecuperaUser eUser = new RecuperaUser();
			while(rs.next()) {
				eUser.setId_user(rs.getInt("ID_USER"));
				eUser.setCodigo(rs.getString("CODIGO"));
				return eUser;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean recuperaUser(RecuperaUser ru) {
		String sql = "INSERT INTO RECUPERA_USUARIO VALUES(0, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ru.getId_user());
			ps.setString(2, ru.getCodigo());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}

