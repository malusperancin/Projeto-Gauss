import java.text.DecimalFormat;
import teclado.Teclado;
import leitorTxt.LeitorTxt;
import matriz.Matriz;
import operacoesMatriz.OperacoesMatriz;
/**
  Programa para resolver sistemas lineares através do método Gauss-Seidel

  @author Giovanna Pavani Martelli-19173 e Maria Luiza Sperancin Mancebo-19186
  @version 1.0
  @since 2019
*/
public class Programa
{
	public static void main(String[] args)
	{
		Matriz matriz;
		LeitorTxt leitor = null;
		OperacoesMatriz operador;
		int qtd = 0;

		try
		{
			System.out.println("================================================================================");
			System.out.println("                             Método de Gauss-Seidel");
			System.out.println("================================================================================");
			System.out.println("Bem vindo ao programinha de Malulis e Gigis. Aqui vamos resolver sistemas de equações lineares com qualquer quantidade de equações pelo método de Gauss-Seidel.\n");

			System.out.println("Digite o nome do arquivo da onde deseja pegar suas equações - (exemplo.txt) ");
			System.out.print("Nome do arquivo: ");
			char opcao = 'S';
			String arquivo;
			for(;;) // enquanto o usuário fornecer um nome de arquivo inválido
			{
				arquivo = Teclado.getUmString();
				try{
					leitor = new LeitorTxt(arquivo);
					break; // ao dar um nome válido, sai do loop
				}
				catch(Exception ex){
					System.out.print(ex.getMessage() + " Digite novamente o nome de um arquivo válido:   ");
				}
			}
			System.out.println("\n=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*\n");
			do
			{
				String texto = leitor.ler();
				matriz = new Matriz(leitor.retornarTxtEmMatriz(texto));
				qtd = matriz.getQtd();

				System.out.println("Esta é a equação a ser resolvida: ");
				// exibe a equacao colocando do lado um char para representar a variável
				for(int lin=0; lin<qtd; lin++)
				{
					String linha="";
					for(int col=0; col<qtd+1; col++)
					if(col<qtd)
						linha += ((matriz.getValor(lin, col)>=0?"+ ":"")+matriz.getValor(lin, col) + "" + (char)(col+97) + ' ');
					else
						linha+= "= " + matriz.getValor(lin, col);

					System.out.println(linha);
				}

				operador = new OperacoesMatriz(matriz);
				// tira os zeros da diagonal principal
				while(operador.aindaHaZeros())
					operador.trocarLinhas();


				for(int a=0; a<qtd; a++)// percorre linhas da matriz
				{
					// divide a linha a pelo valor da posicao(a,a), isto é, um dos valores da diagonal principal, tonarndo-o 1
					double valor = matriz.getValor(a,a);
					operador.dividirLinha(a, valor);
					for(int b = 0; b < qtd; b++)// torna 0 coluna i menos a posicao da diagonal principal da minha
					{
						if(a != b) // ve se n é a diagonal principal
							if(matriz.getValor(b, a) != 0) //verifica se já não é 0  | vamos inverter o a e b, pq agora não vai ser a coluna
							{                                                      //| que mudará, e sim a linha, pois estaremos trabalhando com a coluna
								double x = -(matriz.getValor(b, a));
								// pega o oposto do valor da posicao (b,a) da matriz
								double aux[] = operador.multiplicarLinha(a, x); // pegamos a última linha em que tornamos 1 o elemento da diagonal multiplicada pelo x
								operador.somarLinhas(b, aux); // e a somamos a linha b, tornando 0 o valor de (b,a)
							}
					}
				}

				//exibir resultados
				System.out.println("");
				System.out.print("Estes são os valores das variáveis, respectivamente: \n");
				DecimalFormat df = new DecimalFormat("#0.0"); // determina o formato dos resultados, deixando apenas 1 casa depois da vírgula
				for(int i=0; i<qtd; i++)
					System.out.println((char)(i+97) +" = "+ df.format(matriz.getValor(i, qtd)));

				if(!leitor.fimDeArquivo()) // se o arquivo ainda n acabou, pergunta ao usuário se deseja resolver a próxima equação
				{
					System.out.println("\n Deseja resolver o próximo sistema? S / N ");
					System.out.print("Sua opção: ");
					for(;;)
					{
						try{
						opcao = Teclado.getUmChar();
						}
						catch(Exception ex){System.out.print("\nOpção inválida. Digite novamente: ");}
						if(opcao != 'S' && opcao != 's' && opcao != 'N' && opcao != 'n')
							System.out.print("\nOpção inválida. Digite novamente: ");
						else
							break;
					}

					if(opcao == 'S' || opcao == 's')
					{
						System.out.println("\n================================================================================");
						continue; // volta no começo do 'do'
					}
					else
						break;
				}
			}
			while(!leitor.fimDeArquivo());
		}
		catch(Exception ex)
		{
			System.out.println("\n Erro: "+ex.getMessage()+"\n");
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		if(leitor.fimDeArquivo())
			System.out.println("\nTodas as equações do arquivo foram resolvidas!");
		System.out.println("\nObrigada por usar nosso programa bj bj <3\n");
	}
}
