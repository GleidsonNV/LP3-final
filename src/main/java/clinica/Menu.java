package clinica;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Menu {
	
	private static Agenda agenda = Agenda.getInstance();
	private static ClinicaFacade cf = new ClinicaFacade();
	private static BancoDeDados bd = BancoDeDados.getInstance();

	public static void main(String[] args) {
		
		Seeder sd = new Seeder();
		sd.seed();
		ClinicaFacade cf = new ClinicaFacade();
		Scanner scanner = new Scanner(System.in);
		int opcao = 0;

		do {
			System.out.println("-------MENU-------");
			System.out.println("\n1. Cadastrar Paciente");
			System.out.println("2. Atender Paciente");
			System.out.println("0. Sair");
			System.out.print("\nEscolha uma opção: ");

			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				cadastrarPaciente(scanner);
				break;
			case 2:
				atenderPaciente(scanner);
				break;
			case 0:
				System.out.println("Saindo do menu...");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
			System.out.println();
		} while (opcao != 0);

		scanner.close();

	}

	public static void cadastrarPaciente(Scanner scanner) {
		System.out.println("=== Cadastrar de Paciente ===");
		System.out.print("\nInforme os dados abaixo:");
		System.out.print("\nNome: ");
		String nome = scanner.next();
		System.out.print("Digite a data de nascimento (dd/mm/aaaa):");
		String dataNascimento = scanner.next();
		int idade = calcularIdade(dataNascimento);
		System.out.print("CPF: ");
		String cpf = scanner.next();
		System.out.print("RG: ");
		String rg = scanner.next();
		System.out.print("Sexo: M/F");
		String sexo = scanner.next();
		System.out.print("O paciente possui um plano? digite o nome, se não possuir digite 0");
		String plano = scanner.next();
		Plano pacientePlano = (Plano) bd.select(Plano.class, plano);
		Paciente paciente = new Paciente(nome, dataNascimento, cpf, rg, sexo, pacientePlano);

		if (idade < 18) {
			System.out.println("=== Cadastro do Responsável ===");
			System.out.print("\nPaciente é menor de 18 anos, informe os dados do responsavel legal do mesmo: ");
			System.out.print("\nNome: ");
			String nomeResponsavel = scanner.next();
			System.out.print("RG: ");
			String rgResponsavel = scanner.next();
			Responsavel responsavel = new Responsavel(nomeResponsavel, rgResponsavel);
			paciente.setResponsavel(responsavel);
		}

		bd.insert(paciente);
	}

	public static void atenderPaciente(Scanner scanner) {
		System.out.println("\n=== Atender Paciente ===");
		System.out.println("Digite o id do paciente");
		int id = scanner.nextInt();
		System.out.print("\nEscolha uma opção para o atendimento do(a) paciente " + ((Paciente) bd.select(Paciente.class, id)).getNome() + ": ");
		Paciente paciente = (Paciente) bd.select(Paciente.class, id);

		int opcao = 0;

		do {
			System.out.println("\n1. Marcar consulta");
			System.out.println("2. Listar consultas");
			System.out.println("3. Adiar Consulta");
			System.out.println("4. Cancelar Consulta");
			System.out.println("0. Sair");
			//System.out.print("\nEscolha uma opção para o atendimento do(a) paciente " + paciente.getNome() + ": ");

			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				marcarConsulta(scanner, paciente);
				break;
			case 2:
				listarConsultas(scanner, paciente);
				break;
			case 3:
				adiarConsulta(scanner, paciente);
				break;
			case 4:
				cancelarConsulta(scanner, paciente);
				break;
			case 0:
				System.out.println("Saindo do menu...");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
			System.out.println();
		} while (opcao != 0);

	}

	public static void marcarConsulta(Scanner scanner, Paciente paciente) {
		Boolean consultaPorPlano = true;
		System.out.print("\nInforme a especialidade da consulta:");
		String especialidade = scanner.next();
		if(cf.VerificaSeClinicaAtendeEspecialidade(especialidade)) {
			if(paciente.getPlano().equals(null)) {
				System.out.print("Paciente não possui um plano parceiro da clinica");
				System.out.print("Continuar com a marcação da consulta particular? S/N");
				String keep = scanner.next();
				if(keep.equals("S")) {
					consultaPorPlano = false;
				}else {
					return;
				}
			}else {
				if(!(cf.VerificaSePlanoAtendeEspecialidade(paciente.getPlano(), especialidade))) {
					System.out.print("O plano do paciente não cobre essa especialidade");
					System.out.print("Continuar com a marcação da consulta particular? S/N");
					String keep = scanner.next();
					if(keep.equals("S")) {
						consultaPorPlano = false;
					}else {
						return;
					}
				}
			}
			Medico medico = cf.VerificaMedicoDaEspecialidade(especialidade);
			System.out.print("O(a) médico(a) que faz a consulta para essa especialidade é:"+medico.getNome()+"\n" );
			
			boolean desistencia = false;
			boolean dataValida = false;
			do {
			System.out.print("\nDigite a data da consulta\n");
			String data = scanner.next();
			System.out.print("Digite a hora da consulta");
			String hora = scanner.next();
			dataValida = agenda.verificiarDisponibilidade(data, hora, medico);
			if(dataValida) {
				double valor = cf.CalcularValorFatura(paciente.getPlano(), especialidade, consultaPorPlano);
				System.out.print("Voce ira pagar "+valor+" na consulta, continuar? S/N");
				String keep = scanner.next();
				if(keep.equals("N")) {
					return;
				}
				
				Fatura fatura = new Fatura(valor);
				Consulta consulta = new Consulta(fatura, paciente, medico.getConsultorio(), medico, especialidade, data, hora);
				System.out.println("\nInformações da consulta:\n");
				System.out.println("\nID: "+consulta.getId()+"\n");
				System.out.println("\nData: "+data+"\n");
				System.out.println("\nHora: "+hora+"\n");
				System.out.println("\nMedico: "+medico.getNome()+"\n");
				System.out.println("\nConsultorio: "+medico.getConsultorio().getNumero()+"\n");
				System.out.println("\nPaciente: "+paciente.getNome()+":\n");
				System.out.println("\nEspecialidade: "+especialidade+":\n");
				System.out.println("\nValor: "+valor+":\n");
				
				System.out.print("Confirmar consulta? S/N");
				keep = scanner.next();
				if(keep.equals("N")) {
					return;
				}
				agenda.marcarConsulta(data, consulta);
				bd.insert(consulta);
				System.out.print("Consulta marcada\n\n");
			}else {
				System.out.print("Essa data e horario não estão disponiveis, tentar outra? S/N");
				String keep = scanner.next();
				if(keep.equals("N")) {
					desistencia = true;
				}
			}
			}while(!dataValida && !desistencia);
		} else {
			System.out.print("Infelizmente a nossa clinica não cobre essa especialidade:");
			return;
		}
	}

	public static void listarConsultas(Scanner scanner, Paciente paciente) {
		List consultas = bd.all(Consulta.class);
		for (Object obj : consultas) {
			Consulta c = (Consulta) obj;
			if(c.getPaciente().getId() == paciente.getId()) {
	        	System.out.println("\n\nInformações da consulta:\n");
				System.out.println("ID: "+c.getId()+"\n");
				System.out.println("Data: "+c.getDia()+"\n");
				System.out.println("Hora: "+c.getHora()+"\n");
				System.out.println("Medico: "+c.getMedico().getNome()+"\n");
				System.out.println("Consultorio: "+c.getMedico().getConsultorio().getNumero()+"\n");
				System.out.println("Paciente: "+c.getPaciente().getNome()+":\n");
				System.out.println("Especialidade: "+c.getEspecialidade()+":\n");
				System.out.println("Valor: "+c.getFatura().getPreco()+":\n");
	        }
		}
	}

	public static void adiarConsulta(Scanner scanner, Paciente paciente) {
		System.out.println("\nDigite o id da consulta:\n");
		int id = scanner.nextInt();
		Consulta consulta = (Consulta) bd.select(Consulta.class, id);
		boolean desistencia = false;
		boolean dataValida = false;
		do {
		System.out.print("\nDigite a nova data da consulta\n");
		String data = scanner.next();
		System.out.print("Digite a nova hora da consulta");
		String hora = scanner.next();
		dataValida = agenda.verificiarDisponibilidade(data, hora, consulta.getMedico());
		if(dataValida) {			
			System.out.println("\nInformações da consulta atualizada:\n");
			System.out.println("\nID: "+consulta.getId()+"\n");
			System.out.println("\nData: "+data+"\n");
			System.out.println("\nHora: "+hora+"\n");
			System.out.println("\nMedico: "+consulta.getMedico().getNome()+"\n");
			System.out.println("\nConsultorio: "+consulta.getConsultorio().getNumero()+"\n");
			System.out.println("\nPaciente: "+consulta.getPaciente().getNome()+":\n");
			System.out.println("\nEspecialidade: "+consulta.getEspecialidade()+":\n");
			System.out.println("\nValor: "+consulta.getFatura().getPreco()+":\n");
			
			System.out.print("Confirmar mudanca? S/N");
			String keep = scanner.next();
			if(keep.equals("N")) {
				return;
			}
			consulta.setDia(data);
			consulta.setHora(hora);
			agenda.alterarConsulta(consulta.getDia(), consulta.getHora(), data, hora, consulta, consulta.getMedico());
			
			System.out.print("Mudanca confirmada\n\n");
		}else {
			System.out.print("Essa data e horario não estão disponiveis, tentar outra? S/N");
			String keep = scanner.next();
			if(keep.equals("N")) {
				desistencia = true;
			}
		}
		}while(!dataValida && !desistencia);
	}

	public static void cancelarConsulta(Scanner scanner, Paciente paciente) {
		System.out.println("\nDigite o id da consulta:\n");
		int id = scanner.nextInt();
		Consulta consulta = (Consulta) bd.select(Consulta.class, id);
		bd.remove(Consulta.class, consulta.getId());
		agenda.cancelaConsulta(consulta.getDia(), consulta);
		System.out.println("\nConsulta cancelada:\n");
	}

	public static int calcularIdade(String dataNascimentoStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
		LocalDate dataAtual = LocalDate.now();
		Period periodo = Period.between(dataNascimento, dataAtual);
		return periodo.getYears();
	}
}
