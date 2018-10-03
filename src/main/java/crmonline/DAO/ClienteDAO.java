package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Categoria;
import crmonline.Entidade.Cliente;

public class ClienteDAO {
	
	private Connection con; 
	
	public ClienteDAO() {
		con      = ConDB.getConnection();
	}
	
	public String categoriaCliente(String ID) {
		String SQL = "SELECT CT.NOME FROM CATEGORIA CT, CLIENTE C " + 
				" WHERE CT.ID = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();
			Categoria c = new Categoria();
			while(rs.next()) {
				c.setCodigo(rs.getInt("ID"));
				c.setNome(rs.getString("NOME"));
			}
			
			return c.getNome().toString();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean novoCliente(Cliente c) {
		System.out.println(c.getCod_categoria());
		String SQL = "INSERT INTO CLIENTE VALUES (0,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getNumeroFuncionario());
			ps.setString(3, c.getCnjp());
			ps.setString(4, c.getTelefone());
			ps.setString(5, c.getEmail());
			ps.setString(6, c.getLogradouro());
			ps.setString(7, c.getCidade());
			ps.setInt(8, c.getCod_categoria());
			
			 if(ps.executeUpdate() > 0) {
				 return true;
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @return
	 */
	public ArrayList<Cliente> listaCliente(){
		ArrayList<Cliente> clientes = new ArrayList<>();
		String SQL = "SELECT * FROM CLIENTE";
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Cliente c = new Cliente(); 
				c.setNome(rs.getString("NOME"));
				c.setNumeroFuncionario(rs.getString("N_FUNCIONARIO"));
				c.setCnjp(rs.getString("CNPJ"));
				c.setTelefone(rs.getString("TELEFONE"));
				c.setEmail(rs.getString("EMAIL"));
				c.setLogradouro(rs.getString("LOGRADOURO"));
				c.setCidade(rs.getString("CIDADE"));
				c.setCod_categoria(rs.getInt("ID_CATEGORIA"));
				c.setCodigo(rs.getInt("ID"));
				clientes.add(c);
			}
		return clientes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
