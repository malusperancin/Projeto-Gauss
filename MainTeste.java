import teclado.*;
import leitorTxt.*;
import matriz.*;
import operacoesMatriz.*;

public class MainTeste
{
	public static void main(String[] args)
	{
		Matriz matriz;
		LeitorTxt leitor;
		OperacoesMatriz operador;
		int qtd = 0;

		try
		{
			// TESTE LEITOR
			leitor = new LeitorTxt("gauss.txt");
			String x = leitor.ler();
			//System.out.println(x);
			double[][] matrizi = leitor.retornarTxtEmMatriz(x);
			for(int a=0; a<matrizi.length; a++)
				for(int b=0; b<matrizi[0].length; b++)
					System.out.print(matrizi[a][b]);

			// TESTE MATRIZ
			//matriz = new Matriz();
			//matriz = new Matriz(4);
			//matriz.setQtd(matrizi.length);
			//matriz.setEquacoes(matrizi);
			matriz = new Matriz(matriz);
			
			if(!Matriz.valida(matriz))
				throw new Exception("matriz invalida");
			//qtd = matriz.getQtd();
			//operador = new OperacoesMatriz(matriz);
			
			// teste do método valida fora da classe(já que ele é protected)
			double mat[][] = new double[2][3];
			mat[0][0] = 1;
			mat[0][1] = 5;
			mat[0][2] = 2;
			mat[1][0] = 2;
			mat[1][1] = 7;
			mat[1][2] = 4;
			double anterior = 0, atual;
			int iguais = 0;
			for(int lin=0; lin<mat.length-1; lin++)
			{
				for(int col=0; col<mat[0].length; col++)
				{
					atual = mat[lin][col] / mat[lin+1][col];
					if(atual == anterior)
						iguais++;
					anterior = atual;
				}
			}
			if(iguais == mat.length) // tirei o +1 pq 1 pd ser diferentes
				throw new Exception("matriz inválida");
			else
				System.out.print("válida");
			
			matriz.setValor(2,3, -8);
			System.out.println(matriz.getValor(2,3));

			//TESTE OPERAÇÕES MATRIZ
			operador = new Operador(matriz);
			System.out.print(matriz.toString());
			operador.trocarLinhas();
			System.out.print(matriz.toString());
			
			while(operador.aindaHaZeros())
				operador.trocarLinhas();

			System.out.print(matriz.toString());

			operador.dividirLinha(2, -4);
			System.out.print(matriz.toString());

			double[]mult = new double[matriz.getQtd+1];
			for(int i=0; i<matriz.getQtd+1; i++)
				System.out.print(mult[i]);
			
			operador.somar(3, mult);
			System.out.print(matriz.toString());
			
			operador.setMatriz(matriz);

			System.out.print("deu tudo certo");
		}
		catch(Exception ex)
		{
			System.out.print("erro");
		}
	}
}