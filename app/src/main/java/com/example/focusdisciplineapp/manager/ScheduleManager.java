import java.util.Calendar;
import java.util.TimeZone;

public class ScheduleManager {

    private static final int WORK_DURATION = 12; // in hours
    private static final int FAMILY_DURATION = 12; // in hours
    private static final String[] SCHEDULE = {"WORK", "SLEEP", "FAMILY"};

    private int currentScheduleIndex;

    public ScheduleManager() {
        this.currentScheduleIndex = 0; // Start with WORK
    }

    public String getCurrentActivity() {
        updateScheduleIndex();
        return SCHEDULE[currentScheduleIndex];
    }

    private void updateScheduleIndex() {
        long currentTimeMillis = System.currentTimeMillis();
        long[] scheduleTimes = getScheduleTimes();

        if (currentTimeMillis >= scheduleTimes[currentScheduleIndex]) {
            currentScheduleIndex = (currentScheduleIndex + 1) % SCHEDULE.length;
        }
    }

    private long[] getScheduleTimes() {
        long[] scheduleTimes = new long[SCHEDULE.length];
        long currentTimeMillis = System.currentTimeMillis();
        long startOfDay = getStartOfDay(currentTimeMillis);

        // Calculate schedule time stamps based on the start of the day
        for (int i = 0; i < SCHEDULE.length; i++) {
            scheduleTimes[i] = startOfDay + (i * WORK_DURATION * 3600 * 1000);
        }

        return scheduleTimes;
    }

    private long getStartOfDay(long currentTimeMillis) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(currentTimeMillis);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}