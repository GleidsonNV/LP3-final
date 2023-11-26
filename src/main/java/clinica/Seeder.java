package clinica;

import clinica.strategies.impl.PagamentoComCobertura;

import java.util.Arrays;

public class Seeder {
	
	public Seeder() {
		
	}
	
	public static void seed() {
		BancoDeDados db = BancoDeDados.getInstance();
		
		// Cria as tabelas
		db.createTable(Medico.class); 
		db.createTable(Consultorio.class);
		db.createTable(Consulta.class);
		db.createTable(Paciente.class); // Tem -> Resonsável, Fatura
		db.createTable(Plano.class); // Tem -> TabelaProcedimentos
		db.createTable(TabelaEspecialidades.class);
		
		
		//Preenche as tabelas
		
		TabelaEspecialidades te = new TabelaEspecialidades();
		te.insertEspecialidade("Cardiologia", 450.00);
		te.insertEspecialidade("Ortopedia", 199.50);
		te.insertEspecialidade("Oftalmologia", 270.90);
		db.insert(te);
		
		//Consultorio consultorio1 = New Consultorio(13);
		
		Consultorio consultorio1 = new Consultorio(13);
		Consultorio consultorio2 = new Consultorio(14);
		Consultorio consultorio3 = new Consultorio(15);
		
		db.insert(new Medico( "Dr. Arlindo", "Cardiologia", consultorio1));
		db.insert(new Medico( "Dr. Bonifácio", "Ortopedia", consultorio2));
		db.insert(new Medico( "Dr. Carlos", "Oftalmologia", consultorio3));
		
		db.insert(consultorio1);
		db.insert(consultorio2);
		db.insert(consultorio3);

		
		Plano bradesco = new Plano("Bradesco", 50);
		TabelaEspecialidades teb = new TabelaEspecialidades();
		teb.insertEspecialidade("Cardiologia", 250.00);
		teb.insertEspecialidade("Ortopedia", 99.50);
		teb.insertEspecialidade("Oftalmologia", 70.90);
		teb.insertEspecialidade("Dermatologia", 160.90);
		bradesco.setTabela_especialidades(teb);
		db.insert(bradesco);
		
		Plano unimed = new Plano("Unimed", 100);
		TabelaEspecialidades teu = new TabelaEspecialidades();
		teu.insertEspecialidade("Cardiologia", 450.00);
		teu.insertEspecialidade("Ortopedia", 199.50);
		teu.insertEspecialidade("Oftalmologia", 270.90);
		teu.insertEspecialidade("Dermatologia", 360.90);
		unimed.setTabela_especialidades(teu);
		db.insert(unimed);
		
		Plano amil = new Plano("Amil", 90);
		TabelaEspecialidades tea = new TabelaEspecialidades();
		tea.insertEspecialidade("Cardiologia", 100.00);
		//tea.insertEspecialidade("Ortopedia", 299.50);
		tea.insertEspecialidade("Oftalmologia", 370.90);
		tea.insertEspecialidade("Dermatologia", 460.90);
		amil.setTabela_especialidades(tea);
		db.insert(amil);
		
		Plano notredame = new Plano("Notredame", 66);
		TabelaEspecialidades ten = new TabelaEspecialidades();
		ten.insertEspecialidade("Cardiologia", 450.00);
		ten.insertEspecialidade("Ortopedia", 199.50);
		ten.insertEspecialidade("Oftalmologia", 270.90);
		ten.insertEspecialidade("Dermatologia", 360.90);
		notredame.setTabela_especialidades(ten);
		
		Paciente paciente = new Paciente("Amaral", "20/05/2000", "98947563524", "2341116352", "M", bradesco);
		db.insert(paciente);
		Paciente paciente2 = new Paciente("Bianca", "02/02/1998", "98947563524", "2341116352", "F", unimed);
		db.insert(paciente2);
		Paciente paciente3 = new Paciente("Carlos", "03/07/1987", "98947563524", "2341116352", "M", amil);
		db.insert(paciente3);
		Paciente paciente4 = new Paciente("Daciolo", "11/09/1994", "98947563524", "2341116352", "M", notredame);
		db.insert(paciente4);
		Paciente paciente5 = new Paciente("Emily", "12/12/1989", "98947563524", "2341116352", "F", null);
		db.insert(paciente5);
		
		//Paciente p = (Paciente) db.select(Paciente.class, 4);
		
		//System.out.println(p.getNome());
		// db.insert(new Plano("Bradesco"));
		
		// db.insert(new Plano("Unimed"));
		
		//db.insert(new Plano("Amil"));
		
		//db.insert(new Plano("Notredame"));
		
	}

}
