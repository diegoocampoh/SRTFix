/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix.transform.io;

import java.text.SimpleDateFormat;
import java.util.Collection;
import srtfix.Subtitle;

/**
 *
 * @author docampo
 */
public interface SubTransformer {
    
    
    public Collection<String> transform(Collection<Subtitle> subs);
    
    public Collection<Subtitle> parse(Collection<String> subs);
}
