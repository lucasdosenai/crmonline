package crmonline.MBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmonline.DAO.CargoDAO;
import crmonline.Entidade.Cargo;
import crmonline.util.Mensagem;

public class CargoMB {

	private Cargo cargo;
	private List<Cargo> listarCargos;
	private CargoDAO cDao;
	
	public CargoMB() {
		cargo = new Cargo();
		listarCargos = new ArrayList<>();
		cDao = new CargoDAO();
		
	}

	public void salvarCargo() {

		if (cDao.salvarCargo(cargo)) {
			cargo = cDao.listarCargo();
			Mensagem.make("Curso " + cargo.getNome() + " inserido com sucesso !");
		} else {
			Mensagem.make("Falha ao salvar curso !");
		}

	}
	
	
	public CargoDAO getcDao() {
		return cDao;
	}




	public void setcDao(CargoDAO cDao) {
		this.cDao = cDao;
	}




	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getListarCargos() {
		return listarCargos;
	}

	public void setListarCargos(List<Cargo> listarCargos) {
		this.listarCargos = listarCargos;
	}
	
	
	
	
	
	
	
	
	
	
	
}
