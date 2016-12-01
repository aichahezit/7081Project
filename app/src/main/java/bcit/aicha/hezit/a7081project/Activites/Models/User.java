package bcit.aicha.hezit.a7081project.Activites.Models;

/**
 * Created by Aicha on 2016-11-30.
 */

public class User {
    private String name;
    private String DOB;
    private String gender;
    private String address;

    public User(String name, String DOB, String gender, String address){
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
