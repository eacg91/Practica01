/**2013
 * Taller de Programacion de Sistemas
 * @author Edwin Alberto Castañeda García
 */
import java.io.*;
import java.util.*;
import java.lang.*;
public class cpuHC12 {
	static final int SI=1,NO=0;
	String linea2,original,retorno,log;
	int nLineas,end,tieneCodop,tieneEtiqueta;
	Scanner Lectora=new Scanner (System.in);
	Stack<String> lineaxlinea=new Stack<String>();
/*************************/
	public int mostrar(String ruta){
		File archivo = new File(ruta);
		if(archivo.exists()){
			System.out.println("\n\t---> Original de "+ruta+"\n"+original);
			System.out.println("\n\t---> Retorno en .INST \n"+retorno);
			if(log.length()>0)
				System.out.println("\n\t---> ERRORES! \n"+log);
			else
				System.out.println(log="\n\t Completado Sin Errores");
			return SI;
		}
		return NO;
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
	public String retornoINST(){
		int j;
		retorno=("#linea Etiqueta  CODOP  Operando");
		for(j=0;j<nLineas;j++)
			retorno+=(lineaxlinea.get(j).toString());
		return retorno;
	}
/*************************/
	public void retornoLOG(){
		if(end==NO)
			log+="\n No finaliza con End";
	}
/*************************/
	public void escribir(String nuevaRuta,String contenido){
		File archivo = new File(nuevaRuta);
		if(archivo.exists()){
			if(archivo.delete()){
			    System.out.println("\n Reescribiendo "+nuevaRuta+"...");
			}else
			    System.out.println("\n Error al reescribir "+nuevaRuta+"...");
		}else
			System.out.println("\n Creando "+nuevaRuta+"...");
		try {
			FileWriter escribirArchivo = new FileWriter(archivo, true);
			BufferedWriter buffer = new BufferedWriter(escribirArchivo);
			buffer.write(contenido);
			buffer.newLine();
			buffer.close();
		}
		catch (Exception ex) {}
	}
/*************************/
	public int esEtiqueta(String sToken){
		char c;
		for(int i=1;i<sToken.length();i++){
			c=sToken.charAt(i);
			if(Character.isLetterOrDigit(c)|c=='_'){
				tieneEtiqueta=SI;
			}else{
				log+="\n Caracter '"+c+"' no valido en linea "+nLineas;
				return NO;
			}
		}
		if(tieneEtiqueta==SI){
			linea2+="\t"+sToken;
		}else if(esCODOP(sToken)==NO)
			log+="\n Token "+sToken+" Nesdo de linea "+nLineas;
		return tieneEtiqueta;
	}
/*************************/
	public int esCODOP(String sToken){
		sToken=sToken.toUpperCase();
		if(sToken.length()<=5 && sToken.indexOf('.')==sToken.lastIndexOf('.') ){
			if(sToken.contains("END"))
				end=SI;
			for(int i=16-linea2.length();i>0;i--)
				linea2+=" ";
			linea2+=sToken.toUpperCase();
			return SI;
		}
		if(esOperando(sToken)==NO)
			log+="\n Token "+sToken+" NO reconocido de linea "+nLineas;
		return NO;
	}
/*************************/
	public int esOperando(String sToken){
		for(int i=23-linea2.length();i>0;i--)
			linea2+=" ";
		linea2+=sToken;
		if(tieneCodop==NO){
			log+="\n No tiene CODOP en linea "+nLineas;
		}
		return NO;
	}
	/*************************/
	public String diferenciarTokens(String linea){
		String sToken;
		char pCt,pcl=linea.charAt(0);;
		StringTokenizer st=new StringTokenizer(linea);
		tieneCodop=tieneEtiqueta=NO;
		linea2=("\n "+nLineas);//Cuenta numero de lineas leidas
		while(st.hasMoreTokens()){
			sToken=st.nextToken();//Tomando token de la linea
			pCt=sToken.charAt(0);//Primer caracter del token
			if(Character.isLetter(pCt) && sToken.length()<=8 && tieneCodop==NO){
				if(tieneEtiqueta==NO && pcl!=' '){
					tieneEtiqueta=esEtiqueta(sToken);
				}else if(tieneCodop==NO){
					tieneCodop=esCODOP(sToken);
				}else
					log+="\n Token "+sToken+" NO reconocido de linea "+nLineas;
			}else if(end==NO && tieneCodop==SI){
				esOperando(sToken);
			}
		};
		return linea2;
    }
/*************************/
	public String leer(String ruta){
		String linea,extension;
		int p=ruta.lastIndexOf('.');
		linea=original=retorno=log="";
		lineaxlinea.clear();
		extension=ruta.substring(p,ruta.length());
		nLineas=end=0;
		File archivo = new File(ruta);
		if(!archivo.exists()){
			if(ruta.contains(extension.toUpperCase()) ){
				ruta=ruta.substring(0,p)+extension.toLowerCase();
				archivo = new File(ruta);
			}else if(ruta.contains(ruta.toLowerCase()) ){
				ruta=ruta.substring(0,p)+extension.toUpperCase();
				archivo = new File(ruta);
			}
		}
		try {
			FileReader leerArchivo = new FileReader(archivo);
			BufferedReader buffer = new BufferedReader(leerArchivo);
			while ((linea = buffer.readLine()) != null){
				original+=linea+"\n";
				linea=QuitarComentarios(linea); //Quitar comentarios
				if(linea.length()>0 && end==NO){
					linea=diferenciarTokens(linea.toString());
					lineaxlinea.add(linea);
					nLineas++;
				}
			}
			buffer.close();
			retornoINST();
			retornoLOG();
		}
		catch (Exception ex){
			System.out.println("NO EXISTE ARCHIVO "+ruta);
		}
		return ruta;
	}
/*************************/
    public static void main(String a[]){
    	Scanner Lectora=new Scanner(System.in);
		cpuHC12 archivo=new cpuHC12();
    	String ruta,extension=".asm";
		int opcion,existe;
		do{
			opcion=existe=0;
		    System.out.print("\n Por Favor, escriba ruta con archivo:   ");
		    ruta=Lectora.next();
			if(!ruta.contains(".")){
				System.out.println("\n No hay punto \".\" en el nombre, se abrira "+ruta+extension);
				ruta+=extension;
			}
			if(ruta.contains(extension.toUpperCase())|ruta.contains(extension.toLowerCase()) ){
				ruta=archivo.leer(ruta);
				existe=archivo.mostrar(ruta);
				if(existe==SI){
					archivo.escribir(ruta.substring(0,ruta.lastIndexOf('.'))+".INST",archivo.retorno);
					archivo.escribir("errores.log",archivo.log);
				}
			}else
				System.out.println("\n ERROR! Solo se admiten archivos "+extension);

			System.out.print("\n Desea reutilizar el programa? (0=NO,1=SI): ");
			opcion=Lectora.nextInt();
		}while(opcion==SI);
    }
}