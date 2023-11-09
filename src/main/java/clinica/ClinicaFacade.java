package clinica;

import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ClinicaFacade {

	private BancoDeDados bd;

	private Paciente paciente;

	private Agenda agenda;

	public ClinicaFacade() {
		this.agenda = Agenda.getInstance();
		this.bd = BancoDeDados.getInstance();

	}

	public void RegistrarPaciente(String nome) {
		// this.paciente = new Paciente(nome);
		this.bd.insert(paciente);
	}

	public void CancelarConsulta(int id) {
		((Consulta) this.bd.select(Consulta.class, id)).CancelarConsulta();
	}

	public boolean VerificaSeClinicaAtendeEspecialidade(String especialidade) {
		Map<String, Double> especialidades = ((TabelaEspecialidades) bd.selectIndex(TabelaEspecialidades.class, 0))
				.getEspecialidades();
		for (String chave : especialidades.keySet()) {
			if (especialidade.equals(chave)) {
				return true;
			}
		}
		return false;
	}
	
	public double CalcularValorFatura(Plano plano, String especialidade, boolean consultaPorPlano) {
		if(consultaPorPlano) {
			TabelaEspecialidades tabela = plano.getTabela_especialidades();
			 double valorCoberto = tabela.getEspecialidades().get(especialidade) - (plano.getCobertura() / 100.0) * tabela.getEspecialidades().get(especialidade);
		     return valorCoberto;
		}else {
			TabelaEspecialidades tabela = (TabelaEspecialidades) bd.selectIndex(TabelaEspecialidades.class, 0);
			return tabela.getEspecialidades().get(especialidade);
		}
    }

	public boolean VerificaSePlanoAtendeEspecialidade(Plano plano, String especialidade) {
		Map<String, Double> especialidades = plano.getTabela_especialidades().getEspecialidades();
		for (String chave : especialidades.keySet()) {
			if (especialidade.equals(chave)) {
				return true;
			}
		}
		return false;
	}

	public Medico VerificaMedicoDaEspecialidade(String especialidade) {
		List<?> medicos = bd.all(Medico.class);
		for (Object obj : medicos) {
			Medico medico = (Medico) obj;
			if (medico.getEspecialidade().equals(especialidade)) {
				return medico;
			}
		}
		return null;
	}

}
