package br.edu.senai.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class SaidaDeDados {

	public static void saidaDedados(Set<Funcionario> funcionarios, String arquivoSaida) throws IOException {
		try {
			FileWriter save = new FileWriter(arquivoSaida);
			PrintWriter escreveSave = new PrintWriter(save);

			for (Funcionario funcionario : funcionarios) {
				String arquivoSave = funcionario.getNome() + ";" + funcionario.getCpf() + ";"
						+ String.format("%.2f", funcionario.getDescontoINSS()) + ";"
						+ String.format("%.2f", funcionario.getDescontoIR()) + ";"
						+ String.format("%.2f", funcionario.getSalarioLiquido());

				escreveSave.printf(arquivoSave + "\n");

			}
			escreveSave.close();
			System.out.println("Gravado!");

		} catch (IOException e) {
			System.out.println("Não foi possivel registrar os dados.");

		}
	}
}