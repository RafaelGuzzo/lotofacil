package br.loto.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.loto.domain.Concurso;

public class OrganizadorUtil2 {
	private static String path = "C:\\Users\\Administrador\\Downloads\\LOTOFÁCIL.txt";

	public static void main(String[] args) {
		ler(path);
	}

	public static void ler(String path) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String frutas[];

			while (br.ready()) {

				// Concurso concurso = new Concurso();
				ArrayList<Integer> dezenas = new ArrayList<Integer>();

				// concurso.setId(null);
				// concurso.setNumConcurso(null);

				String linha = br.readLine();
				frutas = linha.split(",");

				// System.out.println(linha);
				for (String string : frutas) {
					dezenas.add(Integer.parseInt(string));
					//System.out.println(Integer.parseInt(string));
				}

				Collections.sort(dezenas);

				System.out.println(dezenas.get(5));
				System.out.println("--------------------------");


			}


		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
