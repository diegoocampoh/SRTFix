/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix;

import java.util.ArrayList;
import java.util.Collection;
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
        String[] lines = ManejadorArchivosGenerico.leerArchivo("src/test.srt");
        Collection<Subtitle> lista = SRTParser.getSubtitles(lines);
        for (Subtitle subtitle : lista) {
            System.out.println(subtitle.toString());
        }
    }
    
    
    //private Collection<Subtitle> addOffset(Collection<Subtitle>, )
    
}
