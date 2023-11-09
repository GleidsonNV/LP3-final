package clinica;

import java.util.List;

public class Medico {
	private int id;
	private String nome;
	private int crm;
	private Consultorio consultorio;
	private String especialidade;
	
	public Medico (String nome, String especialidade, Consultorio consultorio) {
		Id gerador = Id.getInstance();
        this.id = gerador.gerarId("Medico");
		this.nome = nome;
		this.especialidade = especialidade;
		this.consultorio = consultorio;
	}
	
	
	
	public int getId() {
		return id;
	}


	public String getEspecialidade() {
		return especialidade;
	}



	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome, List<String> especialidades) {
		this.nome = nome;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public Consultorio getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}
}
