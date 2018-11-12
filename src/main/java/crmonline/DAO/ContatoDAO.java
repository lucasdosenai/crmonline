package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Cargo;
import crmonline.Entidade.Contato;

public class ContatoDAO {

	private Connection con;

	public ContatoDAO() {
		con = ConDB.getConnection();
	}

	public boolean inserircontato(Contato contato) throws SQLException {
		String sql = "INSERT INTO CONTATO VALUES (0,?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, contato.getNome());
		ps.setString(2, contato.getTelefone());
		ps.setString(3, contato.getCelular());
		ps.setString(4, contato.getEmail());
		ps.setInt(5, contato.getCargo().getId());
		ps.setInt(6, contato.getCliente().getId());

		System.out.println(ps.toString());

		return ps.executeUpdate() > 0;
	}

	public List<Contato> listarcontato() {
		List<Contato> contato = new ArrayList<>();

		String sql = "SELECT * FROM contato";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Contato c = new Contato();
				c.setId(rs.getInt("ID"));
				c.setNome(rs.getString("NOME"));
				c.setTelefone(rs.getString("TELEFONE"));
				c.setCelular(rs.getString("CELULAR"));
				c.setEmail(rs.getString("EMAIL"));
				c.getCargo().setId(rs.getInt("ID_CARGO"));
				c.getCliente().setId(rs.getInt("ID_CLIENTE"));

				contato.add(c);
			}

			return contato;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}