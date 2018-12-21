/**
 * Classe Responsavel por manipular os processos
 */
package projeto.service;

import java.util.ArrayList;
import java.util.List;

import projeto.model.Processo;

/**
 * @authors Matheus Ribeiro Barbosa Santos            RA: 201516660   CCO3AN-MCA
 * 			Victor Renato Frutuoso Gama               RA: 201508677   CCO3AN-MCA
 * 			Samuel Alves de Almeida                   RA: 201516313   CCO3AN-MCA
 * 			Michelle Fernandes Januário Fazolare	  RA: 201516916	  CCO3AN-MCA
 * 			Victor de Lacerda Alves Branco			  RA: 201516438	  CCO3AN-MCA
 *
 */

public class ProcessoService 
{

	//Atributo
	private List< Processo > processos  ;
	
	
	/**
	 *Construtor Padrão 
	 */
	public ProcessoService( )
	{
		processos = new ArrayList< >( );
	}
	
	/**
	 *Responsavel por adicionar um processo
	 *@param processo : Processo
	 */
	public void adicionar( Processo processo )
	{
		processos.add( processo );
	}
	
	/**
	 *Responsavel por retornar os Processos
	 */
	public List< Processo > retornaProcessos( )
	{
		return processos;
	}

	/**
	 *Método responsavel por alterar o status do processo
	 */
	public void alteraStatus( )
	{
		for( Processo p : processos )
			p.setStatus( Status.A_SER_EXECUTADO );
	}
	 
	 /**
	  *Método que limpa a fila
	  */
	 public void limparFila( )
	 {
		 processos.clear( );
	 }
	
}
