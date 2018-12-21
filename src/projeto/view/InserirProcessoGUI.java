/**
 * Classe responsavel pelo formulario do processo a ser inserido
 */
package projeto.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import projeto.view.evento.EventoInserirProcesso;

/**
 * @authors Matheus Ribeiro Barbosa Santos            RA: 201516660   CCO3AN-MCA
 * 			Victor Renato Frutuoso Gama               RA: 201508677   CCO3AN-MCA
 * 			Samuel Alves de Almeida                   RA: 201516313   CCO3AN-MCA
 * 			Michelle Fernandes Januário Fazolare	  RA: 201516916	  CCO3AN-MCA
 * 			Victor de Lacerda Alves Branco			  RA: 201516438	  CCO3AN-MCA
 *
 */
public class InserirProcessoGUI extends JDialog
{

	/**
	 * Versão de Serie
	 */
	private static final long serialVersionUID = 1L;

	//Atributos
	private JLabel lNomeProcesso , lTempoSegundos;
	public JTextField tNomeProcesso ;
	public JSpinner sTempoSegundos;
	public JButton bCadastrar;
	private JPanel centro , sul ;
	private Tela janela;
	
	/**
	 * Construtor não Padrão
	 * @param janela : Tela
	 */
	public InserirProcessoGUI( Tela janela ) 
	{
		//Chama o construtor da super classe
		super( janela , true );
		
		//Pega a referência da janela
		setJanela( janela );
		//Seta o Layout do Container
		getContentPane( ).setLayout( new BorderLayout( ) );
	
		//Instancia Elementos
		instanciaJPanel( );
		instanciaJLabel( );
		instanciaJButon( );
		instanciaJTextField( );
		instanciaJSpinner( );
		
		//Coloca elementos no Container
		colocaNoPainel( );
		
		//Titulo da janela
		setTitle( "Inserir Processo na fila" );
		
		//Define as configurações da janela
		setSize( 620 , 150 );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setLocationRelativeTo( janela );
		setResizable( false );
		setVisible( true );
	}
	
	/**
	 *Set e get de janela
	 */
	public void setJanela( Tela janela )
	{
		this.janela = janela;
	}
	
	public Tela getJanela( )
	{
		return janela;
	}
	
	
	/**
	 *Instancia elementos JPanel
	 */
	private void instanciaJPanel( )
	{
		centro = new JPanel( new GridLayout( 2 , 2 ) );
		centro.setBackground( Color.BLACK );
		getContentPane( ).add( centro , BorderLayout.CENTER );
		
		sul = new JPanel( new FlowLayout( ) );
		sul.setBackground( Color.BLACK );
		getContentPane( ).add( sul , BorderLayout.SOUTH );
	}	
	
	/**
	 *Instancia elementos JLabel
	 */
	private void instanciaJLabel( )
	{
		Font fonte = new Font( "Arial" , 1 , 20 );
			
		lNomeProcesso = new JLabel( "Processo: " );
		lNomeProcesso.setFont( fonte );
		lNomeProcesso.setForeground( Color.WHITE );
		
		lTempoSegundos = new JLabel( "Tempo de CPU (em segundos): " );
		lTempoSegundos.setFont( fonte );
		lTempoSegundos.setForeground( Color.WHITE );
	}
	
	/**
	 *Instancia elementos JTextField
	 */
	private void instanciaJTextField( )
	{
		Font fonte = new Font( "Arial" , 1 , 20 );
		
		tNomeProcesso = new JTextField( 10 );
		tNomeProcesso.setFont( fonte );
	}
	
	/**
	 *Instancia elementos JSpinner
	 */
	private void instanciaJSpinner( )
	{
		Font fonte = new Font( "Arial" , 1 , 20 );
		
		sTempoSegundos = new JSpinner(  );
		
		//Instancia um modelo para o JSpinner
		SpinnerNumberModel modelo = new SpinnerNumberModel( 1 , 1 , Integer.MAX_VALUE , 1 );
		
		//Coloca o modelo no JSpinner
		sTempoSegundos.setModel( modelo );
		sTempoSegundos.setFont( fonte );
	}
	
	/**
	 *Instancia elementos JButton 
	 */
	private void instanciaJButon( )
	{
		bCadastrar = new JButton( "Colocar na fila" );
		bCadastrar.addActionListener( new EventoInserirProcesso( this ) );
		bCadastrar.setBackground( new Color( 34 , 177 , 76 ) );
		bCadastrar.setForeground( Color.WHITE );
		bCadastrar.setFont( new Font( "Arial" , 1 , 20 ) );
		sul.add( bCadastrar );
	}
	
	/**
	 *Coloca elementos no JPanel
	 */
	private void colocaNoPainel( )
	{
		centro.add( lNomeProcesso );
		centro.add( tNomeProcesso );
		centro.add( lTempoSegundos );
		centro.add( sTempoSegundos );
	}

}
