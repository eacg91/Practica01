/**
 * Taller de Programacion Orientada a Objetos
 * @author Edwin Alberto Castañeda García
 */
import java.io.*;
import java.util.Scanner;
public class Archivos {
	String n,tel,dir;
	int b=0;
	Scanner Lectora=new Scanner (System.in);
	public void capturar(){
		System.out.println("Nombre:");
		n=Lectora.next();
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
			System.out.println("no se pudo abrir archivo....");
		}

    }
    public void mostrar(){
    	try{
    		RandomAccessFile bw=new RandomAccessFile("agenda.txt","r");
    		while(bw.getFilePointer()!=bw.length()){
    			n = bw.readUTF();
    			tel = bw.readUTF();
    			dir = bw.readUTF();
    			System.out.println("Nombre:"+n+"\n Telefono:"+tel+"\nDireccion:"+dir);
    		}
    		bw.close();
    	}catch(IOException ioe){
    		System.out.println("NO EXISTE ARCHIVO");
    	}
    }
    public void buscar(){
    	int b=0;
    	System.out.println("\nNOMBRE A BUSCAR: ");
    	String nom=Lectora.next();
    	try{
    		RandomAccessFile bw=new RandomAccessFile("agenda.txt","r");
    		while(bw.getFilePointer()!=bw.length()){
    			n= bw.readUTF();
    			tel=bw.readUTF();
    			dir=bw.readUTF();
    			if(n.equals(nom)==true){
    				b=1;
    				System.out.println("\n Nombre:"+n+"\n Telefono:"+tel+"\n Direccion:"+dir);
    				break;
    			}
    		}
    		if(b==0)
    			System.out.println("Registro no encontrado.....");
    		bw.close();
    	}
    	catch(IOException ioe){}
    }
    public void eliminar(){
    	int band=0;
    	String o="p";
    	System.out.println("Nombre a Buscar: ");
    	String nom=Lectora.next();
    	try{
    		RandomAccessFile bw=new RandomAccessFile("agenda.txt","r");
    		while(bw.getFilePointer()!=bw.length()){
    			n= bw.readUTF();
    			tel= bw.readUTF();
    			dir= bw.readUTF();
    			if(n.equals(nom)){
    				System.out.println("\n Nombre:"+n+"\n Telefono:"+tel+"\n Direccion:"+dir+"\nDESEAS ELIMINAR:(S/N):");
    				o=Lectora.next();
    				band=1;
    				break;
    			}
    		}
    		bw.close();
    	}
    	catch(IOException ioe){}
    	if (o=="s"||o=="S"){
    		try{
    			RandomAccessFile f=new RandomAccessFile("agenda.txt","r");
    			RandomAccessFile tmp=new RandomAccessFile("temp.txt","rw");
    			while(f.getFilePointer()!=f.length()){
    				n= f.readUTF();
    				tel= f.readUTF();
    				dir= f.readUTF();
    				if(!n.equals(nom)){
    					tmp.seek( tmp.length());
    					tmp.writeUTF(n);
    					tmp.writeUTF(tel);
    					tmp.writeUTF(dir);
    				}
    			}
    			tmp.close();
    			f.close();
    		}
    		catch(IOException ioe){}
    		File a,b;
    		a=new File("agenda.txt");
    		b=new File("temp.txt");
    		a.delete();
    		b.renameTo(a);
    	}
    	if(band==0)
    		System.out.println("Registro no encontrado.....");
    }
    public void borrar(){
    	File a=new File("variable.txt");
    	a.delete();
    }
    public void modificar(){
    	int band=0;
    	String o="p";
    	System.out.println("Nombre a Buscar: ");
    	String nom=Lectora.next();
    	try{
    		RandomAccessFile bw=new RandomAccessFile("agenda.txt","r");
    		while(bw.getFilePointer()!=bw.length()){
    			n= bw.readUTF();
    			tel= bw.readUTF();
    			dir= bw.readUTF();
    			if(n.equals(nom)){
    				System.out.println("\n Nombre:"+n+"\n Telefono:"+tel+"\n Direccion:"+dir+"\n Deseas modificar ?:(S/N): ");
    				o=Lectora.next();
    				band=1;
    				break;
    			}
    		}
    		bw.close();
    	}
    	catch(IOException ioe){}
    	if (o=="s"||o=="S"){
    		try{
    			RandomAccessFile f=new RandomAccessFile("agenda.txt","r");
    			RandomAccessFile tmp=new RandomAccessFile("temp.txt","rw");
    			while(f.getFilePointer()!=f.length()){
    				n= f.readUTF();
    				tel= f.readUTF();
    				dir= f.readUTF();
    				if(n.equals(nom)){
    					System.out.println("Nombre");
    					n=Lectora.next();
    					System.out.println("Telefono");
    					tel=Lectora.next();
    					System.out.println("Direccion");
    					dir=Lectora.next();
    				}
    				tmp.seek( tmp.length());
    				tmp.writeUTF(n);
    				tmp.writeUTF(tel);
    				tmp.writeUTF(dir);
    			}
    			tmp.close();
    			f.close();
    		}
    		catch(IOException ioe){}
    		File a,b;
    		a=new File("variable.txt");
    		b=new File("temp.txt");
    		a.delete();
    		b.renameTo(a);
    	}
    	if(band==0)
    		System.out.println("Registro no encontrado.....");
    }
    public void modificar1(){
    	int b=0;
    	System.out.println("NOMBRE A BUSCAR: ");
    	String nom=Lectora.next();
    	try{
    		RandomAccessFile bw=new RandomAccessFile("agenda.txt","rw");
    		while(bw.getFilePointer()!=bw.length()){
    			n= bw.readUTF();
    			tel= bw.readUTF();
    			dir= bw.readUTF();
    			if(n.equals(nom)){
    				b=1;
    				System.out.println("Nombre:"+n+"\n Telefono:"+tel+"\n Direccion:"+dir);
    				System.out.println("\n\n1.- Telefono\n2.- Direccion\n3.- Ninguno\nSelecciona la opcion del campo a modificar:");
    				int o=Lectora.nextInt();
    				if(o==1){
    					System.out.println("Telefono:");
    					tel=Lectora.next();
    					bw.seek(bw.getFilePointer()-12);
    					bw.writeUTF(tel);
    				}
    				if(o==2){
    					System.out.println("Direccion:");
    					dir=Lectora.next();
    					bw.seek(bw.getFilePointer()-4);
    					bw.writeUTF(dir);
    				}
    			}
    		}
    		bw.close();
    		if(b==0)
    			System.out.println("Registro no encontrado.....");
    	}catch(IOException ioe){}
    }
    public static void main(String a[]){
    	Scanner Lectora=new Scanner(System.in);
    	Archivos x=new Archivos();
    	int opcion;
    	do{
    		System.out.println("\n0. Borrar Archivo");
    		System.out.println("\n1. Capturar");
    		System.out.println("\n2. Mostrar");
    		System.out.println("\n3. Buscar");
    		System.out.println("\n4. Modificar Registro");
    		System.out.println("\n5. Modificar saldo/dias");
    		System.out.println("\n6. Eliminar");
    		System.out.println("\n7. Salir\n");
    		opcion=Lectora.nextInt();
    		switch(opcion){
    	    	case 1:
        			x.capturar();
        			break;
	        	case 2:
        			x.mostrar();
        			break;
	        	case 3:
        			x.buscar();
        			break;
	        	case 4:
        			x.modificar();
        			break;
	        	case 5:
        			x.modificar1();
        			break;
	        	case 6:
        			x.eliminar();
        			break;
        	}
        }while(opcion<7);
    }
}
