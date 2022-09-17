package Main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import br.edu.senai.model.EntradaDados;
import br.edu.senai.model.Funcionario;
import br.edu.senai.model.SaidaDeDados;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(
				"Bem vindo! Lembre-se que seu arquivo.csv deve estar formatado de acordo com o modelo abaixo.");
		System.out.println("Nome,CPF,Data de Nascimento,Salario - para funcionario.");
		System.out.println("Nome,CPF,Data de Nascimento,Parentesco - para dependente.");

		System.out.println("Informe o diretório do arquivo CSV:");
		String nomeArquivo = in.next();

		System.out.println("Informe o nome do arquivo para gravação:");
		String arquivoSaida = in.next();

		in.close();

		Set<Funcionario> funcionarios = new HashSet<Funcionario>();
		try {
			funcionarios = EntradaDados.EntradaDados(nomeArquivo);
			SaidaDeDados.saidaDedados(funcionarios, arquivoSaida);

		} catch (Exception e) {
			System.out.println("Erro para fazer a leitura e/ou Gravação de dados");

		}

	}
}
