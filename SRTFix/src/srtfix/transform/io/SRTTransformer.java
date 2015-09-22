/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srtfix.transform.io;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import srtfix.Subtitle;

/**
 *
 * @author docampo
 */
public class SRTTransformer implements SubTransformer {

    private static final String numericRegex = "[0-9]+";
    private static final String NEW_LINE = System.getProperty("line.separator");

    @Override
    public Collection<String> transform(Collection<Subtitle> subs) {
        Collection<String> output = new LinkedList<>();
        for (Subtitle sub : subs) {
            output.add(toSRT(sub));
        }
        return output;
    }

    @Override
    public Collection<Subtitle> parse(Collection<String> subs) {
        ArrayList<String> input = new ArrayList<>();
        input.addAll(subs);
        String[] lines = input.toArray(new String[input.size()]);

        List<Subtitle> list = new ArrayList<Subtitle>(lines.length / 3);
        for (int i = 0; i < lines.length; i++) {
            String firstLine = lines[i];

            //Numeric value
            if (firstLine.matches(numericRegex)) {
                try {
                    Subtitle subtitle = new Subtitle();
                    subtitle.setOrdinal(Integer.valueOf(firstLine));

                    String[] timeData = lines[++i].split("-->");

                    subtitle.setFromTime(stringToTimestamp(timeData[0]));
                    subtitle.setToTime(stringToTimestamp(timeData[1]));

                    String content;
                    while (i < lines.length - 1 && !(content = lines[++i]).isEmpty() && content != null) {
                        if (subtitle.getContents().isEmpty()) {
                            subtitle.setContents(content);
                        } else {
                            subtitle.setContents(subtitle.getContents() + NEW_LINE + content);
                        }
                    }
                    list.add(subtitle);
                } catch (ParseException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    private Timestamp stringToTimestamp(String value) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        return new Timestamp(sdf.parse("1971-01-01 " + value).getTime());
    }

    private String toSRT(Subtitle sub) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,SSS");        
        StringBuilder sb = new StringBuilder();
        
        sb.append(sub.getOrdinal());
        sb.append(NEW_LINE);
        sb.append(sdf.format(sub.getFromTime())).append(" --> ").append(sdf.format(sub.getToTime()));
        sb.append(NEW_LINE);
        sb.append(sub.getContents());
        sb.append(NEW_LINE);
        return sb.toString();
    }

}
