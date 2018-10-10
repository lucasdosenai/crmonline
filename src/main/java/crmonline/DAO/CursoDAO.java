package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crmonline.DB.ConDB;
import crmonline.Entidade.Curso;

public class CursoDAO {

	private Connection con;

	public CursoDAO() {
		con = ConDB.getConnection();
	}

	public boolean salvarCurso(Curso curso) throws SQLException {

		String sql = "INSERT INTO CURSO values(0, ?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, curso.getNome());
		ps.setInt(2, curso.getVAGAS_TOTAL());
		ps.setString(3, curso.getDESCRI());
		ps.setString(4, curso.getDURACAO());
		ps.setInt(5, curso.getCATEGORIA().getId());

		System.out.println(ps.toString());

		return ps.executeUpdate() > 0;
	}

}
