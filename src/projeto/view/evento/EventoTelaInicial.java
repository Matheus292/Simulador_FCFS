/**
 * Responsavel por tratar os Eventos dos componentes 
 */
package projeto.view.evento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import projeto.service.Execucao;
import projeto.view.InserirProcessoGUI;
import projeto.view.ModeloTabela;
import projeto.view.Tela;

/**
 * @authors  Matheus Ribeiro Barbosa Santos RA: 201516660   CCO3AN-MCA
 *			 Victor Renato Frutuoso Gama    RA: 201508677   CCO3AN-MCA
 *			 Samuel Alves de Almeida        RA: 201516313   CCO3AN-MCA
 *			 Michelle Fernandes Januário Fazolare  RA: 201516916  CCO3AN-MCA
 *			 Victor de Lacerda Alves Branco RA: 201516438  CCO3AN-MCA
 *
 */
public class EventoTelaInicial implements ActionListener
{

	//Atributo
	private Tela window;

	/**
	 *Construtor não padrão
	 *@param window : Tela
	 */
	public EventoTelaInicial( Tela window )
	{
		this.window = window;
	}

	/**
	 *Responsável por tratar os eventos dos botões
	 */
	@Override
	public void actionPerformed( ActionEvent evento )
	{
		if( evento.getSource( ) == window.bInserirProcesso )
		{		
			new InserirProcessoGUI( window );

			if( window.getServico( ).retornaProcessos( ).size( ) == 0 )
			{
				window.bInserirProcesso.setEnabled( true );//Deixo o botão habilitado
				window.bIniciarSimulacao.setEnabled( false );//Deixo o botão desabilitado
			}
			else
			{
				window.bInserirProcesso.setEnabled( false );//Deixo o botão desabilitado
				window.bIniciarSimulacao.setEnabled( true );//Deixo o botão habilitado

			}
		}//fim do if

		if( evento.getSource( ) == window.bIniciarSimulacao )
		{	
			window.bIniciarSimulacao.setEnabled( false );
			new Thread( new Execucao( window.getServico( ).retornaProcessos( ) , window ) ).start( );

		}//fim do if

		if( evento.getSource( ) == window.bNovaSimulacao )
		{
			//Habilito componentes
			window.barraAtualizada.setEnabled( true );
			window.bInserirProcesso.setEnabled( true );


			//Desabilito este componente
			window.bNovaSimulacao.setEnabled( false );
			window.bRefazer.setEnabled( false );

			window.painel.clear( );

			//Limpa a fila
			window.getServico( ).limparFila( );

			//Colunas da tabela
			String colunas[ ] = new String[ ]{ "Processo" , "Tempo de CPU( Segundos )" , "Status" };

			//Limpa Tabela
			window.tabela.setModel( new ModeloTabela( window.getServico( ).retornaProcessos( ) , colunas  ) );

			//Atualizo alguns Componentes
			window.barraAtualizada.setValue( 0 );
			window.barraAtualizada.setString( "0,00 %" );

			window.lProcesso.setText( "Processo em execução: " );
			window.lQuantidade.setText( "Quantidade de processos a serem executados: " );
			window.lTempo.setText( "Tempo médio de espera: " );

		}//fim do if

		if( evento.getSource( ) == window.bRefazer )
		{
			//Deixa os status como iniciou
			window.getServico( ).alteraStatus( );

			//Habilito componente
			window.barraAtualizada.setEnabled( true );

			window.bRefazer.setEnabled( false ); //Deixo desabilitado
			window.bNovaSimulacao.setEnabled( false );//Deixo desabiliado

			window.painel.clear( );//Reseta o painel

			//Ira refazer a simulação anterior			
			new Thread( new Execucao( window.getServico( ).retornaProcessos( ) , window ) ).start( ) ;

		}//Fim do if

	}//Fim do metodo

}
