package bcit.aicha.hezit.a7081project.Activites.Models;

/**
 * Created by Aicha on 2016-11-30.
 */

public class Doctor {
    private String name;
    private String specialty;
    private String address;
    private String comments;

    public Doctor(String name, String specialty, String address, String comments){
        this.name = name;
        this.specialty = specialty;
        this.address = address;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) { this.comments = comments; }
}
