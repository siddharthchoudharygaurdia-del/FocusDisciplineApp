package com.example.focusdisciplineapp.model;

public class Alarm {
    private int id;
    private String name;
    private String time;
    private boolean enabled;
    private long createdAt;

    public Alarm(int id, String name, String time, boolean enabled) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.enabled = enabled;
        this.createdAt = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return name + " at " + time + (enabled ? " (Enabled)" : " (Disabled)");
    }
}