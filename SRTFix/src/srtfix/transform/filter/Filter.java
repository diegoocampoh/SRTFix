/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix.transform.filter;

import java.util.Collection;
import srtfix.Subtitle;

/**
 *
 * @author docampo
 */
public interface Filter {
    
    public Collection<Subtitle> filter (Collection<Subtitle> subs);
}
