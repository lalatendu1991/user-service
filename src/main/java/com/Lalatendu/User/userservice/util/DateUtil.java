package com.Lalatendu.User.userservice.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

@Component
public class DateUtil {

    public LocalDate toLocalDate(String date) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("dd-MMM-yyyy")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        return LocalDate.parse(date, df);
    }

    public boolean isFutureDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        if (date.isAfter(today)) {
            return true;
        }
        return false;
    }

}
