/**
 *Classe responsavel por montar o modelo da tabela que será exibida na tela
 **/
package projeto.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import projeto.model.Processo;

/**
 * @authors Matheus Ribeiro Barbosa Santos            RA: 201516660   CCO3AN-MCA
 * 			Victor Renato Frutuoso Gama               RA: 201508677   CCO3AN-MCA
 * 			Samuel Alves de Almeida                   RA: 201516313   CCO3AN-MCA
 * 			Michelle Fernandes Januário Fazolare	  RA: 201516916	  CCO3AN-MCA
 * 			Victor de Lacerda Alves Branco			  RA: 201516438	  CCO3AN-MCA
 *
 */
@SuppressWarnings("serial")
public class ModeloTabela extends AbstractTableModel
{

	//Atributos
	private List< Processo > linhas;
	private String[ ] colunas = null;

	/**
	 *Construtor Não Padrão
	 *@param linhas : List< Processo > , colunas : String [ ]
	 */
	public ModeloTabela( List< Processo > linhas , String [ ] colunas )
	{
		setLinhas( linhas );
		setColunas( colunas );
	}


	/**************************Sets e gets***********************************************************/
	public void setLinhas( List< Processo > linhas )
	{
		this.linhas = linhas;
	}

	public List< Processo > getLinhas( )
	{
		return linhas;
	}

	public void setColunas( String [ ] colunas )
	{
		this.colunas = colunas;
	}

	public String[ ] getColunas( )
	{
		return colunas;
	}
	/*************************************************************************************************/

	/**
	 *Retorna a quantidade de colunas
	 */
	@Override
	public int getColumnCount( )
	{
		return colunas.length;
	}

	/**
	 *Retorna a quantidade de linhas
	 */
	@Override
	public int getRowCount( )
	{
		return linhas.size( );
	}

	/**
	 *Retorna o nome da coluna
	 */
	@Override
	public String getColumnName( int coluna )
	{
		return colunas[ coluna ];
	}

	/**
	 *Monta a tabela
	 *@param numeroLinha , numeroColuna
	 */
	@Override
	public Object getValueAt( int numeroLinha , int numeroColuna )
	{	
		Processo p = linhas.get( numeroLinha );

		switch( numeroColuna )
		{
		case 0:
			return p.getProcesso( );
		case 1:
			return p.getTempoCpu( );
		case 2: 
			return p.getStatus( );
		}
		return null;

	}

}//Fim da Classe