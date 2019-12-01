package hu.unideb.RFTDatingSite.Model;


import org.apache.commons.io.FileUtils;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Entity
@Table(name="pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer user_id;

    @Column(length = 4194304)
    byte[] image=SetDefaultImage();

    @Transient
    String Picture;

    public Picture() throws IOException {
        //this.user_id = null;
        this.image = SetDefaultImage();
        this.Picture = Base64.getEncoder().encodeToString(image);
    }

    public Picture(User user) throws IOException{
        this.user_id =user.getUser_id();
        this.image = user.picture.getImage();
        this.Picture = user.picture.getPicture();
    }


    private byte[] SetDefaultImage() throws IOException {
        File file = new File(getClass().getResource("/Pictures/NAN.jpg").getFile());
        this.image = FileUtils.readFileToByteArray(file);

        return image;
    }

    public String getPicture(){ return this.Picture; }

    public void setPicture(String picture){this.Picture = picture;}

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }



}
