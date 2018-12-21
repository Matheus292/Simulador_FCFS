/**
 *Classe responsavel por alterar o tema da aplicação
 */
package projeto.view;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;;

/**
 * @authors Matheus Ribeiro Barbosa Santos RA: 201516660   CCO3AN-MCA
 *			Victor Renato Frutuoso Gama    RA: 201508677   CCO3AN-MCA
 *		    Samuel Alves de Almeida        RA: 201516313   CCO3AN-MCA
 *		    Michelle Fernandes Januário Fazolare  RA: 201516916  CCO3AN-MCA
 *		    Victor de Lacerda Alves Branco RA: 201516438  CCO3AN-MCA
 */
public class Tema 
{
	//Atributo
	private static LookAndFeelInfo aparencia[ ];

	static
	{
		aparencia = UIManager.getInstalledLookAndFeels( );

	}

	/**
	 *Construtor privado
	 */
	private Tema( )
	{
	}

	/**
	 *Define o tema da Aplicação
	 *@param janela , valor 
	 * 
	 * 0  javax.swing.plaf.metal.MetalLookAndFeel
	 *	1  javax.swing.plaf.nimbus.NimbusLookAndFeel
	 *	2  com.sun.java.swing.plaf.motif.MotifLookAndFeel
	 *	3  com.sun.java.swing.plaf.windows.WindowsLookAndFeel
	 *	4  com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
	 */
	public static void alteraAparencia( Component janela , int valor )
	{

		try
		{
			UIManager.setLookAndFeel( aparencia[ valor ].getClassName( ) );
			SwingUtilities.updateComponentTreeUI( (Component) janela );
		}
		catch( Exception e )
		{			
			JOptionPane.showMessageDialog( janela , "Erro ao carregar aparência" , "Mensagem de erro" , 0 );
		}	
	}

}//Fim da classe