package it.polito.tdp.rivers.model;

import java.util.*;

public class RiverIdMap {

	
	private Map<Integer, River> map;
	
	public RiverIdMap() {
		map = new HashMap<>();
	}
	
	public River get(int id) {
		return map.get(id);
	}
	
	public River get(River a) {
		River old = map.get(a.getId());
		if(old == null) {
			map.put(a.getId(), a);
			return a;
		}
		return old;
	}
	
	public void put(River airport, int id ) {
		map.put(id, airport);
	}

	
}
