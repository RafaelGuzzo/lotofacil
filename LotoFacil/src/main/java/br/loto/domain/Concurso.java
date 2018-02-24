package br.loto.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "concurso")
public class Concurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int numConcurso;

	private ArrayList<String> dezenas;

	public Concurso() {
		// TODO Auto-generated constructor stub
	}

	public Concurso(int id, int numConcurso, ArrayList<String> dezenas) {
		super();
		this.id = id;
		this.numConcurso = numConcurso;
		this.dezenas = dezenas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumConcurso() {
		return numConcurso;
	}

	public void setNumConcurso(int numConcurso) {
		this.numConcurso = numConcurso;
	}

	public ArrayList<String> getDezenas() {
		return dezenas;
	}

	public void setDezenas(ArrayList<String> dezenas) {
		this.dezenas = dezenas;
	}

	@Override
	public String toString() {
		return "Concurso [id=" + id + ", numConcurso=" + numConcurso + ", dezenas=" + dezenas + "]";
	}

}
