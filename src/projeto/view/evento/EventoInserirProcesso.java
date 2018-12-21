/**
 * Classe Responsavel por tratar eventos dos componentes da janela( InserirProcessoGUI )
 */
package projeto.view.evento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import projeto.model.Processo;
import projeto.service.Status;
import projeto.view.InserirProcessoGUI;
import projeto.view.ModeloTabela;

/**
 * @authors Matheus Ribeiro Barbosa Santos RA: 201516660   CCO3AN-MCA
 *			Victor Renato Frutuoso Gama    RA: 201508677   CCO3AN-MCA
 *			Samuel Alves de Almeida        RA: 201516313   CCO3AN-MCA
 *			Michelle Fernandes Januário Fazolare  RA: 201516916  CCO3AN-MCA
 *			Victor de Lacerda Alves Branco RA: 201516438  CCO3AN-MCA
 *
 */
public class EventoInserirProcesso implements ActionListener
{
	//Atributo
	private InserirProcessoGUI window;

	/**
	 *Construtor não Padrão 
	 *@param window : InserirProcessoGUI
	 */
	public EventoInserirProcesso( InserirProcessoGUI window )
	{
		this.window = window;
	}

	/**
	 *Responsável por tratar eventos dos botões
	 *@param evento : ActionEvent
	 */
	@Override
	public void actionPerformed( ActionEvent evento )
	{
		if( evento.getSource( ) == window.bCadastrar )
		{		
			//If else responsavel por verificar se os campos estão preenchidos
			if( window.tNomeProcesso.getText( ).equals( "" ) )
			{
				JOptionPane.showMessageDialog( window , "Não deixe campo vazio !" , "Aviso" , 2 );
			}//Fim do if
			else
			{
				String nomeProcesso = window.tNomeProcesso.getText( );

				double tempoCpu = Double.parseDouble( window.sTempoSegundos.getValue( ).toString( ) ); //Tentara fazer Casting
				//se não consguir é por que colocaram caracteres inválidos

				//Instancia o Processo
				Processo p = new Processo( );
				p.setProcesso( nomeProcesso );
				p.setTempoCpu( tempoCpu );
				p.setStatus( Status.A_SER_EXECUTADO );

				//Adiciona processo na fila
				window.getJanela( ).getServico( ).adicionar( p );

				//Colunas
				String colunas[ ] = new String[ ]{ "Processo" , "Tempo de CPU( Segundos )" , "Status" };

				//Atualiza Tabela
				window.getJanela( ).tabela.setModel( new ModeloTabela( window.getJanela( ).getServico( ).retornaProcessos( ) , colunas  ) );

				JOptionPane.showMessageDialog( window , "Processo colocado na fila !" , "Mensagem" , 1 );


				//Aqui pede a confirmação do usuário
				int i = JOptionPane.showConfirmDialog
						( window , "Deseja colocar mais processos na fila ?" , "Confirmação do usuário" , JOptionPane.YES_NO_OPTION , 3 );

				//Este if else tem como responsabilidade de continuar colando os processos na fila ou parara de colocar
				if( i == 0 )
				{
					//Limpa os campos
					window.tNomeProcesso.setText( "" );
					window.sTempoSegundos.setValue( 1 );
				}
				else
				{
					window.dispose( );//Dispensa a janela
				}

			}//fim do else


		}//Fim do if

	}//Fim do método

}//Fim da Classe