package com.example.focusdisciplineapp.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.focusdisciplineapp.model.Alarm;
import com.example.focusdisciplineapp.receiver.AlarmReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmManagerHelper {
    private Context context;
    private AlarmManager alarmManager;
    private List<Alarm> alarms;

    public AlarmManagerHelper(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.alarms = new ArrayList<>();
    }

    public void setAlarm(Alarm alarm) {
        if (!alarm.isEnabled()) {
            return;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            String[] timeParts = alarm.getTime().split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            Intent intent = new Intent(context, AlarmReceiver.class);
            intent.putExtra("alarm_name", alarm.getName());
            intent.putExtra("alarm_id", alarm.getId());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    alarm.getId(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    alarmManager.setExactAndAllowWhileIdle(
                            AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(),
                            pendingIntent
                    );
                } else {
                    alarmManager.setAndAllowWhileIdle(
                            AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(),
                            pendingIntent
                    );
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                );
            }

            alarms.add(alarm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelAlarm(Alarm alarm) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                alarm.getId(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        alarmManager.cancel(pendingIntent);
        alarms.remove(alarm);
    }

    public void cancelAllAlarms() {
        for (Alarm alarm : alarms) {
            cancelAlarm(alarm);
        }
        alarms.clear();
    }

    public List<Alarm> getAllAlarms() {
        return new ArrayList<>(alarms);
    }

    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
        setAlarm(alarm);
    }

    public void removeAlarm(int alarmId) {
        for (int i = 0; i < alarms.size(); i++) {
            if (alarms.get(i).getId() == alarmId) {
                cancelAlarm(alarms.get(i));
                alarms.remove(i);
                break;
            }
        }
    }
}