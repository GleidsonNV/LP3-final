package clinica;

public class Responsavel {
	
	private int id;
	private String nome;
	private String RG;
	
	public Responsavel (String nome, String RG) {
		Id gerador = Id.getInstance();
        this.id = gerador.gerarId("Responsavel");
        this.nome = nome;
        this.RG = RG;
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

	public String getRG() {
		return RG;
	}

	public void setRG(String RG) {
		this.RG = RG;
	}


}
