package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Result {
	
	private LocalDate dataPrima;
	private LocalDate dataUltima;
	private int numeroMisurazioni;
	private double mediaFlusso;
	public Result(LocalDate dataPrima, LocalDate dataUltima, int numeroMisurazioni, double mediaFlusso) {
		super();
		this.dataPrima = dataPrima;
		this.dataUltima = dataUltima;
		this.numeroMisurazioni = numeroMisurazioni;
		this.mediaFlusso = mediaFlusso;
	}
	public LocalDate getDataPrima() {
		return dataPrima;
	}
	public void setDataPrima(LocalDate dataPrima) {
		this.dataPrima = dataPrima;
	}
	public LocalDate getDataUltima() {
		return dataUltima;
	}
	public void setDataUltima(LocalDate dataUltima) {
		this.dataUltima = dataUltima;
	}
	public int getNumeroMisurazioni() {
		return numeroMisurazioni;
	}
	public void setNumeroMisurazioni(int numeroMisurazioni) {
		this.numeroMisurazioni = numeroMisurazioni;
	}
	public double getMediaFlusso() {
		return mediaFlusso;
	}
	public void setMediaFlusso(double mediaFlusso) {
		this.mediaFlusso = mediaFlusso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPrima == null) ? 0 : dataPrima.hashCode());
		result = prime * result + ((dataUltima == null) ? 0 : dataUltima.hashCode());
		long temp;
		temp = Double.doubleToLongBits(mediaFlusso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numeroMisurazioni;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (dataPrima == null) {
			if (other.dataPrima != null)
				return false;
		} else if (!dataPrima.equals(other.dataPrima))
			return false;
		if (dataUltima == null) {
			if (other.dataUltima != null)
				return false;
		} else if (!dataUltima.equals(other.dataUltima))
			return false;
		if (Double.doubleToLongBits(mediaFlusso) != Double.doubleToLongBits(other.mediaFlusso))
			return false;
		if (numeroMisurazioni != other.numeroMisurazioni)
			return false;
		return true;
	}
	
	

}
