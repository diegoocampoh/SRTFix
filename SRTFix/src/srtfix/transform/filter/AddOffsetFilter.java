/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srtfix.transform.filter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import srtfix.Subtitle;

/**
 * @author docampo
 */
public class AddOffsetFilter implements Filter {

    private final long milisecs;

    public AddOffsetFilter(long milisecs) {
        this.milisecs = milisecs;
    }

    private Collection<Subtitle> addOffset(Collection<Subtitle> subtitles) {
        List<Subtitle> result = new ArrayList<>();
        for (Subtitle sub : subtitles) {
            Date newFrom = sub.getFromTime();
            Date newTo = sub.getToTime();

            newFrom = addOffset(newFrom, this.milisecs);
            newTo = addOffset(newTo, this.milisecs);

            result.add(new Subtitle(sub.getOrdinal(), new Timestamp(newFrom.getTime()), new Timestamp(newTo.getTime()),
                    sub.getContents()));
        }
        return result;
    }

    private Date addOffset(Date oldDate, long offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        long currentMilis = cal.getTimeInMillis();
        currentMilis += offset;
        cal.setTimeInMillis(currentMilis);
        return cal.getTime();
    }

    @Override
    public Collection<Subtitle> filter(Collection<Subtitle> subs) {
        return addOffset(subs);
    }

}
