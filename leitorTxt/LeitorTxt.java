package leitorTxt;
import java.io.*;
import java.util.StringTokenizer;

/**
  LeitorTxt é uma classe que lê arquivos texto, armazena e pode retornar o mesmo em matriz
  Instâncias desta classe permitem a leitura de arquivos tipo texto através de um BufferedReader
  Nela encontramos, métodos para ler,  retornar em matriz, etc.
  @author Giovanna Pavani Martelli-19173 e Maria Luiza Sperancin Mancebo-19186
  @version 1.0
*/

public class LeitorTxt
{
	/** Objeto que vai ler o texto do arquivo*/
	protected BufferedReader leitor;

	/** Mantém armazenado o texto bruto que o leitor leu do arquivo*/
	protected String texto;

	/** Expressa, em cada instante, a linha do arquivo que estamos lindo.*/
	protected int linhaAtual;

	/**
	  Constrói uma nova instância da classe LeitorTxt
	  Para tanto, deve ser fornecido uma string que será utilizada
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
	  Método que verifica se o arquivo chegou ao fim através da função ready(). Se der erro ou n estiver ready, é fim do arquivo.
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
	   Método que lê as linhas do arquivo e armazena no this.texto
	   Nesse método, lemos a primeira linha do arquivo, que representa a quantidade de linhas da matriz que leremos posteriormente.
	   Então, fazemos um for em que esse número é o máximo e a cada linha não nula que lemos, a concatenamos na variavel texto que será retornada e atribuida a this.texto.
	   @throws Exception se o número lido na primeira linha, que indica a quantidade de linhas que a matriz terá, for menor que 2
	*/
	public String ler() throws Exception
	{
		String linhaUm = leitor.readLine();
		String texto = "";
		int qtdLinhas = Integer.parseInt(linhaUm);
		if(qtdLinhas < 2)
			throw new Exception("Número de equações/linhas inválido");

		String linha = "";
		for(int i = 0; i < qtdLinhas; i++)
		{
			linha = leitor.readLine(); // supondo q começa na 2º linha
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
	   Retorna this.texto em forma de matriz de números reais
	   Método que retorna o this.texto, isto é, tudo que o leitor leu, em forma de matriz de double.
	   Fazemos um split no texto e atribuimos cada linha a uma posição do vetor linhas.
	   Pegamos o numero de linhas, isto é, linhas.length e instanciamos uma matriz com linhas.length linhas e linhas.length+1 colunas.
	   A partir daí, fazemos um for onde: separamos cada valor da linha através de um StringTokeneizer e colocamos cada valor do vetor linhas na sua respectiva posição na matriz
	   @return this.texto em forma de matriz de reais
	   @throws Exception se o this.texto ainda não tem valor
	*/
	public double[][] retornarTxtEmMatriz(String texto) throws Exception
	{
		if(texto == "")
			throw new Exception("Texto inválido para transformar em matriz");

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
	  Gera um String com toda a informação presente na classe LeitorTxt.
	  É feito um String que recebe o this.texto, isto é, tudo que o leitor leu do arquivo
	  @return this.texto
	*/
	public String toString()
	{
		return this.texto;
	}

	/**
	   Método que retorna o hash code da instância da classe
	   Calcula o hashcode do LeitorTxt representada pela instância à qual o método for aplicado.
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
	   Método que retorna se o this é igual ao parametro obj do tipo objeto
	   Verifica se o Object fornecido como parâmetro é o mesmo LeitorTxt da instância, resultando true em caso afirmativo,
	   ou false, caso não forem iguais.
	   @param obj do tipo Object é  o objeto com o qual this será comparado
	   @return boolean se this é igual a obj
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
	   Produz e retorna uma cópia da instância this de LeitorTxt.
	   @return a cópia do this
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
	   Constroi uma cópia da instância da classe LeitorTxt.
	   Deve ser passado no parâmetro uma instância de LeitorTxt para ser
	   usada como modelo para criar uma nova.
	   @param modelo instância de LeitorTxt que será usada como molde.
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