package hu.unideb.RFTDatingSite.Model;

import hu.unideb.RFTDatingSite.Model.validation.MinimumYearsSince;
import hu.unideb.RFTDatingSite.Model.validation.UniqueEmail;
import hu.unideb.RFTDatingSite.Model.validation.UniqueUsername;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;


@Entity
@Table(name="users")
//@NamedQuery(name="getUserByUsername", query="select u from User u where u.username like :uname")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer user_id;

    @UniqueUsername(message="Username is already taken!")
    @Size(min=6,max=50,message = "The username should have at least 6 and maximum 50 characters!")
    String username;

    @Size(min=1, message = "Please type in your name!")
    String full_name;
    
    @MinimumYearsSince(min=18,message = "You must be 18 in order to register!")
    Date birthdate;

    Sex sex;
    SexualOrientation sexualOrientation;
    String bio;

    @UniqueEmail(message = "Email is already registered.")
    @Email
    @Size(min=1, message = "Please type in your email address!")
    String email;

    @Size(min=4,max=60, message = "The password should have at least 4 and maximum 20 characters!")
    String password;

    public User(){}
    public User(User user) {
        this.user_id=user.getUser_id();
        this.username=user.getUsername();
        this.full_name=user.getFull_name();
        this.birthdate=user.getBirthdate();
        this.sex=user.getSex();
        this.sexualOrientation=user.getSexualOrientation();
        this.bio=user.getBio();
        this.email=user.getEmail();
        this.password=user.getPassword();

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public SexualOrientation getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(SexualOrientation sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", full_name='" + full_name + '\'' +
                ", birthdate=" + birthdate +
                ", sex=" + sex +
                ", sexualOrientation=" + sexualOrientation +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
