package it.polito.tdp.rivers.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;

public class Simulatore {
	
	private PriorityQueue<Event> queue;
	private Model model;
	private int k;
	private River river;
	private int OUTmiss=0;
	private double C;
	private double Q;
	private double f_medio;
	private Map<LocalDate, Double> capienze;
	private double capienza_ieri;
	private double capienza_oggi;
	
	public Simulatore (int k, River river, Model model) {
		this.river=river;
		this.k=k;
		this.model=model;
		queue = new PriorityQueue<Event>();
		capienze = new HashMap<>();
		capienza_ieri=0;
		capienza_oggi=0;
	}
	
	//coda ordinata per priorità : susseguirsi di eventi che devono essere organizzati
	
	public double run() {
		List<Flow> flows = model.getFlowsByRiver(river);
		
		//Aggiungere gli eventi all pq
		for(Flow f : flows) {
			queue.add(new Event(f));
		}
		
		this.f_medio = model.popola(river).getMediaFlusso()*(24*60*60);
		this.Q = this.k*30*f_medio;
		this.C=Q/2;
		System.out.println("***************************");
		while(!queue.isEmpty()) {
			Event e = queue.poll();
			
			Flow flow = e.getFlow();
			System.out.println("DATA :"+flow.getDay());
			double f_in = flow.getFlow()*(24*60*60);
			System.out.println("FLUSSO IN :"+f_in);
			capienza_ieri = C;
			capienza_oggi = capienza_ieri + f_in;
			System.out.println("Capienza oggi : "+capienza_oggi);
			double casuale = Math.random();
			double f_out;
			if(casuale<0.05) {
				f_out = 10*0.8*f_medio;
			}
			else {
				f_out = 0.8*f_medio;
			}
			System.out.println("FLUSSO OUT : "+f_out);
			if(f_out>capienza_oggi) {
				OUTmiss++;
				capienze.put(flow.getDay(), 0.0);
				C=0;
			}
			else {
				capienza_oggi -= f_out;
				if(capienza_oggi>Q) {
					capienze.put(flow.getDay(), Q);
					C=Q;
				}
				else {
				capienze.put(flow.getDay(), capienza_oggi);
				C=capienza_oggi;
				}
			}
			NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
			nf.setMinimumIntegerDigits(0);
			nf.setMaximumIntegerDigits(20);
			System.out.println("Capienza alla fine del giorno :"+nf.format(C));
			
		}
		double media=0;
		for(LocalDate data : capienze.keySet()) {
			media+=capienze.get(data);
		}
		media/=capienze.size();
		return media;
			}

	public int getOUTmiss() {
		return OUTmiss;
	}
	
	
}

