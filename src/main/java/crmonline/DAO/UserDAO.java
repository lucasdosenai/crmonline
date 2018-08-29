package crmonline.DAO;

import java.sql.Connection;

import crmonline.DB.ConDB;
import crmonline.Entidade.Usuario;

public class UserDAO {
	private Connection con;
	
	public UserDAO() {
		con = ConDB.getConnection();
	}
	
	public Usuario buscaUsuario() {
		
	}
	
}
