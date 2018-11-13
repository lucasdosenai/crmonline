package crmonline.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.Curso;

public class RelatorioDAO {
	
	private Connection con;
	public RelatorioDAO() {
		con = ConDB.getConnection();
	}
	
	public List<Agenda> listaRelatorioPorTipo(Integer codigo,String acao) throws SQLException, ParseException{
		List<Agenda> relatorios = new ArrayList<>();
		Agenda a = new Agenda();
		String SQL = "SELECT * FROM AGENDA";
		
		if(acao == "cliente") {
			SQL = "SELECT * FROM AGENDA AS A INNER JOIN CLIENTE AS C ON "
					+ "A.ID_CLIENTE = ? AND C.ID = A.ID_CLIENTE "
					+ "INNER JOIN CURSO AS CG ON CG.ID = A.ID_CURSO";
		}else if(acao == "cargo") {
			SQL = "SELECT * FROM AGENDA WHERE ID_CURSO = ?";
		}
		
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			a.setCodigo(rs.getInt("ID"));
			a.setNome(rs.getString("NOME"));
			a.setAtendente(rs.getString("ATENDENTE"));
			
			String data = rs.getString("DATAV");
			SimpleDateFormat b = new SimpleDateFormat("dd/MM/yyyy");
			a.setData(b.parse(data));
			a.setHora(rs.getString("HORARIO"));
			a.setEstadovisita(rs.getInt("ESTADOS"));
			a.setClassificacao(rs.getString("CLASSFICACOES"));
			a.setObservacao(rs.getString("OBSERVACOES"));
			a.setId_visitante(rs.getInt("ID_VISITANTE"));
			a.setId_cliente(rs.getInt("ID_CLIENTE"));
			
			Cliente c = new Cliente();
			c.setCodigo(rs.getInt("C.ID"));
			c.setNome(rs.getString("C.NOME"));
			a.setCliente(c);
			
			Curso cs = new Curso();
			cs.setNome(rs.getString("CG.NOME"));
			cs.setId(rs.getInt("CG.ID"));
			cs.getCATEGORIA().setId(rs.getInt("CG.ID_CATEGORIA"));
			a.setCursoObj(cs);
			a.setCurso(rs.getInt("ID_CURSO"));
			
			relatorios.add(a);
		}
		
		return relatorios;
	}
	
	public List<Agenda> listaAgendaKeyDown(String busca) {
		String SQL = "SELECT * FROM AGENDA WHERE DATAV LIKE" +" 12%";
		List<Agenda> listaCompleta = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);
			//ps.setString(1, busca);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
				Agenda a = new Agenda();
				a.setCodigo(rs.getInt("ID"));
				a.setNome(rs.getString("NOME"));
				a.setAtendente(rs.getString("ATENDENTE"));
				String data = rs.getString("DATAV");
				SimpleDateFormat b = new SimpleDateFormat("dd/MM/yyyy");
				try {
					a.setData(b.parse(data));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					a.setData(new Date(0));
				}
				a.setHora(rs.getString("HORARIO"));
				a.setEstadovisita(rs.getInt("ESTADOS"));
				a.setClassificacao(rs.getString("CLASSFICACOES"));
				a.setObservacao(rs.getString("OBSERVACOES"));
				a.setId_visitante(rs.getInt("ID_VISITANTE"));
				a.setId_cliente(rs.getInt("ID_CLIENTE"));
				a.setCurso(rs.getInt("ID_CURSO"));
				listaCompleta.add(a);
			}
			return listaCompleta;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String clienteConverte(String codigo) {
		String SQL = "SELECT*FROM CLIENTE WHERE ID = ?";
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, codigo);
			ResultSet rs = ps.executeQuery();
			Cliente c = new Cliente();
			if(rs.next()) {	
				c.setNome(rs.getString("NOME"));
			}
			return c.getNome();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Sem informações";
	}
	
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
}
