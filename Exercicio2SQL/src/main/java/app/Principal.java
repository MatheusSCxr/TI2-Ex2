//Código baseado no tutorial disponibilizado no link: https://www.youtube.com/watch?v=iY4jlkh_AHU

package app;

import java.util.List;
import java.util.Scanner;
import dao.CarroDAO;
import modelo.Carro;

public class Principal {
	
	public static void main(String[] args) throws Exception {
		//Criando o objeto DAO que irá modificar os dados do carro
		CarroDAO carroDAO = new CarroDAO();
		int num = 0;
		int codigo = -1;
		String marca = "";
		String modelo = "";
		int ano = -1;
		Scanner entrada = new Scanner(System.in);
		//Menu em loop (parar até a tecla 5 digitada como opção)
		while (num != 5) {
		System.out.println("\n==== MENU PRINCIPAL ==== \n\n");
		System.out.println("Opção [1] -> Inserir Carro");
		System.out.println("Opção [2] -> Remover Carro");
		System.out.println("Opção [3] -> Atualizar Carro");
		System.out.println("Opção [4] -> Listar todos os Carros");
		System.out.println("Opção [5] -> Sair\n\n");
		System.out.print("Digite um número: ");
		num = entrada.nextInt();
		//Ações do menu
		switch (num) {
			//inserir carro
		  case 1:
		    System.out.print("Digite a marca do carro: ");
		    marca = entrada.next();
		    System.out.print("Digite o modelo do carro: ");
		    modelo = entrada.next();
		    System.out.print("Digite o ano do carro: ");
		    ano = entrada.nextInt();
		    System.out.print("Digite um código para esse carro: ");
		    codigo = entrada.nextInt();
			Carro carro = new Carro(codigo,marca,modelo,ano);
			if(carroDAO.insert(carro) == true) {
				System.out.println("Inserção com sucesso -> " + carro.toString());
			}
		    break;
		    //remover carro
		  case 2:	
			System.out.print("Digite o codigo do carro que deseja deletar: ");
		    codigo = entrada.nextInt();
			carroDAO.delete(codigo);
		    break;
		    //atualizar carro
		  case 3:
			System.out.print("Digite o codigo do carro que deseja atualizar: ");
			codigo = entrada.nextInt();
		    System.out.print("Digite a marca atualizada do carro: ");
		    marca = entrada.next();
		    System.out.print("Digite o modelo atualizado do carro: ");
		    modelo = entrada.next();
		    System.out.print("Digite o ano atualizado do carro: ");
		    ano = entrada.nextInt();
			if(carroDAO.update(codigo,marca,modelo,ano) == true) {
				System.out.println("Atualização bem-sucedida! ");
			}
		    break;
		    //listar todos os carros
		  case 4:
			System.out.println("Lista de todos os carros (ordenada por código): ");
			List<Carro> carros = carroDAO.getOrderByCodigo();
			for (Carro k: carros) {
					System.out.println(k.toString());
			}
		    break;
			}
		}
		//tecla 5 digitada como opção
		System.out.println("Fim do programa.");
		entrada.close();
	}
}