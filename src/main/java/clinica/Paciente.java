package clinica;

import clinica.strategies.PagamentoStrategy;

public class Paciente {
	
	private int id;
	private String nome;
	private String cpf;
	private String rg;
	private String data_de_nascimento;
	private String sexo;
	private Plano plano;
	private PagamentoStrategy pagamento;
	private Responsavel responsavel;
	
	public Paciente (String nome, String data_de_nascimento, String cpf, String rg, String sexo, Plano plano) {
		Id gerador = Id.getInstance();
        this.id = gerador.gerarId("Paciente");
		this.nome = nome;
		this.data_de_nascimento = data_de_nascimento;
		this.cpf = cpf;
		this.rg = rg;
		this.sexo = sexo;
		this.plano = plano;
	}
	
	
	public Plano getPlano() {
		return plano;
	}


	public void setPlano(Plano plano) {
		this.plano = plano;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Responsavel getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}


	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getData_de_nascimento() {
		return data_de_nascimento;
	}

	public void setData_de_nascimento(String data_de_nascimento) {
		this.data_de_nascimento = data_de_nascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public PagamentoStrategy getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoStrategy pagamento) {
		this.pagamento = pagamento;
	}
}
