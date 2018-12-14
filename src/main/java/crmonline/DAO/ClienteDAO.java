package crmonline.DAO;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Categoria;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.ClienteFiltro;

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

	public boolean desativarAtivarUsuario(Cliente c) throws SQLException {
		String SQL = "UPDATE CLIENTE SET STATU = ? WHERE ID = ?";
		PreparedStatement ps;

		if (c.getStatu() != 0) { 
			c.setStatu(0);	
		}else c.setStatu(1);

		ps = con.prepareStatement(SQL);
		ps.setInt(1, c.getStatu());
		ps.setInt(2, c.getCodigo());
		
		return ps.executeUpdate() > 0;
	}

	public boolean updateCliente(Cliente c) {
		System.out.println("UpdateCliente()");
		String SQL = "UPDATE CLIENTE " + "SET NOME = ? " + ",N_FUNCIONARIO = ? " + ",CNPJ = ? " + ",TELEFONE = ? "
				+ ",EMAIL = ? " + ",LOGRADOURO = ? " + ",CIDADE = ? " + ",ID_CATEGORIA = ? WHERE ID = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getNumeroFuncionario());
			ps.setString(3, c.getCnjp());
			ps.setString(4, c.getTelefone());
			ps.setString(5, c.getEmail());
			ps.setString(6, c.getLogradouro());
			ps.setString(7, c.getCidade());
			ps.setInt(8, c.getCategoria().getId());
			ps.setInt(9, c.getCodigo());
			
			System.out.println(ps.toString());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
	
	public List<Cliente> listaFiltro(ClienteFiltro cFiltro) throws SQLException{
		/*
		 Essa Lógica foi pensada pelo Felipe Dias (Grupo 5 - Giventario)
		 */
		String SQL = "SELECT * FROM CLIENTE";
		List<Cliente> clientes = new ArrayList<>();
		int contador = 0;
		for(Field var : cFiltro.getClass().getDeclaredFields()) {
			try {
				Object value = var.get(cFiltro);
				if(value != null) {
					if(contador == 0) {
						if(var.getName().equals("ramo")) {
							SQL +=  " WHERE ID_CATEGORIA = " + value;
						}else {
							SQL += " WHERE STATU = " + value;
						}
					}else {
						SQL += " AND STATU = " + value;
					}
					contador++;
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
			c.getCategoria().setNome(nomeCategoria(c.getCategoria().getId()));
			c.setStatu(rs.getInt("STATU"));
			clientes.add(c);
		}
		return clientes;
	}
	
	private String nomeCategoria(Integer codigo) {
		String SQL = "SELECT * FROM CATEGORIA WHERE ID = " + codigo;
		String resultado = "";
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				resultado = rs.getString("NOME");
			}else {
				resultado = "S/Ramo";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ArrayList<Cliente> listaCategoriaCliente(Integer codigo,Integer statu) {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String SQL = "SELECT * FROM CLIENTE AS C INNER JOIN CATEGORIA AS CT ON C.ID_CATEGORIA = CT.ID AND C.STATU = ? WHERE C.ID_CATEGORIA = ?  ";
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, statu);
			ps.setInt(2, codigo);
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
		String SQL = "INSERT INTO CLIENTE VALUES (0,?,?,?,?,?,?,?,?,?)";

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
			ps.setInt(9, 1);

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
	public ArrayList<Cliente> listaDesativados() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String SQL = "SELECT c.*, cat.NOME as nomeCategoria FROM CLIENTE AS c "
				+ "INNER JOIN CATEGORIA as cat ON cat.id = c.ID_CATEGORIA AND c.STATU = 0;";

		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setCodigo(rs.getInt("ID"));
				c.setNome(rs.getString("NOME"));
				c.setNumeroFuncionario(rs.getString("N_FUNCIONARIO"));
				c.setCnjp(rs.getString("CNPJ"));
				c.setTelefone(rs.getString("TELEFONE"));
				c.setEmail(rs.getString("EMAIL"));
				c.setLogradouro(rs.getString("LOGRADOURO"));
				c.setCidade(rs.getString("CIDADE"));
				c.getCategoria().setId(rs.getInt("ID_CATEGORIA"));
				c.setStatu(rs.getInt("STATU"));
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
	
	public ArrayList<Cliente> listaCliente(Integer statu) {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String SQL = "SELECT c.*, cat.NOME as nomeCategoria FROM CLIENTE AS c "
				+ "INNER JOIN CATEGORIA as cat ON cat.id = c.ID_CATEGORIA AND c.STATU = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, statu);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente c = new Cliente();
				c.setCodigo(rs.getInt("ID"));
				c.setNome(rs.getString("NOME"));
				c.setNumeroFuncionario(rs.getString("N_FUNCIONARIO"));
				c.setCnjp(rs.getString("CNPJ"));
				c.setTelefone(rs.getString("TELEFONE"));
				c.setEmail(rs.getString("EMAIL"));
				c.setLogradouro(rs.getString("LOGRADOURO"));
				c.setCidade(rs.getString("CIDADE"));
				c.getCategoria().setId(rs.getInt("ID_CATEGORIA"));
				c.setStatu(rs.getInt("STATU"));
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
