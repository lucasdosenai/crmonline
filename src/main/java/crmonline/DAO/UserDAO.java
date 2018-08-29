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
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(buscaLogin);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Usuario buscaLogin(String usuario, String password) {
		
		return null;
	}
	
}
