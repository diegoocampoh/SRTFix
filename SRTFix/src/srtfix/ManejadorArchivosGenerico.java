package srtfix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

public class ManejadorArchivosGenerico {

    /**
     * @param nombreCompletoArchivo
     * @param listaLineasArchivo lista con las lineas del archivo
     * @throws IOException
     */
    public static void escribirArchivo(String nombreCompletoArchivo, Collection<String> listaLineasArchivo, Charset charset) {       
        Charset localCharset = charset == null ? Charset.defaultCharset() : charset;
        try {            
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(nombreCompletoArchivo), localCharset
            ));            
            for (String string : listaLineasArchivo) {
                out.write(string);
                out.newLine();
            }
            out.close();           
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo " + nombreCompletoArchivo);
            e.printStackTrace();
        }
    }

    public static Collection<String> leerArchivo(String nombreCompletoArchivo, Charset charset) {      
        Charset localCharset = charset == null ? Charset.defaultCharset() : charset;
        ArrayList<String> listaLineasArchivo = new ArrayList<String>();
        try {      
            BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(nombreCompletoArchivo), localCharset
            ));  
            
            String lineaActual;
            while ( (lineaActual = in.readLine()) != null) {
                listaLineasArchivo.add(lineaActual);                
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo " + nombreCompletoArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + nombreCompletoArchivo);
            e.printStackTrace();
        }
        System.out.println("Archivo leido satisfactoriamente");

        return listaLineasArchivo;
    }
}
