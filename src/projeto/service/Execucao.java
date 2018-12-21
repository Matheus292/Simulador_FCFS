/**
 * Responsavel por executar a Thread de execução
 */
package projeto.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import projeto.model.Processo;
import projeto.view.ModeloTabela;
import projeto.view.Tela;

/**
 * @authors Matheus Ribeiro Barbosa Santos            RA: 201516660   CCO3AN-MCA
 * 			Victor Renato Frutuoso Gama               RA: 201508677   CCO3AN-MCA
 * 			Samuel Alves de Almeida                   RA: 201516313   CCO3AN-MCA
 * 			Michelle Fernandes Januário Fazolare	  RA: 201516916	  CCO3AN-MCA
 * 			Victor de Lacerda Alves Branco			  RA: 201516438	  CCO3AN-MCA
 *
 */
public class Execucao implements Runnable
{

	//Atributo 
	private List< Processo > fila;
	private Tela tela;
	private DecimalFormat formato;

	/**
	 *Construtor Não Padrão
	 *@param fila : List< Processo > , tela : Tela 
	 */
	public Execucao( List< Processo > fila , Tela tela )
	{
		this.fila = fila;
		this.tela = tela;

		formato = new DecimalFormat( "0.00" );
	}


	/**
	 *Responsavel por fazer a execução da simulação
	 */
	@Override
	public void run( )
	{
		boolean executa = true;

		double somador = 0.0 ;

		int execucao = 0 , indice = 0 , quantidade , medidor = 0  ;

		List< Double > tempo = new ArrayList< >( );

		//Colunas da tabela
		String colunas[ ] = new String[ ]{ "Processo" , "Tempo de CPU( Segundos )" , "Status" };

		//Inicia a barra de progresso
		tela.barraAtualizada.setMaximum( 100 );
		tela.barraAtualizada.setValue( execucao );
		tela.barraAtualizada.setString( "0,00" + " %" );


		//Faz o primeiro indice da fila mudar o Status			
		fila.get( indice ).setStatus( Status.EM_EXECUCAO );
		Processo processo = fila.get( indice );

		tempo.add( 0.0 );

		tela.painel.draw( medidor , processo.getProcesso( ) , true );

		for( Double d : tempo )
			somador += d ;

		tela.lTempo.setText( "Tempo médio de espera: " + formato.format( ( somador / tempo.size( ) ) ) + " segundo(s)" );


		//Quantidade de processos na fila a serem executados
		quantidade = fila.size( ) - 1;

		//Enquanto existir processos na fila a serem executados, rode isto
		while( executa )
		{
			tela.painel.repaint( );

			try
			{	
				//Atualiza a tabela
				tela.tabela.setModel( new ModeloTabela( fila , colunas ) );

				//Mostra qual processo esta em execução
				tela.lProcesso.setText( "Processo em execução: " + processo.getProcesso( ) );

				//Mostra quantos processos há na fila para serem executados
				tela.lQuantidade.setText( "Quantidade de processos a serem executados: " + quantidade );


				//Põe a Thread pra "Dormir" 1 segundo
				Thread.sleep( 1000 );

				execucao ++ ;
				medidor ++;
							
				//Calcula a porcentagem da execução
				double porcentagem = ( execucao /  processo.getTempoCpu( ) ) * 100;

				//Atualiza a barra de Progresso				
				tela.barraAtualizada.setValue( ( int ) porcentagem );
				tela.barraAtualizada.setString( formato.format( porcentagem ) +  " %" );

				tela.painel.draw( medidor , processo.getProcesso( ) , true );

				if( porcentagem == 100 )
				{
					if( quantidade == 0 )
					{
						fila.get( indice ).setStatus( Status.CONCLUIDO );											
						executa = false;
						fila.get( indice ).setStatus( Status.CONCLUIDO );											

					}//Fim do if
					else
					{
						//Muda o status do processo
						fila.get( indice ).setStatus( Status.CONCLUIDO );
						//Adiciona no ArrayList o tempo de Cpu
						tempo.add( tempo.get( indice ) + processo.getTempoCpu( ) );						

						//Faz algumas operações para lógica do negócio
						indice ++;						
						processo = trocaProcesso( indice );
						tela.painel.draw( medidor , processo.getProcesso( ) , false );
						quantidade --;
						execucao = 0;
						porcentagem = 0;

						//Atualiza a barra de Progresso				
						tela.barraAtualizada.setValue( ( int ) porcentagem );
						tela.barraAtualizada.setString( formato.format( porcentagem ) +  " %" );

						//Muda status
						fila.get( indice ).setStatus( Status.EM_EXECUCAO );				

						//Seta o somador
						somador = 0.0;

						for( Double d : tempo )
							somador += d ;

						tela.lTempo.setText( "Tempo médio de espera: " + formato.format( ( somador / tempo.size( ) ) ) + " segundo(s)" );
					}
				}		
			}
			catch( Exception e )
			{
				e.getStackTrace( );
			}

		}	
		//Atualiza a tabela
		tela.tabela.setModel( new ModeloTabela( fila , colunas ) );

		//Atualiza o processo em execução
		tela.lProcesso.setText( "Processo em execução: " );

		tela.painel.draw( medidor + 1 , processo.getProcesso( ) , true );
		tela.painel.draw( medidor , processo.getProcesso( ) , false );
		
		tela.painel.repaint( );

		//Desabilita a barra de progresso
		tela.barraAtualizada.setEnabled( false );

		//Botões habilitados
		tela.bRefazer.setEnabled( true );
		tela.bNovaSimulacao.setEnabled( true );

	}

	/**
	 *Aqui troca o processo a ser executado
	 */
	public Processo trocaProcesso( int proximo )
	{
		if( fila.size( ) > proximo )
		{
			Processo processo = fila.get( proximo );
			return processo;
		}
		return null;
	}

}//Fim da classe