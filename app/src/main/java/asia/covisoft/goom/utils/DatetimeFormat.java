package asia.covisoft.goom.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public final class DatetimeFormat {

    public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat APP_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
}
