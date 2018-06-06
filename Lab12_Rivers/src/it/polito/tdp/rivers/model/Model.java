package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao;
	private Simulatore simulatore;
	private RiverIdMap riverIdMap;
	
	public Model() {
		dao = new RiversDAO();
		riverIdMap = new RiverIdMap();
		
	}
	
	public List<River> getAllRivers(){
		return dao.getAllRivers(riverIdMap);
	}
	
	public Result popola (River river) {
		Result result = dao.getResult(river);
		return result;
	}

	public void simula(int k, River river) {
		simulatore = new Simulatore(k, river, this);
		simulatore.run();
		
	}

	public List<Flow> getFlowsByRiver(River river) {
		
		return dao.getFlowsByRiver(river, riverIdMap);
	}
	
	public double mediaCapienza() {
		return simulatore.run();
	}
	
	public int mancati() {
		return simulatore.getOUTmiss();
	}
}
