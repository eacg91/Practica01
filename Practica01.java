/**
 * Taller de Programacion de Sistemas
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
			linea=linea.substring(0,p);
		}
		return linea;
    }
	public String diferenciarTokens(String linea){
		int flag=0;
		StringTokenizer st=new StringTokenizer(linea);
		char pCt;
		String sToken,linea2;
		sToken=linea2="";
		if(nLineas>0)
			linea2+=("\n   "+nLineas);//Cuenta numero de lineas leidas
		while(st.hasMoreTokens()){
			sToken=st.nextToken();//Tomando token de la linea
			pCt=sToken.charAt(0);//Primer caracter del token
			if(flag==0)
				linea2+="\t";
			else //Si hay Bandera, quitarla
				flag=0;
			if(Character.isLetter(pCt) && sToken.length()<=8){
				if(pCt==linea.charAt(0))
					//es Etiqueta
				else if(sToken.length()<=5 && pCt!=linea.charAt(0)
				         && sToken.indexOf('.')==sToken.lastIndexOf('.') ){
					linea2+="\t\t ";//es CODOP
				}
				flag=1;
			}else{
				linea2+="\t\t ";//es Operando
			}
			linea2+=sToken;
			//Aqui mandar token al arbol... instruccion pendiente
		};
		return linea2;
    }
/*************************/
	public void escribir(String ruta){
		File archivo = new File(ruta+".INST");
		try {
			FileWriter escribirArchivo = new FileWriter(archivo, true);
			BufferedWriter buffer = new BufferedWriter(escribirArchivo);
			buffer.write(retorno);
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
		File archivo = new File(ruta);
		try {
			FileReader leerArchivo = new FileReader(archivo);
			BufferedReader buffer = new BufferedReader(leerArchivo);
			retorno=("#linea\t Etiqueta\t CODOP\t\t Operando");
			while ((linea = buffer.readLine()) != null){
				linea=QuitarComentarios(linea); //Quitar comentarios
				linea=diferenciarTokens(linea.toString());
				retorno+=linea;
				nLineas++;
			}
			buffer.close();
			System.out.print(retorno);
			escribir(ruta);
		}
		catch (Exception ex){
			extension=ruta.substring(ruta.lastIndexOf('.'),ruta.length());
			if(extension.equals(".ASM")){
				p=ruta.lastIndexOf('.');
				leer(ruta.replace(extension,".asm"));
			}else
				System.out.println("NO EXISTE ARCHIVO "+ruta);
		}
	}
/*************************/
    public static void main(String a[]){
    	Scanner Lectora=new Scanner(System.in);
    	Practica01 archivo=new Practica01();
    	String ruta,extension=".asm";
		int opcion;
		do{
			opcion=0;
		    System.out.print("\n Por Favor, escriba ruta con archivo:   ");
		    ruta=Lectora.next();
			if(ruta.contains(extension.toUpperCase())|ruta.contains(extension.toLowerCase()) ){
				archivo.leer(ruta);
			}else if(!ruta.contains(".")){
				System.out.println("\n No hay punto \".\" en el nombre, se abrira "+ruta+extension);
				archivo.leer(ruta+=extension);
			}else
				System.out.println("\n ERROR! Solo se admiten archivos "+extension);
			System.out.print("\n Desea reutilizar el programa? (0=NO,1=SI): ");
			opcion=Lectora.nextInt();
		}while(opcion==1);
    }
}