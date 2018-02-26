package br.loto.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OrganizadorUtil {
	public static void main(String[] args) {
		ler(path);
	}
	
	private static String path = "C:\\Users\\Administrador\\Downloads\\LOTOFÁCIL.txt";

	public static void ler(String path) {

		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			ArrayList<String> dezenas = new ArrayList<String>();
			
			String frutas[];
			
			while (br.ready()) {
				String linha = br.readLine();
				frutas = linha.split(",");
				
				System.out.println(linha);
				for (String string : frutas) {
					System.out.println(string);
				}
			}
			
			
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
