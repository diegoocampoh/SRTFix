/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import srtfix.transform.filter.AddOffsetFilter;
import srtfix.transform.filter.Filter;
import srtfix.transform.io.SRTTransformer;
import srtfix.transform.io.SubTransformer;

/**
 *
 * @author docampo
 */
public class SRTFix {
    
    private static final Charset LOCAL_CAHRSET = Charset.forName("latin1");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "src/John.Wick.2014.720p.BluRay.x264.YIFY.srt";
        Collection<String> lines = ManejadorArchivosGenerico.leerArchivo(fileName, LOCAL_CAHRSET);
        
        SubTransformer transformer = new SRTTransformer();
        Filter filter1 = new AddOffsetFilter(-1000);
        Collection<Subtitle> lista = transformer.parse(lines);
      
                
        Collection<String> output = transformer.transform(filter1.filter(lista));
    
        ManejadorArchivosGenerico.escribirArchivo(fileName+".fix", output, LOCAL_CAHRSET);
    }
    
    
}
