package org.cb.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtils {

    private LocalDateTimeUtils() {
    }

    public static final String dd_MMM_yyyy_HH_mm = "dd-MMM-yyyy HH:mm";

    public static DateTimeFormatter defaultDateTimeFormat = DateTimeFormatter.ofPattern(dd_MMM_yyyy_HH_mm);

    public static String convertLdtToString(LocalDateTime ldt){
        if (ldt == null){
            return null;
        }
        return ldt.format(defaultDateTimeFormat);
    }

}
