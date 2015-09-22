/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author docampo
 */
public class SRTParser {
    
    private static final String numericRegex = "[0-9]+";
    
    public static Collection<Subtitle> getSubtitles(String[] lines){
        
        List<Subtitle> list = new ArrayList<Subtitle>(lines.length / 3);
        for (int i = 0; i < lines.length; i++) {
            String firstLine = lines[i];
            
            //Numeric value
            if (firstLine.matches(numericRegex)){
                try {
                    Subtitle subtitle = new Subtitle();
                    subtitle.setOrdinal(Integer.valueOf(firstLine));
                    
                    String[] timeData = lines[++i].split("-->");
                    
                    subtitle.setFromTime(stringToTimestamp(timeData[0]));
                    subtitle.setToTime(stringToTimestamp(timeData[1]));
                    
                    
                    String content;
                    while(i < lines.length -1 && !(content = lines[++i]).isEmpty() && content != null){
                         subtitle.setContents(subtitle.getContents()+content);
                    }
                   
                    list.add(subtitle);
                } catch (ParseException ex) {
                    Logger.getLogger(SRTParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;        
    }
    
    
    
    private static Timestamp stringToTimestamp(String value) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        return new Timestamp(sdf.parse("1971-01-01 " + value).getTime());                
    }
    
}
