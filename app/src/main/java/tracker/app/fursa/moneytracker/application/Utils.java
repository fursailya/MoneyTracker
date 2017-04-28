package tracker.app.fursa.moneytracker.application;

import java.text.SimpleDateFormat;

/**
 * Created by Ilya Fursa on 24.04.2017.
 */

public class Utils {

    public static String getDateAndTime(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH.mm");
        return dateFormat.format(date);
    }

//    public static String getTime(long time) {
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
//        return timeFormat.format(time);
//    }
}
