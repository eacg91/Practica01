/**
 * Taller de Programacion Orientada a Objetos
 * @author Edwin Alberto Castañeda García
 */
import java.io.*;
import java.util.*;
public class Practica01 {
	String n,tel,dir,retorno;
	int p=0,nLineas;
	Scanner Lectora=new Scanner (System.in);
/*************************/
	public void capturar(){
		System.out.println("Nombre:");
		n=Lectora.nextLine();
		System.out.println("Telefono:");
		tel=Lectora.next();
		System.out.println("Direccion:");
		dir=Lectora.next();
		try{
			RandomAccessFile bw=new RandomAccessFile("agenda.txt","rw");
			bw.seek( bw.length());
			bw.writeUTF(n);
			bw.writeUTF(tel);
			bw.writeUTF(dir);
			bw.close();
		}
		catch(IOException ioe){
			System.out.println("No se pudo abrir archivo....");
		}

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
		String linea,retorno;
		linea=retorno="";
		nLineas=p=0;
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
		}
		catch (Exception ex){
			if(ruta.contains(".ASM")){
				p=ruta.lastIndexOf('.');
				leer(ruta.substring(0,p)+".asm");
			}else
				System.out.println("NO EXISTE ARCHIVO "+ruta);
		}
		System.out.println("/***************/\n"+retorno+"/***************/");
	}
/*************************/
    public static void main(String a[]){
    	Scanner Lectora=new Scanner(System.in);
    	Practica01 archivo=new Practica01();
    	String ruta,extension=".ASM";
		int opcion;
		do{
			opcion=0;
		    System.out.println("\n Por Favor, escriba ruta con archivo:");
		    ruta=Lectora.next();
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
