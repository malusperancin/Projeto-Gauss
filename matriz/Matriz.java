package matriz;
/**
   Matriz é uma classe que armazena uma matriz de double e a valida
   Instâncias desta classe permitem a criação de matrizes do tipo Matriz.
   Nela encontramos, getters e setters, validador de uma matriz útil para quando usamos
   matrizes para resolver um sistema matemático, etc.
   @author Giovanna Pavani Martelli-19173 e Maria Luiza Sperancin Mancebo-19186
   @version 1.0
   @since 2019.
*/
public class Matriz
{
	/** Número constante que indica a quantidade inicial que a matriz deve ter*/
	protected static final int QTD_INICIAL = 2;

	/** Mantém armazenado o número de equações, isto é, o número de linhas que a matriz tem*/
	protected int qtd;

	/** Mantém armazenado as equações em si, isto é, a matriz de números reais*/
	protected double[][] equacoes;

	/**
	   Constrói uma nova instância da classe Matriz
	   Ele apenas instancia o atributo qtd com a constante QTD_INICIAL, assim com
	   o atributo equacoes com qtd como nº de linhas e qtd+1 como nº de colunas
	   @throws Exception se o nome do arquivo for nulo ou vazio
	*/
	public Matriz()throws Exception
	{
		this.qtd = QTD_INICIAL;
		equacoes = new double[this.qtd][this.qtd+1];
	}

	/**
	   Constrói uma nova instância da classe Matriz
	   Para tanto, deve ser fornecido um int qtd que vai ser utilizado como parametro na instanciação da matriz,
	   sendo qtd o número de linhas e qtd+1 o número de colunas
	   @param qtd int quantidade de equacoes
	   @throws Exception se o nome do arquivo for nulo ou vazio
	*/
	public Matriz(int qtd) throws Exception
	{
		this.setQtd(qtd);
		equacoes = new double[this.qtd][this.qtd+1];
	}

	/**
	   Constrói uma nova instância da classe Matriz
	   Para tanto, deve ser fornecido uma matriz de números reais (double)
       para definir o atributo equacoes, além de denifir o qtd como o length da matriz passada por parâmetro
	   @param matriz double[][] matriz com os números reais armazenados
	   @throws Exception se a quantidade ou a matriz fornecidas forem inválidas(ver métodos setQtd e setEquacoes)
	*/
	public Matriz(double[][] matriz) throws Exception
	{
		this.setQtd(matriz.length); // nº de linhas
		this.setEquacoes(matriz);
	}

	/**
	   Retorna o atributo equacoes da instância
	   Retorna a matriz de valores reais armazenada no atributo equacoes
	   @return matriz de números reais armazenada no objeto
	*/
	public double[][] getEquacoes()
	{
		return this.equacoes;
	}

	/**
	   Atribui ao atributo equacoes uma matriz do parâmetro
	   Atributo this.equacoes recebe uma matriz (mat) de double passada pelo parametro, se ela for válida
	   @param mat matriz de números reais que se deseja utilizar na classe
	*/
	public void setEquacoes(double[][] mat) throws Exception
	{
		if(mat == null)
			throw new Exception("Matriz nula");

		if(!this.valida(mat))
			throw new Exception("Matriz inválida! Coloque valores válidos.");

		this.equacoes = mat;
	}

	/**
	   Retorna se a matriz de números reais é válida
	   O método valida recebe pelo parametro uma matriz de double, onde, por
	   meio de um for, compara as divisões de todos os valores da linha e coluna atuais pelo valor da próxima linha e mesma coluna
	   Ou seja, dividi-se os valores da linha x pelos da linha x+1, respectivamente, e verifica se todos resultados são iguais.
	   Se todos os resultados forem iguais, matriz é inválida, portanto, retorna false. Caso 1 ou mais divisões forem diferentes, o método retorna true porque a matriz é válida
	   @param mat matriz de números reais que se deseja validar
	   @return se a matriz é válida nos quesitos ele retorna true, caso
	   ela não seja valida, retorna false.
	*/
	protected boolean valida(double[][] mat)
	{
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
		if(iguais == mat.length) // tirei o +1 pq 1 pd ser diferente
			return false;
		return true;
	}

	/**
	   Retorna se a matriz do tipo Matriz é válida
	   O método valida recebe pelo parametro uma matriz do tipo Matriz, onde, por
	   meio de um for, compara as divisões de todos os valores da linha e coluna atuais pelo valor da próxima linha e mesma coluna
	   Ou seja, dividi-se os valores da linha x pelos da linha x+1, respectivamente e verifica se todos resultados são iguais.
	   Se todos os resultados forem iguais, matriz é inválida, portanto, retorna false. Caso 1 ou mais divisões forem diferentes, o método retorna true porque a matriz é válida
	   @param mat Matriz matriz do tipo Matriz que se deseja validar
	   @return se a matriz é válida nos quesitos bla bla
	*/
	public static boolean valida(Matriz mat)
	{
		double anterior = 0, atual;
		int iguais = 0;
		for(int lin=0; lin<mat.qtd-1; lin++)
		{
			for(int col=0; col<mat.equacoes[0].length; col++)
			{
				atual = mat.equacoes[lin][col] / mat.equacoes[lin+1][col];
				if(atual == anterior)
					iguais++;
				anterior = atual;
			}
		}
		if(iguais == mat.qtd)
			return false;
		return true;
	}

	/**
	   Atribui Quantidade
	   Atribui ao atributo qtd um número inteiro passado por parâmetro para ser o número de linhas da matriz.
	   @param qtd int número inteiro para ser o número de linhas da matriz, isto é, o número de equações
	*/
	public void setQtd(int qtd) throws Exception
	{
		if(qtd <0)
			throw new Exception("Quantidade de linhas inválida para matriz");

		this.qtd = qtd;
	}

	/**
	   Retorna atributo qtd
       Retorna o atributo qtd da instância à qual este método for aplicado.
	   @return número de linhas da matriz
	*/
	public int getQtd()
	{
		return this.qtd;
	}

	/**
	   Retorna o valor.
	   Retorna o valor real da linha lin e coluna col do atributo equações.
	   @param lin int número correspondente à linha da matriz
	   @param col int número correspondente à coluna da linha da matriz
	   @return double valor da posição passada por parâmetro da matriz
	   @throws Exception se os parâmetros de linha e coluna estiver fora da matriz
	*/
	public double getValor(int lin, int col)throws Exception
	{
		if(lin < 0 || lin> this.qtd || col<0 || col>this.qtd+1)
			throw new Exception("Posição para retorno de valor inválida");
		return this.equacoes[lin][col];
	}

	/**
	   Muda o valor da matriz da linha lin e coluna col do atributo equacoes
	   Coloca o valor passado por parâmetro na coluna col e linha lin do atributo equações
	   @param lin int número correspondente à linha da matriz
	   @param col int número correspondente à coluna da linha da matriz
	   @param valor double novo número real pelo qual se deseja trocar da posição de equacoes
	   @throws Exception se os parametros de linha e coluna estiver fora da matriz
	*/
	public void setValor(int lin, int col, double valor) throws Exception
	{
		if(lin< 0 || lin > qtd || col<0 || col>qtd+1)
			throw new Exception("Posição para settar valor inválida");
		this.equacoes[lin][col] = valor;
	}

	/**
	   Clona Matriz
	   Produz e retorna uma cópia da instância this de Matriz.
	   @return a cópia do this
	 */
	public Object clone()
	{
		Matriz ret = null;
		try
		{
			ret = new Matriz(this);
		}
		catch(Exception ex){}
		return ret;
	}

	/**
       Constroi uma cópia da instância da classe Matriz.
       Deve ser passado no parâmetro uma instância de Matriz para ser
	   usada como modelo para criar uma nova.
       @param modelo instância de Matriz que será usada como molde.
       @throws Exception caso o molde for nulo.
    */
	public Matriz(Matriz modelo)throws Exception
	{
		if(modelo == null)
			throw new Exception("Modelo de matriz nulo");
		this.qtd = modelo.qtd;
		this.equacoes = modelo.equacoes;
	}

	/**
	   Método que retorna se o this é igual ao parametro obj do tipo objeto
	   Verifica se o Object fornecido como parâmetro é a mesma Matriz da instância, resultando true em caso afirmativo,
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

		Matriz mat = (Matriz)obj;

		if(this.qtd != mat.qtd)
			return false;

		for(int l = 0; l< this.qtd;l++)
		{
			for(int c = 0; c< this.qtd + 1;c++)
			{
				if(this.equacoes[l][c] != mat.equacoes[l][c])
					return false;
			}
		}

		return true;
	}

	/**
	   Método que retorna o hash code da instância da classe
	   Calcula o hashcode da Matriz representada pela instância à qual o método for aplicado.
	   @return int hash code que engloba o this.qtd e cada valor de equacoes
	*/
	public int hashCode()
	{
		int ret = 1;
		for(int l = 0; l< this.qtd;l++)
		{
			for(int c = 0; c < this.qtd + 1;c++)
				ret = 3 * ret + new Double (this.equacoes[l][c]).hashCode();
		}
		ret = 3 * ret + new Integer (this.qtd).hashCode();

		return ret;
	}

     /**
	    Gera um String com toda a informação presente na classe Matriz.
	    É feito um String que recebe todos os valores presentes na matriz
	    @return um String com todos os dados.
	 */
	public String toString()
	{
		String saida = "";
		for(int l = 0; l<this.qtd; l++)
		{
			for(int c = 0; c < this.qtd+1; c++)
				saida += this.equacoes[l][c];
			saida += "\n";
		}

		return saida;
	}
}