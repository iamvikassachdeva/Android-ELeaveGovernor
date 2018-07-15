package com.example.sachdeva.eleavegovernor;

/**
 * Created by Sachdeva on 02-Apr-18.
 */

public class RequestsRaisedData {
    String lectureNo, date, Status;

    public RequestsRaisedData(String lectureNo, String date, String Status) {
        this.lectureNo = lectureNo;
        this.date = date;
        this.Status = Status;
    }

    public String getLectureNo() {
        return lectureNo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setLectureNo(String lectureNo) {
        this.lectureNo = lectureNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
