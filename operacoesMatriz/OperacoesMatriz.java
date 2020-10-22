package operacoesMatriz;
import matriz.Matriz;
/**
	A classe OperacoesMatriz é uma classe com operações para matriz do tipo Matriz
	Instâncias desta classe permitem a relização das operações com matrizes do tipo Matriz.
	Nela encontramos, métodos para verificar se possui zeros da diagonal, divisão
	de linhas da matriz, multiplicacao de linhas da matriz, etc.
	@author Giovanna Pavani Martelli - 19173 e Maria Luiza Sperancin Mancebo - 19186
	@since 2019.
*/
public class OperacoesMatriz
{
	/**Mantém armazenado uma matriz do tipo Matriz com os numeros que irão ser manipulados*/
	protected Matriz matriz;

	/**Armazena a quantidade de linhas presentes na matriz*/
	protected int qtd;

	/**
     Contrutor padrão da classe.
	 Constroe uma nova instancia da classe e, para isso, deve ser fornecida uma instancia da
	 classe Matriz.
     @param matriz Matriz que ira ser manipulada.
     @throws Exception caso a matriz seja nula
    */
	public OperacoesMatriz(Matriz matriz)throws Exception
	{
		this.setMatriz(matriz);
		this.qtd = matriz.getQtd();
	}

	/**
     Troca as linhas de lugar na matriz.
	 Através dos métodos get e set presentes na classe Matriz, o método trocarLinhas()
	 percorre todas as linhas da matriz, guarda os valores da linha lida em um vetor auxiliar, substitui os valores da linha a qual o auxiliar pertencia
	 pelos valores da linha seguinte e adiciona os valores do auxiliar na linha anteriormente copiada e substituida.
	*/
	public void trocarLinhas()
	{
		for(int lin = 0; lin < qtd-1; lin++)
		{
			double aux[] = new double[qtd+1];
			for(int col=0; col < qtd+1; col++)
			{
				try
				{
					aux[col] = this.matriz.getValor(lin,col); // coloca todos os números/colunas da linha lin da matriz
					this.matriz.setValor(lin,col, this.matriz.getValor(lin+1, col));
					this.matriz.setValor(lin+1, col, aux[col]);
				}
				catch(Exception ex){} // sei q n vai dar erro, pois lin e col nunca vão ser < 0 nem >= qtd-1 e qtd+1, respectivamente
			}
		}
	}

	/**
	  Verificador de zeros na diagonal principal
	  Verifica se possui zeros na diagonal principal da matriz. Para isso, fazemos um for que passa por todos os valores
	  da diagonal principal ((0,0), (1,1), (2,2)...). Se algum desses valores for igual a 0, retorna true, se não achar nenhum, retorna false
	  @return caso encontre 0 na diagonal, retorna true, caso não encontre, retorna false.
	 */
	public boolean aindaHaZeros()
	{
		for(int i=0; i<qtd; i++)
		{
			try{
				if(this.matriz.getValor(i,i) == 0)
				return true;
			}
			catch(Exception ex){} // sei que não vai dar erro porque a linha e a coluna passadas por parâmetro nunca serão <0 nem >= qtd()

		}
		return false;
	}

	/**
	  Divide todos os valores de um linha da matriz por um valor real
	  Metodo que divide uma linha da matriz pelo divisor passado pelo parâmetro. Nele, verifica se a linha e o divisor são válidos
	  e logo depois, através de um for, acessamos os valores de todas as colunas daquela linha, dividindo pelo divisor e
	  colocando de volta na matriz com o metodo setValor da classe Matriz.
	  @param linha numero da linha que será dividida
	  @param divisor numero que irá divir a linha
	  @throws Exception se a linha for maior que a quantidade de linhas da matriz ou menor que zero,
	  ou se o divisor for igual a zero.
	 */
	public void dividirLinha(int linha, double divisor)throws Exception
	{
		if(linha<0 || linha>qtd)
			throw new Exception("Erro nas operações");
		if(divisor == 0)
			throw new Exception("Erro nas operações");
		for(int col = 0; col < qtd+1; col++)
		{
			double valor = this.matriz.getValor(linha, col)/divisor;
			this.matriz.setValor(linha, col, valor);
		}
	}

	/**
	  Multiplica uma linha da matriz
	  Método que multiplica uma linha da matriz pelo fator passado pelo parâmetro. Nele, verifica se a linha é valida
	  e logo depois, através de um for, acessamos os valores de cada coluna daquela linha multiplicando pelo fator, colocamos em um vetor
	  e retornamos o mesmo, não alterando a matriz em nenhum momento.
	  @param linha numero da linha que será dividida
	  @param fator numero que irá multiplicar a linha
	  @throws Exception se a linha for maior que a quantidade de linhas da matriz ou menor que zero.
	  @return vetor com os respectivos valores multiplicados da linha pelo fator
	 */
	public double[] multiplicarLinha(int linha, double fator)throws Exception
	{
		if(linha<0 || linha>qtd)
			throw new Exception("Erro nas operações");

		double ret[] = new double[qtd+1];
		for(int col = 0; col < qtd+1; col++)
		{
			double valor = this.matriz.getValor(linha, col) * fator;
			ret[col] = valor;
		}
		return ret;
	}

	/**
	  Soma os valores de uma linha da matriz aos valores de um vetor
	  Método que soma uma linha da matriz com um vetor passado por parametro. É feito a partir de
	  um for que percorre alguma linha da matriz e, através do getValor da classe Matriz, ele obtém o valor daquela
	   celula e soma com a celula correspondente do vetor passado no paramâtro. Através do método
	  setValor, o novo valor é inserido na linha(passada pelo parametro) e na coluna(obtido pelo for) da matriz.
	  @param linha número da linha que será somada
	  @param vet vetor que será somado em alguma linha da matriz
	  @throws Exception se a linha for maior que a quantidade de linhas da matriz ou menor que zero, se o vetor for
	  nulo ou se o tamanho do vetor for diferente do tamanho da linha da matriz.
	 */
	public void somarLinhas(int linha, double[] vet)throws Exception
	{
		if(linha<0 || linha>this.qtd)
			throw new Exception("Erro nas operações");
		if(vet == null || vet.length != qtd+1)
			throw new Exception("Erro nas operações");

		for(int col = 0; col<this.qtd+1; col++)
		{
			try{
				double valor = vet[col] + this.matriz.getValor(linha, col);
				this.matriz.setValor(linha, col, valor);
			}
			catch(Exception ex){}
		}
	}

	/**
	  Setter do atributo matriz do tipo Matriz
	  Atributo matriz recebe um clone da matriz fornecida
	  @param mat matriz da classe Matriz que será settada
	  @throws Exception caso a mat for nula.
	 */
	public void setMatriz(Matriz mat)throws Exception
	{
		if(mat == null)
			throw new Exception("Matriz nula");
		this.matriz = (Matriz)mat.clone();
	}


	/**
	  Clona OperacoesMatriz
	  Produz e retorna uma cópia da instância this de OperacoesMatriz.
	  @return a cópia do this
	 */
	public Object clone()
	{
		OperacoesMatriz ret = null;
		try
		{
			ret = new OperacoesMatriz(this);
		}
		catch(Exception erro)
		{}
		return ret;
	}

	/**
	  Gera um String com toda a informação presente na classe OperacoesMatriz.
	  É feito um String que recebe todos os valores presentes na matriz, além da quantidade de
	  linhas que essa matriz possui.
	  @return um String com todos os dados.
	 */
	public String toString()
	{
		return matriz.toString();
	}

	/**
     Calula e retorna o hashcode da instância do tipo OperacoesMatriz.
     Calcula o hashcode da OperacaoMatriz representada pela instância à qual o método for aplicado.
     @return o hashcode de quem chamou o método
    */
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + this.matriz.hashCode();
		ret = 3 * new Integer(this.qtd).hashCode();
		return ret;
	}

	/**
	 Verifica se duas instâncias de OperacoesMatriz são iguais.
     Verifica se o Object fornecido como parâmetro é a mesma OperacoesMatriz da instância, resultando true em caso afirmativo,
     ou false, caso não forem iguais.
     @param  obj o objeto que vai ser comparado com a instância
     @return true caso forem iguais, e false caso forem diferentes
	*/
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		OperacoesMatriz op = (OperacoesMatriz)obj;

		if(!(this.matriz.equals(op.matriz)) || this.qtd != op.qtd)
			return false;

		return true;
	}

	/**
     Constroi uma cópia da instância da classe OperacoesMatriz.
     Deve ser passado no parametro uma instância de OperacoesMatriz para ser
	 usada como modelo para criar uma nova.
     @param op instância de OperacoesMatriz que será usada como molde.
     @throws Exception caso o molde for nulo.
    */
	public OperacoesMatriz (OperacoesMatriz op) throws Exception
	{
		if(op == null)
			throw new Exception();

		this.matriz = op.matriz;
		this.qtd    = op.qtd;
	}
}