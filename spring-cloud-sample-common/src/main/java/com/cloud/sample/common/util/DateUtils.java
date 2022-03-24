package com.cloud.sample.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utils for common operations with dates.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static final ZoneId UTC_ZONE = ZoneId.of("UTC");

    public static ZonedDateTime convertToZonedDateTimeUTC(Object date) {

        if (date instanceof ZonedDateTime) {
            return ((ZonedDateTime) date).withZoneSameInstant(UTC_ZONE);
        }

        if (date instanceof String || date instanceof Instant) {
            return ZonedDateTime.parse(date.toString(), DateTimeFormatter.ISO_ZONED_DATE_TIME)
                    .withZoneSameInstant(UTC_ZONE);
        }
        return null;
    }

}
