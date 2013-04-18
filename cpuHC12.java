/**2013
 * Taller de Programacion de Sistemas
 * @author Edwin Alberto Castañeda García
 */
import java.io.*;
import java.util.*;
import java.lang.*;
public class cpuHC12 {
	static final int SI=1,NO=0;
	String original,retorno,log;
	int nLineas,end;
	Scanner Lectora=new Scanner (System.in);
	Stack<String> etiquetas=new Stack<String>();
	Stack<String> codops=new Stack<String>();
	Stack<String> operandos=new Stack<String>();
	Stack<String> modosdir=new Stack<String>();
/*************************/
	x
/*************************/
    public static void main(String a[]){
    	Scanner Lectora=new Scanner(System.in);
    	cpuHC12 archivo=new cpuHC12();
    	String ruta,extension=".asm";
		int opcion,existe;
		do{
			opcion=existe=0;
			archivo.codops.clear();//limpia lista de codops
			archivo.etiquetas.clear();//limpia lista de etiquetas
			archivo.operandos.clear();//limpia lista de operandos
		    System.out.print("\n Por Favor, escriba ruta con archivo:   ");
		    ruta=Lectora.next();
			if(!ruta.contains(".")){
				System.out.println("\n No hay punto \".\" en el nombre, se abrira "+ruta+extension);
				ruta+=extension;
			}
			if(ruta.contains(extension.toUpperCase())|ruta.contains(extension.toLowerCase()) ){
				ruta=archivo.leer(ruta);
				existe=archivo.mostrar(ruta);
				if(existe==1){
					archivo.escribir(ruta.substring(0,ruta.lastIndexOf('.'))+".INST",archivo.retorno);
					archivo.escribir("log.log",archivo.log);
				}
			}else
				System.out.println("\n ERROR! Solo se admiten archivos "+extension);
			System.out.print("\n Desea reutilizar el programa? (0=NO,1=SI): ");
			opcion=Lectora.nextInt();
		}while(opcion==1);
    }
}