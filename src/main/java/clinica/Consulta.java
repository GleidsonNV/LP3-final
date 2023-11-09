package clinica;

public class Consulta {
	
	private int id;
	private Fatura fatura;
	private Paciente paciente;
	private Consultorio consultorio;
	private Medico medico;
	private String especialidade;
	private String dia;
	private String hora;
	
	public Consulta(Fatura fatura, Paciente paciente, Consultorio consultorio, Medico medico, String especialidade, String dia, String hora) {
		Id gerador = Id.getInstance();
        this.id = gerador.gerarId("Consulta");
        this.fatura = fatura;
        this.paciente = paciente;
        this.consultorio = consultorio;
        this.medico = medico;
        this.especialidade = especialidade;
        this.dia = dia;
        this.hora = hora;
	}
	
	public void CancelarConsulta() {
		
	}
	
	
	
	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fatura getFatura() {
		return fatura;
	}
	
	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Consultorio getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}
