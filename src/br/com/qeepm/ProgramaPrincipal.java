package br.com.qeepm;

import java.sql.SQLException;
import java.util.List;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.Scanner;

import br.com.qeepm.cidadeDAO.CidadeDAO;
import br.com.qeepm.pojo.Cidade;

public class ProgramaPrincipal {
	
	public static void cadastrarCidade(CidadeDAO cidadeDAO, Scanner teclado) {
		System.out.println("░░░░░░░░ Cadastrar Cidade ░░░░░░░░");
		teclado.nextLine();
		System.out.println("Digite o DDD: ");
		int ddd = teclado.nextInt();
		System.out.println("Digite o Cidade: ");
		String nomeCidade = teclado.nextLine();
		System.out.println("Digite o Nº de habitantes: ");
		int habitantes = teclado.nextInt();
		System.out.println("Digite o Renda per Capita: ");
		float perCapita = teclado.nextFloat();
		System.out.println("Digite se é Capital\nDigite true para capital e false para cidade que não é uma capital: ");
		boolean Capital = teclado.nextBoolean();		
		System.out.println("Digite o Estado (Sigla): ");
		String estado = teclado.next();
		System.out.println("Digite o Prefeito: ");
		String prefeito = teclado.nextLine();
		
		Cidade cidade = new Cidade(ddd, nomeCidade, habitantes, perCapita, Capital, estado, prefeito);
		try {
		cidadeDAO.insereCidade(cidade);
		System.out.println("Cidade inserida com sucesso!");
		}catch(SQLException e) {
			System.err.println("Erro ao inserir cidade!");
			System.err.println(e.getMessage());
		}
	}
	public static void listarCidades(CidadeDAO cidadeDAO) {
		System.out.println("░░░░░░░░ Listar Cidades ░░░░░░░░");
		List<Cidade> cidades = cidadeDAO.listaCidades();
		
		for(Cidade cidade: cidades) {
			System.out.println(cidade);
		}
	}
	
	public static void consultarCidadeDDD(CidadeDAO cidadeDAO, Scanner teclado) {
		System.out.println("░░░░░░░░ Consultar Cidades por DDD░░░░░░░░");
		System.out.println("Digite o DDD: ");
		int ddd = teclado.nextInt();
		System.out.println(cidadeDAO.consultaCidade(ddd));
	}
	
	public static void consultarCidadePorNome(CidadeDAO cidadeDAO, Scanner teclado) {
		teclado.nextLine();
		System.out.println("░░░░░░░░ Consultar Cidades por Nome░░░░░░░░");
		System.out.println("Consulte a Cidade: ");
		String nomeCidade = teclado.nextLine();
		List<Cidade> cidades = cidadeDAO.listaCidadesPorTexto(nomeCidade);
		
		for(Cidade cidade : cidades) {
			System.out.println(cidade);
		}
	}
	public static void consultarCidadePorEstado(CidadeDAO cidadeDAO, Scanner teclado) {
		teclado.nextLine();
		System.out.println("░░░░░░░░ Consultar Cidades por Nome░░░░░░░░");
		System.out.println("Digite o Estado(Sigla): ");
		String estadoSigla = teclado.next();
		 List<Cidade> estado = cidadeDAO.listaCidadesPorSigla(estadoSigla);
		 
		 for(Cidade cidade : estado) {
			 System.out.println(cidade);
		 }
	}
	public static void totalCidadesPorEstado(CidadeDAO cidadeDAO, Scanner teclado) {
		teclado.nextLine();
		System.out.println("░░░░░░░░ Total de Cidade por Estado░░░░░░░░");
		System.out.println("Digite o Estado(Sigla): ");
		String estadoSigla = teclado.next();
		int totalCidade = cidadeDAO.quantidadeCidadesPorEstado(estadoSigla);
		System.out.println("O total de cidades do Estado do " + estadoSigla +": "+ totalCidade);
	}
	public static void listarCapitais(CidadeDAO cidadeDAO) {
		System.out.println("░░░░░░░░ Capitais ░░░░░░░░");
		
		List<Cidade> capitais = cidadeDAO.listaFiltroCapital(true);
		for (Cidade cidade: capitais) {
			System.out.println(cidade);
		}
	}
	
	 public static void main (String[] args) /*throws SQLEception8*/ {
		 CidadeDAO cidadeDAO = new CidadeDAO();
		Scanner teclado = new Scanner(System.in);
		int opcao = 0;
		
		do {
			
			System.out.println("┌───────────────────────────────────────┐");
			System.out.println("│░░░░░░░░ Gerenciador de Cidade ░░░░░░░░│");
			System.out.println("│ 1 - Cadastrar Cidade                  │");
			System.out.println("│ 2 - Listar Cidades                    │");
			System.out.println("│ 3 - Consultar Cidade por DDD          │");
			System.out.println("│ 4 - Consultar Cidade por nome         │");
			System.out.println("│ 5 - Consultar Cidade por Estado       │");
			System.out.println("│ 6 - Total cidades por Estado          │");
			System.out.println("│ 7 - Listar capitais                   │");
			System.out.println("│ 0 - Sair                              │");
			System.out.println("└───────────────────────────────────────┘");
			System.out.println("Digite o número da opção desejada: ");
			opcao = teclado.nextInt();
			for (int i = 0; i < 50; ++i) { System.out.println ();}
			switch (opcao) {
			case 1:{
				int fechar = 0;
				do {
				cadastrarCidade(cidadeDAO, teclado);
				
				System.out.println("Deseja finalizar a Cadastro:\n Digite 0- Fechar ou 1- Continuar Cadastrando");
				fechar = teclado.nextInt();
				}while(fechar != 0);
				
				System.out.println("Cadastro Fechado!");
				
				break;
			}
			case 2:{
				int fechar = 0;
				do {
				listarCidades(cidadeDAO);
				
				System.out.println("Deseja finalizar a Consulta:\n Digite 0- Fechar ou 1- Continuar Consulta");
				fechar = teclado.nextInt();
				}while(fechar != 0);
				
				System.out.println("Consulta Fechada!");
				
				break;
			}
			case 3:{
				int fechar = 0;
				do {
				consultarCidadeDDD(cidadeDAO, teclado);
				
				System.out.println("Deseja finalizar a Consulta:\n Digite 0- Fechar ou 1- Continuar Consulta");
				fechar = teclado.nextInt();
				}while(fechar != 0);
				
				System.out.println("Consulta Fechada!");
				
				break;
			}
			case 4:{
				int fechar = 0;
				do {
				consultarCidadePorNome(cidadeDAO, teclado);
				
				System.out.println("Deseja finalizar a Consulta:\n Digite 0- Fechar ou 1- Continuar Consulta");
				fechar = teclado.nextInt();
				}while(fechar != 0);
				
				System.out.println("Consulta Fechada!");
				
				break;
			}
			case 5:{
				int fechar = 0;
				do {
					consultarCidadePorEstado(cidadeDAO, teclado);
				
				System.out.println("Deseja finalizar a Consulta:\n Digite 0- Fechar ou 1- Continuar Consulta");
				fechar = teclado.nextInt();
				}while(fechar != 0);
				
				System.out.println("Consulta Fechada!");
				
				break;
			}
			case 6:{
				int fechar = 0;
				do {
					totalCidadesPorEstado(cidadeDAO, teclado);
				
				System.out.println("Deseja finalizar a Consulta:\n Digite 0- Fechar ou 1- Continuar Consulta");
				fechar = teclado.nextInt();
				}while(fechar != 0);
				
				System.out.println("Consulta Fechada!");
				
				break;
			}
			case 7:{
				int fechar = 0;
				do {
					listarCapitais(cidadeDAO);
				
				System.out.println("Deseja finalizar a Consulta:\n Digite 0- Fechar ou 1- Continuar Consulta");
				fechar = teclado.nextInt();
				}while(fechar != 0);
				
				System.out.println("Consulta Fechada!");
				
				break;
			}
			default:
				System.err.println("Opção invalida digite novamente!");
				
				break;
			}
		}while(opcao !=0);
		System.out.println("Programa fechado!");
		teclado.close();
	 }

}
