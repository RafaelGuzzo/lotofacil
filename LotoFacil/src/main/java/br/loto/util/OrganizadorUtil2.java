package br.loto.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Element;

import br.loto.domain.Aposta;
import br.loto.domain.Concurso;

public class OrganizadorUtil2 {
	private static String path = "C:\\Users\\Administrador\\Downloads\\LOTOFÁCIL.txt";

	public static void main(String[] args) {
		ler(path);
	}

	public static void ler(String path) {
		LotoCrudUtil util = new LotoCrudUtil();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String frutas[];

			while (br.ready()) {
				int qtdPar = 0;
				int qtdImpar = 0;
				int qtdPrimo = 0;

				Aposta aposta = new Aposta();

				ArrayList<Integer> dezenas = new ArrayList<Integer>();
				ArrayList<String> temp = new ArrayList<String>();

				// concurso.setId(null);
				// concurso.setNumConcurso(null);

				String linha = br.readLine();
				frutas = linha.split(",");

				// System.out.println(linha);
				for (String string : frutas) {
					int num = Integer.parseInt(string);
					dezenas.add(num);
					temp.add(string);
					if (parImpar(num)) {
						qtdPar++;
					} else {
						qtdImpar++;
					}
				}

				Collections.sort(temp);
				Collections.sort(dezenas);
				localizarNoBanco(dezenas, util);

				System.out.println(temp);
				System.out.println(dezenas + "pares " + qtdPar + "impares " + qtdImpar);
				System.out.println("--------------------------");

			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public static boolean parImpar(int num) {
		if (num % 2 == 0) {
			return true;
		} else {
			return false;
		}

	}

	public static void localizarNoBanco(ArrayList<Integer> dezenas, LotoCrudUtil util) {

		String temp = "";
		int contador = 0;

		for (Integer dez : dezenas) {
			contador++;

			if (dez <= 9 && contador < 15) {
				temp = temp + ("0" + dez + ",");
			} else if (contador < 15) {
				temp = temp + (dez + ",");
			} else {
				temp = temp + dez;
			}

		}

		List<Concurso> lista = util.existeConcurso(temp);

		if (lista.isEmpty()) {
			System.out.println("Nao localizado no banco");
		} else {
			System.out.println(lista);

		}

	}

}
