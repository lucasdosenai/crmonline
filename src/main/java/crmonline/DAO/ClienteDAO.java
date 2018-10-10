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
		con = ConDB.getConnection();
	}

	public boolean deletaCliente(String codigo) throws SQLException {
		String SQL = "DELETE FROM CLIENTE WHERE ID = ? ";
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setString(1, codigo);
		return ps.executeUpdate() > 0;
	}

	public boolean updateCliente(Cliente c) throws SQLException {
		String SQL = "UPDATE CLIENTE "
					+ "SET NOME = ? "  
					+ "SET N_FUNCIONARIOS = ? " 
					+ "SET CPNJ = ? "
					+ "SET TELEFONE = ? "
					+ "SET EMAIL = ? "
					+ "SET LOGRADOURO = ? "
					+ "SET CIDADE = ? "
					+ "SET ID_CATEGORIA = ? WHERE ID = ?";
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getNumeroFuncionario());
		ps.setString(3, c.getCnjp());
		ps.setString(4, c.getTelefone());
		ps.setString(5, c.getEmail());
		ps.setString(6, c.getLogradouro());
		ps.setString(7, c.getCidade());
		
		ps.setInt(8, c.getCodigo());
		return ps.executeUpdate() > 0;
	}

	public Cliente editaCliente(Cliente codigo) {
		String SQL = "SELECT * FROM CLIENTE WHERE ID = ?";

		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);
			ps.setInt(1, codigo.getCodigo());
			ResultSet rs = ps.executeQuery();
			Cliente c = new Cliente();
			while (rs.next()) {
				c.setNome(rs.getString("NOME"));
				c.setNumeroFuncionario(rs.getString("N_FUNCIONARIO"));
				c.setCnjp(rs.getString("CNPJ"));
				c.setTelefone(rs.getString("TELEFONE"));
				c.setEmail(rs.getString("EMAIL"));
				c.setLogradouro(rs.getString("LOGRADOURO"));
				c.setCidade(rs.getString("CIDADE"));
				c.setCodigo(rs.getInt("ID"));
				c.getCategoria().setId(rs.getInt("ID_CATEGORIA"));
			}
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Cliente> listaCategoriaCliente(String codigo) {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String SQL = "SELECT * FROM CLIENTE AS C INNER JOIN CATEGORIA AS CT ON C.ID_CATEGORIA = ? AND CT.ID = ?";
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, codigo);
			ps.setString(2, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setNome(rs.getString("NOME"));
				c.setNumeroFuncionario(rs.getString("N_FUNCIONARIO"));
				c.setCnjp(rs.getString("CNPJ"));
				c.setTelefone(rs.getString("TELEFONE"));
				c.setEmail(rs.getString("EMAIL"));
				c.setLogradouro(rs.getString("LOGRADOURO"));
				c.setCidade(rs.getString("CIDADE"));
				c.setCodigo(rs.getInt("ID"));
				c.getCategoria().setId(rs.getInt("ID_CATEGORIA"));
				c.getCategoria().setNome(rs.getString("CT.NOME"));
				clientes.add(c);
			}
			return clientes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean novoCliente(Cliente c) {
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
			ps.setInt(8, c.getCategoria().getId());

			System.out.println(ps.toString());

			if (ps.executeUpdate() > 0) {
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
	public ArrayList<Cliente> listaCliente() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String SQL = "SELECT c.*, cat.NOME as nomeCategoria FROM CLIENTE AS c "
				+ "INNER JOIN CATEGORIA as cat ON cat.id = c.ID_CATEGORIA;";

		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setNome(rs.getString("NOME"));
				c.setNumeroFuncionario(rs.getString("N_FUNCIONARIO"));
				c.setCnjp(rs.getString("CNPJ"));
				c.setTelefone(rs.getString("TELEFONE"));
				c.setEmail(rs.getString("EMAIL"));
				c.setLogradouro(rs.getString("LOGRADOURO"));
				c.setCidade(rs.getString("CIDADE"));
				c.setCodigo(rs.getInt("ID"));
				c.getCategoria().setId(rs.getInt("ID_CATEGORIA"));
				c.getCategoria().setNome(rs.getString("nomeCategoria"));
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
