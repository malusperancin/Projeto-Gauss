package matriz;
/**
   Matriz � uma classe que armazena uma matriz de double e a valida
   Inst�ncias desta classe permitem a cria��o de matrizes do tipo Matriz.
   Nela encontramos, getters e setters, validador de uma matriz �til para quando usamos
   matrizes para resolver um sistema matem�tico, etc.
   @author Giovanna Pavani Martelli-19173 e Maria Luiza Sperancin Mancebo-19186
   @version 1.0
   @since 2019.
*/
public class Matriz
{
	/** N�mero constante que indica a quantidade inicial que a matriz deve ter*/
	protected static final int QTD_INICIAL = 2;

	/** Mant�m armazenado o n�mero de equa��es, isto �, o n�mero de linhas que a matriz tem*/
	protected int qtd;

	/** Mant�m armazenado as equa��es em si, isto �, a matriz de n�meros reais*/
	protected double[][] equacoes;

	/**
	   Constr�i uma nova inst�ncia da classe Matriz
	   Ele apenas instancia o atributo qtd com a constante QTD_INICIAL, assim com
	   o atributo equacoes com qtd como n� de linhas e qtd+1 como n� de colunas
	   @throws Exception se o nome do arquivo for nulo ou vazio
	*/
	public Matriz()throws Exception
	{
		this.qtd = QTD_INICIAL;
		equacoes = new double[this.qtd][this.qtd+1];
	}

	/**
	   Constr�i uma nova inst�ncia da classe Matriz
	   Para tanto, deve ser fornecido um int qtd que vai ser utilizado como parametro na instancia��o da matriz,
	   sendo qtd o n�mero de linhas e qtd+1 o n�mero de colunas
	   @param qtd int quantidade de equacoes
	   @throws Exception se o nome do arquivo for nulo ou vazio
	*/
	public Matriz(int qtd) throws Exception
	{
		this.setQtd(qtd);
		equacoes = new double[this.qtd][this.qtd+1];
	}

	/**
	   Constr�i uma nova inst�ncia da classe Matriz
	   Para tanto, deve ser fornecido uma matriz de n�meros reais (double)
       para definir o atributo equacoes, al�m de denifir o qtd como o length da matriz passada por par�metro
	   @param matriz double[][] matriz com os n�meros reais armazenados
	   @throws Exception se a quantidade ou a matriz fornecidas forem inv�lidas(ver m�todos setQtd e setEquacoes)
	*/
	public Matriz(double[][] matriz) throws Exception
	{
		this.setQtd(matriz.length); // n� de linhas
		this.setEquacoes(matriz);
	}

	/**
	   Retorna o atributo equacoes da inst�ncia
	   Retorna a matriz de valores reais armazenada no atributo equacoes
	   @return matriz de n�meros reais armazenada no objeto
	*/
	public double[][] getEquacoes()
	{
		return this.equacoes;
	}

	/**
	   Atribui ao atributo equacoes uma matriz do par�metro
	   Atributo this.equacoes recebe uma matriz (mat) de double passada pelo parametro, se ela for v�lida
	   @param mat matriz de n�meros reais que se deseja utilizar na classe
	*/
	public void setEquacoes(double[][] mat) throws Exception
	{
		if(mat == null)
			throw new Exception("Matriz nula");

		if(!this.valida(mat))
			throw new Exception("Matriz inv�lida! Coloque valores v�lidos.");

		this.equacoes = mat;
	}

	/**
	   Retorna se a matriz de n�meros reais � v�lida
	   O m�todo valida recebe pelo parametro uma matriz de double, onde, por
	   meio de um for, compara as divis�es de todos os valores da linha e coluna atuais pelo valor da pr�xima linha e mesma coluna
	   Ou seja, dividi-se os valores da linha x pelos da linha x+1, respectivamente, e verifica se todos resultados s�o iguais.
	   Se todos os resultados forem iguais, matriz � inv�lida, portanto, retorna false. Caso 1 ou mais divis�es forem diferentes, o m�todo retorna true porque a matriz � v�lida
	   @param mat matriz de n�meros reais que se deseja validar
	   @return se a matriz � v�lida nos quesitos ele retorna true, caso
	   ela n�o seja valida, retorna false.
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
	   Retorna se a matriz do tipo Matriz � v�lida
	   O m�todo valida recebe pelo parametro uma matriz do tipo Matriz, onde, por
	   meio de um for, compara as divis�es de todos os valores da linha e coluna atuais pelo valor da pr�xima linha e mesma coluna
	   Ou seja, dividi-se os valores da linha x pelos da linha x+1, respectivamente e verifica se todos resultados s�o iguais.
	   Se todos os resultados forem iguais, matriz � inv�lida, portanto, retorna false. Caso 1 ou mais divis�es forem diferentes, o m�todo retorna true porque a matriz � v�lida
	   @param mat Matriz matriz do tipo Matriz que se deseja validar
	   @return se a matriz � v�lida nos quesitos bla bla
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
	   Atribui ao atributo qtd um n�mero inteiro passado por par�metro para ser o n�mero de linhas da matriz.
	   @param qtd int n�mero inteiro para ser o n�mero de linhas da matriz, isto �, o n�mero de equa��es
	*/
	public void setQtd(int qtd) throws Exception
	{
		if(qtd <0)
			throw new Exception("Quantidade de linhas inv�lida para matriz");

		this.qtd = qtd;
	}

	/**
	   Retorna atributo qtd
       Retorna o atributo qtd da inst�ncia � qual este m�todo for aplicado.
	   @return n�mero de linhas da matriz
	*/
	public int getQtd()
	{
		return this.qtd;
	}

	/**
	   Retorna o valor.
	   Retorna o valor real da linha lin e coluna col do atributo equa��es.
	   @param lin int n�mero correspondente � linha da matriz
	   @param col int n�mero correspondente � coluna da linha da matriz
	   @return double valor da posi��o passada por par�metro da matriz
	   @throws Exception se os par�metros de linha e coluna estiver fora da matriz
	*/
	public double getValor(int lin, int col)throws Exception
	{
		if(lin < 0 || lin> this.qtd || col<0 || col>this.qtd+1)
			throw new Exception("Posi��o para retorno de valor inv�lida");
		return this.equacoes[lin][col];
	}

	/**
	   Muda o valor da matriz da linha lin e coluna col do atributo equacoes
	   Coloca o valor passado por par�metro na coluna col e linha lin do atributo equa��es
	   @param lin int n�mero correspondente � linha da matriz
	   @param col int n�mero correspondente � coluna da linha da matriz
	   @param valor double novo n�mero real pelo qual se deseja trocar da posi��o de equacoes
	   @throws Exception se os parametros de linha e coluna estiver fora da matriz
	*/
	public void setValor(int lin, int col, double valor) throws Exception
	{
		if(lin< 0 || lin > qtd || col<0 || col>qtd+1)
			throw new Exception("Posi��o para settar valor inv�lida");
		this.equacoes[lin][col] = valor;
	}

	/**
	   Clona Matriz
	   Produz e retorna uma c�pia da inst�ncia this de Matriz.
	   @return a c�pia do this
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
       Constroi uma c�pia da inst�ncia da classe Matriz.
       Deve ser passado no par�metro uma inst�ncia de Matriz para ser
	   usada como modelo para criar uma nova.
       @param modelo inst�ncia de Matriz que ser� usada como molde.
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
	   M�todo que retorna se o this � igual ao parametro obj do tipo objeto
	   Verifica se o Object fornecido como par�metro � a mesma Matriz da inst�ncia, resultando true em caso afirmativo,
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
	   M�todo que retorna o hash code da inst�ncia da classe
	   Calcula o hashcode da Matriz representada pela inst�ncia � qual o m�todo for aplicado.
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
	    Gera um String com toda a informa��o presente na classe Matriz.
	    � feito um String que recebe todos os valores presentes na matriz
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