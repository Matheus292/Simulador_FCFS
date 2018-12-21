/**
 *Classe Responsavel por visualizar a execução dos processos 
 */
package projeto.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @authors Matheus Ribeiro Barbosa Santos            RA: 201516660   CCO3AN-MCA
 * 			Victor Renato Frutuoso Gama               RA: 201508677   CCO3AN-MCA
 * 			Samuel Alves de Almeida                   RA: 201516313   CCO3AN-MCA
 * 			Michelle Fernandes Januário Fazolare	  RA: 201516916	  CCO3AN-MCA
 * 			Victor de Lacerda Alves Branco			  RA: 201516438	  CCO3AN-MCA
 *
 */
public class PainelCentral extends JPanel
{

	//Atributos
	private List< String > processo ;
	private List< Integer > tempoAtual ;
	private List< Boolean > trocar;
	private boolean poeNome = true;
	private JLabel rotulo;

	/**
	 * Versão de Série
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *Construtor 
	 */
	public PainelCentral( )
	{
		processo = new ArrayList<  >( );
		tempoAtual = new ArrayList<  >( );
		trocar = new ArrayList< >( );
	}

	/**
	 *Método responsavel por colocar no mapa de execução
	 *@param atual : int , nome : String 
	 */
	public void draw( int atual , String  nome , boolean troca )
	{
		if( processo.size( ) == 0 )
			processo.add( nome );
		else
			if( ! processo.get( processo.size( ) - 1 ).equals( nome ) )
				processo.add( nome );

		tempoAtual.add( atual );
		trocar.add( troca );
	}

	public void clear( )
	{
		tempoAtual.clear( );
		processo.clear( );
		trocar.clear( );

		repaint( ); //Repinta a janela
	}

	/**
	 *Método que desenha a imagem
	 @param g : Graphics
	 */
	public void paintComponent( Graphics g )
	{
		//Chama o Construtor da superclasse 
		super.paintComponent( g );        

		String texto = " ";

		if( rotulo != null )
			this.remove( rotulo );

		rotulo = new JLabel( );
		add( rotulo );
		//Define a cor de fundo do painel
		this.setBackground( Color.DARK_GRAY );

		int inicio = 20 , indice = 0 , indiceProcesso = 0;
		
		//Este try catch é uma gambiarra para que não de erro no sistema
		try
		{
		
			//Responsavel pela animação do painel central
			for( int segundo : tempoAtual )
			{
				if( indice == 0 )
				{
					g.setColor( Color.GREEN );
					g.fillOval( inicio , 97 , 7 , 7 );	

					g.setFont( new Font( "Arial" , 1 , 16 ) );

					g.setColor( Color.WHITE );
					g.drawString( "0" , inicio , 150 );

					g.drawString( processo.get( indice ), inicio + 15 , 90 );

					poeNome = false;
					inicio += 7;
					indice ++;
				}
				else
				{
					if( trocar.get( indice ) ) 
					{
						g.setColor( Color.ORANGE );
						g.drawLine( inicio , 100 , inicio + 20 , 100 );
						inicio += 20;
						indice ++ ;

						if( poeNome )
						{
							indiceProcesso ++;
							g.setColor( Color.WHITE );
							g.drawString( processo.get( indiceProcesso), inicio - 5 , 90 );

							poeNome = false;
						}
					}
					else
					{
						g.setColor( Color.GREEN );
						g.fillOval( inicio , 97 , 7 , 7 );	

						g.setFont( new Font( "Arial" , 1 , 16 ) );

						g.setColor( Color.WHITE );
						g.drawString( "" + segundo , inicio , 150 );

						inicio += 7;
						indice++;
						poeNome = true;
					}

					//Faz com que funcione a barra de rolagem
						texto += "       ";
						rotulo.setText( texto );
						add( rotulo );

				}
			}			
			g.dispose( ); //Para não ter falhas na proxima atualização da linha do tempo
			
		}
		catch( Exception e )
		{
		}		
		
	}//Fim do metodo
}//Fim da classe