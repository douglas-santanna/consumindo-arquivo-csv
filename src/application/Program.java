package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Produto;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Produto> produto = new ArrayList<Produto>();
		
		System.out.println("Digite o caminho do arquivo: ");
		String caminhoDigitado = sc.nextLine();
		
		File caminhoDaPasta = new File(caminhoDigitado);
		String pastaSaida = caminhoDaPasta.getParent() + "/out";
		boolean cricacao = new File(pastaSaida).mkdir();
		String novoArquivo = pastaSaida+"/summary.csv";
		
		System.out.println(" ");
		
		try(BufferedReader br = new BufferedReader( new FileReader(caminhoDigitado))){
			
			String linha = br.readLine();
			
			while(linha != null) {
				System.out.println(linha);
				String[] linhaProduto = linha.split(",");
				String nomeProduto = linhaProduto[0];
				Double preco = Double.parseDouble(linhaProduto[1]);
				Integer quantidade = Integer.parseInt(linhaProduto[2]);
				produto.add(new Produto(nomeProduto, preco, quantidade));
				linha = br.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println(" ");
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(novoArquivo))){
			for(Produto linhaProduto : produto) {
				System.out.println(linhaProduto.getNomeProduto() +" - "+ String.format("%.2f",linhaProduto.calcularTotal()));
				bw.write(linhaProduto.getNomeProduto() +","+ String.format("%.2f",linhaProduto.calcularTotal()));
				bw.newLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

		
		
		
		
		sc.close();

	}

}
