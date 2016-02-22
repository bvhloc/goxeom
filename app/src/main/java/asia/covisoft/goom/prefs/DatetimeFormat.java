package asia.covisoft.goom.prefs;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public final class DatetimeFormat {

    public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final String APP_DATE = "dd-MM-yyyy";
    public static final SimpleDateFormat APP_DATE_FORMAT = new SimpleDateFormat(APP_DATE);

    public static final SimpleDateFormat APP_HISTORY_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
