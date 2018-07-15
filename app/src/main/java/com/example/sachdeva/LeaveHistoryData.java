package com.example.sachdeva.eleavegovernor;

/**
 * Created by Sachdeva on 29-Oct-17.
 */

public class LeaveHistoryData {
    String date, leaveCtegory, Status;

    public LeaveHistoryData(String date, String leaveCtegory, String Status) {
        this.setDate(date);
        this.setLeaveCtegory(leaveCtegory);
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLeaveCtegory() {
        return leaveCtegory;
    }

    public void setLeaveCtegory(String leaveCtegory) {
        this.leaveCtegory = leaveCtegory;
    }
}
