/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srtfix;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author docampo
 */
public class Subtitle {

    private int ordinal;
    private Timestamp fromTime;
    private Timestamp toTime;
    private String contents = "";

    public Subtitle(int ordinal, Timestamp fromTime, Timestamp toTime, String contents) {
        this.ordinal = ordinal;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.contents = contents;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public Timestamp getFromTime() {
        return fromTime;
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    public Timestamp getToTime() {
        return toTime;
    }

    public void setToTime(Timestamp toTime) {
        this.toTime = toTime;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Subtitle{" + "ordinal=" + ordinal + ", fromTime=" + fromTime + ", toTime=" + toTime + ", contents=" + contents + '}';
    }


}
