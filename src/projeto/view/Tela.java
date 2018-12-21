/**
 * Tela inicial da aplica��o
 */
package projeto.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

import projeto.service.ProcessoService;
import projeto.view.evento.EventoTelaInicial;

/**
 * @authors Matheus Ribeiro Barbosa Santos            RA: 201516660   CCO3AN-MCA
 * 			Victor Renato Frutuoso Gama               RA: 201508677   CCO3AN-MCA
 * 			Samuel Alves de Almeida                   RA: 201516313   CCO3AN-MCA
 * 			Michelle Fernandes Janu�rio Fazolare	  RA: 201516916	  CCO3AN-MCA
 * 			Victor de Lacerda Alves Branco			  RA: 201516438	  CCO3AN-MCA
 *
 */

public class Tela extends JFrame
{
	/**
	 * Vers�o de S�rie
	 */
	private static final long serialVersionUID = 1L;

	//Atributos
	public JButton bInserirProcesso , bIniciarSimulacao , bNovaSimulacao , bRefazer ;
	public JLabel lProcesso , lQuantidade , lTempo ;
	public JProgressBar barraAtualizada;
	public JTable tabela;
	public ModeloTabela modelo;
	private ProcessoService servico;
	public PainelCentral painel;
	
	
	
	/**
	 *Construtor Padr�o 
	 */
	public Tela( )
	{	
		//Chama o Construtor da super classe
		super( "Simulador FCFS" );

		//Define Look and fell Nimbus
		Tema.alteraAparencia( this , 1 );
		
		//Define Layout da janela
		getContentPane( ).setLayout( null );

		//Define cor de fundo
		getContentPane( ).setBackground( Color.BLACK );

		//Instancia ProcessoService
		servico = new ProcessoService( );

		//Instancia Componentes
		instanciaJTable( );
		instanciaJButton( );
		instanciaJLabel( );
		instanciaJProgressBar( );
		instanciaPainel( );
		

		//Configura��es da janela
		//setUndecorated( true );
		setSize( 980 , 720 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null ); //Deixa a tela centralizada
		setResizable( false );//N�o posso redimensionar a janela
		setVisible( true );
	}

	/**
	 *M�todo get de ProcessoService
	 *@return sercico : ProcessoService
	 */
	public ProcessoService getServico( )
	{
		return servico;
	}

	/**
	 *Instancia Painel Central 
	 */
	private void instanciaPainel( )
	{
		painel = new PainelCentral( );	
		painel.setSize( 900 , 234 );
		
		//Instancia objeto JScrollPane
		JScrollPane rolagem = new JScrollPane( painel );
				
		//painel.scrollRectToVisible( getBounds( ) );
		rolagem.setViewportView( painel );
		
				
		//Borda
		TitledBorder borda = new TitledBorder( "Linha do tempo da simula��o" );
		borda.setTitleColor( Color.GREEN );
		
		rolagem.setBorder( borda );
		
		//Adiciona ao Container
		getContentPane( ).add( rolagem );
		
		//Localiza��o na janela
		rolagem.setBounds( 50 , 250 , 871 , 234 );

	}

	/**
	 * Aqui instancia os JButtons
	 */
	private void instanciaJButton( )
	{
		bInserirProcesso = new JButton( "Inserir Processos" );
		bInserirProcesso.setBounds( 750 , 50 , 150 , 30 );
		bInserirProcesso.addActionListener( new EventoTelaInicial( this ) );
		bInserirProcesso.setEnabled( false );
		getContentPane( ).add( bInserirProcesso );
		
		
		bIniciarSimulacao = new JButton( "Iniciar Simula��o" );
		bIniciarSimulacao.setBounds( 750 , 90 , 150 , 30 );
		bIniciarSimulacao.addActionListener( new EventoTelaInicial( this ) );
		bIniciarSimulacao.setEnabled( false );
		getContentPane( ).add( bIniciarSimulacao );
		
		bNovaSimulacao = new JButton( "Nova Simula��o" );
		bNovaSimulacao.setBounds( 750 , 130 , 150 , 30 );
		bNovaSimulacao.addActionListener( new EventoTelaInicial( this ) );
		bNovaSimulacao.setEnabled( true );
		getContentPane( ).add( bNovaSimulacao );
		
		bRefazer = new JButton( "Refazer Simula��o" );
		bRefazer.setBounds( 750 , 170 , 150 , 30 );
		bRefazer.addActionListener( new EventoTelaInicial( this ) );
		bRefazer.setEnabled( false );
		getContentPane( ).add( bRefazer );

	}
	
	/**
	 *Aqui Instancia os JLabels
	 */
	private void instanciaJLabel( )
	{
		Font fonte = new Font( "Arial" , 1 , 25 );
		
		lProcesso = new JLabel( "Processo em execu��o: "  );
		lProcesso.setFont( fonte );
		lProcesso.setForeground( Color.WHITE );
		lProcesso.setBounds( 50 , 50 , 400 , 30 );
		getContentPane( ).add( lProcesso );
		
		lQuantidade = new JLabel( "Quantidade de processos a serem executados: " );
		lQuantidade.setFont( fonte );
		lQuantidade.setForeground( Color.WHITE );
		lQuantidade.setBounds( 50 , 120 , 600 , 30 );
		getContentPane( ).add( lQuantidade );
		
		lTempo = new JLabel( "Tempo m�dio de espera: "  );
		lTempo.setFont( fonte );
		lTempo.setForeground( Color.WHITE );
		lTempo.setBounds( 50 , 190 , 500 , 30 );
		getContentPane( ).add( lTempo );
		
		
	}

	
	/**
	 *Aqui instancia a barra de progresso
	 */
	private void instanciaJProgressBar( )
	{
		barraAtualizada = new JProgressBar( );

		barraAtualizada.setStringPainted( true ); //Aparecer� a porcentagem realizada do processo
		
		barraAtualizada.setEnabled( false ); //Deixo desabilitada
		
		barraAtualizada.setBounds( 450 , 48 , 250 , 40 );
		barraAtualizada.setMaximum( 20 );
		getContentPane( ).add( barraAtualizada );

	}
	
	/**
	 *Aqui Instancia a JTable
	 */
	private void instanciaJTable( )
	{		
		//Instancia um modelo de tabela
		String colunas[ ] = new String[ ]{ "Processo" , "Tempo de CPU( Segundos )" , "Status" };
		
		modelo = new ModeloTabela( getServico( ).retornaProcessos( ) , colunas );
		
		tabela = new JTable( )
		{
			/**
			 * Vers�o de S�rie
			 */
			private static final long serialVersionUID = 1L;

			/**
			 *Aqui � Responsavel por mostrar a situa��o 
			 */
			public Component prepareRenderer( TableCellRenderer s , int linha , int coluna )
			{
				Component c = super.prepareRenderer(s, linha, coluna);
			
				if( modelo.getValueAt( linha , 2 ).toString( ).equals( "A ser executado" ) )
					c.setBackground( Color.YELLOW );
				else
					if( modelo.getValueAt( linha , 2 ).toString( ).equals( "Em execu��o" ) )
						c.setBackground( new Color( 136 , 205 , 225 ) );
					else
						c.setBackground( new Color( 100 , 225 , 137 ) );
				
				return c;
			}
		
		};
		
		//Os processos a serem executados as linhas ficar�o amarelas
		//O processo em execu��o a linha ficar� azul
		//Os Processos conclu�dos ficar�o com verde
		tabela.setModel( modelo );
					
		//Aqui instancia a barra de rolagem
		JScrollPane barra = new JScrollPane( tabela );

		TitledBorder borda = new TitledBorder( "Processos colocados na fila de execu��o" );
		borda.setTitleColor( Color.GREEN );
		
		barra.setBorder( borda );
		
		barra.setBounds( 50 , 500 , 880 , 150 );
		getContentPane( ).add( barra );
		
	}
	
		
	
}//Fim da classe