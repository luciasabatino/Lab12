package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Result;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.RiverIdMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers(RiverIdMap riverIdMap) {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				River r = new River(res.getInt("id"), res.getString("name"));
				rivers.add(riverIdMap.get(r));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
public Result getResult(River river) {
		
		final String sql = "SELECT MIN(day) as dataMin, MAX(day) as dataMax, COUNT(*) as misure, AVG(flow) as avg from flow WHERE river=?";

		Result result=null;
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			if (res.next()) {
				result = new Result(res.getDate("dataMin").toLocalDate(), res.getDate("dataMax").toLocalDate(),
						res.getInt("misure"), res.getDouble("avg"));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}

public List<Flow> getFlowsByRiver(River river, RiverIdMap riverIdMap) {
	
	final String sql = "SELECT * from flow WHERE river=? ORDER BY day";

	List<Flow> list = new ArrayList<>();
	
	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, river.getId());
		ResultSet res = st.executeQuery();

		while (res.next()) {
			Flow flow = new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"),riverIdMap.get(res.getInt("river")));
			list.add(flow);
		}

		conn.close();
		
	} catch (SQLException e) {
		//e.printStackTrace();
		throw new RuntimeException("SQL Error");
	}

	return list;
}
}
