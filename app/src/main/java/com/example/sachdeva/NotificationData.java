package com.example.sachdeva.eleavegovernor;

/**
 * Created by Sachdeva on 08-Apr-18.
 */

public class NotificationData {
    String date, username, status,employeeId;

    public NotificationData(String date, String username, String status, String employeeId) {
        this.date = date;
        this.username = username;
        this.status = status;
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
