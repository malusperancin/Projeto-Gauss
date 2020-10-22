package leitorTxt;
import java.io.*;
import java.util.StringTokenizer;

/**
  LeitorTxt � uma classe que l� arquivos texto, armazena e pode retornar o mesmo em matriz
  Inst�ncias desta classe permitem a leitura de arquivos tipo texto atrav�s de um BufferedReader
  Nela encontramos, m�todos para ler,  retornar em matriz, etc.
  @author Giovanna Pavani Martelli-19173 e Maria Luiza Sperancin Mancebo-19186
  @version 1.0
*/

public class LeitorTxt
{
	/** Objeto que vai ler o texto do arquivo*/
	protected BufferedReader leitor;

	/** Mant�m armazenado o texto bruto que o leitor leu do arquivo*/
	protected String texto;

	/** Expressa, em cada instante, a linha do arquivo que estamos lindo.*/
	protected int linhaAtual;

	/**
	  Constr�i uma nova inst�ncia da classe LeitorTxt
	  Para tanto, deve ser fornecido uma string que ser� utilizada
      para instanciar o leitor(BufferedReader)
	  @param arq String nome do arquivo
	  @throws Exception se o nome do arquivo for nulo ou vazio
	*/
	public LeitorTxt(String arq)throws Exception
	{
		if(arq == "" || arq == null)
			throw new Exception("Arquivo inexistente ou nulo");
		leitor = new BufferedReader(new FileReader(arq));
		this.texto = "";
		this.linhaAtual = 0;
	}

	/**
	  Verificador do fim do arquivo
	  M�todo que verifica se o arquivo chegou ao fim atrav�s da fun��o ready(). Se der erro ou n estiver ready, � fim do arquivo.
	  @return se o arquivo chegou ao fim
	*/
	public boolean fimDeArquivo()
	{
		try{
			if(!leitor.ready())
				return true;
		}
		catch(Exception ex){
			return true;
		}
		return false;
	}

	/**
	   M�todo que l� as linhas do arquivo e armazena no this.texto
	   Nesse m�todo, lemos a primeira linha do arquivo, que representa a quantidade de linhas da matriz que leremos posteriormente.
	   Ent�o, fazemos um for em que esse n�mero � o m�ximo e a cada linha n�o nula que lemos, a concatenamos na variavel texto que ser� retornada e atribuida a this.texto.
	   @throws Exception se o n�mero lido na primeira linha, que indica a quantidade de linhas que a matriz ter�, for menor que 2
	*/
	public String ler() throws Exception
	{
		String linhaUm = leitor.readLine();
		String texto = "";
		int qtdLinhas = Integer.parseInt(linhaUm);
		if(qtdLinhas < 2)
			throw new Exception("N�mero de equa��es/linhas inv�lido");

		String linha = "";
		for(int i = 0; i < qtdLinhas; i++)
		{
			linha = leitor.readLine(); // supondo q come�a na 2� linha
			if (linha != null)
			{
				texto += linha + "\n";
				linhaAtual++;
			}
			else
				break;
		}
		this.texto += texto;
		return texto;
	}

	/**
	   Retorna this.texto em forma de matriz de n�meros reais
	   M�todo que retorna o this.texto, isto �, tudo que o leitor leu, em forma de matriz de double.
	   Fazemos um split no texto e atribuimos cada linha a uma posi��o do vetor linhas.
	   Pegamos o numero de linhas, isto �, linhas.length e instanciamos uma matriz com linhas.length linhas e linhas.length+1 colunas.
	   A partir da�, fazemos um for onde: separamos cada valor da linha atrav�s de um StringTokeneizer e colocamos cada valor do vetor linhas na sua respectiva posi��o na matriz
	   @return this.texto em forma de matriz de reais
	   @throws Exception se o this.texto ainda n�o tem valor
	*/
	public double[][] retornarTxtEmMatriz(String texto) throws Exception
	{
		if(texto == "")
			throw new Exception("Texto inv�lido para transformar em matriz");

		String linhas[] = texto.split("\n");
		int qtdLinhas = linhas.length;
		double matriz[][] = new double[qtdLinhas][qtdLinhas+1];
		for(int lin = 0; lin < qtdLinhas; lin ++)
		{
			String linha = linhas[lin];
			StringTokenizer token = new StringTokenizer(linha, " ");
			//if(token.countTokens() != qtd+1)
			for (int col = 0; token.hasMoreTokens(); col++)
				matriz[lin][col] = Double.parseDouble(token.nextToken());
		}
		return matriz;
	}

	/**
	  Gera um String com toda a informa��o presente na classe LeitorTxt.
	  � feito um String que recebe o this.texto, isto �, tudo que o leitor leu do arquivo
	  @return this.texto
	*/
	public String toString()
	{
		return this.texto;
	}

	/**
	   M�todo que retorna o hash code da inst�ncia da classe
	   Calcula o hashcode do LeitorTxt representada pela inst�ncia � qual o m�todo for aplicado.
	   @return int hash code que engloba o this.texto, o this.linhaAtual e o this.leitor
	*/
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + this.texto.hashCode();
		ret = 3 * ret + new Integer (this.linhaAtual).hashCode();
		ret = 3 * ret + this.leitor.hashCode();
		return ret;
	}

	/**
	   M�todo que retorna se o this � igual ao parametro obj do tipo objeto
	   Verifica se o Object fornecido como par�metro � o mesmo LeitorTxt da inst�ncia, resultando true em caso afirmativo,
	   ou false, caso n�o forem iguais.
	   @param obj do tipo Object �  o objeto com o qual this ser� comparado
	   @return boolean se this � igual a obj
	*/
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		LeitorTxt leit = (LeitorTxt)obj;

		if(this.linhaAtual != leit.linhaAtual || this.texto != leit.texto || !(this.leitor.equals(leit.leitor)))
			return false;

		return true;
    }

    /**
	   Clona LeitorTxt
	   Produz e retorna uma c�pia da inst�ncia this de LeitorTxt.
	   @return a c�pia do this
	 */
	public Object clone()
	{
		LeitorTxt ret = null;
		try
		{
			ret = new LeitorTxt(this);
		}
		catch(Exception ex){}
		return ret;
	}

	/**
	   Constroi uma c�pia da inst�ncia da classe LeitorTxt.
	   Deve ser passado no par�metro uma inst�ncia de LeitorTxt para ser
	   usada como modelo para criar uma nova.
	   @param modelo inst�ncia de LeitorTxt que ser� usada como molde.
	   @throws Exception caso o molde for nulo.
	*/
	public LeitorTxt(LeitorTxt modelo)throws Exception
	{
		if(modelo == null)
			throw new Exception("Modelo de LeitorTxt nulo");
		this.texto = modelo.texto;
		this.leitor = modelo.leitor;
		this.linhaAtual = modelo.linhaAtual;
	}
}