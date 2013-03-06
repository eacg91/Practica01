/**
 * Taller de Programacion Orientada a Objetos
 * @author Edwin Alberto Castañeda García
 */
import java.io.*;
import java.util.*;
import java.lang.*;
public class Practica01 {
	String retorno;
	int p=0,nLineas;
	Scanner Lectora=new Scanner (System.in);
/*************************/
	public String QuitarComentarios(String linea){
		if(linea.contains(";")){
			int p=linea.indexOf(';');
			if(p>0){
				linea=linea.substring(0,p);
			}else if(p==0)
				linea="";
		}
		return linea;
    }
/*************************/
	public void escribir(String ruta, String cadena){
		File archivo = new File(ruta+".INST");
		try {
			FileWriter escribirArchivo = new FileWriter(archivo, true);
			BufferedWriter buffer = new BufferedWriter(escribirArchivo);
			buffer.write(cadena);
			buffer.newLine();
			buffer.close();
		}
		catch (Exception ex) {
		}
	}
/*************************/
	public void leer(String ruta){
		ArrayList array = new ArrayList();
		String linea,extension;
		linea=retorno="";
		nLineas=p=0;
		extension=ruta.substring(ruta.lastIndexOf('.'),ruta.length());
		File archivo = new File(ruta);
		try {
			FileReader leerArchivo = new FileReader(archivo);
			BufferedReader buffer = new BufferedReader(leerArchivo);
			while ((linea = buffer.readLine()) != null){
				nLineas++;
				if(linea.contains(";")){
					p=linea.indexOf(';');
					if(p>0){
						linea=linea.substring(0,p);
					}else if(p==0)
						linea="";
				}
				retorno+=linea+"\n";
			}
			buffer.close();
			System.out.println("/***************/\n"+retorno+"/***************/");
		}
		catch (Exception ex){
			/*** Solucion generica ***/
			if(extension.equals(".ASM")){
				p=ruta.lastIndexOf('.');
				leer(ruta.replace(extension,".asm"));
			}else
			/*** FIN de Solucion generica ***/
				System.out.println("NO EXISTE ARCHIVO "+ruta);
		}
	}
/*************************/
    public static void main(String a[]){
    	Scanner Lectora=new Scanner(System.in);
    	Practica01 archivo=new Practica01();
    	String ruta,extension=".ASM";
		int opcion;
		do{
			opcion=0;
		    System.out.print("\n Por Favor, escriba ruta con archivo:   ");
		    ruta=Lectora.nextLine();
			if(ruta.contains(extension)|ruta.contains(extension.toLowerCase()) ){
				archivo.leer(ruta);
			}else if(!ruta.contains(".")){
				System.out.println("\n No hay punto \".\" en el nombre, se abrira "+ruta+extension);
				archivo.leer(ruta+=extension);
			}else
				System.out.println("\n ERROR! Solo se admiten archivos "+extension);
			System.out.println(" Desea reutilizar el programa? (0=NO,1=SI):");
			opcion=Lectora.nextInt();
		}while(opcion==1);
    }
}