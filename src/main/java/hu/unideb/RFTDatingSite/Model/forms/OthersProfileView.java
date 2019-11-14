package hu.unideb.RFTDatingSite.Model.forms;

import hu.unideb.RFTDatingSite.Model.Sex;
import hu.unideb.RFTDatingSite.Model.SexualOrientation;

public class OthersProfileView {
    private String username;
    private String bio;
    private int age;
    private Sex sex;
    private SexualOrientation so;
    public OthersProfileView() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public SexualOrientation getSo() {
        return so;
    }

    public void setSo(SexualOrientation so) {
        this.so = so;
    }
}
