package br.edu.senai.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import br.edu.senai.Enums.EnumIR;
import br.edu.senai.Enums.EnumInss;
import br.org.serratec.Interface.ImplementaCalculos;

public class Funcionario extends Pessoa implements ImplementaCalculos {
	private Double salarioBruto;
	private Double descontoINSS;
	private Double descontoIR;
	private Double salarioLiquido;
	private Double descontoDependente = 0.;
	private Set<Dependente> dependentes;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto,
			Set<Dependente> dependentes) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		this.dependentes = dependentes;
		descontoDependente();
		calcularINSS();
		calcularIDR();
		calcularSalarioLiquido();
	}

	@Override
	public String toString() {
		return super.toString() + " salarioBruto:" + salarioBruto + ", dependentes: " + dependentes;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public Double getDescontoINSS() {
		return descontoINSS;
	}

	public Double getDescontoIR() {
		return descontoIR;
	}

	public Double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void calcularINSS() {

		if (salarioBruto <= EnumInss.FAIXAA.getSalario()) {
			descontoINSS = salarioBruto * EnumInss.FAIXAA.getAliquota();
		} else if (salarioBruto > EnumInss.FAIXAA.getSalario() && salarioBruto <= EnumInss.FAIXAB.getSalario()) {
			descontoINSS = (salarioBruto * EnumInss.FAIXAB.getAliquota()) - EnumInss.FAIXAB.getParcelaDeduzir();
		} else if (salarioBruto > EnumInss.FAIXAB.getSalario() && salarioBruto <= EnumInss.FAIXAC.getSalario()) {
			descontoINSS = (salarioBruto * EnumInss.FAIXAC.getAliquota()) - EnumInss.FAIXAC.getParcelaDeduzir();
		} else if (salarioBruto > EnumInss.FAIXAC.getSalario() && salarioBruto <= EnumInss.FAIXAD.getSalario()) {
			descontoINSS = (salarioBruto * EnumInss.FAIXAD.getAliquota()) - EnumInss.FAIXAD.getParcelaDeduzir();
		} else {
			descontoINSS = EnumInss.FAIXAD.getSalario() * EnumInss.FAIXAD.getAliquota();

		}
	}

	@Override
	public void calcularSalarioLiquido() {
		salarioLiquido = salarioBruto - descontoINSS - descontoIR;
	}

	@Override
	public void calcularIDR() {
		Double baseCalculo = (salarioBruto - descontoDependente - descontoINSS);
		
		if (baseCalculo < EnumIR.FAIXAA.getSalario()) {
			descontoIR = 0.0;
		} else if (baseCalculo >= EnumIR.FAIXAA.getSalario() && baseCalculo <= EnumIR.FAIXAB.getSalario()) {
			descontoIR = (baseCalculo * EnumIR.FAIXAB.getAliquota()) - EnumIR.FAIXAB.getParcelaDeduzir();
		} else if (baseCalculo >= EnumIR.FAIXAB.getSalario() && baseCalculo <= EnumIR.FAIXAC.getSalario()) {
			descontoIR = (baseCalculo * EnumIR.FAIXAC.getAliquota()) - EnumIR.FAIXAC.getParcelaDeduzir();
		} else if (baseCalculo > EnumIR.FAIXAC.getSalario() && baseCalculo <= EnumIR.FAIXAD.getSalario()) {
			descontoIR = (baseCalculo * EnumIR.FAIXAD.getAliquota()) - EnumIR.FAIXAD.getParcelaDeduzir();
		} else {
			descontoIR = (baseCalculo * EnumIR.FAIXAE.getAliquota()) - EnumIR.FAIXAE.getParcelaDeduzir();
		}
	}

	@Override
	public void descontoDependente() {
		for (Dependente dependente : dependentes) {
			LocalDate dataAtual = LocalDate.now();

			Period period = Period.between(dependente.getDataNascimento(), dataAtual);
			if (period.getYears() < 18) {
				descontoDependente += 189.59;
			}

		}

	}

}

