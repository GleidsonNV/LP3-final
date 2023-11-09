package clinica;

public class Consultorio {
	
	private int id;
	private int numero;
	
	public Consultorio (int numero) {
		Id gerador = Id.getInstance();
        this.id = gerador.gerarId("Consultorio");
		this.numero = numero;
	}

	public int getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
