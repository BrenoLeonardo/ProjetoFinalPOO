package br.edu.senai.model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EntradaDados {

	public static Set<Funcionario> EntradaDados(String nomeArquivo) {

		Set<Funcionario> funcionarios = new HashSet<>();
		File arquivo = new File(nomeArquivo);

		try {
			Scanner sc = new Scanner(arquivo);

			while (sc.hasNext()) {
				Set<Dependente> dependentes = new HashSet<>();
				String linha = sc.nextLine();

				if (!linha.isEmpty()) {
					String vetor[] = linha.split(",");
					String nomeFuncionario = vetor[0];
					String cpfFuncionario = vetor[1];
					LocalDate nascimentoFuncionario = LocalDate.parse(vetor[2], DateTimeFormatter.BASIC_ISO_DATE);
					Double salarioFuncionario = Double.parseDouble(vetor[3]);
					linha = sc.nextLine();

					while (!linha.isEmpty()) {
						String Dvetor[] = linha.split(",");
						String dependenteNome = Dvetor[0];
						String dependenteCPF = Dvetor[1];
						LocalDate dependenteNascimento = LocalDate.parse(Dvetor[2], DateTimeFormatter.BASIC_ISO_DATE);
						String parentesco = Dvetor[3];
						dependentes.add(new Dependente(dependenteNome, dependenteCPF, dependenteNascimento, parentesco));
						
						if (sc.hasNextLine()) {
							linha = sc.nextLine();
							
						} else {
							break;
						}
					}

					funcionarios.add(new Funcionario(nomeFuncionario, cpfFuncionario, nascimentoFuncionario, salarioFuncionario, dependentes));
				}
			}
			sc.close();
			
		} catch (IOException e) {
			System.out.println("Houve um erro durante a leitura arquvio!");

		}
		
		return funcionarios;
	}
}

