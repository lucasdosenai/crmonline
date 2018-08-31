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
	 
	
	public UserDAO() {
		con = ConDB.getConnection();
		buscaLogin = "SELECT USUARIO.NOME FROM USUARIO"
				  + " WHERE USUARIO.NIF = ? AND USUARIO.SENHA = ?";
	}
	
	public ArrayList<Usuario> buscaLogin(String usuario, String password) {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(buscaLogin);
			ps.setString(1, usuario);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setNome(rs.getString("USUARIO.NOME"));
				u.setNif(rs.getString("USUARIO.NIF"));
				u.setEmail(rs.getString("USUARIO.EMAIL"));
				u.setPassword(rs.getString("USUARIO.SENHA"));
				u.setStatu(rs.getInt("USUARIO.STATU"));
				u.setTipo_user(rs.getInt("USUARIO.TIPO_USUARIO"));
				
				usuarios.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}
	
}

