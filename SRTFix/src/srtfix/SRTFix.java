/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import srtfix.transform.filter.AddOffsetFilter;
import srtfix.transform.filter.Filter;
import srtfix.transform.filter.MoveOrdinalFilter;
import srtfix.transform.io.SRTTransformer;
import srtfix.transform.io.SubTransformer;
import sun.reflect.Reflection;

/**
 *
 * @author docampo
 */
public class SRTFix {
    
    private static final Charset LOCAL_CAHRSET = Charset.forName("latin1");
    private List<SubTransformer> transformers = new LinkedList<SubTransformer>();
    private Map<String, SubTransformer> transformersMap = new HashMap<String, SubTransformer>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SRTFix fixer = new SRTFix();
        
        String fileName = "src/John.Wick.2014.720p.BluRay.x264.YIFY.srt";

        Collection<String> lines = ManejadorArchivosGenerico.leerArchivo(fileName, LOCAL_CAHRSET);
        
        SubTransformer transformer = fixer.getSubTransformerInstance(fileName);
        Filter filter1 = new AddOffsetFilter(-1000);
        
        Filter filterMove = null ;
        try {
            Timestamp newDate = new Timestamp(sdf.parse("1971-01-01 00:01:51,885").getTime());
            filterMove = new MoveOrdinalFilter(4, newDate);
        } catch (ParseException ex) {
            Logger.getLogger(SRTFix.class.getName()).log(Level.SEVERE, null, ex);
        }

        Collection<Subtitle> lista = transformer.parse(lines);
        Collection<String> output = transformer.transform(filterMove.filter(filter1.filter(lista)));
        ManejadorArchivosGenerico.escribirArchivo(fileName+".fix", output, LOCAL_CAHRSET);
    }

    public SRTFix() {
        initTransformers();
    }
    

    private void initTransformers(){
         //cambiar esto por reflection:
        SubTransformer transformer = new SRTTransformer();
        transformers.add(transformer);
        transformersMap.put(transformer.getFileExtension(), transformer);
    }
    
    private SubTransformer getSubTransformerInstance(String filename){
        return transformersMap.get(getFileExtension(filename));
    }
    
    private String getFileExtension(String filename){
        int index = filename.lastIndexOf(".");
        return filename.substring(index);
    }
}
