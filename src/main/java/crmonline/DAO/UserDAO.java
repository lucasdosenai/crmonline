package crmonline.DAO;

import java.sql.Connection;

import crmonline.DB.ConDB;
import crmonline.Entidade.Usuario;

public class UserDAO {
	private Connection con;
	String buscaLogin;
	
	public UserDAO() {
		con = ConDB.getConnection();
	}
	
	public Usuario buscaLogin(String usuario, String password) {
		
		return null;
	}
	
}
