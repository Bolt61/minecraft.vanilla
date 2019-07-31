package ch.bolt61.vanillaserver.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
  
  private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  
  private static final String SWITZERLAND_ZONE = "CET";

  public static String getDateTimeLabel(Date date) {
    LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of(SWITZERLAND_ZONE));
    return dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
  }
}
