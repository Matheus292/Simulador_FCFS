/**
 * Classe Responsável por modelar os processos da aplicação
 */
package projeto.model;

/**
 * @authors Matheus Ribeiro Barbosa Santos            RA: 201516660   CCO3AN-MCA
 * 			Victor Renato Frutuoso Gama               RA: 201508677   CCO3AN-MCA
 * 			Samuel Alves de Almeida                   RA: 201516313   CCO3AN-MCA
 * 			Michelle Fernandes Januário Fazolare	  RA: 201516916	  CCO3AN-MCA
 * 			Victor de Lacerda Alves Branco			  RA: 201516438	  CCO3AN-MCA
 *
 */
public class Processo 
{
	//Atributos
	private String processo , status ;
	private double tempoCpu;
	//private int status;
	
	/**
	 * Construtor Padrão
	 */
	public Processo( )
	{
	
	}
	
	/**
	 *******************************Métodos gets e sets dos atributos ****************************************************	
	 **/
	public void setProcesso( String processo )
	{
		this.processo = processo;
	}
	
	public String getProcesso( )
	{
		return processo;
	}
	
	public void setTempoCpu( double tempoCpu )
	{
		this.tempoCpu = ( tempoCpu < 0.0 ? 0.0 : tempoCpu );
	}
	
	public double getTempoCpu( )
	{
		return tempoCpu;
	}
	
	public void setStatus( String status )
	{
		this.status = status;
	}
	
	public String getStatus( )
	{
		return status;
	}
	/***********************************************************************************************************************/
	
}//Fim da Classe