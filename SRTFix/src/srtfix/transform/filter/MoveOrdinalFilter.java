/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix.transform.filter;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import srtfix.Subtitle;

/**
 *
 * @author docampo
 */
public class MoveOrdinalFilter implements Filter{

    private int ordinal;
    private Timestamp newTime;

    public MoveOrdinalFilter(int ordinal, Timestamp newTime) {
        this.ordinal = ordinal;
        this.newTime = newTime;
    }
    
    
    
    @Override
    public Collection<Subtitle> filter(Collection<Subtitle> subs) {
        HashMap<Integer, Subtitle> mapa = new HashMap<>();
        for (Subtitle sub : subs){           
            mapa.put(sub.getOrdinal(), sub);
        }
        
        Subtitle toChange = mapa.get(this.ordinal);
        long offset = newTime.getTime() - toChange.getFromTime().getTime();
        
        Filter addFilter = new AddOffsetFilter(offset);
        return addFilter.filter(subs);
    }
    
    
    
}
