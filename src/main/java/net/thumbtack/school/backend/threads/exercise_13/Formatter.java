package net.thumbtack.school.backend.threads.exercise_13;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {

    private SimpleDateFormat dateFormat;

    Formatter(SimpleDateFormat dateFormat){
        this.dateFormat = dateFormat;
    }

    public String format(Date date){
       return dateFormat.format(date);
    }
}
