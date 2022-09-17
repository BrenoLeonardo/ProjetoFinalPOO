package br.edu.senai.Enums;
public enum EnumIR {
	
	FAIXAA(1903.98, 0.0, 0.0), FAIXAB(2826.65, 0.075, 142.80), FAIXAC(3751.05, 0.15, 354.80),
	FAIXAD(4664.68, 0.225, 636.13), FAIXAE(4664.68, 0.275, 869.36);
	
	private Double salario, aliquota, parcelaDeduzir;

	private EnumIR(Double salario, Double aliquota, Double parcelaDeduzir) {
		this.salario = salario;
		this.aliquota = aliquota;
		this.parcelaDeduzir = parcelaDeduzir;
	}

	public Double getSalario() {
		return salario;
	}

	public Double getAliquota() {
		return aliquota;
	}

	public Double getParcelaDeduzir() {
		return parcelaDeduzir;
	}
	
}