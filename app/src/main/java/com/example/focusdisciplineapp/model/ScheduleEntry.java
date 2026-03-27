package com.example.focusdisciplineapp.model;

public class ScheduleEntry {
    private String startTime;
    private String endTime;
    private String type;
    private int duration;

    public static final String WORK = "WORK";
    public static final String SLEEP = "SLEEP";
    public static final String FAMILY = "FAMILY";

    public ScheduleEntry(String startTime, String endTime, String type, int duration) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}