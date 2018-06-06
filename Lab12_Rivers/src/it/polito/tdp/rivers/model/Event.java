package it.polito.tdp.rivers.model;


public class Event implements Comparable<Event>{

	private Flow flow;
	
	public Event(Flow flow) {
		super();
		this.flow = flow;
	}



	public Flow getFlow() {
		return flow;
	}



	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	@Override
	public int compareTo(Event other) {
		return this.flow.getDay().compareTo(other.flow.getDay());
	}

}
