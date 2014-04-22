package cgeo.geocaching.utils;

import cgeo.geocaching.Geocache;
import cgeo.geocaching.enumerations.CacheType;

import java.util.Calendar;

import junit.framework.TestCase;

public class DateUtilsTest extends TestCase {

    public static void testDaysSince() {
        final Calendar start = Calendar.getInstance();
        for (int hour = 0; hour < 24; hour++) {
            start.set(Calendar.HOUR_OF_DAY, hour);
            assertEquals(0, DateUtils.daysSince(start.getTimeInMillis()));
        }
    }

    public static void testIsPastEvent() {
        final Calendar start = Calendar.getInstance();
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 10);
        assertPastEvent(start, false);

        start.set(Calendar.HOUR_OF_DAY, 23);
        assertPastEvent(start, false);

        start.add(Calendar.DAY_OF_MONTH, -1);
        assertPastEvent(start, true);
    }

    private static void assertPastEvent(final Calendar start, boolean expectedPast) {
        final Geocache cache = new Geocache();
        cache.setType(CacheType.EVENT);

        cache.setHidden(start.getTime());
        assertEquals(expectedPast, DateUtils.isPastEvent(cache));
    }

}
