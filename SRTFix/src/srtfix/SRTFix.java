/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author docampo
 */
public class SRTFix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "src/John.Wick.2014.720p.BluRay.x264.YIFY.srt";
        String[] lines = ManejadorArchivosGenerico.leerArchivo(fileName);
        Collection<Subtitle> lista = SRTParser.getSubtitles(lines);
      
        
        Collection<Subtitle> lista2 = addOffset(lista, -1000);
        
     
        String[] output = toSRTFormat(lista2);
        for (String s : output){
            System.out.println(s);
        }
        
        ManejadorArchivosGenerico.escribirArchivo(fileName+".fix", output);
    }
    
    
    private static Collection<Subtitle> addOffset(Collection<Subtitle> subtitles, int milisecs ){
        List<Subtitle> result = new ArrayList<>();
        for(Subtitle sub : subtitles){
            Date newFrom = sub.getFromTime();
            Date newTo = sub.getToTime();
            
            newFrom = addOffset(newFrom,Calendar.MILLISECOND, milisecs);
            newTo = addOffset(newTo,Calendar.MILLISECOND, milisecs);
           
            Subtitle newSub = new Subtitle();
            newSub.setContents(sub.getContents());
            newSub.setFromTime(new Timestamp(newFrom.getTime()));
            newSub.setToTime(new Timestamp(newTo.getTime()));
            newSub.setOrdinal(sub.getOrdinal());
            result.add(newSub);
        }
        
        return result;
    }
    
    private static Date addOffset(Date oldDate, int calendarType, int offset){
        
            Calendar cal = Calendar.getInstance();
            cal.setTime(oldDate);
            cal.add(calendarType, offset);
            return cal.getTime();
    }
    
    
    private static String[] toSRTFormat(Collection<Subtitle> subs){
        ArrayList<String> output = new ArrayList<>(subs.size()*4);
        
        for (Subtitle sub : subs) {
            output.add(sub.toSRT());
        }
               
        return output.toArray(new String[output.size()]);
    }
    
}
