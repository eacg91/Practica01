/**2013
 * Taller de Programacion de Sistemas
 * @author Edwin Alberto Castañeda García
 */
import java.io.*;
import java.util.*;
import java.lang.*;
public class Practica01 {
	String retorno="",original="",log="";
	int p=0,nLineas=0;
	Scanner Lectora=new Scanner (System.in);
/*************************/
	public void escribirlog(/*String ruta*/){
		//int p=ruta.lastIndexOf('.');
		//File revisando = new File(ruta.substring(0,p)+".INST");
		//Lo anterior es por lo pronto inecesario
		File resultado = new File("log.log");
		try {
			FileWriter escribirArchivo = new FileWriter(resultado, true);
			BufferedWriter buffer = new BufferedWriter(escribirArchivo);
			buffer.write(log);
			buffer.newLine();
			buffer.close();
		}
		catch (Exception ex) {
		}
	}
/*************************/
	public String QuitarComentarios(String linea){
		if(linea.contains(";")){
			int p=linea.indexOf(';');
			linea=linea.substring(0,p);
		}
		return linea;
    }
/*************************/
	public String diferenciarTokens(String linea){
		int flag=0,tieneCodop=0;
		StringTokenizer st=new StringTokenizer(linea);
		char pCt;
		String sToken,linea2;
		sToken=linea2="";
		linea2+=("\n   "+nLineas);//Cuenta numero de lineas leidas
		while(st.hasMoreTokens()){
			sToken=st.nextToken();//Tomando token de la linea
			pCt=sToken.charAt(0);//Primer caracter del token
			if(flag==0)
				linea2+="\t";
			else //Si hay Bandera, quitarla
				flag=0;
			if(Character.isLetter(pCt) && sToken.length()<=8){
				if(pCt==linea.charAt(0)){
					//es Etiqueta
				}else if(sToken.length()<=5 && pCt!=linea.charAt(0) && sToken.indexOf('.')==sToken.lastIndexOf('.') ){
					//es CODOP
					linea2+="\t\t ";
					tieneCodop=1;
				}else{
					log+="\n Frase No reconocida en linea "+nLineas;
				}
				flag=1;
			}else{
				//es Operando
				linea2+="\t\t ";
			}
			linea2+=sToken;
			//Aqui mandar token al arbol... instruccion pendiente
		};
		if(tieneCodop==0)
			log+="\n No tiene CODOP en linea "+nLineas;
		return linea2;
    }
/*************************/
	public void escribir(String ruta){
		int p=ruta.lastIndexOf('.');
		String nuevaRuta=ruta.substring(0,p)+".INST";
		File archivo = new File(nuevaRuta);
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
		linea=retorno=original="";
		nLineas=p=0;
		File archivo = new File(ruta);
		try {
			FileReader leerArchivo = new FileReader(archivo);
			BufferedReader buffer = new BufferedReader(leerArchivo);
			retorno=("#linea\tEtiqueta\tOPeration CODe\t\tOperando");
			while ((linea = buffer.readLine()) != null){
				original+=linea+"\n";
				linea=QuitarComentarios(linea); //Quitar comentarios
				if(linea.length()>0){
					nLineas++;
					linea=diferenciarTokens(linea.toString());
					retorno+=linea;
				}
			}
			/***** Ultimas revisiones de errores *****/
			if(retorno.lastIndexOf("END")==0)
			   log+="\n No finaliza con END";
			/***** *****/
			buffer.close();
			System.out.println("\n\t---> Original de "+ruta+"\n"+original);
			System.out.println("\n\t---> Retorno en .INST \n"+retorno);
			escribir(ruta);
			escribirlog();
			if(log.length()>0)
				System.out.println("\n\t---> ERRORES! \n"+log);
			else
				System.out.println("\n\t Completado Sin Errores \n");
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