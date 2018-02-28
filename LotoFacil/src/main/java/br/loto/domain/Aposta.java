package br.loto.domain;

import java.util.ArrayList;

public class Aposta {
	private ArrayList<Integer> dezenas;
	private ArrayList<Integer> sequencia;
	private int qtdPar;
	private int qtdInpar;
	private int qtdPrimos;
	private int qtdNumSequencia;

	public ArrayList<Integer> getDezenas() {
		return dezenas;
	}

	public void setDezenas(ArrayList<Integer> dezenas) {
		this.dezenas = dezenas;
	}

	public ArrayList<Integer> getSequencia() {
		return sequencia;
	}

	public void setSequencia(ArrayList<Integer> sequencia) {
		this.sequencia = sequencia;
	}

	public int getQtdPar() {
		return qtdPar;
	}

	public void setQtdPar(int qtdPar) {
		this.qtdPar = qtdPar;
	}

	public int getQtdInpar() {
		return qtdInpar;
	}

	public void setQtdInpar(int qtdInpar) {
		this.qtdInpar = qtdInpar;
	}

	public int getQtdPrimos() {
		return qtdPrimos;
	}

	public void setQtdPrimos(int qtdPrimos) {
		this.qtdPrimos = qtdPrimos;
	}

	public int getQtdNumSequencia() {
		return qtdNumSequencia;
	}

	public void setQtdNumSequencia(int qtdNumSequencia) {
		this.qtdNumSequencia = qtdNumSequencia;
	}

}
