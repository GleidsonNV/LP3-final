package clinica;

public class Fatura {
	private double preco;
	private String status;
	
	public Fatura (double preco) {
		this.preco = preco;
		this.status = "Pendente";
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
