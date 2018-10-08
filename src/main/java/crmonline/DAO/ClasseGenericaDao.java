package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.ClasseGenerica;
import crmonline.Entidade.Curso;

public class ClasseGenericaDao {

	Connection conn;

	public ClasseGenericaDao() {
		conn = ConDB.getConnection();
	}

	public List<ClasseGenerica> buscaCategoria() throws SQLException {
		String sql = "SELECT * FROM CATEGORIA;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<ClasseGenerica> cate = new ArrayList<>();

		while (rs.next()) {
			ClasseGenerica c = new ClasseGenerica();

			c.setId(rs.getInt("ID"));
			c.setNome(rs.getString("NOME"));

			cate.add(c);
		}

		return cate;

	}

	
	
	public List<Curso> listarCurso() throws SQLException {
		String sql = "SELECT * FROM CURSO;";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Curso> cate = new ArrayList<>();

		while (rs.next()) {
			Curso c = new Curso();
            ClasseGenerica CG = new ClasseGenerica();
            CG.setId(rs.getInt("ID"));
            CG.setNome(rs.getString("NOME"));
            c.setCurso(CG);
            c.setVAGAS_TOTAL(rs.getInt("VAGAS_TOTAL"));
            c.setDESCRI(rs.getString("DESCRI"));
            c.setDURACAO(rs.getString("DURACAO"));
            CG.setId(rs.getInt("ID_CATEGORIA"));
			cate.add(c);
		}

		return cate;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
