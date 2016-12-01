package bcit.aicha.hezit.a7081project.Activites.Models;

/**
 * Created by Aicha on 2016-11-30.
 */

public class Visit {
    private String date;
    private String reason;
    private String doctor;
    private String comments;

    public Visit(String reason, String date, String gender, String address){
        this.date = date;
        this.reason = reason;
        this.doctor = doctor;
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
