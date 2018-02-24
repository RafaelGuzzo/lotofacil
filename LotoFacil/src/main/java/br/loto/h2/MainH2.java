package br.loto.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainH2 {
	public static void main(String[] a) throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:file:./src/main/resources/LotoFacil", "sa", "");

		Statement st = conn.createStatement();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select * from concurso");
		while (rset.next()) {
			String name = rset.getString(1);
			System.out.println(name);
			System.out.println();
		}

		conn.close();
	}
}
